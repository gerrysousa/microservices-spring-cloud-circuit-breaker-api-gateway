package br.com.alura.microservice.loja.service;

import br.com.alura.microservice.loja.client.FornecedorClient;
import br.com.alura.microservice.loja.client.TransportadorClient;
import br.com.alura.microservice.loja.controller.dto.CompraDTO;
import br.com.alura.microservice.loja.controller.dto.InfoEntregaDTO;
import br.com.alura.microservice.loja.controller.dto.InfoFornecedorDTO;
import br.com.alura.microservice.loja.controller.dto.InfoPedidoDTO;
import br.com.alura.microservice.loja.controller.dto.VoucherDTO;
import br.com.alura.microservice.loja.model.Compra;
import br.com.alura.microservice.loja.model.CompraStatus;
import br.com.alura.microservice.loja.repository.CompraRepository;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import java.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompraService {

  private static final Logger LOG = LoggerFactory.getLogger(CompraService.class);

  @Autowired private FornecedorClient fornecedorClient;

  @Autowired private CompraRepository compraRepository;

  @Autowired private TransportadorClient transportadorClient;


  @HystrixCommand(threadPoolKey = "getByIdThreadPoolKey")
  public Compra getById(Long id) {
    return compraRepository.findById(id).orElse(new Compra());
  }

  @HystrixCommand(
      fallbackMethod = "realizaCompraFallback",
      threadPoolKey = "realizarCompraThreadPoolKey",
      commandProperties = {
          @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "2000"),
      }
 )
  public Compra realizarCompra(CompraDTO compra) {

    Compra compraSalva = new Compra();
    compraSalva.setStatus(CompraStatus.RECEBIDO);
    compraSalva.setEnderecoDestino(compra.getEndereco().toString());
    compraRepository.save(compraSalva);
    compra.setCompraId(compraSalva.getId());

    LOG.info("buscando informaçoes do fornecedor de {}", compra.getEndereco().getEstado());
    InfoFornecedorDTO infoFornecedorDTO =
        fornecedorClient.getInfoPorEstado(compra.getEndereco().getEstado());

    LOG.info("realizando um pedido");
    InfoPedidoDTO infoPedido = fornecedorClient.realizaPedido(compra.getItens());
    compraSalva.setPedidoId(infoPedido.getId());
    compraSalva.setTempoDePreparo(infoPedido.getTempoDePreparo());

    compraSalva.setStatus(CompraStatus.PEDIDO_REALIZADO);
    compraRepository.save(compraSalva);

    InfoEntregaDTO entregaDTO = new InfoEntregaDTO();
    entregaDTO.setPedidoId(infoPedido.getId());
    entregaDTO.setDataParaEntrega(LocalDate.now().plusDays(infoPedido.getTempoDePreparo()));
    entregaDTO.setEnderecoOrigem(infoFornecedorDTO.getEndereco());
    entregaDTO.setEnderecoDestino(compra.getEndereco().toString());
    VoucherDTO voucherDTO = transportadorClient.reservaEntrega(entregaDTO);
    compraSalva.setDataParaEntrega(voucherDTO.getPrevisaoParaEntrega());
    compraSalva.setVoucher(voucherDTO.getNumero());
    compraSalva.setStatus(CompraStatus.RESERVA_ENTREGA_REALIZADO);
    compraRepository.save(compraSalva);

    return compraSalva;
  }

  public Compra realizaCompraFallback(CompraDTO compra) {
    LOG.info("Entrou no Fallback !!!");
    if (compra.getCompraId()!=null){
      return compraRepository.findById(compra.getCompraId()).get();
    }

    Compra compraFallback = new Compra();
    compraFallback.setEnderecoDestino(compra.getEndereco().toString());
    return compraFallback;
  }

  public Compra reprocessarCompra(Long id){
    return null;
  }

  public Compra cancelarCompra(Long id){
    return null;
  }
}
