package com.example.examenG7.enums;

import java.util.Optional;

public enum EstadoPedido {
    PENDIENTE(1), PROCESO(2), CONFIRMADO(3), ELIMINADO(4);

    private int codigo;
    EstadoPedido(int codigo){
        this.codigo=codigo;

    }

    public int getCodigo() {
        return codigo;
    }

    public static Optional<EstadoPedido> findestado(int codigo){
        for (EstadoPedido e:EstadoPedido.values()) {
            if (e.getCodigo()==codigo){
                return Optional.of(e);
            }
        }
        return Optional.ofNullable(null);
    }

}
