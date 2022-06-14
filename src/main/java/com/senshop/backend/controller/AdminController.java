package com.senshop.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;

import com.senshop.backend.model.Admin;
import com.senshop.backend.model.Response;
import com.senshop.backend.repository.AdminRepository;
import com.senshop.backend.utils.JsonWebToken;

@Controller
public class AdminController {

    @Autowired
    AdminRepository adminRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    HttpServletResponse response;

    @Autowired
    HttpServletRequest request;

    @QueryMapping
    public Admin getAdminByName(@Argument String username) {
        return adminRepository.findByUsername(username);

    }

    @MutationMapping
    public Response adminRegister(@Argument String username, @Argument String password, @Argument String email) {
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

    @MutationMapping
    public Response adminLogin(@Argument String username, @Argument String password) {
        Admin user = adminRepository.findByUsername(username);
        Response res = null;
        JsonWebToken myJWB = new JsonWebToken();
        Cookie cookie = myJWB.sendRefreshToken(username,
                myJWB.createRefreshToken(username, user.getTokenVersion()));
        response.addCookie(cookie);
        if (user.getUsername() == null) {
            res = new Response("401", "Tên đăng nhập hoặc mật khẩu không đúng!", "false");
        }
        boolean decodePassword = passwordEncoder.matches(password, user.getPassword());
        if(!decodePassword) {
            res = new Response("401", "Tên đăng nhập hoặc mật khẩu không đúng!", "false");
        }
        String accessToken = myJWB.createAccessToken(user.getUsername());
        res = new Response("200", "Đăng nhập thành công!", "true", accessToken);
        return res;
    }
}
