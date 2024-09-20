package com.upeu.payment.service;

import com.upeu.payment.entity.Payment;
import com.upeu.payment.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    // Obtener todos los pagos
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    // Crear un nuevo pago
    public Payment createPayment(Payment payment) {
        payment.setStatus("PENDIENTE"); // Inicializa el estado como PENDIENTE
        return paymentRepository.save(payment);
    }

    // Obtener un pago por ID
    public Payment getPaymentById(Long id) {
        return paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment not found for id: " + id));
    }

    // Actualizar un pago existente
    public Payment updatePayment(Long id, Payment paymentDetails) {
        Payment payment = getPaymentById(id);
        payment.setAmount(paymentDetails.getAmount());
        payment.setStatus(paymentDetails.getStatus());
        payment.setMethod(paymentDetails.getMethod());
        return paymentRepository.save(payment);
    }

    // Eliminar un pago
    public void deletePayment(Long id) {
        Payment payment = getPaymentById(id);
        paymentRepository.delete(payment);
    }
}
