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
public class DocentKeep {
    @Id
    private Long id;

    @Column
    private Long user_id;

    @Column
    private Long docent_id;
}
