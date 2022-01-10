package org.generation.italy.controller;

import org.generation.italy.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/Pizza")
public class PizzaController {

	@Autowired
	private PizzaService service;

}
