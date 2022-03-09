package ca.concordia.soen487.lab5.server;

public class IdAlreadyExists extends Exception {

    public IdAlreadyExists() {
        super("Id already exists.");
    }
}
