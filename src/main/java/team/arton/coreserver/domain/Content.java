package team.arton.coreserver.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Content {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @ManyToOne(targetEntity = Editor.class, fetch = FetchType.LAZY)
    @JoinColumn(name="author_id")
    private Editor editor;

    private String link;

    @OneToOne
    @JoinColumn(name = "quiz_id")
    private Quiz quiz;

    @Column(name = "thumbnail_url")
    private String thumbnailUrl;

}
