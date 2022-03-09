package ca.concordia.soen487.lab5.server;

import jakarta.xml.ws.Endpoint;

public class CustomerServicePublisher {

    public static void main(String[] args) {
        String endpoint = "http://localhost:8080/CustomerService";
        Endpoint.publish(endpoint, new CustomerServiceImpl());
        System.out.println("Service has started.");
        System.out.println();
    }
}
