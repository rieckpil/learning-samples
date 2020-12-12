package de.rieckpil.learning;

import de.rieckpil.learning.infrastructure.CreateUserValidationGroupSequence;
import de.rieckpil.learning.infrastructure.EditUserValidationGroupSequence;
import de.rieckpil.learning.user.*;
import de.rieckpil.learning.user.web.CreateUserFormData;
import de.rieckpil.learning.user.web.EditUserFormData;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.SortDefault;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

  private final UserService service;

  public UserController(UserService service) {
    this.service = service;
  }

  @ModelAttribute("genders")
  public List<Gender> genders() {
    return List.of(Gender.MALE, Gender.FEMALE, Gender.OTHER);
  }

  @ModelAttribute("possibleRoles")
  public List<UserRole> possibleRoles() {
    return List.of(UserRole.values());
  }

  @GetMapping
  public String index(Model model,
                      @SortDefault.SortDefaults({
                        @SortDefault("userName.lastName"),
                        @SortDefault("userName.firstName")}) Pageable pageable) {
    model.addAttribute("users", service.getUsers(pageable));
    return "users/list";
  }

  @Secured("ROLE_ADMIN")
  @GetMapping("/create")
  public String createUserForm(Model model) {
    model.addAttribute("user", new CreateUserFormData());
    model.addAttribute("editMode", EditMode.CREATE);
    return "users/edit";
  }

  @Secured("ROLE_ADMIN")
  @PostMapping("/create")
  public String doCreateUser(@Validated(CreateUserValidationGroupSequence.class)
                             @ModelAttribute("user") CreateUserFormData formData,
                             BindingResult bindingResult, Model model) {
    if (bindingResult.hasErrors()) {
      model.addAttribute("editMode", EditMode.CREATE);
      return "users/edit";
    }
    service.createUser(formData.toParameters());
    return "redirect:/users";
  }


  @Secured("ROLE_ADMIN")
  @GetMapping("/{id}")
  public String editUserForm(@PathVariable("id") UserId userId,
                             Model model) {
    User user = service.getUser(userId)
      .orElseThrow(() -> new UserNotFoundException(userId));
    model.addAttribute("user", EditUserFormData.fromUser(user));
    model.addAttribute("editMode", EditMode.UPDATE);
    return "users/edit";
  }


  @Secured("ROLE_ADMIN")
  @PostMapping("/{id}")
  public String doEditUser(@PathVariable("id") UserId userId,
                           @Validated(EditUserValidationGroupSequence.class) @ModelAttribute("user") EditUserFormData formData,
                           BindingResult bindingResult,
                           Model model) {
    if (bindingResult.hasErrors()) {
      model.addAttribute("editMode", EditMode.UPDATE);
      return "users/edit";
    }

    service.editUser(userId, formData.toParameters());

    return "redirect:/users";
  }

  @Secured("ROLE_ADMIN")
  @PostMapping("/{id}/delete")
  public String doDeleteUser(@PathVariable("id") UserId userId, RedirectAttributes redirectAttributes) {
    User user = service.getUser(userId).orElseThrow(() -> new UserNotFoundException(userId));

    service.deleteUser(userId);

    redirectAttributes.addFlashAttribute("deletedUserName", user.getUserName().getFullName());
    return "redirect:/users";
  }
}
