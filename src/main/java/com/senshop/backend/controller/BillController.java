package com.senshop.backend.controller;

import java.util.List;
import java.util.Optional;

import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.senshop.backend.model.Bill;
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
