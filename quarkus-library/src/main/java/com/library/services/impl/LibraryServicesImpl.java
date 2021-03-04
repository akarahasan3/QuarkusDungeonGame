package com.library.services.impl;

import com.library.services.LibraryServices;
import com.library.services.dto.BookDto;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
@Transactional
public class LibraryServicesImpl implements LibraryServices {
    @Override
    public List<BookDto> getAllBooks() {
        return null;
    }
}
