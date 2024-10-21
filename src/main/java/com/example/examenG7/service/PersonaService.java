package com.example.examenG7.service;

import com.example.examenG7.entity.PersonaEntity;

import java.util.List;

public interface PersonaService {

    PersonaEntity crearPersona(PersonaEntity persona);
    PersonaEntity obtenerPersonaPorId(Long id);

    PersonaEntity buscarPersonaXnumDocumento(Long id);
    List<PersonaEntity> buscarTodos();
    PersonaEntity actualizarPersona(Long id, PersonaEntity persona);
    void eliminarPersona(Long id);
}
