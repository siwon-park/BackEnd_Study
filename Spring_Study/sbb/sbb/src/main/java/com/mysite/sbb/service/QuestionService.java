package com.mysite.sbb.service;

import com.mysite.sbb.DataNotFoundException;
import com.mysite.sbb.model.Question;
import com.mysite.sbb.repository.QuestionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {

    private final QuestionRepository questionRepository;

    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public List<Question> getList() {
        return this.questionRepository.findAll();
    }

    // QuestionService
    public Question getQuestion(Integer id) {
        Optional<Question> question = this.questionRepository.findById(id);
        if (question.isPresent()) {
            return question.get();
        } else {
            throw new DataNotFoundException("질문이 존재하지 않습니다.");
        }
    }


    // question create 메서드(QuestionService.java)
    public void create(String title, String content) {
        Question question = new Question();
        question.setTitle(title);
        question.setContent(content);
        question.setCreatedAt(LocalDateTime.now());
        this.questionRepository.save(question);
    }
}
