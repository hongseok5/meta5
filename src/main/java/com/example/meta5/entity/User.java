package com.example.meta5.entity;

import com.example.meta5.dto.Inputable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import com.example.meta5.entity.MetaObject;

@Entity
@Getter
@Setter
@Table(name = "tb_user")
public class User implements Inputable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    private String role; // 예: "ROLE_USER", "ROLE_ADMIN"

    @Override
    public MetaObject toMetaObject() {
        return new MetaObject.Builder()
                .setObjRmk1(password)
                .setObjRmk2(username)
                .build();
    }
}