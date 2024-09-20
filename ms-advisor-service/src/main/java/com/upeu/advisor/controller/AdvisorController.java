package com.upeu.advisor.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import com.upeu.advisor.entity.Advisor;
import com.upeu.advisor.service.AdvisorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@Tag(name = "Advisors", description = "API para gestionar asesores")
@RestController
@RequestMapping("/advisors")
public class AdvisorController {

    @Autowired
    private AdvisorService advisorService;

    /**
     * Obtener todos los asesores con paginación.
     *
     * @param page Número de página (default 0)
     * @param size Tamaño de la página (default 10)
     * @return Página de asesores
     */
    @Operation(summary = "Obtener todos los asesores con paginación")
    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'ASSESSOR', 'EVALUATOR')")
    public ResponseEntity<Page<Advisor>> getAllAdvisors(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Advisor> advisors = advisorService.getAllAdvisors(pageable);
        return ResponseEntity.ok(advisors);
    }

    /**
     * Crear un nuevo asesor.
     *
     * @param advisor Detalles del asesor a crear
     * @return Asesor creado
     */
    @Operation(summary = "Crear un nuevo asesor")
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    @CacheEvict(value = {"advisors", "advisorsBySpecialization"}, allEntries = true)
    public ResponseEntity<Advisor> createAdvisor(@Valid @RequestBody Advisor advisor) {
        Advisor createdAdvisor = advisorService.createAdvisor(advisor);
        return ResponseEntity.status(201).body(createdAdvisor);
    }

    /**
     * Obtener un asesor por su ID.
     *
     * @param id ID del asesor
     * @return Asesor correspondiente
     */
    @Operation(summary = "Obtener un asesor por su ID")
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'ASSESSOR', 'EVALUATOR')")
    public ResponseEntity<Advisor> getAdvisorById(@PathVariable Long id) {
        Advisor advisor = advisorService.getAdvisorById(id);
        return ResponseEntity.ok(advisor);
    }

    /**
     * Actualizar los detalles de un asesor existente.
     *
     * @param id             ID del asesor a actualizar
     * @param advisorDetails Nuevos detalles del asesor
     * @return Asesor actualizado
     */
    @Operation(summary = "Actualizar un asesor existente")
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @CacheEvict(value = {"advisors", "advisorsBySpecialization"}, allEntries = true)
    public ResponseEntity<Advisor> updateAdvisor(@PathVariable Long id, @Valid @RequestBody Advisor advisorDetails) {
        Advisor updatedAdvisor = advisorService.updateAdvisor(id, advisorDetails);
        return ResponseEntity.ok(updatedAdvisor);
    }

    /**
     * Eliminar un asesor por su ID.
     *
     * @param id ID del asesor a eliminar
     * @return Respuesta sin contenido
     */
    @Operation(summary = "Eliminar un asesor por su ID")
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @CacheEvict(value = {"advisors", "advisorsBySpecialization"}, allEntries = true)
    public ResponseEntity<Void> deleteAdvisor(@PathVariable Long id) {
        advisorService.deleteAdvisor(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Obtener asesores filtrados por especialización.
     *
     * @param specialization Especialización para filtrar
     * @return Lista de asesores con la especialización indicada
     */
    @Operation(summary = "Obtener asesores por especialización")
    @GetMapping("/specialization/{specialization}")
    @PreAuthorize("hasAnyRole('ADMIN', 'ASSESSOR', 'EVALUATOR')")
    @Cacheable(value = "advisorsBySpecialization", key = "#specialization")
    public ResponseEntity<List<Advisor>> getAdvisorsBySpecialization(@PathVariable String specialization) {
        List<Advisor> advisors = advisorService.getAdvisorsBySpecialization(specialization);
        return ResponseEntity.ok(advisors);
    }

    /**
     * Verificar si un asesor puede aceptar un nuevo proyecto.
     *
     * @param id ID del asesor
     * @return true si puede aceptar, false de lo contrario
     */
    @Operation(summary = "Verificar si un asesor puede aceptar un nuevo proyecto")
    @GetMapping("/{id}/can-accept")
    @PreAuthorize("hasAnyRole('ADMIN', 'ASSESSOR')")
    public ResponseEntity<Boolean> canAcceptNewProject(@PathVariable Long id) {
        boolean canAccept = advisorService.canAcceptNewProject(id);
        return ResponseEntity.ok(canAccept);
    }

    /**
     * Añadir una nueva calificación a un asesor.
     *
     * @param id     ID del asesor
     * @param rating Calificación (1 a 5)
     * @return Asesor con la calificación actualizada
     */
    @Operation(summary = "Añadir una nueva calificación a un asesor")
    @PostMapping("/{id}/rating")
    @PreAuthorize("hasAnyRole('ADMIN', 'ASSESSOR')")
    @CacheEvict(value = {"advisors", "advisorsBySpecialization"}, allEntries = true)
    public ResponseEntity<Advisor> addAdvisorRating(@PathVariable Long id, @RequestParam int rating) {
        if (rating < 1 || rating > 5) {
            return ResponseEntity.badRequest().build();
        }
        Advisor updatedAdvisor = advisorService.addAdvisorRating(id, rating);
        return ResponseEntity.ok(updatedAdvisor);
    }
}
