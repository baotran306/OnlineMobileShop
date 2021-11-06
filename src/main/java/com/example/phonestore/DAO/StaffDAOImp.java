package com.example.phonestore.DAO;

import com.example.phonestore.object.*;
import com.example.phonestore.object.user.User;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Repository
public class StaffDAOImp implements StaffDAO {
    WebClient client = WebClient.builder()
            .baseUrl("http://127.0.0.1:5000/")
            .defaultCookie("cookieKey", "cookieValue")
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .build();

    @Override
    public List<GetStaff> getStaffList() {

        Mono<List<GetStaff>> response = client.get()
                .uri("admin/list-staff")
                .retrieve().bodyToMono(new ParameterizedTypeReference<List<GetStaff>>() {
                });
        List<GetStaff> staffs = response.block();

        return staffs;
    }

    @Override
    public GetStaff getStaffById(String theId) {
        Mono<GetStaff> response = client.get()
                .uri("staff/show-info/"+theId)
                .retrieve().bodyToMono(new ParameterizedTypeReference<GetStaff>() {
                });
        GetStaff staffs = response.block();
        return staffs;
    }

    @Override
    public void deleteStaffById(String theId) {
        Mono<ResponseMessage> response = client.delete()
                .uri("admin/delete-staff/"+theId)
                .retrieve().bodyToMono(new ParameterizedTypeReference<ResponseMessage>() {
                });
        ResponseMessage message = response.block();
        System.out.println(message.toString());
    }

    @Override
    public void updateStaff(StaffUpdate staffUpdate) {
        Mono<ResponseMessage> response = client.post()
                .uri("admin/update-staff")
                .body(Mono.just(staffUpdate),StaffUpdate.class)
                .retrieve().bodyToMono(new ParameterizedTypeReference<ResponseMessage>() {
                });
        ResponseMessage message = response.block();
        System.out.println(message.toString());
    }

    @Override
    public void postStaff(StaffUpload staffUpload) {
        Mono<ResponseMessage> response = client.post()
                .uri("admin/insert-staff")
                .body(Mono.just(staffUpload), StaffUpload.class)
                .retrieve().bodyToMono(new ParameterizedTypeReference<ResponseMessage>() {
                });
        ResponseMessage message = response.block();
        System.out.println(message.toString());
    }

    @Override
    public void resetPassword(User user) {
        Mono<ResponseMessage> response = client.post()
                .uri("admin/reset-password")
                .body(Mono.just(user), User.class)
                .retrieve().bodyToMono(new ParameterizedTypeReference<ResponseMessage>() {
                });
        ResponseMessage message = response.block();
        System.out.println(message.toString());
    }
}
