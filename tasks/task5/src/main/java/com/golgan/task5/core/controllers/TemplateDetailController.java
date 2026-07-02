package com.golgan.task5.core.controllers;

import com.golgan.task5.core.services.CRUDService;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


public abstract class TemplateDetailController<E, ID> extends TemplateController<E, ID> {

    protected TemplateDetailController(CRUDService<E, ID> service) {
        super(service);
    }


    @GetMapping("/{id}")
    public String getById(@PathVariable ID id, Model model) {
        E entity = getOrNotFound(service.getById(id));

        return render(model, getTemplateName(), entity);
    }
}
