package com.example.phonestore.DAO;

import com.example.phonestore.object.CustomerOrder;
import com.example.phonestore.object.PhonePost;
import com.example.phonestore.object.ResponseMessage;
import com.example.phonestore.object.SearchDateOrder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
@Repository
public class OrderDAOImp implements OrderDAO {
    WebClient client = WebClient.builder()
            .baseUrl("http://127.0.0.1:5000/")
            .defaultCookie("cookieKey", "cookieValue")
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .build();

    @Override
    public List<CustomerOrder> getListOrder(SearchDateOrder searchDateOrder) {
        System.out.println(searchDateOrder.toString());
        Mono<List<CustomerOrder>> response = client.post()
                .uri("admin/get-list-all-customer-order-by-date")
                .body(Mono.just(searchDateOrder),SearchDateOrder.class)
                .retrieve().bodyToMono(new ParameterizedTypeReference<List<CustomerOrder>>() {
                });
        List<CustomerOrder> customerOrders = response.block();
        return customerOrders;
    }
}
