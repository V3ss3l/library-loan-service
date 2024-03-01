package com.library.libraryloanservice.services;

import com.library.libraryloanservice.exceptions.NotFoundException;

import java.util.List;

public interface EntityService<T> {
    public List<T> getAllEntries();

    public T getEntryById(Long id) throws NotFoundException;

    public T create(T obj);

    public void delete(Long id) throws NotFoundException;
}
