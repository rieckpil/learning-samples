package de.rieckpil.learning;

import java.util.logging.Logger;

import javax.resource.ResourceException;
import javax.resource.spi.ActivationSpec;
import javax.resource.spi.BootstrapContext;
import javax.resource.spi.ResourceAdapter;
import javax.resource.spi.ResourceAdapterInternalException;
import javax.resource.spi.endpoint.MessageEndpointFactory;
import javax.transaction.xa.XAResource;

public class NatsStreamingAdapter implements ResourceAdapter {

	private static final Logger LOGGER = Logger.getLogger(NatsStreamingAdapter.class.getName());

	@Override
	public void start(BootstrapContext ctx) throws ResourceAdapterInternalException {
		LOGGER.info("start");
	}

	@Override
	public void stop() {
		LOGGER.info("stop");
	}

	@Override
	public void endpointActivation(MessageEndpointFactory endpointFactory, ActivationSpec spec)
			throws ResourceException {
		LOGGER.info("endpointActivation");
	}

	@Override
	public void endpointDeactivation(MessageEndpointFactory endpointFactory, ActivationSpec spec) {
		LOGGER.info("endpointDeactivation");
	}

	@Override
	public XAResource[] getXAResources(ActivationSpec[] specs) throws ResourceException {
		return null;
	}

}
