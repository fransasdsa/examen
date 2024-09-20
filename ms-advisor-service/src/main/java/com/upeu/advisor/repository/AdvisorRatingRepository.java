package com.upeu.advisor.repository;

import com.upeu.advisor.entity.AdvisorRating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AdvisorRatingRepository extends JpaRepository<AdvisorRating, Long> {

    @Query("SELECT AVG(ar.stars) FROM AdvisorRating ar WHERE ar.advisor.id = :advisorId")
    double findAverageRatingByAdvisorId(Long advisorId);
}
