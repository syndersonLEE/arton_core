package team.arton.coreserver.model;

import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class AdminContentReqDto {
    @NotNull
    private Long authorId;

    @NotNull
    private String title;

    @NotNull
    private String link;

    private String thumbnailUrl;

    private QuizReqDto quiz;
}
