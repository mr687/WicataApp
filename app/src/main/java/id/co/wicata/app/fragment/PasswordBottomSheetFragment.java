package id.co.wicata.app.fragment;

import android.app.Dialog;
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
import id.co.wicata.app.activity.RegisterActivity;
import id.co.wicata.app.model.Response;
import retrofit2.Call;
import retrofit2.Callback;

public class PasswordBottomSheetFragment extends BottomSheetDialogFragment
implements View.OnClickListener {
    @BindView(R.id.passwordNext)
    TextView mPasswordNext;
    @BindView(R.id.password)
    EditText mPassword;

    public static final String TAG = "ActionBottom";

    public static PasswordBottomSheetFragment newInstance(){
        return new PasswordBottomSheetFragment();
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
                if(count>4){
                    mPasswordNext.setEnabled(true);
                    mPasswordNext.setAlpha(1);
                }else{
                    mPasswordNext.setEnabled(false);
                    mPasswordNext.setAlpha(0.6f);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

//        mPasswordNext.setOnClickListener(v->getActivity().register());
    }

//    public void register(){
//        String password = mPassword.getText().toString().trim();
//        call = service.register(currentEmail, password);
//        call.enqueue(new Callback<Response>() {
//            @Override
//            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
////                if(response.body().isError()){
////                    Toast.makeText(RegisterActivity.this,
////                            response.body().getMessage(), Toast.LENGTH_LONG)
////                            .show();
////                    return;
////                }
//                Toast.makeText(RegisterActivity.this,
//                        response.body().getMessage(), Toast.LENGTH_LONG)
//                        .show();
//            }
//
//            @Override
//            public void onFailure(Call<Response> call, Throwable t) {
//                Toast.makeText(RegisterActivity.this,
//                        t.getMessage(), Toast.LENGTH_LONG)
//                        .show();
//            }
//        });
//    }

    @Override
    public void onClick(View v) {

    }
}
