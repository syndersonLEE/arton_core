package team.arton.coreserver.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Content {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @ManyToOne(targetEntity = Editor.class, fetch = FetchType.EAGER)
    @JoinColumn(name="author_id")
    private Editor editor;

    @Column(name = "thumbnail_url")
    private String thumbnailUrl;

}
