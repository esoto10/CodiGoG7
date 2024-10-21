package com.example.examenG7.controller;


import com.example.examenG7.entity.PedidoEntity;
import com.example.examenG7.entity.PersonaEntity;
import com.example.examenG7.enums.EstadoPedido;
import com.example.examenG7.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/pedidos/v1")
public class PedidoController {
    @Autowired
    private PedidoService pedidoService;

    @PostMapping("/crearPedido")
    public ResponseEntity<Void> crearPedidos(@RequestBody List<PedidoEntity> pedidos) {
        for (PedidoEntity pe : pedidos){
            pedidoService.crearPedido(pe);
        }
        return new ResponseEntity<>( HttpStatus.CREATED);
    }

    @GetMapping("/listar/{estado}")
    public ResponseEntity buscartodos(@PathVariable int estado){
        Optional<EstadoPedido> obj=EstadoPedido.findestado(estado);
        List<PedidoEntity> resul= pedidoService.buscarTodos(obj.orElseThrow(()-> new NoSuchElementException("estado no existe")));
        return ResponseEntity.ok(resul);
    }

    @GetMapping("/parametro/")
    public ResponseEntity buscarPedidoPorParametro(@RequestParam (required = false) Long id,
                                                   @RequestParam(required = false) Integer estado){
        EstadoPedido e=null;
        if(estado!=null){
             e=EstadoPedido.findestado(estado).orElseThrow(()-> new NoSuchElementException("estado no existe"));
        }
       List<PedidoEntity>  lista=pedidoService.buscarPedidoPorparametro(id,e);
        return ResponseEntity.ok(lista);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminarDNI(@PathVariable long id){
        pedidoService.eliminarPedido(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/update/{id}/persona/{idPersona}")
    public ResponseEntity<PedidoEntity> actualizarPedido(@PathVariable Long id,
                                                         @PathVariable Long idPersona,
                                                         @RequestBody PedidoEntity pedido) {
        PedidoEntity pedidoActualizado = pedidoService.actualizarPedido(id,idPersona ,pedido);
        return new ResponseEntity<>(pedidoActualizado, HttpStatus.OK);
    }

}
