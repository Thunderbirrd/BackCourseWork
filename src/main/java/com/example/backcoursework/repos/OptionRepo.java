package com.example.backcoursework.repos;

import com.example.backcoursework.models.Option;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OptionRepo extends CrudRepository<Option, Integer> {
    @Query("SELECT option FROM Option option WHERE option.surveyId=:surveyId")
    List<Option> getAllBySurveyId(@Param("surveyId") Integer surveyId);

    @Modifying
    @Query(value = "UPDATE Option o SET o.description=:newDescription WHERE o.id=:id")
    Integer updateOptionDesc(@Param("newDescription") String newDescription, @Param("id") Integer id);

    @Modifying
    @Query(value = "UPDATE Option o SET o.votes = o.votes + 1 WHERE o.id=:id")
    void addVote(@Param("id") Integer id);

    @Modifying
    @Query(value = "UPDATE Option o SET o.votes = o.votes - 1 WHERE o.id=:id")
    void removeVote(@Param("id") Integer id);

    @Modifying
    @Query(value = "DELETE from Option o WHERE o.surveyId=:surveyId")
    void deleteAllBySurveyId(@Param("surveyId") Integer surveyId);
}
