package de.rieckpil.learning;

import java.io.PrintWriter;

import javax.resource.ResourceException;
import javax.resource.spi.ConnectionEventListener;
import javax.resource.spi.ConnectionRequestInfo;
import javax.resource.spi.LocalTransaction;
import javax.resource.spi.ManagedConnection;
import javax.resource.spi.ManagedConnectionMetaData;
import javax.security.auth.Subject;
import javax.transaction.xa.XAResource;

public class NatsStreamingManagedConnection implements ManagedConnection {

	@Override
	public Object getConnection(Subject subject, ConnectionRequestInfo cxRequestInfo) throws ResourceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void destroy() throws ResourceException {
		// TODO Auto-generated method stub

	}

	@Override
	public void cleanup() throws ResourceException {
		// TODO Auto-generated method stub

	}

	@Override
	public void associateConnection(Object connection) throws ResourceException {
		// TODO Auto-generated method stub

	}

	@Override
	public void addConnectionEventListener(ConnectionEventListener listener) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeConnectionEventListener(ConnectionEventListener listener) {
		// TODO Auto-generated method stub

	}

	@Override
	public XAResource getXAResource() throws ResourceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LocalTransaction getLocalTransaction() throws ResourceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ManagedConnectionMetaData getMetaData() throws ResourceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setLogWriter(PrintWriter out) throws ResourceException {
		// TODO Auto-generated method stub

	}

	@Override
	public PrintWriter getLogWriter() throws ResourceException {
		// TODO Auto-generated method stub
		return null;
	}

}
