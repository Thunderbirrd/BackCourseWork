package com.example.backcoursework.repos;

import com.example.backcoursework.models.Answer;
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

    @Query("SELECT a.userId FROM Answer a WHERE a.surveyId=:surveyId")
    List<Integer> getAllUserIdBySurveyId(@Param("surveyId") Integer surveyId);

    @Query("SELECT a FROM Answer a WHERE a.surveyId=:surveyId AND a.userId =:userId AND a.optionId=:optionId")
    Answer getAnswerByAllId(@Param("surveyId") Integer surveyId, @Param("userId") Integer userId,
                                   @Param("optionId") Integer optionId);

    @Query("SELECT a FROM Answer a WHERE a.userId=:userId")
    List<Answer> getAllUsersAnswers(@Param("userId") Integer userId);
}
