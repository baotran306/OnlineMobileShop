package com.example.phonestore.DAO;

import com.example.phonestore.object.GetCustomer;
import com.example.phonestore.object.GetStaff;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Repository
public class CustomerDAOImp implements CustomerDAO {

    WebClient client = WebClient.builder()
            .baseUrl("http://127.0.0.1:5000/")
            .defaultCookie("cookieKey", "cookieValue")
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .build();

    @Override
    public List<GetCustomer> getListCustomer() {
        Mono<List<GetCustomer>> response = client.get()
                .uri("admin/list-customer")
                .retrieve().bodyToMono(new ParameterizedTypeReference<List<GetCustomer>>() {
                });
        List<GetCustomer> customers = response.block();
        return customers;
    }
}
