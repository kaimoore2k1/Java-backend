package com.senshop.backend;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.collect.ImmutableMap;
import com.senshop.backend.model.Account;
import com.senshop.backend.model.Product;
import com.senshop.backend.repository.AccountRepository;
import com.senshop.backend.repository.ProductRepository;

import graphql.schema.DataFetcher;

@Component
public class GraphQLDataFetchers {

        @Autowired
        AccountRepository accountRepository;

        @Autowired
        ProductRepository productRepository;

        public DataFetcher getAccountDataFetcher() {
                return dataFetchingEnvironment -> {
                        List<Account> acc = accountRepository.findByUsername("huutamcbt");
                        return acc.stream().findFirst();
                };
        }

        public DataFetcher getAllAccounts() {
                return dataFetchingEnvironment -> {
                        return accountRepository.findAll();
                };
        }

        public DataFetcher getAllProductsByCategory() {
                System.out.print("============================================================");
                System.out.println(productRepository.findByCategory("chocanh"));
                return dataFetchingEnvironment -> {
                        List<Product> acc = productRepository.findByCategory("chocanh");
                        return acc;
                };
        }
}