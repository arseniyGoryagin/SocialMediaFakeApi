package com.arseniy.fakeapi.user.domain.dto;
import lombok.Data;

@Data
public class UserEditRequest {

    Long id;

    String name;
    String username;

}
