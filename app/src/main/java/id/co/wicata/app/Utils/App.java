package id.co.wicata.app.Utils;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import id.co.wicata.app.R;

public class App extends Application implements Constants {
    private static App mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

    public static synchronized App getInstance(){
        return mInstance;
    }

    public boolean isConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    public void setColor(TextView textView, Context context, int color){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            textView.setTextColor(ContextCompat.getColor(context, color));
        }else{
            textView.setTextColor(context.getResources().getColor(color));
        }
    }
}
