package com.example.examenG7.enums;

public enum EstadoPersona {

    ACTIVO(1), INACTIVO(0);

    private final int id;

     EstadoPersona(int id){
        this.id=id;
    }


    public int getId() {
        return id;
    }
}
