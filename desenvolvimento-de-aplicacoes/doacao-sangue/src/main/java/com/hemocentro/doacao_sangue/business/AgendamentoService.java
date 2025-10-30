package com.hemocentro.doacao_sangue.business;

import com.hemocentro.doacao_sangue.infrastructure.entity.request.CadastrarAgendamentoRequest;
import com.hemocentro.doacao_sangue.infrastructure.entity.Agendamento;
import com.hemocentro.doacao_sangue.infrastructure.entity.Doador;
import com.hemocentro.doacao_sangue.infrastructure.repository.AgendamentoRepository;
import com.hemocentro.doacao_sangue.infrastructure.repository.DoadorRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class AgendamentoService {
  private final AgendamentoRepository repository;
  private final DoadorRepository doadorRepository;

  public AgendamentoService(AgendamentoRepository repository, DoadorRepository doadorRepository) {
    this.repository = repository;
    this.doadorRepository = doadorRepository;
  }

  public Agendamento salvarAgendamento(CadastrarAgendamentoRequest request) {

    // Busca todos od doadores existentes através dos ids fornecidos...
    List<Doador> doadores = doadorRepository.findAllById(request.doadorIds());

    if (doadores.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Lista de ids inválida!");
    }

    // Atualiza o relacionamento bidirecional
    Agendamento agendamento = Agendamento.builder()
      .dataHora(request.dataHora())
      .unidade(request.unidade())
      .doadores(doadores)
      .build();

    doadores.forEach(d -> d.setAgendamento(agendamento));

    return repository.saveAndFlush(agendamento);
  }

  public List<Agendamento> buscarAgendamentos() {
    return repository.findAll();
  }

  public Agendamento buscarAgendamentoPorId(Long id) {
    return repository
      .findById(id)
      .orElseThrow(() -> new ResponseStatusException(
        HttpStatus.NOT_FOUND, "Agendamento não encontrado"
      ));
  }

  public void deletarAgendamento(Long id) {
    var agendamento = buscarAgendamentoPorId(id);
    agendamento.getDoadores().forEach(d -> d.setAgendamento(null));
    repository.deleteById(id);
  }

  public Agendamento editarAgendamento(Long id, Agendamento a) {
    Agendamento agendamentoEntity = buscarAgendamentoPorId(id);

    if (a.getUnidade() != null) agendamentoEntity.setUnidade(a.getUnidade());
    if (a.getDataHora() != null) agendamentoEntity.setDataHora(a.getDataHora());
    if (a.getDoadores() != null && !a.getDoadores().isEmpty()) {
      // Garantir que cada doador saiba qual agendamento ele pertence!
      a.getDoadores().forEach(d -> d.setAgendamento(agendamentoEntity));
      agendamentoEntity.setDoadores(a.getDoadores());
    }
//    Agendamento agendamentoAtualizado = Agendamento.builder()
//      .id(agendamentoEntity.getId())
//      .dataHora(a.getDataHora() != null ? a.getDataHora() : agendamentoEntity.getDataHora())
//      .unidade(a.getUnidade() != null ? a.getUnidade() : agendamentoEntity.getUnidade())
//      .build();
    return repository.saveAndFlush(agendamentoEntity);
  }
}

