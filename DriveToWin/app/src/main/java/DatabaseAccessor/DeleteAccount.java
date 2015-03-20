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
 * Created by Ashwin Kumar on 20-03-2015.
 */
public class DeleteAccount extends AsyncTask<Void,Void,String> {
    private String phone_num;
    public DeleteAccount(String phone_num){
        this.phone_num=phone_num;
    }
    protected String doInBackground(Void...params){
        try{
            HttpClient postClient = new DefaultHttpClient();
            HttpPost postRequest = new HttpPost("http://cs.okstate.edu/~tashwin/DriveToWin/deleteAllData.php");
            List<NameValuePair> list = new ArrayList<NameValuePair>();
            list.add(new BasicNameValuePair("phone_num",this.phone_num));
            postRequest.setEntity(new UrlEncodedFormEntity(list));
            HttpResponse response = postClient.execute(postRequest);
            Log.d("From Web Service", new LibraryFunction().getTextFromHttpResponse(response.getEntity().getContent()));
        }catch(Exception e){
            Log.d("Exception", e.getMessage());
        }
        return null;
    }
}