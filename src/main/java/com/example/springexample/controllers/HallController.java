package com.example.springexample.controllers;

import com.example.springexample.dto.HallDto;
import com.example.springexample.services.HallService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Controller
@RequestMapping("/halls")
@RequiredArgsConstructor
public class HallController {

    private final HallService hallService;

    @GetMapping
    public String getAllHalls(Model model) {
        Collection<HallDto> hallDtos = hallService.getAll();
        model.addAttribute("halls", hallDtos);
        return "hall";
    }

    @PostMapping
    public String createHall(@ModelAttribute HallDto hallDto) {
        hallService.create(hallDto);
        return "redirect:/halls";
    }

    @PostMapping("/delete/{id}")
    public String deleteHall(@PathVariable Long id) {
        hallService.delete(Math.toIntExact(id));
        return "redirect:/halls";
    }

    @GetMapping("/edit/{id}")
    public String getHallById(@PathVariable Long id, Model model) {
        HallDto hallDto = hallService.getById(Math.toIntExact(id));
        model.addAttribute("hall", hallDto);
        return "edit-hall";
    }

    @PutMapping("/edit/{id}")
    public String updateHall(@PathVariable Long id, @ModelAttribute HallDto hallDto) {
        hallDto.setId(id);
        hallService.update(hallDto);
        return "redirect:/halls";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public String handleFormSubmit(@PathVariable Long id, @ModelAttribute HallDto hallDto) {
        return updateHall(id, hallDto);
    }
}
