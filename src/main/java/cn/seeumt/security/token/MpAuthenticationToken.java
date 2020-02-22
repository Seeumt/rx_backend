package cn.seeumt.security.token;

import cn.seeumt.form.MpWxUserInfo;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * @author Seeumt
 * @version 1.0
 * @date 2020/2/3 10:40
 */
public class MpAuthenticationToken extends AbstractAuthenticationToken {

    private static final long serialVersionUID = -2730294016361141263L;
    /**
     * 放认证信息
     */
    private final Object principal;

    public MpAuthenticationToken(MpWxUserInfo mpwxUserInfo) {
        super((Collection)null);
        this.principal = mpwxUserInfo;
        this.setAuthenticated(false);
    }

    public MpAuthenticationToken(Object principal,Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principal = principal;
        super.setAuthenticated(true);
    }
    @Override
    public Object getCredentials() {
        return null;
    }
    @Override
    public Object getPrincipal() {
        return this.principal;
    }
    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        if (isAuthenticated) {
            throw new IllegalArgumentException("Cannot set this token to trusted - use constructor which takes a GrantedAuthority list instead");
        } else {
            super.setAuthenticated(false);
        }
    }
    @Override
    public void eraseCredentials() {
        super.eraseCredentials();
    }
}
