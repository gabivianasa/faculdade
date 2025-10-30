package com.hemocentro.doacao_sangue.infrastructure.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hemocentro.doacao_sangue.infrastructure.entity.enums.Sexo;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "doador")

public class Doador {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank
  @Email
  @Column(name = "email", unique = true)
  private String email;

  @NotBlank
  @Column(name = "nome")
  private String nome;

  @NotBlank
  @Column(name = "dataNascimento")
  private LocalDate dataNascimento;

  @NotBlank
  @Column(name = "tipoSanguineo")
  private String tipoSanguineo;

  @NotBlank
  @Column(name = "doencas")
  private String doencas;

  @NotNull
  @Column(name = "altura")
  private Double altura;

  @NotNull
  @Column(name = "peso")
  private Double peso;

  @Enumerated(EnumType.STRING)
  @NotNull
  @Column(name = "sexo")
  private Sexo sexo;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "agendamento")
  @JsonIgnoreProperties("doadores")
  private Agendamento agendamento;

}
