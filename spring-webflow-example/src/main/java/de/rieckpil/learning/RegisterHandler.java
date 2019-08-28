package de.rieckpil.learning;


import de.rieckpil.learning.model.BillingInfo;
import de.rieckpil.learning.model.PersonalInfo;
import de.rieckpil.learning.model.RegisterModel;
import org.springframework.binding.message.MessageBuilder;
import org.springframework.binding.message.MessageContext;
import org.springframework.stereotype.Component;

@Component
public class RegisterHandler {

    public RegisterModel init() {
        return new RegisterModel();
    }

    public void addPersonalInfo(RegisterModel registerModel, PersonalInfo personalInfo) {
        registerModel.setPersonalInfo(personalInfo);
    }

    public void addBillingInfo(RegisterModel registerModel, BillingInfo billingInfo) {
        registerModel.setBillingInfo(billingInfo);
    }

    public String saveAll(RegisterModel registerModel, MessageContext error) {
        String transitionValue = "success";

        error.addMessage(new MessageBuilder()
                .error()
                .source("registration")
                .defaultText(
                        String.format("Couldn't register user with username: %s!",
                                registerModel.getPersonalInfo().getUsername()))
                .build());
        transitionValue = "failure";

        return transitionValue;
    }

    public String validatePersonalInfo(PersonalInfo personalInfo, MessageContext error) {
        String transitionValue = "success";

        if (personalInfo.getUsername().equalsIgnoreCase("admin")) {
            error.addMessage(new MessageBuilder()
                    .error()
                    .source("username")
                    .defaultText("You are not allowed to use [admin] as the username!")
                    .build());

            transitionValue = "failure";
        }

        if (!personalInfo.getPassword().equals(personalInfo.getConfirmPassword())) {
            error.addMessage(new MessageBuilder()
                    .error()
                    .source("confirmPassword")
                    .defaultText("Password doesn't match up the confirm password!")
                    .build());

            transitionValue = "failure";
        }
        return transitionValue;
    }
}