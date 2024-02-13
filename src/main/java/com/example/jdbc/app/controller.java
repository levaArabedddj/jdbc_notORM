package com.example.jdbc.app;

import org.springframework.web.bind.annotation.*;

@RestController
public class controller {
    private  final  CustomerDao dao;

    public controller(CustomerDao dao) {
        this.dao = dao;
    }


    @PostMapping("/api")
    public Long createdCustomer(@RequestBody Customer customer){
        return  dao.createdCustomer(customer);
    }
    @GetMapping
    public Customer getCostomerById(@RequestParam Long id){
        return dao.getCustomer(id);
        }

        @PutMapping
    public void editCustumer(@RequestBody Customer customer){
        dao.editCustomer(customer);
        }

        @DeleteMapping
    public  void deleteCustomer(@RequestParam Long id){
        dao.delete(id);
        }

}
