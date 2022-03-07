package team.arton.coreserver.model;

import com.sun.istack.NotNull;
import lombok.Getter;

@Getter
public class BookmarkReq {
    @NotNull
    private Long contentId;
}
