package com.group4.group4.service.base;

import java.util.List;

public interface IBaseService <T>{
    List<T> getAll();
    T findById(long id);
    boolean insert(T obj);
    boolean delete(long id);
    boolean update(T obj);
}
