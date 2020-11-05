package com.tahayvz.publisherapp.controllers;

import com.tahayvz.publisherapp.services.PublishingHouseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class IndexController {

    private final PublishingHouseService publishingHouseService;

    public IndexController(PublishingHouseService publishingHouseService) {
        this.publishingHouseService = publishingHouseService;
    }

    //    @RequestMapping({"", "/", "/index"})
    @RequestMapping("/index")
    public String getIndexPage(Model model) {
        log.debug("Getting Index page");

        model.addAttribute("publishingHouses", publishingHouseService.getPublishingHouses());

        return "index";
    }


}
