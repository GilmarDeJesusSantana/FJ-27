package br.com.caelum.casadocodigo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
	@RequestMapping
	public String login(){
		return "autenticacao/login";
	}

}
