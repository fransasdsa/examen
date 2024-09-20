package com.upeu.advisor.repository;

import com.upeu.advisor.entity.Advisor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdvisorRepository extends JpaRepository<Advisor, Long> {
    List<Advisor> findBySpecialization(String specialization);
}
