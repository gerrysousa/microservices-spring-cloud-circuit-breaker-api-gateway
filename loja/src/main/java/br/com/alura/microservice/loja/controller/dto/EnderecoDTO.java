package br.com.alura.microservice.loja.controller.dto;

public class EnderecoDTO {
  private String rua;
  private Integer numero;
  private String estado;

  public void setRua(String rua) {
    this.rua = rua;
  }

  public String getRua() {
    return rua;
  }

  public Integer getNumero() {
    return numero;
  }

  public void setNumero(Integer numero) {
    this.numero = numero;
  }

  public void setEstado(String estado) {
    this.estado = estado;
  }

  public String getEstado() {
    return estado;
  }

  @Override
  public String toString() {
    return "EnderecoDTO{" +
        "rua='" + rua + '\'' +
        ", numero=" + numero +
        ", estado='" + estado + '\'' +
        '}';
  }
}
