package com.example.baithi.Repository_VEHICLE;


import com.example.baithi.VEHICLE_RENTAL_E.Customer;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends PagingAndSortingRepository<Customer, Long> {}
