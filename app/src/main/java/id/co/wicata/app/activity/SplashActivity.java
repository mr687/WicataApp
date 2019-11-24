
package id.co.wicata.app.activity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.co.wicata.app.R;

public class SplashActivity extends BaseActivity {
    @BindView(R.id.appLogo)
    ImageView appLogo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setNavigationBarColor(getResources().getColor(R.color.colorPrimary));
        }
        new Handler().postDelayed(() -> {
            ActivityOptions options = ActivityOptions
                    .makeSceneTransitionAnimation(
                            SplashActivity.this,
                            appLogo, getString(R.string.logo_transition));
            Intent intent = new Intent(SplashActivity.this, RegisterActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent, options.toBundle());
            SplashActivity.this.overridePendingTransition(0,0);
        }, 3000);

    }
}
