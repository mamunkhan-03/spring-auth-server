package com.example.authserver.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "OAUTH_USERS")
@Getter @Setter @NoArgsConstructor
public class OauthUser {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "oauth_users_gen")
    @SequenceGenerator(name = "oauth_users_gen", sequenceName = "OAUTH_USERS_SEQ", allocationSize = 1)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    private String email;
    private String fullName;

    @Column(nullable = false)
    private boolean enabled = true;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<OauthUserAuthority> authorities;
}