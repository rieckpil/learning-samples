package de.rieckpil.learning.user.control;

import de.rieckpil.learning.user.entity.Profile;
import de.rieckpil.learning.user.entity.ProfileType;
import de.rieckpil.learning.user.entity.UserProfile;

import javax.enterprise.inject.Default;

@Profile(ProfileType.OPERATOR)
@Default
public class ImplOperator implements UserProfile {

    @Override
    public ProfileType type() {
        System.out.println("BeanValidationUser is operator");
        return ProfileType.OPERATOR;
    }
}