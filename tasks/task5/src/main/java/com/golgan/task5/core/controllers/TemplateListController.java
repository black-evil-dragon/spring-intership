package com.golgan.task5.core.controllers;

import com.golgan.task5.core.services.CRUDService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


import java.util.List;


public abstract class TemplateListController<E, ID> extends TemplateController<E, ID> {

    protected TemplateListController(CRUDService<E, ID> service) {
        super(service);
    }

    /// # Метод получения списка сущностей
    ///
    /// Если нужно задать определенную выборку,
    /// достаточно переопределить этот метод
    protected List<E> getEntities() {
        return service.findAll();
    }


    @GetMapping
    public String getAll(Model model) {
        List<E> entities = getEntities();

        return renderList(model, getTemplateName(), entities);
    }
}
