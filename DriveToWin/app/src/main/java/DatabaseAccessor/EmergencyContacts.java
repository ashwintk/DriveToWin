package DatabaseAccessor;

import java.io.Serializable;

public class EmergencyContacts implements Serializable{
    private static final long serialVersionUID = -7060210544600464485L;
    private String NAME;
    private String E_MAIL;
    private String PHONE_NUMBER;
    public EmergencyContacts(){
        this.NAME="";
        this.E_MAIL="";
        this.PHONE_NUMBER="";
    }
    public void Set_NAME(String str){this.NAME=str;}
    public String Get_NAME(){return this.NAME;}
    public void Set_E_MAIL(String str){this.E_MAIL=str;}
    public String Get_E_MAIL(){return this.E_MAIL;}
    public void Set_PHONE_NUMBER(String str){this.PHONE_NUMBER=str;}
    public String Get_PHONE_NUMBER(){return this.PHONE_NUMBER;}
}