package com.upeu.degree.controller;
import com.upeu.degree.exception.ResourceNotFoundException;

import com.upeu.degree.entity.Degree;
import com.upeu.degree.repository.DegreeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/degrees")
public class DegreeController {

    @Autowired
    private DegreeRepository degreeRepository;

    @GetMapping
    public List<Degree> getAllDegrees() {
        return degreeRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Degree> createDegree(@RequestBody Degree degree) {
        Degree newDegree = degreeRepository.save(degree);
        return ResponseEntity.created(URI.create("/degrees/" + newDegree.getId())).body(newDegree);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Degree> getDegreeById(@PathVariable Long id) {
        Degree degree = degreeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Degree not found for this id: " + id));
        return ResponseEntity.ok(degree);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Degree> updateDegree(@PathVariable Long id, @RequestBody Degree degreeDetails) {
        Degree degree = degreeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Degree not found for this id: " + id));

        degree.setName(degreeDetails.getName());
        degree.setDescription(degreeDetails.getDescription());
        degree.setLevel(degreeDetails.getLevel());
        degree.setStatus(degreeDetails.getStatus());

        final Degree updatedDegree = degreeRepository.save(degree);
        return ResponseEntity.ok(updatedDegree);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDegree(@PathVariable Long id) {
        Degree degree = degreeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Degree not found for this id: " + id));

        degreeRepository.delete(degree);
        return ResponseEntity.noContent().build();
    }
}
