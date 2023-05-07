package com.example.IndiaMart.Dto.request;

import jakarta.persistence.Column;
import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CustomerRequest {

    String name;
    int age;
    String mobile;
    String  address;
    String email;
}
