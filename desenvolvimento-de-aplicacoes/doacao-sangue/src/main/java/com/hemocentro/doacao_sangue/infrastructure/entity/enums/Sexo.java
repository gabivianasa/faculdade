package com.hemocentro.doacao_sangue.infrastructure.entity.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Sexo {
  MASCULINO("M"),
  FEMININO("F");

  private final String codigo;

  Sexo(String codigo) {
    this.codigo = codigo;
  }

  @JsonValue
  public String getCodigo() {
    return codigo;
  }

  @JsonCreator
  public static Sexo retornarCodigo(String codigo) {
    for (Sexo sexo : Sexo.values()) {
      if (sexo.codigo.equalsIgnoreCase(codigo)) {
        return sexo;
      }
    }
    throw new IllegalArgumentException("Sexo inválido: " + codigo);
  }
}
