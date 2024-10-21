package com.example.examenG7.service;

import com.example.examenG7.entity.PedidoEntity;
import com.example.examenG7.entity.PersonaEntity;
import com.example.examenG7.enums.EstadoPedido;

import java.util.List;

public interface PedidoService {

    PedidoEntity crearPedido(PedidoEntity pedido);
    List<PedidoEntity> buscarTodos(EstadoPedido estado);

    PedidoEntity obtenerPedidoPorId(Long id);
    List<PedidoEntity>buscarPedidoPorparametro(Long id, EstadoPedido estadoPedido);
    PedidoEntity actualizarPedido(Long id, Long idPersona, PedidoEntity pedido);
    void eliminarPedido(Long id);

}
