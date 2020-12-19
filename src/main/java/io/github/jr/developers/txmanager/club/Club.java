package io.github.jr.developers.txmanager.club;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "CLUB")
@Getter
@Setter
@NoArgsConstructor
public class Club {
    @Id
    @Column
    private String id;
}
