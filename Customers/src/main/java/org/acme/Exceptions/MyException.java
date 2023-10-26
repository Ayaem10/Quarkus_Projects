package org.acme.Exceptions;

public class MyException extends Exception{

    int status;

    public MyException(){}

    public MyException( int status , String message ){
        super(message);
        this.status=status;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
