package id.co.wicata.app.activity;

import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.co.wicata.app.R;
import id.co.wicata.app.fragment.CodeBottomSheetFragment;

public class RegisterActivity extends BaseActivity {
    @BindView(R.id.next)
    TextView mNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setNavigationBarColor(getResources().getColor(R.color.colorPrimary));
        }

        mNext.setOnClickListener(v->{
            CodeBottomSheetFragment passwordBottomSheetFragment = CodeBottomSheetFragment.newInstance();
            passwordBottomSheetFragment.setCancelable(false);
            passwordBottomSheetFragment.show(getSupportFragmentManager(), passwordBottomSheetFragment.TAG);
        });
    }
}
