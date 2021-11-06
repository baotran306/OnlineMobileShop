package com.example.phonestore.DAO;

import com.example.phonestore.object.*;
import com.example.phonestore.object.PostColor;
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

    @Override
    public List<Color> getListColor() {
        Mono<List<Color>> response = client.get()
                .uri("admin/get-list-phone-color")
                .retrieve().bodyToMono(new ParameterizedTypeReference<List<Color>>() {
                });
        List<Color> colors = response.block();
        return colors;
    }

    @Override
    public List<Brand> getListBrand() {
        Mono<List<Brand>> response = client.get()
                .uri("admin/get-list-phone-type")
                .retrieve().bodyToMono(new ParameterizedTypeReference<List<Brand>>() {
                });
        List<Brand> brands = response.block();
        return brands;
    }

    @Override
    public void savePhone(PhonePost phone) {
        Mono<ResponseMessage> response = client.post()
                .uri("admin/insert-phone")
                .body(Mono.just(phone),PhonePost.class)
                .retrieve().bodyToMono(new ParameterizedTypeReference<ResponseMessage>() {
                });
        ResponseMessage responseMessage = response.block();
    }

    @Override
    public void updatePhone(PhonePut phone) {
        Mono<ResponseMessage> response = client.post()
                .uri("admin/update-phone")
                .body(Mono.just(phone),PhonePut.class)
                .retrieve().bodyToMono(new ParameterizedTypeReference<ResponseMessage>() {
                });
        ResponseMessage responseMessage = response.block();
    }

    @Override
    public void postColor(PostColor color) {
        Mono<ResponseMessage> response = client.post()
                .uri("admin/insert-phone-color")
                .body(Mono.just(color),PostColor.class)
                .retrieve().bodyToMono(new ParameterizedTypeReference<ResponseMessage>() {
                });
        ResponseMessage responseMessage = response.block();
    }

    @Override
    public void postBrand(PostBrand brand) {
        Mono<ResponseMessage> response = client.post()
                .uri("admin/insert-phone-type")
                .body(Mono.just(brand),PostBrand.class)
                .retrieve().bodyToMono(new ParameterizedTypeReference<ResponseMessage>() {
                });
        ResponseMessage responseMessage = response.block();
    }
}
