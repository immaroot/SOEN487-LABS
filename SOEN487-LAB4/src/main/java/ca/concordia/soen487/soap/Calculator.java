package ca.concordia.soen487.soap;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface Calculator {

    @WebMethod
    double add(double num1, double num2);

    @WebMethod
    double subtract(double num1, double num2);

    @WebMethod
    double multiply(double num1, double num2);

    @WebMethod
    ResultData multiplyV2(double num1, double num2);
}
