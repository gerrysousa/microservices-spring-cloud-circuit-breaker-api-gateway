package br.com.alura.microservice.loja.controller.dto;

public class ItemDaCompraDTO {
  private long id;
  private int quantidade;

  public void setId(long id) {
    this.id = id;
  }

  public long getId() {
    return id;
  }

  public void setQuantidade(int quantidade) {
    this.quantidade = quantidade;
  }

  public int getQuantidade() {
    return quantidade;
  }
}
