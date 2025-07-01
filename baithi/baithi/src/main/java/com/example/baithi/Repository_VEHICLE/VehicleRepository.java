package com.example.baithi.Repository_VEHICLE;


import com.example.baithi.VEHICLE_RENTAL_E.Vehicle;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepository extends PagingAndSortingRepository<Vehicle, Long> {}
