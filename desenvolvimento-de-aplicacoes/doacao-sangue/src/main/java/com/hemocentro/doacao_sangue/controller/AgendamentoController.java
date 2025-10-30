package com.hemocentro.doacao_sangue.controller;

import com.hemocentro.doacao_sangue.business.AgendamentoService;
import com.hemocentro.doacao_sangue.infrastructure.entity.request.CadastrarAgendamentoRequest;
import com.hemocentro.doacao_sangue.infrastructure.entity.Agendamento;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/agendamento")
@RequiredArgsConstructor

public class AgendamentoController {

  private final AgendamentoService agendamentoService;

  @PostMapping
  public ResponseEntity<Agendamento> salvarAgendamento(@RequestBody CadastrarAgendamentoRequest request) {
    return ResponseEntity.ok(agendamentoService.salvarAgendamento(request));
  }

  @GetMapping
  public ResponseEntity<List<Agendamento>> listarAgendamentos() {
    return ResponseEntity.ok(agendamentoService.buscarAgendamentos());
  }

  @GetMapping("/{id}")
  public ResponseEntity<Agendamento> buscarAgendamento(@PathVariable Long id) {
    return ResponseEntity.ok(agendamentoService.buscarAgendamentoPorId(id));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deletarAgendamento(@PathVariable Long id) {
    agendamentoService.deletarAgendamento(id);
    return ResponseEntity.ok().build();
  }

  @PutMapping("/{id}")
  public ResponseEntity<Agendamento> editarAgendamento(@PathVariable Long id,
                                           @RequestBody Agendamento agendamento) {
    return ResponseEntity.ok(agendamentoService.editarAgendamento(id, agendamento));
  }
}

