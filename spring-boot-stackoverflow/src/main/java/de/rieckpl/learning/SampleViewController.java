package de.rieckpl.learning;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/public")
public class SampleViewController {

  @GetMapping("/index")
  public String getIndex(Model model) {
    model.addAttribute("message", "duke");
    model.addAttribute("order", new Order());
    return "index";
  }

  @PostMapping("/index")
  public ModelAndView sendOrder(@ModelAttribute Order order) {
    ModelAndView modelAndView = new ModelAndView();
    modelAndView.setViewName("index");
    modelAndView.addObject("message", order.getAmountValue());
    System.out.println(order);
    return modelAndView;
  }
}
