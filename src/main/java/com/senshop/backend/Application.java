package com.senshop.backend;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.JWT;
import com.senshop.backend.repository.AccountRepository;
import com.senshop.backend.repository.AdminRepository;
import com.senshop.backend.utils.JsonWebToken;
import com.senshop.backend.model.Account;
import com.senshop.backend.model.Admin;

@PropertySource(value = { "classpath:graphql/graphql.properties" })
@RestController
@SpringBootApplication
@EnableMongoRepositories
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Autowired
	HttpServletResponse response;

	@Autowired
	HttpServletRequest request;

	@Autowired
	AccountRepository accountRepository;

	@Autowired
	AdminRepository adminRepository;

	@GetMapping("/refresh_token")
	public Object RefreshTokenRouter(@CookieValue(value = "senshop") String refreshToken) {
		JsonWebToken myJWB = new JsonWebToken();
		if (refreshToken == null) {
			response.setStatus(401);
			return "{\"success\":false, \"accessToken\": null}";
		} else {
			JWT decodedUser = JWT.decode(refreshToken);
			String decodedUsername = decodedUser.getClaim("username").asString();
			JWT decodedTokenVersion = JWT.decode(decodedUser.getClaim("tokenVersion").asString());
			JWT tokenVersion = JWT.decode(decodedTokenVersion.getClaim("tokenVersion").asString());
			List<Account> users = accountRepository.findByUsername(decodedUsername);
			Optional<Account> user = users.stream().findFirst();
			if (!user.isEmpty()) {
				Admin admin = adminRepository.findByUsername(decodedUsername);
				if (admin == null) {
					response.setStatus(401);
				} else {
					if (admin.getTokenVersion() != tokenVersion.getClaim("tokenVersion").asString()) {
						response.setStatus(401);
					} else {
						myJWB.sendRefreshToken(admin.getUsername(), admin.getTokenVersion());
						String accessToken = myJWB.createAccessToken(admin.getUsername());
						return "{\"success\":true, \"accessToken\":" + "\"" + accessToken + "\"" + "}";
					}
				}
			}
			if (user.isEmpty()) {
				response.setStatus(401);
			} else {
				if (user.get().getTokenVersion() != tokenVersion.getClaim("tokenVersion").asString()) {
					response.setStatus(401);
				} else {
					myJWB.sendRefreshToken(user.get().getUsername(), user.get().getTokenVersion());
					String accessToken = myJWB.createAccessToken(user.get().getUsername());
					return "{\"success\":true, \"accessToken\":" + "\"" + accessToken + "\"" + "}";
				}
			}
			return "{\"success\":false, \"accessToken\": null}";
		}
	}

}
