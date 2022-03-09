
package ca.concordia.soen487.lab5.client.generated;

import javax.xml.namespace.QName;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlElementDecl;
import jakarta.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ca.concordia.soen487.lab5.client.generated package. 
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

    private final static QName _IdAlreadyExists_QNAME = new QName("http://concordia.ca/soen487", "IdAlreadyExists");
    private final static QName _CustomerNotFound_QNAME = new QName("ca.concordia.soen487.lab5.server", "CustomerNotFound");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ca.concordia.soen487.lab5.client.generated
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link IdAlreadyExists }
     * 
     */
    public IdAlreadyExists createIdAlreadyExists() {
        return new IdAlreadyExists();
    }

    /**
     * Create an instance of {@link Customer }
     * 
     */
    public Customer createCustomer() {
        return new Customer();
    }

    /**
     * Create an instance of {@link Address }
     * 
     */
    public Address createAddress() {
        return new Address();
    }

    /**
     * Create an instance of {@link CustomerNotFound }
     * 
     */
    public CustomerNotFound createCustomerNotFound() {
        return new CustomerNotFound();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IdAlreadyExists }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link IdAlreadyExists }{@code >}
     */
    @XmlElementDecl(namespace = "http://concordia.ca/soen487", name = "IdAlreadyExists")
    public JAXBElement<IdAlreadyExists> createIdAlreadyExists(IdAlreadyExists value) {
        return new JAXBElement<IdAlreadyExists>(_IdAlreadyExists_QNAME, IdAlreadyExists.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CustomerNotFound }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link CustomerNotFound }{@code >}
     */
    @XmlElementDecl(namespace = "ca.concordia.soen487.lab5.server", name = "CustomerNotFound")
    public JAXBElement<CustomerNotFound> createCustomerNotFound(CustomerNotFound value) {
        return new JAXBElement<CustomerNotFound>(_CustomerNotFound_QNAME, CustomerNotFound.class, null, value);
    }

}
