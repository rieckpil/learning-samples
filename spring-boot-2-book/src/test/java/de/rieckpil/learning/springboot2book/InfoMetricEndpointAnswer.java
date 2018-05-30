package de.rieckpil.learning.springboot2book;

import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

@Component
public class InfoMetricEndpointAnswer implements InfoContributor {

    @Override
    public void contribute(Info.Builder builder) {
        builder.withDetail("theAnswer", 6 * 7);
    }
}
