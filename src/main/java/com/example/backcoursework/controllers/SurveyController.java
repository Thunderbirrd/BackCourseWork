package com.example.backcoursework.controllers;

import com.example.backcoursework.controllers.responses.BaseResponse;
import com.example.backcoursework.models.Survey;
import com.example.backcoursework.services.SurveyService;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/survey")
public class SurveyController {

    private final SurveyService service;

    @RequestMapping(value = "/save", method = RequestMethod.PUT)
    public BaseResponse saveSurvey(@RequestBody String surveyData){
        Survey survey = new Survey();
        JSONObject data = new JSONObject(surveyData);
        survey.setDescription(data.getString("description"));
        survey.setType(data.getString("type"));
        return service.saveSurvey(survey);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String updateDescription(@RequestBody String surveyData){
        JSONObject data = new JSONObject(surveyData);
        String newDesc = data.getString("description");
        Integer id = data.getInt("id");
        return service.updateDescription(newDesc, id);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public BaseResponse deleteSurvey(@RequestBody String surveyData){
        JSONObject data = new JSONObject(surveyData);
        Integer id = data.getInt("id");
        return service.deleteSurvey(id);
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public List<Survey> getAll(){
        return service.getAllSurveys();
    }
}
