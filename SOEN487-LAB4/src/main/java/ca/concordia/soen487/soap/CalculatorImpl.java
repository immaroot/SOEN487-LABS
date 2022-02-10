package ca.concordia.soen487.soap;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import jakarta.xml.ws.Endpoint;

@WebService(endpointInterface = "ca.concordia.soen487.soap.Calculator")
public class CalculatorImpl implements Calculator {

    @WebMethod
    @Override
    public double add(double num1, double num2) {
        return num1 + num2;
    }

    @WebMethod
    @Override
    public double subtract(double num1, double num2) {
        return (num1 - num2);
    }

    @WebMethod
    @Override
    public double multiply(double num1, double num2) {
        return num1 * num2;
    }

    @WebMethod
    @Override
    public ResultData multiplyV2(double num1, double num2) {
        return new ResultData(num1, num2, num1 * num2, "Hello from server");
    }

    public static void main(String[] args) {

        Calculator calculator = new CalculatorImpl();

        String address = "http://localhost:9000/calculator";
        Endpoint.publish(address, calculator);

        System.out.println("Published at: " + address);
    }
}
