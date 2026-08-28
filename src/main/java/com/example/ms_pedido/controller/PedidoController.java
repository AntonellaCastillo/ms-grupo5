package com.example.ms_pedido.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ms_pedido.models.Pedido;
import com.example.ms_pedido.service.PedidoService;

@RestController
@RequestMapping("/api/v1/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @GetMapping
    public ResponseEntity<List<Pedido>> getAllPedidos() {
        List<Pedido> pedidos = pedidoService.findAll();
        if (pedidos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(pedidos);
    }

    @GetMapping("/{idPedido}")
    public ResponseEntity<Pedido> getPedidoById(@PathVariable Long idPedido) {
        Pedido pedido = pedidoService.findById(idPedido);
        if (pedido == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(pedido);
    }

    @PostMapping
    public ResponseEntity<Pedido> createPedido(@RequestBody Pedido pedido) {
        Pedido createdPedido = pedidoService.save(pedido);
        return ResponseEntity.status(201).body(createdPedido);
    }

    @PutMapping("/{idPedido}")
    public ResponseEntity<Pedido> updatePedido(@PathVariable Long idPedido, @RequestBody Pedido pedido) {
        if (pedidoService.findById(idPedido) == null) {
            return ResponseEntity.notFound().build();
        }
        pedido.setIdPedido(idPedido);
        Pedido updatedPedido = pedidoService.save(pedido);
        return ResponseEntity.ok(updatedPedido);
    }

    @PatchMapping("/{idPedido}")
    public ResponseEntity<Pedido> patchPedido(@PathVariable Long idPedido, @RequestBody Pedido pedido) {
        Pedido patchedPedido = pedidoService.patchPedido(idPedido, pedido);
        if (patchedPedido == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(patchedPedido);
    }

    @DeleteMapping("/{idPedido}")
    public ResponseEntity<Void> deletePedido(@PathVariable Long idPedido) {
        if (pedidoService.findById(idPedido) == null) {
            return ResponseEntity.notFound().build();
        }
        pedidoService.deleteById(idPedido);
        return ResponseEntity.noContent().build();
    }
}
