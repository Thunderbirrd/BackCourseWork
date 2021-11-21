package com.example.backcoursework.services;

import com.example.backcoursework.controllers.responses.BaseResponse;
import com.example.backcoursework.models.Answer;
import com.example.backcoursework.models.Option;
import com.example.backcoursework.models.Survey;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SurveyService {
    BaseResponse saveSurvey(Survey survey);

    String updateDescription(String newDescription, Integer id);

    BaseResponse deleteSurvey(Integer id);

    void deleteAnswer(Integer id);

    List<Survey> getAllSurveys();

    List<Option> getAllOptionsBySurveyId(Integer surveyId);

    BaseResponse saveAnswer(Answer answer);

    BaseResponse saveOption(Option option);

    String updateOptionDesc(String newDescription, Integer id);

    void addVote(Integer id);

    void removeVote(Integer id);

    List<Integer> getAllUserIdBySurveyId(Integer surveyId);

    Answer getAnswerByAllId(Integer surveyId, Integer optionId, Integer userId);
}
