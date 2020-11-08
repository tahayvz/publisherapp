package com.tahayvz.publisherapp.controllers;

import com.tahayvz.publisherapp.commands.AuthorCommand;
import com.tahayvz.publisherapp.commands.BookCommand;
import com.tahayvz.publisherapp.domain.Book;
import com.tahayvz.publisherapp.services.AuthorService;
import com.tahayvz.publisherapp.services.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
public class BookController {

    private final BookService bookService;
    private final AuthorService authorService;

    public BookController(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }

    @GetMapping("/publishinghouse/{publishingHouseId}/author/{authorId}/books")
    public String listBooks(@PathVariable String authorId, @PathVariable String publishingHouseId, Model model){
        log.debug("Getting book list for author id: " + authorId);

        // use command object to avoid lazy load errors in Thymeleaf.
        model.addAttribute("author", authorService.findCommandById(Long.valueOf(authorId)));

        return "publishinghouse/author/book/list";
    }

    @GetMapping("/publishinghouse/{publishingHouseId}/author/{authorId}/book/{id}/show")
    public String showAuthorBook(@PathVariable String authorId, @PathVariable String publishingHouseId,
                                 @PathVariable String id, Model model){
        model.addAttribute("book", bookService.findByAuthorIdAndBookId(Long.valueOf(authorId), Long.valueOf(id)));

        return "publishinghouse/author/book/show";
    }

    @GetMapping("/publishinghouse/{publishingHouseId}/author/{authorId}/book/new")
    public String newAuthor(@PathVariable String authorId, @PathVariable String publishingHouseId, Model model){

        //make sure we have a good id value
        AuthorCommand authorCommand = authorService.findCommandById(Long.valueOf(authorId));
        //todo raise exception if null

        //need to return back parent id for hidden form property
        BookCommand bookCommand = new BookCommand();
        bookCommand.setAuthorId(Long.valueOf(authorId));
        bookCommand.setPublishingHouseId(Long.valueOf(publishingHouseId));
        model.addAttribute("book", bookCommand);

        return "publishinghouse/author/book/bookform";
    }

    @GetMapping("/publishinghouse/{publishingHouseId}/author/{authorId}/book/{id}/update")
    public String updateAuthorBook(@PathVariable String authorId, @PathVariable String publishingHouseId,
                                   @PathVariable String id, Model model){
        model.addAttribute("book", bookService.findByAuthorIdAndBookId(Long.valueOf(authorId), Long.valueOf(id)));

        return "publishinghouse/author/book/bookform";
    }

    @PostMapping("/publishinghouse/{publishingHouseId}/author/{authorId}/book")
    public String saveOrUpdate(@ModelAttribute BookCommand command){
        BookCommand savedCommand = bookService.saveBookCommand(command);

        log.debug("saved author id:" + savedCommand.getAuthorId());
        log.debug("saved book id:" + savedCommand.getId());
        log.debug("saved publishing house id:" + savedCommand.getPublishingHouseId());

        return "redirect:/publishinghouse/"+  savedCommand.getPublishingHouseId() +"/author/" + savedCommand.getAuthorId() + "/book/" + savedCommand.getId() + "/show";
    }

    @GetMapping("/publishinghouse/{publishingHouseId}/author/{authorId}/book/{id}/delete")
    public String deleteBook(@PathVariable String authorId, @PathVariable String publishingHouseId,
                             @PathVariable String id){

        log.debug("deleting book id:" + id);
        bookService.deleteById(Long.valueOf(authorId), Long.valueOf(id));

        return "redirect:/publishinghouse/" +  publishingHouseId  + "/author/" + authorId + "/books";
    }

    @RequestMapping("/publishinghouse/book/find")
    public String findOwners(Model model){
        model.addAttribute("book", new Book());

        return "publishinghouse/author/book/findbooks";
    }

    @GetMapping("/publishinghouse/{publishingHouseId}/author/{authorId}/book/find")
    public String processFindForm(Book book, BindingResult result, Model model, @PathVariable String publishingHouseId){
        if ((book.getName() == "")&(book.getSubname() == "")&(book.getIsbnNumber() == null)) {
            book.setName(""); // empty string signifies broadest possible search
        }
        else if((book.getName()=="")){
            book.setName(null); //empty string prevents other searches.
        }
        if(book.getSubname()==""){
            book.setSubname(null); //empty string prevents other searches
        }
        List<Book> results = bookService.findAllByNameIgnoreCaseLike("%"+ book.getName() + "%");
        List<Book> resultsSubname = bookService.findAllBySubnameIgnoreCaseLike("%"+ book.getSubname() + "%");
        List<Book> resultsIsbnNumber = bookService.findAllByIsbnNumberLike(book.getIsbnNumber());
        if (results.isEmpty()&(resultsSubname.isEmpty())&(resultsIsbnNumber.isEmpty())) {
            // no owners found
            result.rejectValue("name", "notFound", "not found");
            return "publishinghouse/author/book/findbooks";
        } else if ((results.size() == 1)|(resultsSubname.size() == 1)|(resultsIsbnNumber.size() == 1)) {
            // 1 owner found
            if(results.size() == 1){book = results.get(0);}
            if(resultsSubname.size() == 1){book = resultsSubname.get(0);}
            if(resultsIsbnNumber.size() == 1){book = resultsIsbnNumber.get(0);}
            return "redirect:/publishinghouse/" + publishingHouseId + "/author/"+ book.getAuthor().getId()+ "/book/" + book.getId() + "/show";
        } else if(results.size() > 1){
            // multiple owners found
            model.addAttribute("selections", results);
            return "publishinghouse/author/book/bookList";
        }
        else {
            // multiple owners found
            model.addAttribute("selections", resultsSubname);
            return "publishinghouse/author/book/bookList";
        }
    }
}