package com.tahayvz.publisherapp.controllers;

import com.tahayvz.publisherapp.commands.AuthorCommand;
import com.tahayvz.publisherapp.commands.PublishingHouseCommand;
import com.tahayvz.publisherapp.services.AuthorService;
import com.tahayvz.publisherapp.services.PublishingHouseService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
public class AuthorController {

    private final AuthorService authorService;
    private final PublishingHouseService publishingHouseService;

    public AuthorController(AuthorService authorService, PublishingHouseService publishingHouseService) {
        this.authorService = authorService;
        this.publishingHouseService = publishingHouseService;
    }

    @GetMapping("/publishinghouse/{publishingHouseId}/authors")
    public String listAuthors(@PathVariable String publishingHouseId, Model model){
        log.debug("Getting author list for publishingHouse id: " + publishingHouseId);

        // use command object to avoid lazy load errors in Thymeleaf.
        model.addAttribute("publishingHouse", publishingHouseService.findCommandById(Long.valueOf(publishingHouseId)));

        return "publishinghouse/author/authorlist";
    }

    @GetMapping("publishinghouse/{publishingHouseId}/author/{id}/show")
    public String showAuthor(@PathVariable String publishingHouseId,
                                            @PathVariable String id, Model model){
        model.addAttribute("author", authorService.findByPublishingHouseIdAndAuthorId(Long.valueOf(publishingHouseId), Long.valueOf(id)));
        return "publishinghouse/author/authorshow";
    }

    @GetMapping("publishinghouse/{publishingHouseId}/author/new")
    public String newAuthor(@PathVariable String publishingHouseId, Model model){

        //make sure we have a good id value
        PublishingHouseCommand publishingHouseCommand = publishingHouseService.findCommandById(Long.valueOf(publishingHouseId));
        //todo raise exception if null

        //need to return back parent id for hidden form property
        AuthorCommand authorCommand = new AuthorCommand();
        authorCommand.setPublishingHouseId(Long.valueOf(publishingHouseId));
        model.addAttribute("author", authorCommand);

        return "publishinghouse/author/authorform";
    }

    @GetMapping("publishinghouse/{publishingHouseId}/author/{id}/update")
    public String updateAuthor(@PathVariable String publishingHouseId,
                                              @PathVariable String id, Model model){
        model.addAttribute("author", authorService.findByPublishingHouseIdAndAuthorId(Long.valueOf(publishingHouseId), Long.valueOf(id)));

        return "publishinghouse/author/authorform";
    }

    @PostMapping("publishinghouse/{publishingHouseId}/author")
    public String saveOrUpdate(@ModelAttribute AuthorCommand command){
        AuthorCommand savedCommand = authorService.saveAuthorCommand(command);

        log.debug("saved publishingHouse id:" + savedCommand.getPublishingHouseId());
        log.debug("saved author id:" + savedCommand.getId());

        return "redirect:/publishinghouse/" + savedCommand.getPublishingHouseId() + "/author/" + savedCommand.getId() + "/show";
    }


    @GetMapping("publishinghouse/{publishingHouseId}/author/{id}/delete")
    public String deleteAuthor(@PathVariable String publishingHouseId,
                               @PathVariable String id){

        log.debug("deleting author id:" + id);
        authorService.deleteById(Long.valueOf(publishingHouseId), Long.valueOf(id));

        return "redirect:/publishinghouse/" + publishingHouseId + "/authors";
    }
}