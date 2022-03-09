package ca.concordia.soen487.lab5.server;

import jakarta.activation.DataHandler;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;

@WebService(name = "CustomerService", targetNamespace = "http://concordia.ca/soen487")
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface CustomerService {

    @WebMethod(operationName = "getCustomer")
    Customer getCustomer(@WebParam(name = "id") int id) throws CustomerNotFound;

    @WebMethod(operationName = "addCustomer")
    void addCustomer(@WebParam(name = "customer") Customer customer) throws IdAlreadyExists;

    @WebMethod(operationName = "updateCustomer")
    void updateCustomer(@WebParam(name = "id") int id,
                        @WebParam(name = "customer") Customer customer) throws CustomerNotFound;

    @WebMethod(operationName = "deleteCustomer")
    void deleteCustomer(@WebParam(name = "id") int id) throws CustomerNotFound;

    @WebMethod(operationName = "addPhoto")
    String addPhoto(@WebParam(name = "id") int id, @WebParam(name = "file") byte[] file, @WebParam(name = "mime") String mime);

    @WebMethod(operationName = "addPhotoV2")
    String addPhotoV2(@WebParam(name = "file") DataHandler file);
}
