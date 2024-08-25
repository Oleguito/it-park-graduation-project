package ru.itpark.projectservice.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponse {
    
    public String email;
}
