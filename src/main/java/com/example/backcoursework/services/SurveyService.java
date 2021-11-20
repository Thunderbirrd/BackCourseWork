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

    List<Survey> getAllSurveys();

    BaseResponse saveAnswer(Answer answer);

    BaseResponse saveOption(Option option);

    BaseResponse deleteOption(Integer id);

    String updateOptionDesc(String newDescription, Integer id);

    String addVote(Integer id);

    String removeVote(Integer id);

    List<Integer> getAllUserIdBySurveyId(Integer surveyId);

    List<Integer> getAllUserIdByOptionId(Integer optionId);
}
