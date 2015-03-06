package DatabaseAccessor;

import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class StoreCustomerDataToServer extends AsyncTask<Customer,Void,String>{
    protected String doInBackground(Customer... cust){
        try{
            Customer customer = cust[0];
             /*Adding post request code here*/
            HttpClient postClient = new DefaultHttpClient();
            HttpPost postRequest = new HttpPost("http://cs.okstate.edu/~tashwin/DriveToWin/AddCustomerInformation.php");
            List<NameValuePair> list = new ArrayList<NameValuePair>();
            list.add(new BasicNameValuePair("first_name",customer.Get_FIRST_NAME()));
            list.add(new BasicNameValuePair("last_name",customer.Get_LAST_NAME()));
            list.add(new BasicNameValuePair("e_mail",customer.Get_E_MAIL()));
            list.add(new BasicNameValuePair("phone_num",customer.Get_PHONE_NUMBER()));
            list.add(new BasicNameValuePair("car_year",customer.Get_CAR_YEAR()));
            list.add(new BasicNameValuePair("car_make",customer.Get_CAR_MAKE()));
            list.add(new BasicNameValuePair("car_model",customer.Get_CAR_MODEL()));
            list.add(new BasicNameValuePair("vin_num",customer.Get_VIN_NUMBER()));
            list.add(new BasicNameValuePair("car_tag",customer.Get_CAR_TAG()));
            list.add(new BasicNameValuePair("passwd",customer.Get_PASSWORD()));
            list.add(new BasicNameValuePair("policy_num",customer.Get_POLICY_NUMBER()));
            list.add(new BasicNameValuePair("claim_num",customer.Get_CLAIM_NUMBER()));
            postRequest.setEntity(new UrlEncodedFormEntity(list));
            HttpResponse response = postClient.execute(postRequest);
            Log.d("From Web Service",getTextFromHttpResponse(response.getEntity().getContent()));
        }catch(Exception e){
            Log.d("Exception",e.getMessage());
        }
        return null;
    }
    private String getTextFromHttpResponse (InputStream in){
        String text="";
        BufferedReader br =new BufferedReader(new InputStreamReader(in));
        String line;
        try{
            while((line=br.readLine())!=null){
                text +=line+"\n";
            }
        }catch (Exception e){
            Log.d("Exception", e.getMessage());
        }
        return text;
    }
}
