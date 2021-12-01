package razgriz.self.appexercise1202.helper;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class CommonHelper {

    public static boolean isConnectWithWIFI(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo nInfo = cm.getActiveNetworkInfo();
        return nInfo.getType() == ConnectivityManager.TYPE_WIFI;
    }
}
