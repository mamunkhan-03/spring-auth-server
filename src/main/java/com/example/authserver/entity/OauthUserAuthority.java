package com.example.authserver.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "OAUTH_USER_AUTHORITIES")
@Getter @Setter @NoArgsConstructor
public class OauthUserAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "oauth_auth_gen")
    @SequenceGenerator(name = "oauth_auth_gen", sequenceName = "OAUTH_USER_AUTHORITIES_SEQ", allocationSize = 1)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private OauthUser user;

    private String authority;
}