package br.com.caelum.casadocodigo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.caelum.casadocodigo.daos.ProductDao;
import br.com.caelum.casadocodigo.models.BookType;
import br.com.caelum.casadocodigo.models.Product;
import br.com.caelum.casadocodigo.models.ShoppingCart;
import br.com.caelum.casadocodigo.models.ShoppingItem;

@Controller
@RequestMapping("/shopping")
public class ShoppingCartController {
	@Autowired
	private ProductDao productDAO;
	@Autowired
	private ShoppingCart shoppingCart;

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView add(Long productId, BookType bookType) {
		ShoppingItem item = createItem(productId, bookType);
		shoppingCart.add(item);
		return new ModelAndView("redirect:/products");
	}

	private ShoppingItem createItem(Long productId, BookType bookType) {
		Product product = productDAO.getById(productId);
		ShoppingItem item = new ShoppingItem(product, bookType);
		return item;
	}
}
