package com.example.backcoursework.repos;

import com.example.backcoursework.models.Survey;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SurveyRepo extends CrudRepository<Survey, Integer> {
}
