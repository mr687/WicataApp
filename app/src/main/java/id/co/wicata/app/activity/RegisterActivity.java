package id.co.wicata.app.activity;

import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.co.wicata.app.R;
import id.co.wicata.app.fragment.CodeBottomSheetFragment;
import id.co.wicata.app.fragment.PasswordBottomSheetFragment;
import id.co.wicata.app.model.Response;
import id.co.wicata.app.remote.ApiClient;
import retrofit2.Call;
import retrofit2.Callback;

public class RegisterActivity
        extends BaseActivity{

    @BindView(R.id.next) TextView mNext;
    @BindView(R.id.registerEmail) EditText mEmail;

    private String currentEmail;

    private Call<Response> call;
    private ApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);

        client = new ApiClient();

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setNavigationBarColor(getResources().getColor(R.color.colorPrimary));
        }

        mNext.setOnClickListener(v-> confirmation());

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

    private void confirmation() {
        String email = currentEmail;
        call = client.getService().confirmation(email);
        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                switch (response.body().getCode()){
                    case CODE_SENT:
                        openBottomCode();
                        break;
                    case USER_EXISTS:
                        openBottomPassword();
                        break;
                    default:
                        break;
                }
                Toast.makeText(RegisterActivity.this,
                        response.body().getMessage(),
                        Toast.LENGTH_LONG)
                        .show();
            }
            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                Toast.makeText(RegisterActivity.this,
                        t.getMessage(),
                        Toast.LENGTH_LONG)
                        .show();
            }
        });
    }

    boolean validateForm() {
        return mEmail.getText().toString().trim().length() > 0;
    }

    public void openBottomCode() {
        currentEmail = mEmail.getText().toString().trim().toLowerCase();
        if (!validateForm()) mEmail.setError("Email required!");
        CodeBottomSheetFragment codeBottomSheetFragment = CodeBottomSheetFragment.newInstance();
        Bundle b = new Bundle();
        b.putString("email", currentEmail);
        codeBottomSheetFragment.setArguments(b);
        codeBottomSheetFragment.show(getSupportFragmentManager(), CodeBottomSheetFragment.TAG);
    }

    private void openBottomPassword(){
        Bundle bundle = new Bundle();
        bundle.putString("email", currentEmail);
        PasswordBottomSheetFragment passwordBottomSheetFragment = PasswordBottomSheetFragment.newInstance();
        passwordBottomSheetFragment.setArguments(bundle);
        passwordBottomSheetFragment.show(getSupportFragmentManager(), PasswordBottomSheetFragment.TAG);
    }
}
