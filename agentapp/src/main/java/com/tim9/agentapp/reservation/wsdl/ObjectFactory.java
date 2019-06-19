//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.0 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.06.19 at 11:29:18 AM CEST 
//


package com.tim9.agentapp.reservation.wsdl;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.tim9.agentapp.reservation.wsdl package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Rating_QNAME = new QName("http://tim9.com/reservations", "Rating");
    private final static QName _Reservation_QNAME = new QName("http://tim9.com/reservations", "Reservation");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.tim9.agentapp.reservation.wsdl
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Rating }
     * 
     */
    public Rating createRating() {
        return new Rating();
    }

    /**
     * Create an instance of {@link Reservation }
     * 
     */
    public Reservation createReservation() {
        return new Reservation();
    }

    /**
     * Create an instance of {@link ConfirmReservationRequest }
     * 
     */
    public ConfirmReservationRequest createConfirmReservationRequest() {
        return new ConfirmReservationRequest();
    }

    /**
     * Create an instance of {@link ConfirmReservationResponse }
     * 
     */
    public ConfirmReservationResponse createConfirmReservationResponse() {
        return new ConfirmReservationResponse();
    }

    /**
     * Create an instance of {@link CreateReservationRequest }
     * 
     */
    public CreateReservationRequest createCreateReservationRequest() {
        return new CreateReservationRequest();
    }

    /**
     * Create an instance of {@link CreateReservationResponse }
     * 
     */
    public CreateReservationResponse createCreateReservationResponse() {
        return new CreateReservationResponse();
    }

    /**
     * Create an instance of {@link DeleteReservationRequest }
     * 
     */
    public DeleteReservationRequest createDeleteReservationRequest() {
        return new DeleteReservationRequest();
    }

    /**
     * Create an instance of {@link DeleteReservationResponse }
     * 
     */
    public DeleteReservationResponse createDeleteReservationResponse() {
        return new DeleteReservationResponse();
    }

    /**
     * Create an instance of {@link GetReservationsRequest }
     * 
     */
    public GetReservationsRequest createGetReservationsRequest() {
        return new GetReservationsRequest();
    }

    /**
     * Create an instance of {@link GetReservationsRequestAgent }
     * 
     */
    public GetReservationsRequestAgent createGetReservationsRequestAgent() {
        return new GetReservationsRequestAgent();
    }

    /**
     * Create an instance of {@link GetReservationsResponse }
     * 
     */
    public GetReservationsResponse createGetReservationsResponse() {
        return new GetReservationsResponse();
    }

    /**
     * Create an instance of {@link GetReservationsResponseAgent }
     * 
     */
    public GetReservationsResponseAgent createGetReservationsResponseAgent() {
        return new GetReservationsResponseAgent();
    }

    /**
     * Create an instance of {@link UpdateReservationRequest }
     * 
     */
    public UpdateReservationRequest createUpdateReservationRequest() {
        return new UpdateReservationRequest();
    }

    /**
     * Create an instance of {@link UpdateReservationResponse }
     * 
     */
    public UpdateReservationResponse createUpdateReservationResponse() {
        return new UpdateReservationResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Rating }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Rating }{@code >}
     */
    @XmlElementDecl(namespace = "http://tim9.com/reservations", name = "Rating")
    public JAXBElement<Rating> createRating(Rating value) {
        return new JAXBElement<Rating>(_Rating_QNAME, Rating.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Reservation }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Reservation }{@code >}
     */
    @XmlElementDecl(namespace = "http://tim9.com/reservations", name = "Reservation")
    public JAXBElement<Reservation> createReservation(Reservation value) {
        return new JAXBElement<Reservation>(_Reservation_QNAME, Reservation.class, null, value);
    }

}
