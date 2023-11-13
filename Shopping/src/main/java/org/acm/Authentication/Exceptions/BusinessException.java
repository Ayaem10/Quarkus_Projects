package org.acm.Authentication.Exceptions;

public class BusinessException extends Exception{

    int Status;


    BusinessException(){}

    public BusinessException(int status, String message){
        super(message);
        this.Status = status;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int status) {
        Status = status;
    }
}
