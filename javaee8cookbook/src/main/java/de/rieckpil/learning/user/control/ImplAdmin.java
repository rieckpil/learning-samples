package de.rieckpil.learning.user.control;

import de.rieckpil.learning.user.entity.Profile;
import de.rieckpil.learning.user.entity.ProfileType;
import de.rieckpil.learning.user.entity.UserProfile;

@Profile(ProfileType.ADMIN)
public class ImplAdmin implements UserProfile {

    @Override
    public ProfileType type() {
        System.out.println("User is admin");
        return ProfileType.ADMIN;
    }
}
