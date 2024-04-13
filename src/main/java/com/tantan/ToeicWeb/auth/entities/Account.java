package com.tantan.ToeicWeb.auth.entities;

import com.tantan.ToeicWeb.entity.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "account")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class Account implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountId;
//    @NotBlank(message = "The name field can't be blank")
//    private String name;
//    @NotBlank(message = "The username field can't be blank")
//    @Column(unique = true)
//    private String username;
    @Column(unique = true)
    @Email(message = "Please input email proper format")
    @NotBlank(message = "The name email can't be blank")
    private String email;
    @NotBlank(message = "The name password can't be blank")
    @Size(min = 5, message = "Password has least 5 characters")
    private String password;

    @Enumerated(EnumType.STRING)
    private UserRole userRole;
    @OneToOne(mappedBy = "account")
    private RefreshToken refreshToken;
    @OneToOne
    private User user;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(
                new SimpleGrantedAuthority(userRole.name())
        );
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
