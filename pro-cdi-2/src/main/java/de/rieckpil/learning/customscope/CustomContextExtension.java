package de.rieckpil.learning.customscope;

import javax.enterprise.event.Observes;
import javax.enterprise.inject.spi.AfterBeanDiscovery;
import javax.enterprise.inject.spi.BeforeBeanDiscovery;
import javax.enterprise.inject.spi.Extension;

public class CustomContextExtension implements Extension {

    public void beforeBeanDiscovery(@Observes BeforeBeanDiscovery beforeBeanDiscovery) {
        System.out.println("CustomContextExtension.beforeBeanDiscovery");
        beforeBeanDiscovery.addScope(CustomScoped.class, true, false);
    }

    public void afterBeanDiscovery(@Observes AfterBeanDiscovery afterBeanDiscovery) {
        System.out.println("CustomContextExtension.afterBeanDiscovery");
        afterBeanDiscovery.addContext(new CustomContext());
    }
}
