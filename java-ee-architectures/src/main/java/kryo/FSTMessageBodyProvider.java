package kryo;

import org.nustaq.serialization.FSTConfiguration;
import org.nustaq.serialization.FSTObjectInput;

import javax.annotation.PostConstruct;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.time.Instant;

@Provider
@Consumes("application/x-fst")
@Produces("application/x-fst")
public class FSTMessageBodyProvider implements MessageBodyWriter<Object>, MessageBodyReader<Object> {

    @PostConstruct
    public void init() {
        System.out.println("#### got initialized");
    }

    public FSTMessageBodyProvider() {
        System.out.println("--- constructor called");
    }

    @Override
    public long getSize(Object object, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return -1;
    }

    @Override
    public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return true;
    }

    @Override
    public void writeTo(Object object, Class<?> type, Type genericType,
                        Annotation[] annotations, MediaType mediaType,
                        MultivaluedMap<String, Object> httpHeaders, OutputStream entityStream)
            throws IOException, WebApplicationException {

        FSTConfiguration conf = FSTConfiguration.createDefaultConfiguration();
        conf.registerClass(Message.class);
        conf.registerClass(Instant.class);
        entityStream.write(conf.asByteArray(object));
    }


    @Override
    public boolean isReadable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return true;
    }

    @Override
    public Object readFrom(Class<Object> type, Type genericType,
                           Annotation[] annotations, MediaType mediaType,
                           MultivaluedMap<String, String> httpHeaders,
                           InputStream entityStream) throws IOException, WebApplicationException {
        try {
            FSTObjectInput in = new FSTObjectInput(entityStream);
            return in.readObject();
        } catch (Exception e) {
            throw new WebApplicationException(e);
        }
    }
}
