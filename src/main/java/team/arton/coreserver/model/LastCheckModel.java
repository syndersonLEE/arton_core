package team.arton.coreserver.model;

import lombok.Getter;

@Getter
public class LastCheckModel {
    private Object data;
    private boolean checkLast;

    public LastCheckModel(Object data, boolean checkLast) {
        this.data = data;
        this.checkLast = checkLast;
    }
}
