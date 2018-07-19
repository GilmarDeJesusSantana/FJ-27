package br.com.caelum.casadocodigo.controllers;

import br.com.caelum.casadocodigo.daos.ProductDao;
import br.com.caelum.casadocodigo.models.BookType;
import br.com.caelum.casadocodigo.models.Product;
import br.com.caelum.casadocodigo.validators.ProductValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired private ProductDao productDao;

    /*@InitBinder
    public void addValidators(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(new ProductValidator());
    }*/

    @RequestMapping("form")
    public ModelAndView form(Product product) {
        ModelAndView view = new ModelAndView("product/form");
        view.addObject("tiposLivros", BookType.values());
        view.addObject("product", product);
        return view;
    }


    @RequestMapping(method = RequestMethod.POST)
    @Transactional
    public ModelAndView save(@Valid Product product, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        System.out.println(product);
        if (bindingResult.hasErrors()) {
            return form(product);
        }
        productDao.save(product);
        String successMessage = "Produto " + product.getTitle() + " salvo com sucesso";
        redirectAttributes.addFlashAttribute("success", successMessage);
        return new ModelAndView("redirect:products");
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView list() {
        ModelAndView view = new ModelAndView("product/list");
        view.addObject("books", productDao.listAll());
        return view;
    }
}
