package br.com.alura.microservice.loja.model;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Compra {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private Long pedidoId;
  private Integer tempoDePreparo;
  private String EnderecoDestino;
  private LocalDate dataParaEntrega;
  private Long voucher;

  @Enumerated(EnumType.STRING)
  private CompraStatus status;

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

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public CompraStatus getStatus() {
    return status;
  }

  public void setStatus(CompraStatus status) {
    this.status = status;
  }
}
