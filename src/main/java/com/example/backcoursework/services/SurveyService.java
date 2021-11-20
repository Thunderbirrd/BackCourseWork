package com.example.backcoursework.services;

import com.example.backcoursework.controllers.responses.BaseResponse;
import com.example.backcoursework.models.Survey;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SurveyService {
    BaseResponse saveSurvey(Survey survey);

    String updateDescription(String newDescription, Integer id);

    BaseResponse deleteSurvey(Integer id);

    List<Survey> getAllSurveys();
}
