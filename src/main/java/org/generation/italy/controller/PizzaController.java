package org.generation.italy.controller;

import org.generation.italy.model.Pizza;
import org.generation.italy.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/pizza")
public class PizzaController {

	@Autowired
	private PizzaService service;

	@GetMapping
	public String list(Model model) {
		model.addAttribute("list", service.findAllSortedByName());
		return "/pizza/list";
	}

	@GetMapping("/detail/{id}")
	public String detail(@PathVariable("id") Integer id, Model model) {
		model.addAttribute("pizza", service.getById(id));
		return "/pizza/detail";
	}

	@GetMapping("/create")
	public String create(Model model) {
		model.addAttribute("pizza", new Pizza());
		return "/pizza/edit";
	}

	@PostMapping("/create")
	public String doCreate(@ModelAttribute("pizza") Pizza formPizza, Model model) {
		service.save(formPizza);
		return "redirect:/pizza";
	}

	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, Model model) {
		model.addAttribute("pizza", service.getById(id));
		return "/pizza/edit";
	}

	@PostMapping("/edit/{id}")
	public String doUpdate(@ModelAttribute("pizza") Pizza formPizza, Model model) {
		return "redirect:/pizza";
	}
	
	@GetMapping("/delete/{id}")
	public String doDelete(Model model, @PathVariable("id") Integer id) {
		service.deleteById(id);
		return "redirect:/pizza";
		
	}

}
