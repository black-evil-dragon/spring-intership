package com.golgan.task5.core.controllers.templates;

import com.golgan.task5.core.services.CRUDService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


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

    /// # Метод поиска списка сущностей по критерию
    ///
    /// @param query Строка поиска из URL
    /// @return Отфильтрованные сущности
    ///
    protected List<E> getFilteredEntities(String query) {
        return List.of();
    }


    @GetMapping
    public String getAll(@RequestParam(value = "q", required = false) String query, Model model) {
        List<E> entities;

        if (query != null && !query.isBlank()) {
            entities = getFilteredEntities(query);
        } else {
            entities = getEntities();
        }

        return renderList(model, getTemplateName(), entities);
    }
}
