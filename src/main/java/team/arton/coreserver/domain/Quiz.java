package team.arton.coreserver.domain;

import lombok.Getter;
import lombok.Setter;
import team.arton.coreserver.model.QuizReqDto;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String question;

    private String answer;

    public Quiz() {}

    public Quiz(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    public Quiz(QuizReqDto quizReqDto
    ) {
        this.question = quizReqDto.getQuestion();
        this.answer = quizReqDto.getAnswer();
    }
}

