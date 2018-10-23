package sample;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.config.inject.ConfigProperty;

@Path("sample")
public class SampleResource {

	@Inject
	private SampleCatalog catalog;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Sample getSample() {
		return catalog.getSample();
	}

}
