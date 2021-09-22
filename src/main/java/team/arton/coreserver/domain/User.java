package team.arton.coreserver.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
@Getter
@Setter
public class User {
    @Id
    private Long id;

    @Column
    private String type;

    @Column
    private String email;

    @Column
    private String token;

    @Column
    private String create_time;
}
