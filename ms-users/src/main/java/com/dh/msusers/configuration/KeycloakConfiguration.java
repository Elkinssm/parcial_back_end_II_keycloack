package com.dh.msusers.configuration;

import org.jboss.resteasy.client.jaxrs.internal.ResteasyClientBuilderImpl;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class KeycloakConfiguration {

    @Value("${keycloak.serverUrl}")
    private String serverUrl;

    @Value("${keycloak.realm}")
    private String realm;

    @Value("${keycloak.clientId}")
    private String clientId;

    @Value("${keycloak.clientSecret}")
    private String clientSecret;

    @Value("${keycloak.user}")
    private String user;

    @Value("${keycloak.password}")
    private String password;

    @Bean
    public Keycloak buildClient() {

        return KeycloakBuilder.builder().
                serverUrl(serverUrl)
                .realm(realm)
                .clientId(clientId)
                .username(user)
                .password(password)
                .resteasyClient(new ResteasyClientBuilderImpl()
                        .connectionPoolSize(10).build())
                //.grantType(OAuth2Constants.PASSWORD)
                .build();
    }
}
