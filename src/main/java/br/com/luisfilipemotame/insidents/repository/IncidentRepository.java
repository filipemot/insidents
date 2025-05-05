package br.com.luisfilipemotame.insidents.repository;

import br.com.luisfilipemotame.insidents.entity.Incident;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IncidentRepository extends JpaRepository<Incident, Long> {
    List<Incident> findTop20ByOrderByCreatedAtDesc();
}
