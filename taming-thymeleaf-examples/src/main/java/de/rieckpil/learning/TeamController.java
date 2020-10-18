package de.rieckpil.learning;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Controller
@RequestMapping("/teams")
public class TeamController {

  @GetMapping
  public String index(Model model) {
    Set<String> teams = Set.of("Duke");
    model.addAttribute("teams", teams);
    return "teams/list";
  }

  @GetMapping("/{id}")
  public String teamInfo(@PathVariable("id") String teamId, Model model) {
    model.addAttribute("teamInfo", teamId);
    return "teams/info";
  }

  @PostMapping("/{id}")
  public String editTeamName(@PathVariable("id") String teamId) {
    return "redirect:/teams/all";
  }
}
