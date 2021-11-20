package com.example.backcoursework.services;

import com.example.backcoursework.controllers.responses.BaseResponse;
import com.example.backcoursework.models.Survey;
import com.example.backcoursework.repos.SurveyRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SurveyServiceImpl implements SurveyService{

    private final SurveyRepo surveyRepo;

    @Override
    @Transactional
    public BaseResponse saveSurvey(Survey survey) {
        Survey saved = surveyRepo.save(survey);
        return new BaseResponse("Survey successfully saved", saved.getId());
    }

    @Override
    @Transactional
    public String updateDescription(String newDescription, Integer id) {
        Integer count = surveyRepo.updateSurveyDesc(newDescription, id);
        if (count == 1){
            return "Description was successfully updated";
        }
        return "Something went wrong";
    }

    @Override
    @Transactional
    public BaseResponse deleteSurvey(Integer id) {
        surveyRepo.deleteById(id);
        return new BaseResponse("Survey successful deleted", id);
    }

    @Override
    @Transactional
    public List<Survey> getAllSurveys() {
        return surveyRepo.getAll();
    }
}
