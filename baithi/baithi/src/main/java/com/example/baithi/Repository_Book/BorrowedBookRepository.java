package com.example.baithi.Repository_Book;




import com.example.baithi.LIBRARY_E.BorrowedBook;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BorrowedBookRepository extends CrudRepository<BorrowedBook, Long> {}
