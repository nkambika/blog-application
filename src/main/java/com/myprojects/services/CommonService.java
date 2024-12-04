package com.myprojects.services;

import org.springframework.http.ResponseEntity;

public interface CommonService<T, ID> {
    ResponseEntity<?> create(T t);
    ResponseEntity<?> update(T t, ID id);
    ResponseEntity<?> delete(ID id);
    ResponseEntity<?> get(ID id);
    ResponseEntity<?> getAll();
}
