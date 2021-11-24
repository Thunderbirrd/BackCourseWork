package com.example.backcoursework.services;

import com.example.backcoursework.controllers.responses.BaseResponse;
import com.example.backcoursework.models.Answer;
import com.example.backcoursework.models.Option;
import com.example.backcoursework.models.Survey;
import com.example.backcoursework.repos.AnswerRepo;
import com.example.backcoursework.repos.OptionRepo;
import com.example.backcoursework.repos.SurveyRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SurveyServiceImpl implements SurveyService{

    private final SurveyRepo surveyRepo;
    private final OptionRepo optionRepo;
    private final AnswerRepo answerRepo;

    @Override
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
    public String updateOptionDesc(String newDescription, Integer id) {
        Integer count = optionRepo.updateOptionDesc(newDescription, id);
        if (count == 1){
            return "Description was successfully updated";
        }
        return "Something went wrong";
    }

    @Override
    @Transactional
    public void addVote(Integer id) {
        optionRepo.addVote(id);
    }

    @Override
    @Transactional
    public void removeVote(Integer id) {
        optionRepo.removeVote(id);
    }

    @Override
    public List<Integer> getAllUserIdBySurveyId(Integer surveyId) {
        return answerRepo.getAllUserIdBySurveyId(surveyId);
    }

    @Override
    public Answer getAnswerByAllId(Integer surveyId, Integer optionId, Integer userId) {
        return answerRepo.getAnswerByAllId(surveyId, userId, optionId);
    }

    @Override
    public List<Answer> getAllUsersAnswers(Integer userId) {
        return answerRepo.getAllUsersAnswers(userId);
    }

    @Override
    public BaseResponse deleteSurvey(Integer id) {
        answerRepo.deleteAllBySurveyId(id);
        optionRepo.deleteAllBySurveyId(id);
        surveyRepo.deleteById(id);
        return new BaseResponse("Survey successful deleted", id);
    }

    @Override
    public void deleteAnswer(Integer id) {
        answerRepo.deleteById(id);
    }

    @Override
    public List<Survey> getAllSurveys() {
        return surveyRepo.getAll();
    }

    @Override
    public List<Option> getAllOptionsBySurveyId(Integer surveyId) {
        return optionRepo.getAllBySurveyId(surveyId);
    }

    @Override
    public BaseResponse saveAnswer(Answer answer) {
        Answer saved = answerRepo.save(answer);
        return new BaseResponse("Answer successfully saved", saved.getId());
    }

    @Override
    public BaseResponse saveOption(Option option) {
        Option saved = optionRepo.save(option);
        return new BaseResponse("Option successfully saved", saved.getId());
    }

}
