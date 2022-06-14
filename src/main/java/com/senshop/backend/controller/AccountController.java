package com.senshop.backend.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.senshop.backend.model.Account;
import com.senshop.backend.model.Admin;
import com.senshop.backend.model.Response;
import com.senshop.backend.model.User;
import com.senshop.backend.repository.AccountRepository;
import com.senshop.backend.repository.AdminRepository;
import com.senshop.backend.repository.UserRepository;
import com.senshop.backend.utils.JsonWebToken;

@Controller
public class AccountController {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    AdminRepository adminRepository;

    @Autowired
    HttpServletResponse response;

    @Autowired
    HttpServletRequest request;

    @Autowired
    UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @QueryMapping
    public List<Account> accounts() {
        return accountRepository.findAll();
    }

    @QueryMapping
    public Account getAccountByName(@Argument String username) {
        List<Account> accounts = accountRepository.findByUsername(username);
        if (accounts.isEmpty()) {
            return null;
        }
        return accounts.get(0);
    }

    @MutationMapping
    public Response logout(@Argument String username) {
        List<Account> users = accountRepository.findByUsername(username);
        Optional<Account> user = users.stream().findFirst();
        Response res;
        if (!user.isEmpty()) {
            String tokenVersion = user.get().getTokenVersion();
            int tokenVersionInt = Integer.parseInt(tokenVersion);
            tokenVersionInt += 1;
            user.get().setTokenVersion(Integer.toString(tokenVersionInt));
            accountRepository.save(user.get());
            Cookie cookie = new Cookie("senshop", null);
            cookie.setMaxAge(0);
            cookie.setSecure(true);
            cookie.setHttpOnly(true);
            cookie.setPath("/refresh_token");
            response.addCookie(cookie);
            res = new Response("200", "Thành công", "true");
        } else {
            Admin admin = adminRepository.findByUsername(username);
            if (admin != null) {
                int tokenVersionInt = Integer.parseInt(admin.getTokenVersion());
                tokenVersionInt += 1;
                admin.setTokenVersion(Integer.toString(tokenVersionInt));
                adminRepository.save(admin);
                Cookie cookie = new Cookie("senshop", null);
                cookie.setMaxAge(0);
                cookie.setSecure(true);
                cookie.setHttpOnly(true);
                cookie.setPath("/refresh_token");
                response.addCookie(cookie);
                res = new Response("200", "Thành công", "true");
            } else {
                res = new Response("201", "Lỗi", "false");
            }
        }
        return res;
    }

    @MutationMapping
    public Response updateAccount(@Argument String username, @Argument String newUsername, @Argument String newPassword,
            @Argument String newEmail) {
        List<Account> users = accountRepository.findByUsername(username);
        Optional<Account> user = users.stream().findFirst();
        Response res = null;
        if (user.isEmpty()) {
            res = new Response("401", "User not found!", "false");
        } else {
            if (newUsername != null) {
                user.get().setUsername(newUsername);
            }
            if (newPassword != null) {
                user.get().setUsername(newPassword);
            }
            if (newEmail != null) {
                user.get().setUsername(newEmail);
            }
            accountRepository.save(user.get());
            res = new Response("200", "Successfully!", "true");
        }
        return res;
    }

    @MutationMapping
    public Response deleteAccount(@Argument String username) {
        List<Account> users = accountRepository.findByUsername(username);
        Optional<Account> user = users.stream().findFirst();
        Response res = null;
        if (user.isEmpty()) {
            res = new Response("401", "User not found!", "false");
        } else {
            accountRepository.delete(user.get());
            res = new Response("200", "Successfully!", "true");
        }
        return res;
    }

    @MutationMapping
    public Response deleteAccountFromFrontend(@Argument String username, @Argument String password) {
        List<Account> users = accountRepository.findByUsername(username);
        Optional<Account> user = users.stream().findFirst();
        Response res = null;
        if (user.isEmpty()) {
            res = new Response("401", "User not found!", "false");
        } else {
            boolean decodePassword = passwordEncoder.matches(password, user.get().getPassword());
            if (decodePassword) {
                accountRepository.delete(user.get());
                res = new Response("200", "Successfully!", "true");
            } else {
                res = new Response("401", "Wrong password!", "false");
            }
        }
        return res;
    }

    @MutationMapping
    public Response register(@Argument String username, @Argument String password, @Argument String email) {
        List<Account> users = accountRepository.findByUsername(username);
        Optional<Account> user = users.stream().findFirst();
        Response res;
        if (!user.isEmpty()) {
            res = new Response("401", "Tên đăng nhập đã tồn tại", "false");
            return res;
        } else {
            String encodePassword = passwordEncoder.encode(password);
            Account newAccount = new Account(username, encodePassword, email);
            res = new Response("200", "Đăng ký người dùng thành công", "true", newAccount);
            User userData = new User();
            userData.setEmail(email);
            userData.setUsername(username);
            Date date = new Date();
            userData.setDateCreate(date.toString());
            userData.setAvatarUrl("https://senshop.tech/static/media/logo.bc588d992055212e8997a878ac242940.svg");
            userRepository.save(userData);
            accountRepository.save(newAccount);
            return res;
        }
    }

    @MutationMapping
    public Response login(@Argument String username, @Argument String password) {
        List<Account> users = accountRepository.findByUsername(username);
        Optional<Account> user = users.stream().findFirst();
        Response res;
        JsonWebToken myJWB = new JsonWebToken();
        Cookie cookie = myJWB.sendRefreshToken(username,
                myJWB.createRefreshToken(username, user.get().getTokenVersion()));
        response.addCookie(cookie);
        if (user.isEmpty()) {
            res = new Response("401", "Tên đăng nhập hoặc mật khẩu không đúng!", "false");
            return res;
        }
        String getPassword = user.get().getPassword();
        boolean decodePassword = passwordEncoder.matches(password, getPassword);
        if (!decodePassword) {
            res = new Response("401", "Tên đăng nhập hoặc mật khẩu không đúng!", "false");
            return res;
        }
        String accessToken = myJWB.createAccessToken(user.get().getUsername());
        res = new Response("200", "Đăng nhập thành công!", "true", accessToken);
        return res;
    }

    @MutationMapping
    public Response updateAccountInfo(@Argument String username, @Argument Account data) {

        List<Account> accounts = accountRepository.findByUsername(username);
        Response response;
        if (accounts.isEmpty()) {
            response = new Response("401", "Không tìm thấy thông tin!", "false");
        } else {
            Account account = accounts.get(0);
            account.setEmail(data.getEmail());
            accountRepository.save(account);
            response = new Response("200", "Cập nhập thành công", "true", account);
        }
        return response;
    }

    @MutationMapping
    public Response changePassword(@Argument String username, @Argument String password, @Argument String newPassword) {
        List<Account> accounts = accountRepository.findByUsername(username);
        Response response;
        if (accounts.isEmpty()) {
            response = new Response("406", "Không tìm thấy user!", "false");
        } else {
            Account account = accounts.get(0);
            String getPassword = account.getPassword();
            boolean decodePassword = passwordEncoder.matches(password, getPassword);
            if (decodePassword) {
                String passwordEncoded = passwordEncoder.encode(newPassword);
                account.setPassword(passwordEncoded);
                accountRepository.save(account);
                response = new Response("200", "Thay đổi mật khẩu thành công!", "true", account);
            } else {
                response = new Response("200", "Mật khẩu không chính xác!", "false", account);
            }
        }
        return response;
    }
}
