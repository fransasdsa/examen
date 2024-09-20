package com.upeu.advisor.service;

import com.upeu.advisor.entity.Advisor;
import com.upeu.advisor.entity.AdvisorRating;
import com.upeu.advisor.exception.ResourceNotFoundException;
import com.upeu.advisor.repository.AdvisorRatingRepository;
import com.upeu.advisor.repository.AdvisorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
public class AdvisorService {

    @Autowired
    private AdvisorRepository advisorRepository;

    @Autowired
    private AdvisorRatingRepository advisorRatingRepository;

    // Obtener todos los asesores con paginación
    public Page<Advisor> getAllAdvisors(Pageable pageable) {
        return advisorRepository.findAll(pageable);
    }

    // Crear un nuevo asesor
    public Advisor createAdvisor(Advisor advisor) {
        advisor.setRating(0.0); // Inicializar la calificación promedio
        return advisorRepository.save(advisor);
    }

    // Obtener un asesor por ID
    public Advisor getAdvisorById(Long id) {
        return advisorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Advisor not found for id :: " + id));
    }

    // Actualizar un asesor existente
    public Advisor updateAdvisor(Long id, Advisor advisorDetails) {
        Advisor advisor = advisorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Advisor not found for id :: " + id));

        advisor.setFirstName(advisorDetails.getFirstName());
        advisor.setLastName(advisorDetails.getLastName());
        advisor.setEmail(advisorDetails.getEmail());
        advisor.setPhoneNumber(advisorDetails.getPhoneNumber());
        advisor.setSpecialization(advisorDetails.getSpecialization());

        return advisorRepository.save(advisor);
    }

    // Eliminar un asesor
    public void deleteAdvisor(Long id) {
        Advisor advisor = advisorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Advisor not found for id :: " + id));
        advisorRepository.delete(advisor);
    }

    // Obtener asesores por especialización
    public List<Advisor> getAdvisorsBySpecialization(String specialization) {
        return advisorRepository.findBySpecialization(specialization);
    }

    // Verificar si un asesor puede aceptar un nuevo proyecto
    public boolean canAcceptNewProject(Long id) {
        Advisor advisor = advisorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Advisor not found for id :: " + id));
        return advisor.getProjects().size() < 3;
    }

    // Añadir una nueva calificación a un asesor
    public Advisor addAdvisorRating(Long id, int stars) {
        Advisor advisor = advisorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Advisor not found for id :: " + id));

        AdvisorRating rating = new AdvisorRating();
        rating.setStars(stars);
        rating.setAdvisor(advisor);
        advisorRatingRepository.save(rating);

        // Calcular el promedio de calificaciones
        double averageRating = advisorRatingRepository.findAverageRatingByAdvisorId(id);
        advisor.setRating(averageRating);
        advisorRepository.save(advisor);

        return advisor;
    }
}
