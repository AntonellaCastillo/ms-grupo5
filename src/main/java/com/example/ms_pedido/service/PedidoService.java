package com.example.ms_pedido.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ms_pedido.models.Pedido;
import com.example.ms_pedido.repository.PedidoRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    public List<Pedido> findAll() {
        return pedidoRepository.findAll();
    }

    public Pedido findById(Long idPedido) {
        return pedidoRepository.findById(idPedido).orElse(null);
    }

    public Pedido save(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    public Pedido patchPedido(Long idPedido, Pedido pedido) {
        Pedido pedidoExistente = findById(idPedido);

        if (pedidoExistente != null) {
            if (pedido.getIdCliente() != null) {
                pedidoExistente.setIdCliente(pedido.getIdCliente());
            }
            if (pedido.getNombreInvitado() != null) {
                pedidoExistente.setNombreInvitado(pedido.getNombreInvitado());
            }
            if (pedido.getCorreoInvitado() != null) {
                pedidoExistente.setCorreoInvitado(pedido.getCorreoInvitado());
            }
            if (pedido.getDireccionInvitado() != null) {
                pedidoExistente.setDireccionInvitado(pedido.getDireccionInvitado());
            }
            if (pedido.getFecha() != null) {
                pedidoExistente.setFecha(pedido.getFecha());
            }
            if (pedido.getEstado() != null) {
                pedidoExistente.setEstado(pedido.getEstado());
            }
            if (pedido.getIdSucursalRetiro() != null) {
                pedidoExistente.setIdSucursalRetiro(pedido.getIdSucursalRetiro());
            }
            if (pedido.getTotal() != null) {
                pedidoExistente.setTotal(pedido.getTotal());
            }

            return save(pedidoExistente);
        }

        return null;
    }

    public void deleteById(Long idPedido) {
        pedidoRepository.deleteById(idPedido);
    }
}
