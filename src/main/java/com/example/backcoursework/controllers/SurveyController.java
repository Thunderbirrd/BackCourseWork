package com.example.backcoursework.controllers;

import com.example.backcoursework.controllers.responses.BaseResponse;
import com.example.backcoursework.models.Answer;
import com.example.backcoursework.models.Option;
import com.example.backcoursework.models.Survey;
import com.example.backcoursework.services.SurveyService;
import com.example.backcoursework.services.UserService;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/survey")
public class SurveyController {

    private final SurveyService service;
    private final UserService userService;

    @RequestMapping(value = "/save", method = RequestMethod.PUT)
    @CrossOrigin(originPatterns = {"http://localhost:63342", "https://thunderbirrd.github.io/"})
    public BaseResponse saveSurvey(@RequestBody String surveyData){
        Survey survey = new Survey();
        JSONObject data = new JSONObject(surveyData);
        survey.setDescription(data.getString("description"));
        survey.setType(data.getString("type"));
        survey.setOwnerId(data.getInt("user_id"));
        return service.saveSurvey(survey);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @CrossOrigin(originPatterns = {"http://localhost:63342", "https://thunderbirrd.github.io/"})
    public String updateDescription(@RequestBody String surveyData){
        JSONObject data = new JSONObject(surveyData);
        String newDesc = data.getString("description");
        Integer id = data.getInt("id");
        return service.updateDescription(newDesc, id);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    @CrossOrigin(originPatterns = {"http://localhost:63342", "https://thunderbirrd.github.io/"})
    public BaseResponse deleteSurvey(@RequestBody String surveyData){
        JSONObject data = new JSONObject(surveyData);
        Integer id = data.getInt("id");
        List<Integer> users = service.getAllUserIdBySurveyId(id);
        for (Integer user : users) {
            userService.decreaseRating(user);
        }
        return service.deleteSurvey(id);
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    @CrossOrigin(originPatterns = {"http://localhost:63342", "https://thunderbirrd.github.io/"})
    public List<Survey> getAll(){
        return service.getAllSurveys();
    }

    @RequestMapping(value = "/option/getAll", method = RequestMethod.POST)
    @CrossOrigin(origins = {"http://localhost:63342", "https://thunderbirrd.github.io/"})
    public List<Option> getAllOptions(@RequestBody String optionData){
        JSONObject data = new JSONObject(optionData);
        Integer surveyId = data.getInt("survey_id");
        return service.getAllOptionsBySurveyId(surveyId);
    }

    @RequestMapping(value = "/option/save", method = RequestMethod.POST)
    @CrossOrigin(originPatterns = {"http://localhost:63342", "https://thunderbirrd.github.io/"})
    public BaseResponse saveOption(@RequestBody String optionData){
        JSONObject data = new JSONObject(optionData);
        Option option = new Option();
        option.setDescription(data.getString("description"));
        option.setSurveyId(data.getInt("survey_id"));
        option.setVotes(0);
        return service.saveOption(option);
    }

    @RequestMapping(value = "/option/update", method = RequestMethod.POST)
    @CrossOrigin(originPatterns = {"http://localhost:63342", "https://thunderbirrd.github.io/"})
    public String updateOptionDesc(@RequestBody String optionData){
        JSONObject data = new JSONObject(optionData);
        String newDesc = data.getString("description");
        Integer id = data.getInt("id");
        return service.updateOptionDesc(newDesc, id);
    }

    @RequestMapping(value = "/vote", method = RequestMethod.POST)
    @CrossOrigin(originPatterns = {"http://localhost:63342", "https://thunderbirrd.github.io/"})
    public BaseResponse vote(@RequestBody String voteData){
        JSONObject data = new JSONObject(voteData);
        Integer surveyId = data.getInt("survey_id");
        Integer userId = data.getInt("user_id");
        Integer optionId = data.getInt("option_id");
        Answer answer = new Answer();
        answer.setSurveyId(surveyId);
        answer.setUserId(userId);
        answer.setOptionId(optionId);
        BaseResponse response = service.saveAnswer(answer);
        service.addVote(optionId);
        userService.increaseRating(userId);
        return response;
    }

    @RequestMapping(value = "/unvote", method = RequestMethod.POST)
    @CrossOrigin(originPatterns = {"http://localhost:63342", "https://thunderbirrd.github.io/"})
    public BaseResponse removeVote(@RequestBody String voteData){
        JSONObject data = new JSONObject(voteData);
        Integer surveyId = data.getInt("survey_id");
        Integer userId = data.getInt("user_id");
        Integer optionId = data.getInt("option_id");
        Answer answer = service.getAnswerByAllId(surveyId, optionId, userId);
        service.deleteAnswer(answer.getId());
        service.removeVote(optionId);
        userService.decreaseRating(userId);
        return new BaseResponse("Success", answer.getId());
    }

    @RequestMapping(value = "/getAllUsersAnswers", method = RequestMethod.POST)
    @CrossOrigin(originPatterns = {"http://localhost:63342", "https://thunderbirrd.github.io/"})
    public List<Answer> getAllUsersAnswers(@RequestBody String userData){
        JSONObject data = new JSONObject(userData);
        return service.getAllUsersAnswers(data.getInt("id"));
    }
}
