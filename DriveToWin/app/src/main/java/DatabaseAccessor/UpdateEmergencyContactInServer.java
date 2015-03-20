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
public class UpdateEmergencyContactInServer extends AsyncTask<EmergencyContacts,Void,String> {
    private Context context;
    public UpdateEmergencyContactInServer(Context context){
        this.context=context;
    }
    protected String doInBackground(EmergencyContacts...emergencyContacts){
        EmergencyContacts em_contact = emergencyContacts[0];
        try{
            HttpClient postClient = new DefaultHttpClient();
            HttpPost postRequest = new HttpPost("http://cs.okstate.edu/~tashwin/DriveToWin/updateEmergencyContacts.php");
            List<NameValuePair> list = new ArrayList<NameValuePair>();
            APIForSQLiteDB obj = new APIForSQLiteDB(this.context);
            Customer cust=obj.getCustomerInformation();
            list.add(new BasicNameValuePair("phone_num",cust.Get_PHONE_NUMBER()));
            list.add(new BasicNameValuePair("new_emergency_contact_name",em_contact.Get_NAME()));
            list.add(new BasicNameValuePair("new_emergency_contact_phone_num",em_contact.Get_E_MAIL()));
            list.add(new BasicNameValuePair("new_emergency_contact_e_mail",em_contact.Get_PHONE_NUMBER()));
            postRequest.setEntity(new UrlEncodedFormEntity(list));
            HttpResponse response = postClient.execute(postRequest);
            Log.d("From Web Service", new LibraryFunction().getTextFromHttpResponse(response.getEntity().getContent()));
        }catch(Exception e){
            Log.d("Exception", e.getMessage());
        }
        return null;
    }
}