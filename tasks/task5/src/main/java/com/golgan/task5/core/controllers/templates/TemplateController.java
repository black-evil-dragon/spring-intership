package com.golgan.task5.core.controllers.templates;

import com.golgan.task5.core.services.CRUDService;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/// Чисто из любопытства хочу изобрести велосипед
///
/// Имитация рукописного Django-Template-View контроллеров
public abstract class TemplateController<E, ID> {

    protected final CRUDService<E, ID> service;

    protected TemplateController(CRUDService<E, ID> service) {
        this.service = service;
    }


    protected abstract String getTemplateName();


    protected void initModel(Model model) {}


    // * ======================== RENDER ========================
    protected String render(Model model, String templateName, E entity) {
        model.addAttribute("entity", entity);
        return templateName;
    }

    protected String renderList(Model model, String templateName, List<E> entities) {
        model.addAttribute("items", entities);
        return templateName;
    }



    protected E getOrNotFound(E entity) {
        if (entity == null) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Сущность не найдена"
            );
        }
        return entity;
    }
}
