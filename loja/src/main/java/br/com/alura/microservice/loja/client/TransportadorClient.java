package br.com.alura.microservice.loja.client;

import br.com.alura.microservice.loja.controller.dto.InfoEntregaDTO;
import br.com.alura.microservice.loja.controller.dto.VoucherDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name="transportador")
public interface TransportadorClient {

  @RequestMapping(method = RequestMethod.POST, path = "/entrega")
  public VoucherDTO reservaEntrega(@RequestBody InfoEntregaDTO pedidoDTO);


  }