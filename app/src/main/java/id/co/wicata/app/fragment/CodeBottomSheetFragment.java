package id.co.wicata.app.fragment;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.co.wicata.app.R;
import id.co.wicata.app.Utils.Constants;
import id.co.wicata.app.model.Response;
import id.co.wicata.app.remote.ApiClient;
import retrofit2.Call;
import retrofit2.Callback;

public class CodeBottomSheetFragment
        extends BottomSheetDialogFragment
implements View.OnClickListener, Constants {

    @BindView(R.id.countDown)
    TextView mCountDown;
    @BindView(R.id.codeVerification)
    EditText mCodeVerification;

    public static final String TAG = "ActionBottom";
    private ApiClient client;
    private Call<Response> call;
    private int counterDown = 30;
    private String currentEmail;

    public static CodeBottomSheetFragment newInstance(){
        return new CodeBottomSheetFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle b = this.getArguments();
        if (b != null){
            currentEmail = b.getString("email");
        }
        client = new ApiClient();
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        BottomSheetDialog dialog = new BottomSheetDialog(requireContext(), R.style.BottomSheetDialogTheme);
        return dialog;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.code_bottom_dialog, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mCodeVerification.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }
            @Override
            public void afterTextChanged(Editable s) {
                if(s.toString().length() > 5){
                    verification();
                }
            }
        });

        startCountDown();
    }

    private void startCountDown(){
        new CountDownTimer(30000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                NumberFormat f = new DecimalFormat("00");
                mCountDown.setText("00:" + f.format(counterDown));
                mCountDown.setEnabled(false);
                counterDown -= 1;
            }

            @Override
            public void onFinish() {
                mCountDown.setEnabled(true);
                mCountDown.setText("Resend code");
                counterDown = 30;
                mCountDown.setOnClickListener(v-> {
                    confirmation();
                    startCountDown();
                });
            }
        }.start();
    }

    private void confirmation() {
        String email = currentEmail;
        call = client.getService().confirmation(email);
        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                Toast.makeText(getActivity(),
                        response.body().getMessage(),
                        Toast.LENGTH_LONG)
                        .show();
            }
            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                Toast.makeText(getActivity(),
                        t.getMessage(),
                        Toast.LENGTH_LONG)
                        .show();
            }
        });
    }

    private void verification(){
        String code = mCodeVerification.getText().toString().trim();
        call = client.getService().verification(currentEmail, code);
        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                if(response.body().isError()){
                    switch (response.body().getCode()){
                        case USER_EXISTS:
                            dismiss();
                            break;
                        case CODE_INVALID:
                            mCodeVerification.setText("");
                            mCodeVerification.requestFocus();
                            dismiss();
                        default:
                            break;
                    }
                }else{
                    Bundle bundle = new Bundle();
                    bundle.putString("email", currentEmail);
                    PasswordBottomSheetFragment passwordBottomSheetFragment = PasswordBottomSheetFragment.newInstance();
                    passwordBottomSheetFragment.setArguments(bundle);
                    passwordBottomSheetFragment.show(getActivity().getSupportFragmentManager(), PasswordBottomSheetFragment.TAG);
                    dismiss();
                }

                Toast.makeText(getActivity(),
                        response.body().getMessage(), Toast.LENGTH_LONG)
                        .show();
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                Toast.makeText(getActivity(),
                        t.getMessage(), Toast.LENGTH_LONG)
                        .show();
            }
        });
    }

    @Override
    public void onClick(View v) {

    }
}
