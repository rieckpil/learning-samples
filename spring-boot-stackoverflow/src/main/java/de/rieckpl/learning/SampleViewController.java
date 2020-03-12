package de.rieckpl.learning;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/public")
public class SampleViewController {

  @GetMapping("/index")
  public String getIndex(Model model) {
    model.addAttribute("message", "duke");
    return "index";
  }
}
