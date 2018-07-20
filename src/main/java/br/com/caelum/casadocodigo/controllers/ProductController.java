package br.com.caelum.casadocodigo.controllers;

import br.com.caelum.casadocodigo.daos.ProductDao;
import br.com.caelum.casadocodigo.infra.FileSaver;
import br.com.caelum.casadocodigo.models.BookType;
import br.com.caelum.casadocodigo.models.Product;
import br.com.caelum.casadocodigo.validators.ProductValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ProductDao productDao;

	/*
	 * @InitBinder public void addValidators(WebDataBinder webDataBinder) {
	 * webDataBinder.addValidators(new ProductValidator()); }
	 */

	@RequestMapping("form")
	public ModelAndView form(Product product) {
		ModelAndView view = new ModelAndView("product/form");
		view.addObject("tiposLivros", BookType.values());
		view.addObject("product", product);
		return view;
	}

	// @RequestMapping("{id}")
	// public ModelAndView product(){
	// ModelAndView view= new ModelAndView("products/show.jsp");
	// productDao.getByid;
	// }
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ModelAndView show(@PathVariable("id") Integer id) {
		ModelAndView modelAndView = new ModelAndView("product/show");
		modelAndView.addObject("product", productDao.getById(id));
		return modelAndView;
	}

	@Autowired
	private FileSaver fileSaver;

	@RequestMapping(method = RequestMethod.POST)
	@Transactional
	public ModelAndView save(MultipartFile summary, @Valid Product product, BindingResult bindingResult,
			RedirectAttributes redirectAttributes) {
		if (bindingResult.hasErrors()) {
			return form(product);
		}
		String webPath = fileSaver.write("uploaded-summaries", summary);
		product.setSummaryPath(webPath);
		productDao.save(product);
		redirectAttributes.addFlashAttribute("sucesso", "Produto cadastrado com sucesso");
		return new ModelAndView("redirect:products");
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView view = new ModelAndView("product/list");
		view.addObject("books", productDao.listAll());
		return view;
	}
}
