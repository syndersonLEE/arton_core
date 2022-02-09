package team.arton.coreserver.domain;

import lombok.*;

import javax.persistence.*;


@Entity
@Builder
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;

    private String serverid;

    private String nickname;

    public User(Long id, String type, String serverid, String nickname) {
        this.id = id;
        this.type = type;
        this.serverid = serverid;
        this.nickname = nickname;
    }

    public User() {
    }
}
