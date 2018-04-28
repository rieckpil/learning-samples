import org.springframework.cloud.contract.spec.Contract
import org.springframework.http.MediaType

Contract.make {
    description "should return all Reservations"
    request {
        method GET()
        url "/api/reservations"
    }
    response {
        headers {
            contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
        }
        status(200)
        body([[id: 1, reservationName: "ReservationOne", created: 1000.0 ],
              [id: 2, reservationName: "ReservationTwo", created: 1000.0]])
    }
}