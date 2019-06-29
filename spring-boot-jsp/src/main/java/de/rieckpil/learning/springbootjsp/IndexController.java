package de.rieckpil.learning.springbootjsp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class IndexController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getMainView(Model model) {
        String message = "Hello Spring Boot + JSP";
        var persons = List.of(new Person("John", "Duke"), new Person("Mike", "Mueller"));
        model.addAttribute("persons", persons);
        model.addAttribute("message", message);
        return "welcome";
    }

}
