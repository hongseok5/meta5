package com.example.meta5.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class UserDto {
    @Data
    public static class UserBase {
        private String id;
        private String username;
        @JsonIgnore
        private String password;
        private String email;
        private boolean enabled;
        private String role;
        private String createdAt;
        private String updatedAt;
    }

    @Data
    @Builder
    public static class UserSearchByEmailCondition {
        private String email;
    }
}