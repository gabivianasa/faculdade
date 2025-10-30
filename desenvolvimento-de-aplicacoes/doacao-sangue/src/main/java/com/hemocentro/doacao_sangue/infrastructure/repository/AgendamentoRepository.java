package com.hemocentro.doacao_sangue.infrastructure.repository;

import com.hemocentro.doacao_sangue.infrastructure.entity.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {
}
