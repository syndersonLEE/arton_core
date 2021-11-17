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
public class Docent {
    @Id
    private Long id;

    @Column
    private String name;

    @Column
    private String thumbnail_url;

    @Column
    private String description;


}
