package com.hemocentro.doacao_sangue.business;

import com.hemocentro.doacao_sangue.infrastructure.entity.request.BuscarDoadoresRequest;
import com.hemocentro.doacao_sangue.infrastructure.entity.Doador;
import com.hemocentro.doacao_sangue.infrastructure.repository.DoadorRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class DoadorService {
  private final DoadorRepository repository;

  public DoadorService(DoadorRepository repository) {
    this.repository = repository;
  }

  public Doador salvarDoador(Doador cadastro) {
    return repository.saveAndFlush(cadastro);
  }

  public List<BuscarDoadoresRequest> listarDoares() {
    return repository
      .findAll()
      .stream()
      .map(doador -> new BuscarDoadoresRequest(doador.getId(), doador.getNome()))
      .toList();
  }

  public Doador buscarDoador(Long id) {
    return repository
      .findByIdWithAgendamento(id)
      .orElseThrow(() -> new ResponseStatusException(
        HttpStatus.NOT_FOUND, "Doador não encontrado"
      ));
  }

  public void deletarDoador(Long id) {
    repository.deleteById(id);
  }

  public Doador editarDoador(Long id, Doador dc) {
    Doador doadorEntity = buscarDoador(id);
//    Doador doadorAtualizado = Doador.builder()
//      .id(doadorEntity.getId())
//      .email(dc.getEmail() != null ? dc.getEmail() : doadorEntity.getEmail())
//      .nome(dc.getNome() != null ? dc.getNome() : doadorEntity.getNome())
//      .dataNascimento(dc.getDataNascimento() != null ? dc.getDataNascimento() : doadorEntity.getDataNascimento())
//      .tipoSanguineo(dc.getTipoSanguineo() != null ? dc.getTipoSanguineo() : doadorEntity.getTipoSanguineo())
//      .doencas(dc.getDoencas() != null ? dc.getDoencas() : doadorEntity.getDoencas())
//      .altura(dc.getAltura() != null ? dc.getAltura() : doadorEntity.getAltura())
//      .peso(dc.getPeso() != null ? dc.getPeso() : doadorEntity.getPeso())
//      .sexo(dc.getSexo() != null ? dc.getSexo() : doadorEntity.getSexo())
//      .build();
    if (dc.getEmail() != null) doadorEntity.setEmail(dc.getEmail());
    if (dc.getNome() != null) doadorEntity.setNome(dc.getNome());
    if (dc.getDataNascimento() != null) doadorEntity.setDataNascimento(dc.getDataNascimento());
    if (dc.getTipoSanguineo() != null) doadorEntity.setTipoSanguineo(dc.getTipoSanguineo());
    if (dc.getDoencas() != null) doadorEntity.setDoencas(dc.getDoencas());
    if (dc.getAltura() != null) doadorEntity.setAltura(dc.getAltura());
    if (dc.getPeso() != null) doadorEntity.setPeso(dc.getPeso());
    if (dc.getSexo() != null) doadorEntity.setSexo(dc.getSexo());
    if (dc.getAgendamento() != null) doadorEntity.setAgendamento(dc.getAgendamento());

    return repository.saveAndFlush(doadorEntity);
  }
}