package com.example.examenG7.service.impl;

import com.example.examenG7.entity.PedidoEntity;
import com.example.examenG7.enums.EstadoPersona;
import com.example.examenG7.entity.PersonaEntity;
import com.example.examenG7.repository.PedidoRepository;
import com.example.examenG7.repository.PersonaRepository;
import com.example.examenG7.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class PersonaServiceImpl implements PersonaService {

    @Autowired
    PersonaRepository personaRepository;

    @Autowired
    PedidoRepository pedidoRepository;

    @Override
    public PersonaEntity crearPersona(PersonaEntity persona) {
        return personaRepository.save(persona);
    }

    @Override
    public PersonaEntity obtenerPersonaPorId(Long id) {
        //se busca las personas por su ID
        return personaRepository.findById(id).orElseThrow(() ->
                new NoSuchElementException("persona no Encontrada"));
    }

    @Override
    public PersonaEntity buscarPersonaXnumDocumento(Long id) {
        //se busca las personas por su DNI
        return personaRepository.findByDni(id).orElseThrow(() ->
                new NoSuchElementException("dni no Encontrada"));

    }

    @Override
    public List<PersonaEntity> buscarTodos() {
        //se buscara todos los personas ACTIVOS
        EstadoPersona e= EstadoPersona.ACTIVO;
        return personaRepository.findAllByEstado(e.getId());
    }

    @Override
    public PersonaEntity actualizarPersona(Long id, PersonaEntity persona) {
        // actualizar persona y actualizar pedidos
        PersonaEntity personaexistente=obtenerPersonaPorId(id);
        personaexistente.setNombre(persona.getNombre());
        personaexistente.setApellido(persona.getApellido());
        personaexistente.setDireccionEntity(persona.getDireccionEntity());
        gestionarPedidos(personaexistente,persona.getPedidos());
        return personaRepository.save(personaexistente);
    }


    private void gestionarPedidos(PersonaEntity personaexistente, List<PedidoEntity> pedidos){

        //extraer lso pedidos de la persona existente
        List<PedidoEntity> pedidosExistentes=personaexistente.getPedidos();
        pedidosExistentes.clear();
        //Buscamos los nuevos pedidos
        for (PedidoEntity pedido : pedidos){
            if(pedido.getId() != null){
                PedidoEntity pedidoEncontrado = pedidoRepository.findById(pedido.getId())
                        .orElseThrow(() -> new NoSuchElementException("Error pedido no ubicado"));
                pedidoEncontrado.setDescripcion(pedido.getDescripcion());
                pedidoEncontrado.setCantidad(pedido.getCantidad());
                pedidoEncontrado.setEstado(pedido.getEstado());
                pedidosExistentes.add(pedidoEncontrado);
            }else{
                //Significa que no existe debe ser añadido
                pedido.setPersona(personaexistente);
                pedidosExistentes.add(pedido);
            }
        }
    }

    @Override
    public void eliminarPersona(Long id) {
        //borrado logico de eperosna con estado inactivo
        PersonaEntity per= personaRepository.findById(id).orElseThrow(() ->
                new NoSuchElementException("persona no Encontrada"));
        per.setEstado(EstadoPersona.INACTIVO.getId());
        personaRepository.save(per);
    }
}
