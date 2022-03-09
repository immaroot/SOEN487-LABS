package ca.concordia.soen487.lab5.server;

import jakarta.xml.ws.WebFault;

@WebFault(name = "CustomerNotFound", targetNamespace = "ca.concordia.soen487.lab5.server")
public class CustomerNotFound extends Exception {

    public CustomerNotFound() {
        super("Customer not found.");
    }
}
