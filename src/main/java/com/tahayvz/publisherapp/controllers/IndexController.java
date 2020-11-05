package com.tahayvz.publisherapp.controllers;

import com.tahayvz.publisherapp.domain.User;
import com.tahayvz.publisherapp.services.PublishingHouseService;
import com.tahayvz.publisherapp.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class IndexController extends CommonMethods{

    private final PublishingHouseService publishingHouseService;
    private final UserService userService;

    public IndexController(PublishingHouseService publishingHouseService, UserService userService) {
        this.publishingHouseService = publishingHouseService;
        this.userService = userService;
    }

    //    @RequestMapping({"", "/", "/index"})
    @RequestMapping("/index")
    public String getIndexPage(Model model) {
        log.debug("Getting Index page");
        User activeUser = userService.findByEmail(getActiveLoggedUserEmail());

        model.addAttribute("publishingHouses", publishingHouseService.getPublishingHouses());
        model.addAttribute("user", activeUser);

        return "index";
    }


}
