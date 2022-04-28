package team.arton.coreserver.model;

import lombok.Getter;

@Getter
public class LastCheckModel {
    private Object content;
    private boolean checkLast;

    public LastCheckModel(Object content, boolean checkLast) {
        this.content = content;
        this.checkLast = checkLast;
    }
}
