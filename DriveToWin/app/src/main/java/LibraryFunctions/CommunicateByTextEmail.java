package LibraryFunctions;

import android.content.Intent;
import android.telephony.SmsManager;
import android.util.Log;

public class CommunicateByTextEmail {
    private SmsManager smsManager;
    public CommunicateByTextEmail(){
        this.smsManager=SmsManager.getDefault();
    }
    public void sendText(String phone,String message){
        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phone, null, message, null, null);
            Log.d("Info", "Finished Sending Text");
        } catch (Exception e) {
            Log.d("Exception", e.getMessage());
        }
    }
    public Intent sendEmail(String to, String subject,String message){
        Intent email = new Intent(Intent.ACTION_SEND);
        email.putExtra(Intent.EXTRA_EMAIL, to);
        email.putExtra(Intent.EXTRA_SUBJECT, subject);
        email.putExtra(Intent.EXTRA_TEXT, message);
        email.setType("message/rfc822");
        return email;
    }
}
