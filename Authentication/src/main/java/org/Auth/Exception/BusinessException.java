package org.Auth.Exception;

import groovy.xml.StreamingDOMBuilder;

public class BusinessException extends Exception{

    int Status;

    public BusinessException(){}

    public BusinessException( int status , String message){
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
