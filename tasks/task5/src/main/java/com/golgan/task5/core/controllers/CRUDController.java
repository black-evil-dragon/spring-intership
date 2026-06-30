package com.golgan.task5.core.controllers;

import com.golgan.task5.core.services.CRUDService;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public abstract class CRUDController<E, ID> {

    protected final CRUDService<E, ID> service;

    protected CRUDController(CRUDService<E, ID> service) {
        this.service = service;
    }


    @GetMapping
    public List<E> list() {
        return service.findAll();
    }


    @GetMapping("/{id}")
    public E getById(@PathVariable ID id) {
        return service.findById(id);
    }


    @PostMapping
    public E create(E createDto) {
        return service.save(createDto);
    }


    @PatchMapping("/{id}")
    public E update(@PathVariable ID id, E updateDto) {
        return service.save(updateDto);
    }


    @DeleteMapping("/{id}")
    public void delete(@PathVariable ID id) {
        service.delete(id);
    }


    @DeleteMapping
    public void deleteMany(@RequestParam List<ID> ids) {
        service.deleteAllById(ids);
    }


}
