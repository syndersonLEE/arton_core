package team.arton.coreserver.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "author")
@Getter
@Setter
public class Editor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "thumbnail_url")
    private String thumbnailUrl;

    private String description;
}
