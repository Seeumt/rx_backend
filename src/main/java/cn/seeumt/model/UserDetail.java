//package cn.seeumt.model;
//
//import lombok.AllArgsConstructor;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.Date;
//import java.util.List;
//a
//@AllArgsConstructor
//public class UserDetail implements UserDetails {
//    private String userId;
//    private String nickName;
//    private Role role;
//
//    public UserDetail(
//            long id,
//            String nickName,
//            Role role,
////            Date lastPasswordResetDate,
//        String password) {
//        this.id = id;
//        this.nickName = nickName;
//        this.password = password;
//        this.role = role;
////        this.lastPasswordResetDate = lastPasswordResetDate;
//    }
//
//    public UserDetail(String nickName,Role role) {
//        this.nickName = nickName;
//        this.role = role;
//    }
//
//    public UserDetail(String userId, String nickName) {
//        this.userId = userId;
//        this.nickName = nickName;
//    }
//
//    //返回分配给用户的角色列表
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        List<GrantedAuthority> authorities = new ArrayList<>();
//        authorities.add(new SimpleGrantedAuthority(role.getName()));
//        return authorities;
//    }
//
//
//
//
//
//
//    /**
//     * 账户是否未过期
//     */
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    /**
//     *  账户是否未锁定
//     */
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//    /**
//     * 密码是否未过期
//     */
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    /** 账户是否激活
//     */
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
//
//
//    public Role getRole() {
//        return role;
//    }
//
//    public void setRole(Role role) {
//        this.role = role;
//    }
//
//
//    public void setUsername(String nickName) {
//        this.nickName = nickName;
//    }
//
//
//
//}
