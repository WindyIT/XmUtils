package wind.acoder.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;

public class PermissionUtil {
    private static final String TAG = PermissionUtil.class.getSimpleName();

    /**
     * 检测通知权限是否开启
     * API19/Android-4.4以下无通知权限管理 默认返回true
     * @param context
     * @return
     */
    public static boolean isNotificationEnabled(Context context){
        if (context == null) {
            Log.e(TAG, "Contet Null Error...");
            return false;
        }

        return NotificationManagerCompat.from(context).areNotificationsEnabled();
    }

    public static void goToNotiSetting(Activity activity, int requestCode){
        if (activity == null) {
            Log.e(TAG, "Activity Null Error...");
            return;
        }

        try{
            Intent intent = new Intent();
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            if(android.os.Build.VERSION.SDK_INT > Build.VERSION_CODES.N_MR1){ //21 以上，直接跳通知栏权限设置页
                intent.setAction("android.settings.APP_NOTIFICATION_SETTINGS");
                intent.putExtra("android.provider.extra.APP_PACKAGE", activity.getPackageName());
            }else if(android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){ //9 - 21，只能跳到 app 详情页
                intent.setAction("android.settings.APP_NOTIFICATION_SETTINGS");
                intent.putExtra("app_package", activity.getPackageName());
                intent.putExtra("app_uid", activity.getApplicationInfo().uid);
            }else { //9以下的 app 详情页
                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                intent.addCategory(Intent.CATEGORY_DEFAULT);
                intent.setData(Uri.parse("package:" + activity.getPackageName()));
            }
            activity.startActivityForResult(intent, requestCode);
        }catch (Exception e){   //无法跳转则跳转到系统设置页面
            goToSetting(activity, requestCode);
            e.printStackTrace();
        }
    }

    public static void goToSetting(Activity activity, int requestCode){
        if (activity == null) {
            Log.e(TAG, "Activity Null Error...");
            return;
        }
        
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.setAction(android.provider.Settings.ACTION_SETTINGS);
        activity.startActivityForResult(intent, requestCode);
    }
}
