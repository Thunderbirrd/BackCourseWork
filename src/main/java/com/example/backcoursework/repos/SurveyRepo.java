package com.example.backcoursework.repos;

import com.example.backcoursework.models.Survey;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SurveyRepo extends CrudRepository<Survey, Integer> {
    @Query("SELECT survey FROM Survey survey")
    List<Survey> getAll();

    @Modifying
    @Query(value = "UPDATE Survey s SET s.description=:newDescription WHERE s.id=:id")
    Integer updateSurveyDesc(@Param("newDescription") String newDescription, @Param("id") Integer id);
}
