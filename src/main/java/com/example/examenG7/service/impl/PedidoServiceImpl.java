package com.example.examenG7.service.impl;

import com.example.examenG7.entity.PedidoEntity;
import com.example.examenG7.entity.PersonaEntity;
import com.example.examenG7.enums.EstadoPedido;
import com.example.examenG7.repository.PedidoRepository;
import com.example.examenG7.repository.PersonaRepository;
import com.example.examenG7.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class PedidoServiceImpl implements PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private PersonaRepository personaRepository;

    @Override
    public PedidoEntity crearPedido(PedidoEntity pedido) {
        return pedidoRepository.save(pedido);
    }

    @Override
    public List<PedidoEntity> buscarTodos(EstadoPedido estadoPedido) {
        //se busca los pedidos por su estado
        return pedidoRepository.findByEstado( estadoPedido.getCodigo());
    }

    @Override
    public PedidoEntity obtenerPedidoPorId(Long id) {
        //se busca los pedidos por su ID
        return pedidoRepository.findById(id).orElseThrow(()
                -> new NoSuchElementException("Pedido no encontrado"));
    }


    @Override
    public List<PedidoEntity> buscarPedidoPorparametro(Long id, EstadoPedido estadoPedido) {
        //se busca los pedidos por su estado o por su ID
       List<PedidoEntity> lista=new ArrayList<>();

        if(id!=null){
            //busqueda por Id del pedido
           PedidoEntity obj= pedidoRepository.findById(id).orElseThrow(()->
                   new NoSuchElementException("no existe el pedido"));
           lista.add(obj);
        }else{
            // busqueda por estado de pedido
            lista= pedidoRepository.findByEstado(estadoPedido.getCodigo());
        }
        return lista;
    }

    @Override
    public PedidoEntity actualizarPedido(Long id, Long idPersona, PedidoEntity pedido) {
        //se actualiza los pedidos y se asigna a nueva persona actualizada
        PedidoEntity pedidoExistente = obtenerPedidoPorId(id);
        pedidoExistente.setDescripcion(pedido.getDescripcion());
        pedidoExistente.setCantidad(pedido.getCantidad());
        pedidoExistente.setEstado(pedido.getEstado());
        PersonaEntity personaEntity = personaRepository.findById(idPersona).orElseThrow(() ->
                new RuntimeException("Error persona a relacionar no existe"));
        pedidoExistente.setPersona(personaEntity);
        return pedidoRepository.save(pedidoExistente);
    }

    @Override
    public void eliminarPedido(Long id) {
        //se elimina de manera logica el pedido Estado ELIMINADO (4)
        PedidoEntity existente= pedidoRepository.findById(id).orElseThrow(()->  new NoSuchElementException("pedido no Encontrado"));
        existente.setEstado(EstadoPedido.ELIMINADO.getCodigo());
        pedidoRepository.save(existente);
    }
}
