package wind.acoder.utils;

import android.content.Context;
import android.widget.Toast;

public class ToastUtil {
    public static void sShow(Context context, String str){
        if (context == null) return;
        Toast.makeText(context, str, Toast.LENGTH_SHORT).show();
    }

    public static void lShow(Context context, String str){
        if (context == null) return;
        Toast.makeText(context, str, Toast.LENGTH_LONG).show();
    }

    public static void sShowWithGravity(Context context, String str, int gravity){
        if (context == null) return;
        Toast toast = Toast.makeText(context, str, Toast.LENGTH_SHORT);
        toast.setGravity(gravity, 0, 0);
        toast.show();
    }
}