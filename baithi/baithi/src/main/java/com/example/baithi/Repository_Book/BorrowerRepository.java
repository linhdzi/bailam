package com.example.baithi.Repository_Book;




import com.example.baithi.LIBRARY_E.Borrower;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BorrowerRepository extends CrudRepository<Borrower, Long> {}
