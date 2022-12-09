package com.m2i.backoffice.service;

import java.util.List;
import java.util.Optional;

public interface Service<T> {

    Optional<T> get(Long id);

    List<T> getAll();

    T create(T t);

    boolean update(T t);

    boolean delete(Long id);

}
