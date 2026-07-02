package com.golgan.task5.core.controllers.templates;

import com.golgan.task5.core.services.CRUDService;
import jakarta.validation.Valid;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/// Чуть посложнее, так как нам нужно еще валидировать данные и сделать так,
/// чтобы это универасально
/// @param <CR> - CreateRequest - DTO формы создания сущности
public abstract class TemplateCreateController<E, CR, ID> extends TemplateController<E, ID> {

    protected TemplateCreateController(CRUDService<E, ID> service) {
        super(service);
    }

    // Для персональных проверок, например, наличие сущности по полю
    protected void validate(CR form, BindingResult bindingResult) {};

    protected abstract void createEntity(CR createRequest);

    protected abstract CR createEmptyDto();

    protected abstract String getSuccessUrl();


    // * ======================== RENDER ========================
    protected String renderForm(Model model, String templateName, CR createRequest) {
        model.addAttribute("form", createRequest);
        return templateName;
    }


    // * ======================== API ========================
    @GetMapping("/new")
    public String getForm(Model model) {
        return renderForm(model, getTemplateName(), createEmptyDto());
    }


    @PostMapping("/new")
    public String create(@Valid @ModelAttribute("form") CR form, BindingResult bindingResult) {
        validate(form, bindingResult);

        if (bindingResult.hasErrors()) {
            // Полезная оказывается штука, автоматически
            // собирает тексты ошибок
            return getTemplateName();
        }

        createEntity(form);

        return "redirect:" + getSuccessUrl();
    }
}
