package com.example.users.service;


import com.example.users.Client.PollClient;
import com.example.users.model.Answer;
import com.example.users.model.AnswerResponse;
import com.example.users.model.Question;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PollClientService {

    @Autowired
    private PollClient pollClient;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private UserService userService;

    public Question getQuestion(int id )  {
   return pollClient.getQuestion(id);


    }
    public String answerQuestion(Answer answer){
       if (userService.getUserById(answer.getUserId()) != null) {
              String answer1 = pollClient.answer(answer);

              return answer1 + " ,The answer is save in the system";
       }else return   " The answer is not save , the user withe id: " + answer.getUserId()+ " is not exists";
    }
    public List<AnswerResponse> allAnswerByUserId(int id){
        if (userService.getUserById(id) != null){

       return pollClient.allAnswerByUserId(id);
        }return null;
    }
    public Integer howManyQuestionUserAnswerById(int id){
       return pollClient.howManyQuestionUserAnswerById(id);
    }
    public void deleteAnswer(int userId){
        pollClient.deleteAnswer(userId);
    }
}
