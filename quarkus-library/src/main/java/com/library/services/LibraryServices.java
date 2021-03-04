package com.library.services;

import com.library.services.dto.BookDto;

import java.util.List;

public interface LibraryServices {
    List<BookDto> getAllBooks();
}
