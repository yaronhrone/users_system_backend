package com.example.users.controller;


import com.example.users.model.Answer;
import com.example.users.model.AnswerResponse;
import com.example.users.model.Question;
import com.example.users.service.PollClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/poll")
public class PollController {
    @Autowired
    private PollClientService pollClientService;

    @GetMapping("/{id}")
    public ResponseEntity<Question> getQuestionById(@PathVariable int id){
            try{
              Question question =   pollClientService.getQuestion(id);
              if (question != null) {
                  return new ResponseEntity<>(question, HttpStatus.OK);
              }else return new ResponseEntity("The question withe id: " +id + " is not exists",HttpStatus.NOT_FOUND);
            } catch (Exception e) {
    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR)    ;    }
    }
    @PostMapping("/answer")
    public ResponseEntity<String> answerQuestion(@RequestBody Answer answer) {
        System.out.println(answer);
        try {
            String result = pollClientService.answerQuestion(answer);
            if (result.contains("sending")){
                return new ResponseEntity<>(result, HttpStatus.OK);
        }else return new ResponseEntity<>(result,HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>( HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
        @GetMapping("/answer-user-by-id/{id}")
                public ResponseEntity<List<AnswerResponse>> getAllAnswerByUserId(@PathVariable int id) {
            try {List<AnswerResponse> result =pollClientService.allAnswerByUserId(id);
                if (result !=null) {
                    if (!result.isEmpty()) {
                        return new ResponseEntity<>(result, HttpStatus.OK);
                    }
                    return new ResponseEntity(" The user not have answer", HttpStatus.NOT_FOUND);
                }else return new ResponseEntity(" The user is not exists ",HttpStatus.NOT_FOUND);
            } catch (Exception e) {
                return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        @GetMapping("/number-user-answer/{id}")
                public ResponseEntity<Integer> howManyQuestionUserAnswerById(@PathVariable int id){
                try {
                    return new ResponseEntity<>(pollClientService.howManyQuestionUserAnswerById(id),HttpStatus.OK);
                } catch (Exception e) {
                    return new ResponseEntity(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }


}
