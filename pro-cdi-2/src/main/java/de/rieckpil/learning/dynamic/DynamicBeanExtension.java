package de.rieckpil.learning.dynamic;

import javax.enterprise.event.Observes;
import javax.enterprise.inject.spi.AfterBeanDiscovery;
import javax.enterprise.inject.spi.Extension;

public class DynamicBeanExtension implements Extension {
    public void afterBean(@Observes AfterBeanDiscovery afterBeanDiscovery) {
        afterBeanDiscovery.addBean()
                .createWith(e -> "My own dynamic Bean")
                .addTransitiveTypeClosure(String.class)
                .id(DynamicBeanExtension.class.getName() + " String");
    }
}
