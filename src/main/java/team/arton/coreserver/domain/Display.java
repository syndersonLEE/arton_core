package team.arton.coreserver.domain;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
@Setter
@Getter
public class Display {
    @Id
    private Long id;

    @Column
    private String title;

    @Column
    private String genre;

    @Column
    private String summary;

    @Column
    private String body;

    @Column
    private String create_time;
}
