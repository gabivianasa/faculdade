package com.hemocentro.doacao_sangue.controller;

import com.hemocentro.doacao_sangue.business.DoadorService;
import com.hemocentro.doacao_sangue.infrastructure.entity.request.BuscarDoadoresRequest;
import com.hemocentro.doacao_sangue.infrastructure.entity.Doador;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doador")
@RequiredArgsConstructor

public class DoadorController {

  private final DoadorService doadorService;

  @PostMapping
  public ResponseEntity<Doador> salvarDoador(@RequestBody Doador doador) {
    return ResponseEntity.ok(doadorService.salvarDoador(doador));
  }

  @GetMapping
  public ResponseEntity<List<BuscarDoadoresRequest>> listarDoares() {
    return ResponseEntity.ok(doadorService.listarDoares());
  }

  @GetMapping("/{id}")
  public ResponseEntity<Doador> buscarDoador(@PathVariable Long id) {
    return ResponseEntity.ok(doadorService.buscarDoador(id));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deletarDoador(@PathVariable Long id) {
    doadorService.deletarDoador(id);
    return ResponseEntity.ok().build();
  }

  @PutMapping("/{id}")
  public ResponseEntity<Doador> editarDoador(@PathVariable Long id,
                                           @RequestBody Doador doador) {
    doadorService.editarDoador(id, doador);
    return ResponseEntity.ok().build();
  }
}
