package LibraryFunctions;

import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.ListView;

/**
 * Created by Ashwin Kumar on 10-03-2015.
 */
public class EmergencyContactListGuestureRecog extends GestureDetector.SimpleOnGestureListener {
    private ListView list;
    public EmergencyContactListGuestureRecog(ListView a){
        this.list=a;
    }
    @Override
    public boolean onFling(MotionEvent e1,MotionEvent e2, float velocity_x, float velocity_y){

    }
}
