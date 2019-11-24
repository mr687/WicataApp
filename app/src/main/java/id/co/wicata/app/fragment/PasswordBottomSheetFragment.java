package id.co.wicata.app.fragment;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
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

import butterknife.BindView;
import butterknife.ButterKnife;
import id.co.wicata.app.R;
import id.co.wicata.app.activity.MainActivity;
import id.co.wicata.app.activity.RegisterActivity;
import id.co.wicata.app.model.Response;
import id.co.wicata.app.remote.ApiClient;
import retrofit2.Call;
import retrofit2.Callback;

public class PasswordBottomSheetFragment extends BottomSheetDialogFragment {
    @BindView(R.id.passwordNext)
    TextView mPasswordNext;
    @BindView(R.id.password)
    EditText mPassword;

    private String email;
    private Call<Response> call;
    private ApiClient client;

    public static final String TAG = "ActionBottom";

    public static PasswordBottomSheetFragment newInstance(){
        return new PasswordBottomSheetFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = this.getArguments();
        if (bundle != null){
            email = bundle.getString("email");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new BottomSheetDialog(requireContext(), R.style.BottomSheetDialogTheme);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.password_bottom_dialog, container, false);
        ButterKnife.bind(this, view);
        client = new ApiClient();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(count > 0){
                    mPasswordNext.setEnabled(true);
                    mPasswordNext.setAlpha(1);
                }else{
                    mPasswordNext.setEnabled(false);
                    mPasswordNext.setAlpha(0.6f);
                }
            }
            @Override
            public void afterTextChanged(Editable s) { }
        });

        mPasswordNext.setOnClickListener(v->register());
    }

    private void register(){
        String password = mPassword.getText().toString();
        call = client.getService().register(email, password);
        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                Toast.makeText(getActivity(),
                        response.body().getMessage(),
                        Toast.LENGTH_LONG)
                        .show();
                if(!response.body().isError()){
                    dismiss();
                    openMainActivity();
                }
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

    private void openMainActivity(){
        Intent intent = new Intent(getActivity(), MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

}
