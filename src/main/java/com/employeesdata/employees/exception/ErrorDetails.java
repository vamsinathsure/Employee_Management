package com.employeesdata.employees.exception;

import lombok.Getter;
import lombok.Setter;

public class ErrorDetails {

    @Getter
    @Setter
    public int errcode;
    public String errmessages;


    public ErrorDetails(int errcode,String errmessages) {
        super();
        this.errcode = errcode;
        this.errmessages = errmessages;
    }
    public ErrorDetails(){

    }
}
