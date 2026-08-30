package com.example.ms_pedido.config;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.ms_pedido.models.Pedido;
import com.example.ms_pedido.models.enums.EstadoPedido;
import com.example.ms_pedido.repository.PedidoRepository;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initDatabase(PedidoRepository pedidoRepository) {
        return args -> {

            if (pedidoRepository.count() > 0) {
                return;
            }

            Pedido pedido1 = new Pedido();
            pedido1.setIdCliente(1L);
            pedido1.setFecha(LocalDateTime.now());
            pedido1.setEstado(EstadoPedido.PENDIENTE_PAGO);
            pedido1.setTotal(new BigDecimal("25990"));

            Pedido pedido2 = new Pedido();
            pedido2.setIdCliente(2L);
            pedido2.setFecha(LocalDateTime.now());
            pedido2.setEstado(EstadoPedido.PAGO_APROBADO);
            pedido2.setTotal(new BigDecimal("45990"));

            Pedido pedido3 = new Pedido();
            pedido3.setNombreInvitado("Juan Pérez");
            pedido3.setCorreoInvitado("juan@gmail.com");
            pedido3.setDireccionInvitado("Av. Siempre Viva 123");
            pedido3.setFecha(LocalDateTime.now());
            pedido3.setEstado(EstadoPedido.PENDIENTE_PAGO);
            pedido3.setTotal(new BigDecimal("15990"));

            pedidoRepository.save(pedido1);
            pedidoRepository.save(pedido2);
            pedidoRepository.save(pedido3);

            System.out.println("Pedidos iniciales creados correctamente.");
        };
    }
}
