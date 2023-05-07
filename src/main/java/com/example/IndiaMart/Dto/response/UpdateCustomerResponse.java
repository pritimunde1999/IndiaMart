package com.example.IndiaMart.Dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class UpdateCustomerResponse {

    String name;
    String mobile;
    String address;
    String email;
    int age;
    String message;

}
