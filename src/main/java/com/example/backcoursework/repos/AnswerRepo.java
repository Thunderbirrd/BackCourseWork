package com.example.backcoursework.repos;

import com.example.backcoursework.models.Answer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerRepo extends CrudRepository<Answer, Integer> {
}
