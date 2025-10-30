package com.hemocentro.doacao_sangue.infrastructure.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "agendamento")

public class Agendamento {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  @NotNull
  @Column(name = "dataHora")
  private ZonedDateTime dataHora;

  @NotBlank
  @Column(name = "unidade")
  private String unidade;

  @NotEmpty
  @OneToMany(
    mappedBy = "agendamento",
    cascade = {CascadeType.PERSIST, CascadeType.MERGE},
    fetch = FetchType.LAZY
  )
  private List<Doador> doadores;

  @PrePersist
  @PreUpdate
  private void ajustarFusoHorario() {
    if (dataHora != null) {
      dataHora = dataHora.withZoneSameInstant(ZoneId.of("America/Sao_Paulo"));
    }
  }
}
