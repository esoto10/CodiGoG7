package com.example.examenG7.repository;

import com.example.examenG7.entity.PedidoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PedidoRepository extends JpaRepository<PedidoEntity, Long> {

    List<PedidoEntity> findByEstado(int estado);
}
