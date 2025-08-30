package org.skypro.skyshop.model.controller;

import org.skypro.skyshop.model.service.NoSuchProductException;
import org.skypro.skyshop.model.service.ShopError;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ShopControllerAdvice {

    @ExceptionHandler(NoSuchProductException.class)
    public ResponseEntity<String> NoSuchProductHandler (NoSuchProductException e) {
        return ResponseEntity.status(404).body(new ShopError("Код 404", "Продукт не найден").toString());
    }
}
