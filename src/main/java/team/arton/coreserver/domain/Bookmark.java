package team.arton.coreserver.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Bookmark {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "content_id")
    private Long contentId;

    public Bookmark() {}

    public Bookmark(Long userId, Long contentId) {
        this.userId = userId;
        this.contentId = contentId;
    }
}
