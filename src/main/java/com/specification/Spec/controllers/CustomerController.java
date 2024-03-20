package com.specification.Spec.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.specification.Spec.data.Customer;
import com.specification.Spec.repositories.CustomerRepository;

import net.kaczmarzyk.spring.data.jpa.domain.Like;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;

@RestController
@RequestMapping("/api")
public class CustomerController {

    private final CustomerRepository customerRepository;
    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        // Log the exception
        logger.error("Error occurred while processing request", e);
        // Return a ResponseEntity with an error message and status code 500 (Internal Server Error)
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
    }

    // @GetMapping
    // public List<Customer> getAllCustomers() {
    //     return customerRepository.findAll();
    // }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> findByName(
            @Spec(path="firstName", spec=Like.class) Specification<Customer> customerSpec) {
            logger.info("Received request to get product with ID: {}", customerSpec); 
        try {
            Object customer = customerRepository.findAll(customerSpec);
            return ResponseEntity.ok(customer);
        } catch (Exception e) {
            // If an exception occurs during processing, handle it and return an error response
            return handleException(e);
        }
    }
}
