package hello.board.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter @Setter
@ToString(exclude = "member")
@Entity
public class Board {

    @Id @GeneratedValue
    private Long seq;

    private String title;

    private String content;

    @Column(updatable = false)
    private LocalDateTime createDate;

    @Column(updatable = false)
    private Long cnt = 0L;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID", nullable = false, updatable = false)
    private Member member;

    public void setMember(Member member) {
        this.member = member;
        member.getBoardList().add(this);
    }
}
