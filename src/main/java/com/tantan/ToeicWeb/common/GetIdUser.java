package com.tantan.ToeicWeb.common;

import com.tantan.ToeicWeb.auth.entities.Account;
import com.tantan.ToeicWeb.entity.User;
import lombok.Data;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
@Data
public class GetIdUser {
    public Long getId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Account account = (Account) authentication.getPrincipal();
        User user = account.getUser();
        return user.getUserId();
    }
}
