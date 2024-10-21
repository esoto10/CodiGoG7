package com.example.examenG7.repository;

import com.example.examenG7.entity.PersonaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PersonaRepository extends JpaRepository<PersonaEntity, Long> {

     List<PersonaEntity> findAllByEstado(int estado);

     Optional<PersonaEntity> findByDni(Long dni);
}
