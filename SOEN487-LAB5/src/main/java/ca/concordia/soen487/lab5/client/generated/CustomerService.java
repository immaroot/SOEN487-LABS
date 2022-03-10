
package ca.concordia.soen487.lab5.client.generated;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.ws.Action;
import jakarta.xml.ws.FaultAction;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 3.0.1
 * Generated source version: 3.0
 * 
 */
@WebService(name = "CustomerService", targetNamespace = "http://concordia.ca/soen487")
@SOAPBinding(style = SOAPBinding.Style.RPC)
@XmlSeeAlso({
    ObjectFactory.class
})
public interface CustomerService {


    /**
     * 
     * @param id
     * @return
     *     returns ca.concordia.soen487.lab5.client.generated.Customer
     * @throws CustomerNotFound_Exception
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://concordia.ca/soen487/CustomerService/getCustomerRequest", output = "http://concordia.ca/soen487/CustomerService/getCustomerResponse", fault = {
        @FaultAction(className = CustomerNotFound_Exception.class, value = "http://concordia.ca/soen487/CustomerService/getCustomer/Fault/CustomerNotFound")
    })
    public Customer getCustomer(
        @WebParam(name = "id", partName = "id")
        int id)
        throws CustomerNotFound_Exception
    ;

    /**
     * 
     * @param customer
     * @throws IdAlreadyExists_Exception
     */
    @WebMethod
    @Action(input = "http://concordia.ca/soen487/CustomerService/addCustomerRequest", output = "http://concordia.ca/soen487/CustomerService/addCustomerResponse", fault = {
        @FaultAction(className = IdAlreadyExists_Exception.class, value = "http://concordia.ca/soen487/CustomerService/addCustomer/Fault/IdAlreadyExists")
    })
    public void addCustomer(
        @WebParam(name = "customer", partName = "customer")
        Customer customer)
        throws IdAlreadyExists_Exception
    ;

    /**
     * 
     * @param id
     * @param customer
     * @throws CustomerNotFound_Exception
     */
    @WebMethod
    @Action(input = "http://concordia.ca/soen487/CustomerService/updateCustomerRequest", output = "http://concordia.ca/soen487/CustomerService/updateCustomerResponse", fault = {
        @FaultAction(className = CustomerNotFound_Exception.class, value = "http://concordia.ca/soen487/CustomerService/updateCustomer/Fault/CustomerNotFound")
    })
    public void updateCustomer(
        @WebParam(name = "id", partName = "id")
        int id,
        @WebParam(name = "customer", partName = "customer")
        Customer customer)
        throws CustomerNotFound_Exception
    ;

    /**
     * 
     * @param id
     * @throws CustomerNotFound_Exception
     */
    @WebMethod
    @Action(input = "http://concordia.ca/soen487/CustomerService/deleteCustomerRequest", output = "http://concordia.ca/soen487/CustomerService/deleteCustomerResponse", fault = {
        @FaultAction(className = CustomerNotFound_Exception.class, value = "http://concordia.ca/soen487/CustomerService/deleteCustomer/Fault/CustomerNotFound")
    })
    public void deleteCustomer(
        @WebParam(name = "id", partName = "id")
        int id)
        throws CustomerNotFound_Exception
    ;

    /**
     * 
     * @param file
     * @param mime
     * @param id
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://concordia.ca/soen487/CustomerService/addPhotoRequest", output = "http://concordia.ca/soen487/CustomerService/addPhotoResponse")
    public String addPhoto(
        @WebParam(name = "id", partName = "id")
        int id,
        @WebParam(name = "file", partName = "file")
        byte[] file,
        @WebParam(name = "mime", partName = "mime")
        String mime);

    /**
     * 
     * @param file
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://concordia.ca/soen487/CustomerService/addPhotoV2Request", output = "http://concordia.ca/soen487/CustomerService/addPhotoV2Response")
    public String addPhotoV2(
        @WebParam(name = "file", partName = "file")
        byte[] file);

}
