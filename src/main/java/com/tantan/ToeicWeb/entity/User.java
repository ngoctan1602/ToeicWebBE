package com.tantan.ToeicWeb.entity;

import com.tantan.ToeicWeb.auth.entities.Account;
import com.tantan.ToeicWeb.entity.history.History;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String name;
    @OneToOne(mappedBy = "user")
    private Account account;
    @OneToMany(mappedBy = "user")
    private List<History> histories = new ArrayList<>();

}
