package com.example.baithi.Controller;


import com.example.baithi.LIBRARY_E.Book;
import com.example.baithi.Repository_Book.BookRepository;
import com.example.baithi.Repository_Book.BorrowedBookRepository;
import com.example.baithi.Repository_Book.BorrowerRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequestMapping("/")
public class LibraryController {

    @Autowired private BookRepository bookRepo;
    @Autowired private BorrowerRepository borrowerRepo;
    @Autowired private BorrowedBookRepository borrowedBookRepo;

    // ---------- BOOK CRUD ----------
    @GetMapping("/books")
    public String listBooks(@RequestParam(defaultValue = "0") int page, Model model) {
        model.addAttribute("books", bookRepo.findAll(PageRequest.of(page, 5)));
        return "book/list";
    }

    @GetMapping("/books/add")
    public String showAddBookForm(Model model) {
        model.addAttribute("book", new Book());
        return "book/add";
    }

    @PostMapping("/books/save")
    public String saveBook(@Valid @ModelAttribute Book book, BindingResult result) {
        if (result.hasErrors()) return "book/add";
        bookRepo.save(book);
        return "redirect:/books";
    }

    @GetMapping("/books/edit/{id}")
    public String showEditBook(@PathVariable Long id, Model model) {
        model.addAttribute("book", bookRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid ID")));
        return "book/add";
    }

    @GetMapping("/books/delete/{id}")
    public String deleteBook(@PathVariable Long id) {
        bookRepo.deleteById(id);
        return "redirect:/books";
    }

    // ---------- BORROWER CRUD ----------
    @GetMapping("/borrowers")
    public String listBorrowers(Model model) {
        model.addAttribute("borrowers", borrowerRepo.findAll());
        return "borrower/list";
    }

    @GetMapping("/borrowers/add")
    public String showAddBorrowerForm(Model model) {
        model.addAttribute("borrower", new Borrower());
        return "borrower/add";
    }

    @PostMapping("/borrowers/save")
    public String saveBorrower(@ModelAttribute Borrower borrower) {
        borrowerRepo.save(borrower);
        return "redirect:/borrowers";
    }

    // ---------- BORROW BOOK ----------
    @GetMapping("/borrow")
    public String listBorrowedBooks(Model model) {
        model.addAttribute("borrowedBooks", borrowedBookRepo.findAll());
        return "borrow/list";
    }

    @GetMapping("/borrow/add")
    public String showBorrowForm(Model model) {
        model.addAttribute("borrowedBook", new BorrowedBook());
        model.addAttribute("borrowers", borrowerRepo.findAll());
        model.addAttribute("books", bookRepo.findAll());
        return "borrow/add";
    }

    @PostMapping("/borrow/save")
    public String borrowBook(@ModelAttribute BorrowedBook borrowedBook) {
        Book book = borrowedBook.getBook();
        if (book.getCopiesAvailable() > 0) {
            book.setCopiesAvailable(book.getCopiesAvailable() - 1);
            bookRepo.save(book);
            borrowedBook.setBorrowDate(LocalDate.now());
            borrowedBookRepo.save(borrowedBook);
        }
        return "redirect:/borrow";
    }

    @GetMapping("/borrow/return/{id}")
    public String markAsReturned(@PathVariable Long id) {
        BorrowedBook borrowedBook = borrowedBookRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid ID"));
        borrowedBook.setReturnDate(LocalDate.now());
        Book book = borrowedBook.getBook();
        book.setCopiesAvailable(book.getCopiesAvailable() + 1);
        bookRepo.save(book);
        borrowedBookRepo.save(borrowedBook);
        return "redirect:/borrow";
    }
}
