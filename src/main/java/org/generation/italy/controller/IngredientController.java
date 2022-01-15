package org.generation.italy.controller;

import java.util.List;

import javax.validation.Valid;

import org.generation.italy.model.Ingredient;
import org.generation.italy.model.Pizza;
import org.generation.italy.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/ingredient")
public class IngredientController {

	@Autowired
	private IngredientService service;

	@GetMapping
	public String list(Model model, @RequestParam(name = "keyword", required = false) String keyword) {
		List<Ingredient> result;
		if (keyword != null && !keyword.isEmpty()) {
			result = service.findByKeywordSortedByName(keyword);
			model.addAttribute("Keyword", keyword);
		} else {
			result = service.findAllSortedByName();
		}
		model.addAttribute("list", result);
		return "/ingredient/list";
	}

	@GetMapping("/create")
	public String create(Model model) {
		model.addAttribute("edit", false);
		model.addAttribute("ingredient", new Ingredient());
		return "/ingredient/edit";
	}

	@PostMapping("/create")
	public String doCreate(@Valid @ModelAttribute("ingredient") Ingredient formIngredient, BindingResult bindingResult,
			Model model, RedirectAttributes redirectAttributes) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("edit", false);
			return "/ingredient/edit";
		}
		redirectAttributes.addFlashAttribute("successMessage", "Ingredient created");
		service.save(formIngredient);
		return "redirect:/ingredient";
	}

	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, Model model) {
		model.addAttribute("ingredient", service.getById(id));
		model.addAttribute("edit", true);
		return "/ingredient/edit";
	}

	@PostMapping("/edit/{id}")
	public String doUptade(@Valid @ModelAttribute("ingredient") Ingredient formIngredient, BindingResult bindingResult,
			Model model, RedirectAttributes redirectAttributes) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("edit", true);
			return "/ingredient/edit";
		}
		service.update(formIngredient);
		redirectAttributes.addFlashAttribute("successMessage", "Ingredient edited");
		return "redirect:/ingredient";
	}

	@GetMapping("/delete/{id}")
	public String doDelete(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
		service.deleteById(id);
		redirectAttributes.addFlashAttribute("successMessage", "Ingredient deleted!");
		return "redirect:/ingredient";
	}

}
