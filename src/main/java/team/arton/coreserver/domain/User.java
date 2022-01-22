package team.arton.coreserver.domain;

import lombok.*;
import team.arton.coreserver.exception.DuplicateException;

import javax.persistence.*;
import java.util.Objects;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String type;

    @Column
    private String serverid;

    @Column
    private String nickname;


}
