package com.tahayvz.publisherapp.controllers;

import com.tahayvz.publisherapp.commands.PublishingHouseCommand;
import com.tahayvz.publisherapp.services.PublishingHouseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Slf4j
@Controller
public class PublishingHouseController{

    private static final String PUBLISHINGHOUSE_PUBLISHINGHOUSEFORM_URL = "publishinghouse/publishinghouseform";
    private final PublishingHouseService publishingHouseService;

    public PublishingHouseController(PublishingHouseService publishingHouseService) {
        this.publishingHouseService = publishingHouseService;
    }
    
    @GetMapping("/publishinghouse")
    public String getIndexPage(Model model){
        model.addAttribute("publishingHouses", publishingHouseService.getPublishingHouses());

        return "index";
    }

    @GetMapping("/publishinghouse/{id}/show")
    public String showById(@PathVariable String id, Model model){

        model.addAttribute("publishingHouse", publishingHouseService.findById(new Long(id)));

        return "publishinghouse/show";
    }

    @GetMapping("publishinghouse/new")
    public String newPublishinghouse(Model model){
        model.addAttribute("publishingHouse", new PublishingHouseCommand());

        return "publishinghouse/publishingHouseForm";
    }

    @GetMapping("publishinghouse/{id}/update")
    public String updateRecipe(@PathVariable String id, Model model){
        model.addAttribute("publishingHouse", publishingHouseService.findCommandById(Long.valueOf(id)));
        return PUBLISHINGHOUSE_PUBLISHINGHOUSEFORM_URL;
    }

    @PostMapping("publishinghouse")
    public String saveOrUpdate(@Valid @ModelAttribute("publishingHouse") PublishingHouseCommand command, BindingResult bindingResult){

        if(bindingResult.hasErrors()){

            bindingResult.getAllErrors().forEach(objectError -> {
                log.debug(objectError.toString());
            });

            return PUBLISHINGHOUSE_PUBLISHINGHOUSEFORM_URL;
        }

        PublishingHouseCommand savedCommand = publishingHouseService.savePublishingHouseCommand(command);

        return "redirect:/publishinghouse/" + savedCommand.getId() + "/show";
    }
    
    @GetMapping("publishinghouse/{id}/delete")
    public String deleteById(@PathVariable String id){

        log.debug("Deleting id: " + id);

        publishingHouseService.deleteById(Long.valueOf(id));
        return "redirect:/";
    }

}
