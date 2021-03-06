package DatabaseAccessor;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

import LibraryFunctions.LibraryFunction;

/**
 * Created by Ashwin Kumar on 19-03-2015.
 */
public class UpdateEmergencyContactInServer extends AsyncTask<Void,Void,String> {
    private Context context;
    private EmergencyContacts old_contact;
    private EmergencyContacts new_contact;
    public UpdateEmergencyContactInServer(Context context, EmergencyContacts oldContact, EmergencyContacts newContact){
        this.context=context;
        this.old_contact=oldContact;
        this.new_contact=newContact;
    }
    protected String doInBackground(Void... params){
        try{
            HttpClient postClient = new DefaultHttpClient();
            HttpPost postRequest = new HttpPost("http://cs.okstate.edu/~tashwin/DriveToWin/updateEmergencyContacts.php");
            List<NameValuePair> list = new ArrayList<NameValuePair>();
            APIForSQLiteDB obj = new APIForSQLiteDB(this.context);
            Customer cust=obj.getCustomerInformation();
            list.add(new BasicNameValuePair("phone_num",cust.Get_PHONE_NUMBER()));
            list.add(new BasicNameValuePair("new_emergency_contact_name",new_contact.Get_NAME()));
            list.add(new BasicNameValuePair("new_emergency_contact_phone_num",new_contact.Get_E_MAIL()));
            list.add(new BasicNameValuePair("new_emergency_contact_e_mail",new_contact.Get_PHONE_NUMBER()));
            list.add(new BasicNameValuePair("old_emergency_contact_phone_num",old_contact.Get_PHONE_NUMBER()));
            list.add(new BasicNameValuePair("old_emergency_contact_name",old_contact.Get_NAME()));
            list.add(new BasicNameValuePair("old_emergency_contact_e_mail",old_contact.Get_E_MAIL()));
            postRequest.setEntity(new UrlEncodedFormEntity(list));
            HttpResponse response = postClient.execute(postRequest);
            Log.d("From Web Service", new LibraryFunction().getTextFromHttpResponse(response.getEntity().getContent()));
        }catch(Exception e){
            Log.d("Exception", e.getMessage());
        }
        return null;
    }
}