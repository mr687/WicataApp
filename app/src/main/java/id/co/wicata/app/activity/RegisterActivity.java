package id.co.wicata.app.activity;

import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.co.wicata.app.R;
import id.co.wicata.app.Utils.Constants;
import id.co.wicata.app.fragment.CodeBottomSheetFragment;
import id.co.wicata.app.fragment.PasswordBottomSheetFragment;
import id.co.wicata.app.model.Response;
import id.co.wicata.app.remote.ApiService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterActivity
        extends BaseActivity{

    @BindView(R.id.next) TextView mNext;
    @BindView(R.id.registerEmail) EditText mEmail;

    private String currentEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setNavigationBarColor(getResources().getColor(R.color.colorPrimary));
        }

        mNext.setOnClickListener(v-> openBottomCode());

        mEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (count > 0){
                    mNext.setEnabled(true);
                    mNext.setAlpha(1);
                }else{
                    mNext.setEnabled(false);
                    mNext.setAlpha(0.6f);
                }
            }
            @Override
            public void afterTextChanged(Editable s) {
            }});
    }

    boolean validateForm() {
        if(mEmail.getText().toString().trim().length() > 0)
        {
            return true;
        }
        return false;
    }

    public void openBottomCode() {
        currentEmail = mEmail.getText().toString().trim().toLowerCase();
        if (!validateForm()) mEmail.setError("Email required!");
        CodeBottomSheetFragment codeBottomSheetFragment = CodeBottomSheetFragment.newInstance();
        Bundle b = new Bundle();
        b.putString("email", currentEmail);
        codeBottomSheetFragment.setArguments(b);
        codeBottomSheetFragment.show(getSupportFragmentManager(), codeBottomSheetFragment.TAG);
    }
}
