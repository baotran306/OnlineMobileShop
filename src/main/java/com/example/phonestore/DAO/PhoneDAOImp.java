package com.example.phonestore.DAO;

import com.example.phonestore.object.Phone;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Repository
public class PhoneDAOImp implements PhoneDAO {
    WebClient client = WebClient.builder()
            .baseUrl("http://127.0.0.1:5000/")
            .defaultCookie("cookieKey", "cookieValue")
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .build();

    @Override
    public List<Phone> getListPhone() {
        Mono<List<Phone>> response = client.get()
                .uri("admin/get-list-phone")
                .retrieve().bodyToMono(new ParameterizedTypeReference<List<Phone>>() {
                });
        List<Phone> phones = response.block();

        return phones;
    }
}
