package com.senshop.backend.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;

import com.senshop.backend.model.ProductsBooked;
import com.senshop.backend.model.User;
import com.senshop.backend.model.UserInput;
import com.senshop.backend.repository.UserRepository;

@Controller
public class UserController {
    
    @Autowired
    UserRepository userRepository;

    @QueryMapping
    public List<User> getAllUsers() {
        
        return userRepository.findAll();
    }

    @QueryMapping
    public User getUserByUsername(@Argument String username){
        List<User> users =  userRepository.findAll();
        
        for (int i = 0; i < users.size(); i++) {
            
            if(users.get(i).getUsername().compareTo(username) == 0){
                System.out.println(users.get(i).get_id());
                return users.get(i);
            }    
        }
        return null;
    }

    @QueryMapping
    public List<ProductsBooked> getProductBooked(@Argument String username){
        User user = getUserByUsername(username);
        if(user !=null){
            return user.getProductsBooked();
        }
        return null;
    } 

    
    @MutationMapping
    public User deleteUser(@Argument String username){
        User user = getUserByUsername(username);
        if(user != null){
            userRepository.delete(user);
            System.out.println("Delete user successfully");
            return user;
        }
        return null;
    }

    @MutationMapping
    public User createOrUpdateUser(@Argument String username, @Argument UserInput data){
        User user = getUserByUsername(username);
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
        Date date = new Date();  
        
        if(user != null){
            user.setFirstName(data.getFirstName());;
            user.setLastName(data.getLastName());
            user.setCountry(data.getCountry());
            user.setAddress(data.getAddress());
            user.setCity(data.getCity());
            user.setNumberPhone(data.getNumberPhone());
            user.setEmail(data.getEmail());
            userRepository.save(user);
            System.out.println("Update user successfully");
            return user;
        }
        else{
            User newUser = new User(data.getUsername(), data.getFirstName(), data.getLastName(),
            data.getCountry(), data.getAddress(), data.getCity(), data.getNumberPhone(), data.getEmail(), new String(formatter.format(date)), "https://senshop.tech/static/media/logo.bc588d992055212e8997a878ac242940.svg");
            userRepository.save(newUser);
            System.out.println("Create user successfully");
            return newUser;
        }
        
    }

    @MutationMapping
    public User updateUser(@Argument String username,@Argument UserInput data){
        User user = getUserByUsername(username);
        
        if(user != null){
            user.setFirstName(data.getFirstName());;
            user.setLastName(data.getLastName());
            user.setCountry(data.getCountry());
            user.setAddress(data.getAddress());
            user.setCity(data.getCity());
            user.setNumberPhone(data.getNumberPhone());
            user.setEmail(data.getEmail());
            userRepository.save(user);
            System.out.println("Update user successfully");
            return user;
        }
        else{
            return null;
        }
    }

}
