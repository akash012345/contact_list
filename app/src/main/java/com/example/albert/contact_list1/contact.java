package com.example.albert.contact_list1;

        import java.io.Serializable;
/**
 * Created by akash on 06-07-2017.
 */

public class contact implements Serializable {

    private int cont_ID;
    private String cont_Name;
    private String cont_Phone;
    private String cont_Email;

    public contact(){
        setcont_ID(0);
        setcont_Name("NA");
        setcont_Phone("NA");
        setcont_Email("NA");
    }

    public contact(int cont_ID, String cont_Name,
                   String cont_Phone, String cont_Email){
        setcont_ID(cont_ID);
        setcont_Name(cont_Name);
        setcont_Phone(cont_Phone);
        setcont_Email(cont_Email);
    }

    public int getcont_ID() {
        return cont_ID;
    }

    public void setcont_ID(int cont_ID) {
        this.cont_ID = cont_ID;
    }

    public String getcont_Name() {
        return cont_Name;
    }

    public void setcont_Name(String cont_Name) {
        this.cont_Name = cont_Name;
    }

    public String getcont_Phone() {
        return cont_Phone;
    }

    public void setcont_Phone(String cont_Phone) {
        this.cont_Phone = cont_Phone;
    }

    public String getcont_Email() {
        return cont_Email;
    }

    public void setcont_Email(String cont_Email) {
        this.cont_Email = cont_Email;
    }

    public static abstract class contactAttr {
        public static final String cont_ID = "cont_ID";
        public static final String cont_NAME = "cont_Name";
        public static final String cont_PHONE = "cont_Phone";
        public static final String cont_EMAIL = "cont_Email";
    }

    @Override
    public String toString() {
        return this.cont_Name + ". " + this.cont_Phone ;
    }

}
