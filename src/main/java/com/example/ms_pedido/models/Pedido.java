package com.example.ms_pedido.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.example.ms_pedido.models.enums.EstadoPedido;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.DecimalMin;


// ENTIDAD PEDIDO (cabecera de la compra web).
// Cubre cliente registrado e invitado (HU-22, 23, 24, 25, 27, 48, 53, 54).
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pedidos")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPedido;

    // Id Externo: el cliente vive en MS Usuarios. Es opcional (invitado = null).
    private Long idCliente;

    // Datos del invitado (HU-53): solo se llenan si NO hay idCliente
    private String nombreInvitado;
    private String correoInvitado;
    private String direccionInvitado;

    private LocalDateTime fecha;

    // Estado del pedido (enum). Empieza en PENDIENTE_PAGO.
    @NotNull(message = "El estado es obligatorio")
    @Enumerated(EnumType.STRING)
    private EstadoPedido estado;

    // Id Externo: si es retiro, en qué sucursal (MS Sucursales). Opcional.
    private Long idSucursalRetiro;

    @NotNull(message = "El total es obligatorio")
    @DecimalMin(value = "0.0", inclusive = false, message = "El total debe ser mayor que cero")
    private BigDecimal total;

}