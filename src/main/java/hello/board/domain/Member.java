package hello.board.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.LAZY;

@Getter @Setter
@ToString(exclude = "boardList")
@Entity
public class Member {

    @Id
    @Column(name = "MEMBER_ID")
    private String id;

    private String password;

    private String name;

    @Enumerated(EnumType.STRING)
    private Role role;

    private Boolean enabled;

    @OneToMany(mappedBy = "member", fetch = LAZY)
    private List<Board> boardList = new ArrayList<Board>();
}
