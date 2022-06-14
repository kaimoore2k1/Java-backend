package com.senshop.backend.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;

import com.senshop.backend.model.BookedProductInput;
import com.senshop.backend.model.Product;
import com.senshop.backend.model.ProductsBooked;
import com.senshop.backend.model.User;
import com.senshop.backend.model.UserInput;
import com.senshop.backend.repository.ProductRepository;
import com.senshop.backend.repository.UserRepository;
import com.senshop.backend.controller.ProductController;

@Controller
public class UserController {
    
    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductRepository productRepository;

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
    public List<Product> getProductBooked(@Argument String username){
        User user = getUserByUsername(username);
        List<Product> allProduct = productRepository.findAll();
        List<Product> products = new ArrayList<Product>();
        if(user !=null){
            List<ProductsBooked> productBook = user.getProductsBooked();
            
            for (int i = 0; i < productBook.size(); i++) {
                for (int j = 0; j < allProduct.size(); j++) {
                    if(allProduct.get(j).get_id().compareTo(productBook.get(i).getID_Product()) == 0){
                        allProduct.get(j).setQuantity(productBook.get(i).getQuantity());
                        allProduct.get(j).setID_Product(productBook.get(i).getID_Product());
                        products.add(allProduct.get(j));
                        break;
                    }
                }
            }
            System.out.println("Get booked product successfully");
            return products;
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
            if(data.getFirstName()!= null && data.getFirstName() != ""){
                user.setFirstName(data.getFirstName());
            }
            if(data.getLastName()!= null && data.getLastName()!= ""){
                user.setLastName(data.getLastName());
            }
            if(data.getCountry()!= null && data.getCountry()!= ""){
                user.setCountry(data.getCountry());
            }
            if(data.getAddress() != null && data.getAddress() != ""){
                user.setAddress(data.getAddress());
            }
            if(data.getCity()!= null && data.getCity() != ""){
                user.setCity(data.getCity());
            }
            if(data.getNumberPhone()!= null && data.getNumberPhone() != ""){
                user.setNumberPhone(data.getNumberPhone());
            }
            if(data.getEmail() != null && data.getEmail() != ""){
                user.setEmail(data.getEmail());
            }
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
            if(data.getFirstName()!= null && data.getFirstName() != ""){
                user.setFirstName(data.getFirstName());
            }
            if(data.getLastName()!= null && data.getLastName()!= ""){
                user.setLastName(data.getLastName());
            }
            if(data.getCountry()!= null && data.getCountry()!= ""){
                user.setCountry(data.getCountry());
            }
            if(data.getAddress() != null && data.getAddress() != ""){
                user.setAddress(data.getAddress());
            }
            if(data.getCity()!= null && data.getCity() != ""){
                user.setCity(data.getCity());
            }
            if(data.getNumberPhone()!= null && data.getNumberPhone() != ""){
                user.setNumberPhone(data.getNumberPhone());
            }
            if(data.getEmail() != null && data.getEmail() != ""){
                user.setEmail(data.getEmail());
            }
            userRepository.save(user);
            System.out.println("Update user successfully");
            return user;
        }
        else{
            return null;
        }
    }

    @MutationMapping
    public User addProductToCart(@Argument String username, @Argument String _id){
        User user = getUserByUsername(username);
        if(user!= null){
            boolean flag = false;
            List<ProductsBooked> bookProducts = user.getProductsBooked();
            for (int i = 0; i < bookProducts.size(); i++) {
                if(bookProducts.get(i).getID_Product().compareTo(_id) == 0){
                    System.out.println(true);
                    flag = true;
                    bookProducts.get(i).setQuantity(bookProducts.get(i).getQuantity() + 1);
                    break;
                }
            }
            user.setProductsBooked(bookProducts);
            if(!flag){
                user.getProductsBooked().add(new ProductsBooked(_id,1));
                user.setProductsBooked(user.getProductsBooked());
            }
            System.out.println("Add product to cart successfully");
            userRepository.save(user);
            return user;
        }
        return null;
    }

    @MutationMapping
    public User updateProductCart(@Argument String username, @Argument List<BookedProductInput> data){
        User user = getUserByUsername(username);
        if(user != null){
            List<ProductsBooked> bookedProducts = new ArrayList<ProductsBooked>();
            for (int i = 0; i < data.size(); i++) {
                bookedProducts.add(new ProductsBooked(data.get(i).getID_Product(), data.get(i).getQuantity()));
            }
            user.setProductsBooked(bookedProducts);
            userRepository.save(user);
            
            return user;
        }
        return null;
    }

    @MutationMapping
    public User clearProductCart(@Argument String username){
        User user = getUserByUsername(username);
        if(user!= null){
            List<ProductsBooked> bookProducts = new ArrayList<ProductsBooked>();
            user.setProductsBooked(bookProducts);
            userRepository.save(user);
            return user;
        }
        return null;
    }
}