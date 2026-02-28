package com.example.bookmanager.controller;

import com.example.bookmanager.service.BookService;
import com.example.bookmanager.entity.Book;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

@RestController
@RequiredArgsConstructor
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;

    /**
     * 全ての書籍を取得するAPI
     * GET /books
     *
     * @return 書籍一覧
     */
    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.findAllBooks();
    }

    /**
     * 指定IDの書籍を取得するAPI
     * GET /books/{id}
     *
     * @param id 書籍ID
     * @return 書籍情報
     * @throws ResponseStatusException 書籍が存在しない場合は404を返す
     */
    @GetMapping("/{id}")
    public Book getBook(@PathVariable Long id) {
        return bookService.getBookById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found"));
    }

    /**
     * 書籍を新規登録するAPI
     * POST /books
     *
     * @param book 登録する書籍情報
     * @return 登録された書籍情報
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book createBook(@RequestBody Book book) {
        return bookService.saveBook(book);
    }
}