package ru.itpark.authservice.domain.user;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.itpark.authservice.domain.user.valueobjects.DateInfo;
import ru.itpark.authservice.domain.user.valueobjects.Language;
import ru.itpark.authservice.domain.user.converters.LanguageConverter;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "Users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class User implements UserDetails {

    @Id
    @SequenceGenerator(name = "users_id_seq", allocationSize = 1, initialValue = 1, sequenceName = "users_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_id_seq")
    private Long id;

    @Column(name = "full_name", length = 200)
    private String fullName;

    @Column(name = "email", length = 255, unique = true)
    private String email;

    @Column(name = "login", length = 255, unique = true)
    private String login;

    @Column(name = "languages", columnDefinition = "jsonb")
    @Convert(converter = LanguageConverter.class)
    private List<Language> languages;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role;

    @Embedded
    private DateInfo dateInfo;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return login;
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

