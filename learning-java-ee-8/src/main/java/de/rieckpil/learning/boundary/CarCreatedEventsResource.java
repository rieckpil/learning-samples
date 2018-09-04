package de.rieckpil.learning.boundary;

import de.rieckpil.learning.entity.CarCreated;

import javax.annotation.PostConstruct;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import javax.enterprise.event.Observes;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.sse.OutboundSseEvent;
import javax.ws.rs.sse.Sse;
import javax.ws.rs.sse.SseBroadcaster;
import javax.ws.rs.sse.SseEventSink;
import java.util.ArrayList;
import java.util.List;

@Singleton
@Path("car-events")
public class CarCreatedEventsResource {

    @Context
    Sse sse;

    private SseBroadcaster broadcaster;

    private List<CarCreated> carCreatedList = new ArrayList<>();

    @PostConstruct
    public void init() {
        broadcaster = sse.newBroadcaster();
    }

    @GET
    @Produces(MediaType.SERVER_SENT_EVENTS)
    @Lock(LockType.READ)
    public void streamCreatedCars(@Context SseEventSink sseEventSink,
                                  @HeaderParam(HttpHeaders.LAST_EVENT_ID_HEADER) @DefaultValue("-1") int lastEventId) {
        broadcaster.register(sseEventSink);

        if (lastEventId >= 0) {
            resentMissingEvents(sseEventSink, lastEventId);
        }
    }

    private void resentMissingEvents(SseEventSink sseEventSink, int lastEventId) {
        for (int i = lastEventId; i < carCreatedList.size(); i++) {
            OutboundSseEvent event = createEvent(carCreatedList.get(i), i + 1);
            sseEventSink.send(event);
        }
    }

    @Lock
    public void onCarCreated(@Observes CarCreated carCreated) {
        broadcaster.broadcast(createEvent(carCreated, carCreatedList.size() + 1));
        carCreatedList.add(carCreated);
    }

    private OutboundSseEvent createEvent(CarCreated carCreated, int eventId) {
        return sse.newEventBuilder().id(String.valueOf(eventId)).data(carCreated.getIdentifier()).build();
    }
}
