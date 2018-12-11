package sample;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.sql.SQLException;

@Path("sample")
@Stateless
public class SampleResource {

    @Inject
    @ConfigProperty(name = "message")
    private String message;

    @PersistenceContext
    EntityManager em;

    @Resource
    DataSource dataSource;

    @GET
    public Response message() throws SQLException {
        System.out.println(dataSource.getConnection().getMetaData().getDriverName());
        System.out.println(dataSource.getConnection().getMetaData().getDriverVersion());
        em.persist(new Sample("Test"));
        return Response.ok(message).build();
    }

}
