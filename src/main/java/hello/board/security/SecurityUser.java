package hello.board.security;

import hello.board.domain.Member;
import lombok.Getter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

import java.io.Serial;

@Getter
public class SecurityUser extends User {

    @Serial
    private static final long serialVersionUID = 1L;
    private final Member member;

    public SecurityUser(Member member) {
        super(member.getId(), member.getPassword(),
                AuthorityUtils.createAuthorityList(member.getRole().toString()));
        this.member = member;
    }

}
