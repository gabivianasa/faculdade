package com.hemocentro.doacao_sangue.infrastructure.entity.request;

import java.time.ZonedDateTime;
import java.util.List;

public record CadastrarAgendamentoRequest(
  ZonedDateTime dataHora,
  String unidade,
  List<Long> doadorIds
) {}