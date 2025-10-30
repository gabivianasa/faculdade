package com.hemocentro.doacao_sangue.infrastructure.repository;

import com.hemocentro.doacao_sangue.infrastructure.entity.Doador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface DoadorRepository extends JpaRepository<Doador, Long> {
  @Query("SELECT d FROM Doador d LEFT JOIN FETCH d.agendamento WHERE d.id = :id")
  Optional<Doador> findByIdWithAgendamento(@Param("id") Long id);
}
