package team.arton.coreserver.model.reqdto;

import lombok.Getter;
import lombok.NonNull;

@Getter
public class ContentViewReqDto {
    @NonNull
    private Long contentId;
}
