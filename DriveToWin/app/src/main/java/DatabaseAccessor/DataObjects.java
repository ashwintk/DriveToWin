package DatabaseAccessor;
import java.security.*;
/**
 * Created by Ashwin Kumar on 25-02-2015.
 */
class Customer {
    private String DEVICE_ID;
    private String FIRST_NAME;
    private String LAST_NAME;
    private String E_MAIL;
    private String PASSWORD;
    private String PHONE_NUMBER;
    private String CAR_YEAR;
    private String CAR_MAKE;
    private String CAR_MODEL;
    private String VIN_NUMBER;
    private String CAR_TAG;

    public Customer(){
        this.DEVICE_ID="";
        this.FIRST_NAME="";
        this.LAST_NAME="";
        this.E_MAIL="";
        this.PHONE_NUMBER="";
        this.PASSWORD="";
        this.CAR_MAKE="";
        this.CAR_MODEL="";
        this.CAR_TAG="";
        this.VIN_NUMBER="";
        this.CAR_YEAR="";
    }

    public void Set_DEVICE_ID(String str){this.DEVICE_ID=str;}
    public String Get_DEVICE_ID(){return this.DEVICE_ID;}
    public void Set_FIRST_NAME(String str){this.FIRST_NAME=str;}
    public String Get_FIRST_NAME(){return this.FIRST_NAME;}
    public void Set_LAST_NAME(String str){this.LAST_NAME=str;}
    public String Get_LAST_NAME(){return this.LAST_NAME;}
    public void Set_E_MAIL(String str){this.E_MAIL=str;}
    public String Get_E_MAIL(){return this.E_MAIL;}
    public void Set_PHONE_NUMBER(String str){this.PHONE_NUMBER=str;}
    public String Get_PHONE_NUMBER(){return this.PHONE_NUMBER;}
    public void Set_CAR_MAKE(String str){this.CAR_MAKE=str;}
    public String Get_CAR_MAKE(){return this.CAR_MAKE;}
    public void Set_CAR_MODEL(String str){this.CAR_MODEL=str;}
    public String Get_CAR_MODEL(){return this.CAR_MODEL;}
    public void Set_CAR_TAG(String str){this.CAR_TAG=str;}
    public String Get_CAR_TAG(){return this.CAR_TAG;}
    public void Set_VIN_NUMBER(String str){this.VIN_NUMBER=str;}
    public String Get_VIN_NUMBER(){return this.VIN_NUMBER;}
    public void Set_CAR_YEAR(String str){this.CAR_YEAR=str;}
    public String Get_CAR_YEAR(){return this.CAR_YEAR;}
    public void Set_PASSWORD(String str){
        try{
            byte[] bytesOfMessage = str.getBytes("UTF-8");
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] thedigest = md.digest(bytesOfMessage);
            this.PASSWORD=thedigest.toString();
        }catch(Exception e){
            this.PASSWORD=null;
        }
    }
    public String Get_PASSWORD(){return this.PASSWORD;}
}

class EmergencyContacts{
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