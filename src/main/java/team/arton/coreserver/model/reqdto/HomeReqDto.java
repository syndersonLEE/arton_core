package team.arton.coreserver.model.reqdto;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Setter
@Slf4j
public class HomeReqDto {

    private Long lastContentId = 0L;

    private int contentNum = 6;

}
