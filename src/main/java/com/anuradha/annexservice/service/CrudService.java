package com.anuradha.annexservice.service;

public interface CrudService<T, ID> {

    T save(T t);

}
