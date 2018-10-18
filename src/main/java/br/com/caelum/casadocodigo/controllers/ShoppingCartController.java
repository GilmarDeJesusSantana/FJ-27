package br.com.caelum.casadocodigo.controllers;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import br.com.caelum.casadocodigo.daos.ProductDao;
import br.com.caelum.casadocodigo.models.BookType;
import br.com.caelum.casadocodigo.models.PaymentData;
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

	@Autowired
	private RestTemplate restTemplate;

	@RequestMapping(value = "/checkout", method = RequestMethod.POST)
	public String checkout() {
		BigDecimal total = shoppingCart.getTotal();
		String uriToPay = "http://book-payment.herokuapp.com/payment";
		try {
			String response = restTemplate.postForObject(uriToPay, new PaymentData(total), String.class);
			System.out.println(response);
			return "redirect:/products";
		} catch (HttpClientErrorException exception) {
			System.out.println("Ocorreu um erro ao criar o Pagamento: " + exception.getMessage());
			return "redirect:/shopping";

		}

	}

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

	@RequestMapping(method = RequestMethod.GET)
	public String list() {
		return "shopping/items";
	}

}
