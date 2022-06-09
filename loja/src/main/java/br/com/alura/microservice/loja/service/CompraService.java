package br.com.alura.microservice.loja.service;

import br.com.alura.microservice.loja.client.FornecedorClient;
import br.com.alura.microservice.loja.controller.dto.CompraDTO;
import br.com.alura.microservice.loja.controller.dto.InfoFornecedorDTO;
import br.com.alura.microservice.loja.controller.dto.InfoPedidoDTO;
import br.com.alura.microservice.loja.model.Compra;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompraService {

  private static final Logger LOG = LoggerFactory.getLogger(CompraService.class);

  @Autowired
  private FornecedorClient fornecedorClient;

  public Compra realizarCompra(CompraDTO compra) {

    LOG.info("buscando informaçoes do fornecedor de {}", compra.getEndereco().getEstado());
    InfoFornecedorDTO infoFornecedorDTO = fornecedorClient.getInfoPorEstado(compra.getEndereco().getEstado());

    LOG.info("realizando um pedido");
    InfoPedidoDTO infoPedido = fornecedorClient.realizaPedido(compra.getItens());
    System.out.println( infoFornecedorDTO.getEndereco());

    Compra compraSalva = new Compra();
    compraSalva.setPedidoId(infoPedido.getId());
    compraSalva.setTempoDePreparo(infoPedido.getTempoDePreparo());
    compraSalva.setEnderecoDestino(compra.getEndereco().toString());

    return compraSalva;
  }
}
