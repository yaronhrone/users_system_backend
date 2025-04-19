package com.example.users.Client;

import com.example.users.model.Answer;
import com.example.users.model.AnswerResponse;
import com.example.users.model.Question;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "${external-api.poll.name}" , url = "${external-api.poll.url}")
public interface PollClient {
    @PostMapping ("/question/answer")
    String answer (@RequestBody Answer answer);
    @GetMapping("/question/{id}")
    Question getQuestion (@PathVariable int id);
    @GetMapping("/question/answer-user/{id}")
    List<AnswerResponse> allAnswerByUserId (@PathVariable int id);
    @GetMapping("question/count-answer-user/{id}")
    Integer howManyQuestionUserAnswerById(@PathVariable int id);
    @DeleteMapping("/question/delete_answer/")
    void deleteAnswer(@RequestParam int id);


}
