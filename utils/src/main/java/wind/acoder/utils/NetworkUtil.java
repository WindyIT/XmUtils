package wind.acoder.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkUtil {
    public static boolean isNetworkAvailable(Context context){
        ConnectivityManager networkManager;
        NetworkInfo networkInfo;
        try {
            networkManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
            networkInfo = networkManager.getActiveNetworkInfo();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return networkInfo != null && networkInfo.isConnected();
    }
}
