package sample;

import org.eclipse.microprofile.config.spi.ConfigSource;

import java.util.Map;
import java.util.Set;

public class AirhacksSource implements ConfigSource {

    @Override
    public Map<String, String> getProperties() {
        return null;
    }

    @Override
    public Set<String> getPropertyNames() {
        return null;
    }

    @Override
    public int getOrdinal() {
        return ConfigSource.super.getOrdinal();
    }

    @Override
    public String getValue(String propertyName) {
        return null;
    }

    @Override
    public String getName() {
        return null;
    }
}
