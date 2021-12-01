package razgriz.self.appexercise1202.helper;

import android.app.AlertDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import razgriz.self.appexercise1202.R;

public class CommonHelper {

    public static boolean isConnectWithWIFI(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo nInfo = cm.getActiveNetworkInfo();
        return nInfo.getType() == ConnectivityManager.TYPE_WIFI;
    }

    public static void showActivatedDialog(Context context, boolean isDayPass, int offset) {
        Date expiredAt = new Date();
        if (isDayPass) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(expiredAt);
            calendar.add(Calendar.DATE, offset);
            calendar.set(Calendar.HOUR, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);
            expiredAt = calendar.getTime();
        } else {
            expiredAt = new Date(expiredAt.getTime() + (long) offset * 60 * 60 * 1000);
        }

        new AlertDialog.Builder(context)
                .setTitle(R.string.action_activated)
                .setMessage(context.getString(R.string.msg_pass_activated, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(expiredAt)))
                .setPositiveButton(R.string.action_ok, null)
                .show();
    }

    public static final String PASS_TYPE_DAY = "day_pass";
    public static final String PASS_TYPE_HOUR = "hour_pass";

    public static boolean isPassTypeDayPass(String passType) {
        // TODO: 2021/12/2 using enum
        return passType.equals(PASS_TYPE_DAY);
    }

    public static String getFormattedPrice(int price) {
        NumberFormat formatter = new DecimalFormat("#,###");
        return formatter.format(price);
    }

}
