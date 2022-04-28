package team.arton.coreserver.model.resdto;

import lombok.Getter;

@Getter
public class QuizReqDto {
    private String question;

    private String answer;

    private String quiz_img;
}
