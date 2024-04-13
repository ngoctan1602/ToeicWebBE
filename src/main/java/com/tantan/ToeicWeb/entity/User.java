package com.tantan.ToeicWeb.entity;

import com.tantan.ToeicWeb.auth.entities.Account;
import jakarta.persistence.*;
import lombok.*;

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
}
