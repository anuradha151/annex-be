package com.anuradha.annexservice.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CrudService<T, ID> {

    T save(T t);

    T update(T t);

    T findById(ID id);

    void delete(ID id);

    Page<T> findAll(Pageable pageable);

}
