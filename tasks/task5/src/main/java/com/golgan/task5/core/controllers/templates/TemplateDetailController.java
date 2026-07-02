package com.golgan.task5.core.controllers.templates;

import com.golgan.task5.core.services.CRUDService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


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
