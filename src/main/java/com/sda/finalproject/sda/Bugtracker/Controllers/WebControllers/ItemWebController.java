package com.sda.finalproject.sda.Bugtracker.Controllers.WebControllers;

import com.sda.finalproject.sda.Bugtracker.Repositories.ItemRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ItemWebController {

    private final ItemRepository itemRepository;


    public ItemWebController(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @GetMapping("/items")
    public ModelAndView items() {
        ModelAndView modelAndView = new ModelAndView("items");
        return modelAndView;
    }
}
