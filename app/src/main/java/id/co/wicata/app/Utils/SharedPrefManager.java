package id.co.wicata.app.Utils;

import android.content.Context;

public class SharedPrefManager {
    private static SharedPrefManager mInstance;
    private static Context mContext;

    private final static String SHARED_PREF_MANAGER = "wicata_shared_pref";

    private SharedPrefManager(Context context){
        this.mContext = context;
    }

    public static synchronized SharedPrefManager getInstance(Context context){
        if(mInstance == null){
            mInstance = new SharedPrefManager(context);
        }
        return mInstance;
    }
}
