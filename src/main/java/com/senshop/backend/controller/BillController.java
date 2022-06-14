package com.senshop.backend.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.senshop.backend.model.Bill;
import com.senshop.backend.model.BillProduct;
import com.senshop.backend.model.InputBill;
import com.senshop.backend.model.InputBillProduct;
import com.senshop.backend.repository.BillRepository;

@Controller
public class BillController {
    @Autowired
    BillRepository billRepository;

    @QueryMapping
    public List<Bill> getAllBills(){
        return billRepository.findAll();
    }

    @QueryMapping
    public Bill getTheLastedBill(){
        List<Bill> bills = billRepository.findAll();
        if(bills.get(bills.size() -1) != null){
            return bills.get(bills.size() - 1);
        }
        return null;
    }

    @QueryMapping
    public List<BillProduct> getBillProductById(@Argument String _id){
        List<Bill> bill = getAllBills();
        for (int i = 0; i < bill.size(); i++) {
            if(bill.get(i).get_id().compareTo(_id) == 0){
                return bill.get(i).getProducts();
            }
        }
        return null;
    }


    @MutationMapping
    public Bill createBill(@Argument InputBill data){
        System.out.println(data);
        if(data != null){
            List<BillProduct> billProducts = new ArrayList<BillProduct>();

        
        List<InputBillProduct> tempProducts = data.getProducts();
        for (int i = 0; i < tempProducts.size(); i++) {
            billProducts.add(new BillProduct(tempProducts.get(i).getName(), tempProducts.get(i).getQuantity(), tempProducts.get(i).getPrice()));
        }
        Bill bill = new Bill();
        bill.setProducts(billProducts);
        if(data.getFirstName() != null && data.getFirstName() != ""){
            bill.setFirstName(data.getFirstName());
        }
        if(data.getLastName() != null && data.getLastName()!= ""){
            bill.setLastName(data.getLastName());
        } 
        if(data.getAddress() != null && data.getAddress() != ""){
            bill.setAddress(data.getAddress());
        }
        if(data.getNumberPhone() != null&& data.getNumberPhone() != ""){
            bill.setNumberPhone(data.getNumberPhone());
        }
        if(data.getTotal() >0){
            bill.setTotal(data.getTotal());
        }
        if(data.getAmount() >0){
            bill.setAmount(data.getAmount());
        }
        if(data.getDate() != null && data.getDate() != ""){
            bill.setDate(data.getDate());
        }
        if(data.getPaymentMethod()!= null && data.getPaymentMethod() != ""){
            bill.setPaymentMethod(data.getPaymentMethod());
        }
        
        billRepository.save(bill);

        System.out.println("Create bill successfully");
        return bill;
        }
        return null;
    } 

    @MutationMapping
    public Bill deleteBillById(@Argument String _id){
         Optional<Bill> bill = billRepository.findById(_id);
         if(bill.get() != null){
            billRepository.deleteById(_id);
            return bill.get();
         }
         return null;
    }
}
