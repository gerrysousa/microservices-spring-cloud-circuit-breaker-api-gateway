package br.com.alura.microservice.loja.model;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Compra {
  @Id
  private Long pedidoId;
  private Integer tempoDePreparo;
  private String EnderecoDestino;
  private LocalDate dataParaEntrega;
  private Long voucher;

  public Long getPedidoId() {
    return pedidoId;
  }

  public void setPedidoId(Long pedidoId) {
    this.pedidoId = pedidoId;
  }

  public Integer getTempoDePreparo() {
    return tempoDePreparo;
  }

  public void setTempoDePreparo(Integer tempoDePreparo) {
    this.tempoDePreparo = tempoDePreparo;
  }

  public String getEnderecoDestino() {
    return EnderecoDestino;
  }

  public void setEnderecoDestino(String enderecoDestino) {
    EnderecoDestino = enderecoDestino;
  }

  public LocalDate getDataParaEntrega() {
    return dataParaEntrega;
  }

  public void setDataParaEntrega(LocalDate dataParaEntrega) {
    this.dataParaEntrega = dataParaEntrega;
  }

  public Long getVoucher() {
    return voucher;
  }

  public void setVoucher(Long voucher) {
    this.voucher = voucher;
  }
}
