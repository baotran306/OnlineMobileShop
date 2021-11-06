package com.example.phonestore.DAO;

import com.example.phonestore.object.user.User;
import com.example.phonestore.object.user.ResponseLoginMessage;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Repository
public class LoginDAOImp implements LoginDAO {
    WebClient client = WebClient.builder()
            .baseUrl("http://127.0.0.1:5000/")
            .defaultCookie("cookieKey", "cookieValue")
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .build();

    @Override
    public ResponseLoginMessage getUser(User user) {
        Mono<ResponseLoginMessage> response = client.post()
                .uri("staff/login")
                .body(Mono.just(user), User.class)
                .retrieve().bodyToMono(new ParameterizedTypeReference<ResponseLoginMessage>() {
                });
        ResponseLoginMessage message = response.block();
        return message;
    }
}
