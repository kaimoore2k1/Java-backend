package com.senshop.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;

import com.senshop.backend.model.Admin;
import com.senshop.backend.model.Response;
import com.senshop.backend.repository.AdminRepository;

@Controller
public class AdminController {
    
    @Autowired
    AdminRepository adminRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @QueryMapping
    public Admin getAdminByName(@Argument String username) {
        return adminRepository.findByUsername(username);

    }
    //admin login
    @MutationMapping
    public Response register(@Argument String username, @Argument String password, @Argument String email) {
        Admin user = adminRepository.findByUsername(username);
        Response res;
        if (user.getUsername() != null) {
            res = new Response("401", "Tên đăng nhập đã tồn tại", "false");
            return res;
        } else {
            String encodePassword = passwordEncoder.encode(password);
            Admin newAccount = new Admin(username, encodePassword, email);
            res = new Response("200", "Đăng ký người dùng thành công", "true", newAccount);
            adminRepository.save(newAccount);
            return res;
        }
    }
}
