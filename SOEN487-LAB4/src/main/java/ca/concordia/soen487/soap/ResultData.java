package ca.concordia.soen487.soap;

import java.io.Serializable;

public class ResultData implements Serializable {

    double num1;

    double num2;

    double result;

    String serverMessage;


    public ResultData() {
    }

    public ResultData(double num1, double num2, double result, String serverMessage) {
        this.num1          = num1;
        this.num2          = num2;
        this.result        = result;
        this.serverMessage = serverMessage;
    }

    public double getNum1() {
        return num1;
    }

    public void setNum1(double num1) {
        this.num1 = num1;
    }

    public double getNum2() {
        return num2;
    }

    public void setNum2(double num2) {
        this.num2 = num2;
    }

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }

    public String getServerMessage() {
        return serverMessage;
    }

    public void setServerMessage(String serverMessage) {
        this.serverMessage = serverMessage;
    }

    @Override
    public String toString() {
        return "ResultData{" +
                "num1=" + num1 +
                ", num2=" + num2 +
                ", result=" + result +
                ", serverMessage='" + serverMessage + '\'' +
                '}';
    }
}
