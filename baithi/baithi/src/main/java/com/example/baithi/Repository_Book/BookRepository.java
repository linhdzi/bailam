package com.example.baithi.Repository_Book;



import com.example.baithi.LIBRARY_E.Book;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends PagingAndSortingRepository<Book, Long> {
}
