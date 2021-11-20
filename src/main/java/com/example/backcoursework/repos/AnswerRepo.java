package com.example.backcoursework.repos;

import com.example.backcoursework.models.Answer;
import com.example.backcoursework.models.Option;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerRepo extends CrudRepository<Answer, Integer> {
    @Modifying
    @Query(value = "DELETE from Answer a WHERE a.surveyId=:surveyId")
    void deleteAllBySurveyId(@Param("surveyId") Integer surveyId);

    @Modifying
    @Query(value = "DELETE from Answer a WHERE a.optionId=:optionId")
    void deleteAllByOptionId(@Param("surveyId") Integer optionId);

    @Query("SELECT a.userId FROM Answer a WHERE a.surveyId=:surveyId")
    List<Integer> getAllUserIdBySurveyId(@Param("surveyId") Integer surveyId);

    @Query("SELECT a.userId FROM Answer a WHERE a.optionId=:optionId")
    List<Integer> getAllUserIdByOptionId(@Param("surveyId") Integer optionId);
}
