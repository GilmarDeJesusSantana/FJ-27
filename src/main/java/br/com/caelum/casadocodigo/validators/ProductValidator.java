package br.com.caelum.casadocodigo.validators;

import br.com.caelum.casadocodigo.models.Product;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.Objects;

public class ProductValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Product.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Product product = (Product) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "campo.obrigatorio");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "campo.obrigatorio");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "author", "campo.obrigatorio");

        if (Objects.isNull(product.getNumberOfPages()) || product.getNumberOfPages() <= 0) {
            errors.rejectValue("numberOfPages", "campo.invalido");
        }
    }
}
