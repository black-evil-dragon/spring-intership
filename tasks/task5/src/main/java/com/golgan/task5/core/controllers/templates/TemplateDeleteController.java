package com.golgan.task5.core.controllers.templates;

import com.golgan.task5.core.services.CRUDService;

import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;



/// # Контроллер удаления
/// ## Контроллер удаления сущности с формой подтверждения
///
/// В нее бы еще вывести зависимы сущности, которые будут
/// затронуты каскадом или что будет установлено у них
public abstract class TemplateDeleteController<E, ID> extends TemplateController<E, ID> {

    protected TemplateDeleteController(CRUDService<E, ID> service) {
        super(service);
    }


    // Повторяется, надо подумать
    // Но вообще это разные операции, и у них может быть своя специфика, не?
    protected abstract String getSuccessUrl();

    protected String renderForm(Model model, String templateName, E entity) {
        model.addAttribute("form", entity);
        return templateName;
    }



    @GetMapping("/delete/{id}")
    public String getForm(@PathVariable ID id, Model model) {
        E entity = getOrNotFound(service.getById(id));
        return renderForm(model, getTemplateName(), entity);
    }


    @PostMapping("/delete/{id}")
    public String deleteById(@PathVariable ID id) {
        service.delete(id);

        return "redirect:" + getSuccessUrl();
    }


}
