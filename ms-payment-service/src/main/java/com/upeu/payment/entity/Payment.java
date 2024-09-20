package com.upeu.payment.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "payments")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long studentId; // ID del estudiante asociado al pago
    private BigDecimal amount; // Monto del pago
    private String status; // Estado del pago (PENDIENTE, COMPLETADO)
    private String method; // Método de pago (CREDITO, DEBITO)
}
