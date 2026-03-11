package com.example.authserver.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.OidcScopes;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.UUID;

// For now: in-memory client. Later we will load from Oracle DB.
@Service
@RequiredArgsConstructor
public class CustomRegisteredClientRepository implements RegisteredClientRepository {

    @Override
    public void save(RegisteredClient registeredClient) {
        // Will persist to Oracle DB later
    }

    @Override
    public RegisteredClient findById(String id) {
        return buildAngularClient();
    }

    @Override
    public RegisteredClient findByClientId(String clientId) {
        if ("angular-client".equals(clientId)) {
            return buildAngularClient();
        }
        return null;
    }

    private RegisteredClient buildAngularClient() {
        return RegisteredClient.withId(UUID.randomUUID().toString())
                .clientId("angular-client")
                .clientSecret("{noop}secret")
                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
                .redirectUri("http://localhost:4200/callback")   // Angular Auth Frontend
                .redirectUri("http://localhost:4201/callback")   // Angular Client Frontend ✅
                .postLogoutRedirectUri("http://localhost:4200/logout")
                .postLogoutRedirectUri("http://localhost:4201/logout")
                .scope(OidcScopes.OPENID)
                .scope(OidcScopes.PROFILE)
                .scope(OidcScopes.EMAIL)
                .scope("read")
                .clientSettings(ClientSettings.builder()
                        .requireAuthorizationConsent(true)
                        .requireProofKey(false)
                        .build())
                .tokenSettings(TokenSettings.builder()
                        .accessTokenTimeToLive(Duration.ofHours(1))
                        .refreshTokenTimeToLive(Duration.ofDays(7))
                        .reuseRefreshTokens(false)
                        .build())
                .build();
    }
}