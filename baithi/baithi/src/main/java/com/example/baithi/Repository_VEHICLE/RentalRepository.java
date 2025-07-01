package com.example.baithi.Repository_VEHICLE;




import com.example.baithi.VEHICLE_RENTAL_E.Rental;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentalRepository extends PagingAndSortingRepository<Rental, Long> {}
