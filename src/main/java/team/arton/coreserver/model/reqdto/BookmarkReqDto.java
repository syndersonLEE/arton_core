package team.arton.coreserver.model.reqdto;

import com.sun.istack.NotNull;
import lombok.Getter;

@Getter
public class BookmarkReqDto {
    @NotNull
    private Long contentId;
}
