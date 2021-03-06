package LibraryFunctions;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.MessageDigest;

/**
 * Created by Ashwin Kumar on 10-03-2015.
 */
public class LibraryFunction {
    public String getTextFromHttpResponse (InputStream in){
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
    public boolean isOnline(ConnectivityManager cm) {
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnected();
    }
    public String getHashedPwd(String pwd){
        try{
            byte[] bytesOfMessage = pwd.getBytes("UTF-8");
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] thedigest = md.digest(bytesOfMessage);
            return thedigest.toString();
        }catch(Exception e) {
            return null;
        }
    }
}
