package com.example.albert.contact_list1;

import java.io.Serializable;

/**
 * Created by akash on 07-07-2017.
 */

public class log_class implements Serializable{

    private String cont_Name;
    private String operation;
    private String timeStamp;
    
    public log_class(){
        setcont_Name("NA");
        setOperation("NA");
        setTimeStamp("NA");
    }

    public log_class(String cont_Name,String operation){
        setcont_Name(cont_Name);
        setOperation(operation);
        setTimeStamp("NA");
    }

    public log_class(String cont_Name,String operation,String timeStamp){
        setcont_Name(cont_Name);
        setOperation(operation);
        setTimeStamp(timeStamp);
    }

    public String getcont_Name(){
        return cont_Name;
    }

    public void setcont_Name(String cont_Name){
        this.cont_Name=cont_Name;

    }

    public String getOperation(){
        return operation;
    }
    
    public void setOperation(String operation){
        this.operation=operation;
    }

    public String getTimeStamp(){
        return timeStamp;
    }
    
    public void setTimeStamp(String timeStamp){
        this.timeStamp=timeStamp;
    }

    public static abstract class logAttr {
        public static final String cont_NAME = "cont_Name";
        public static final String OPERATION = "operation";
        public static final String TIMESTAMP = "timestamp";
    }

    @Override
    public String toString() {
        return this.timeStamp + ".   "+this.cont_Name + ".  " + this.operation ;
    }

}
