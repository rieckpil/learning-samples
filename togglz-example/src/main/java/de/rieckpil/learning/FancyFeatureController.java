package de.rieckpil.learning;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.togglz.core.manager.FeatureManager;
import org.togglz.core.util.NamedFeature;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

@RestController
public class FancyFeatureController {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private FeatureManager featureManager;

    @GetMapping("/feature")
    public String getFancyFeature() throws SQLException {

        String result = "You can see fancy feature: ";

        if(featureManager.isActive(new NamedFeature("FOO"))){
            result += " FOO ";
        }

        if(featureManager.isActive(new NamedFeature("BAR"))){
            result += " BAR ";
        }

        ResultSet resultSet = dataSource.getConnection().createStatement().executeQuery("SELECT * FROM TOGGLZ");

        while (resultSet.next()) {
            System.out.println(resultSet.getString(1));
            System.out.println(resultSet.getInt(2));
            System.out.println(resultSet.getString(3));
            System.out.println(resultSet.getString(4));
        }

        return result;
    }
}
