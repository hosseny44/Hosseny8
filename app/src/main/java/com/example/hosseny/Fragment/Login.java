package com.example.hosseny.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hosseny.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

public class Login extends Fragment {

    private EditText etUsername , etPassword;
    private TextView tvSignupLink, tvForgotpassword;
    private Button btnLogin;
    private FirebaseServices fbs;

    public Login() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        etUsername = getView().findViewById(R.id.etUsernameLogin);
        etPassword = getView().findViewById(R.id.etPasswordLogin);
        btnLogin = getView().findViewById(R.id.btnLoginLogin);
        tvSignupLink = getView().findViewById(R.id.tvSignupLogin);
        tvForgotpassword = getView().findViewById(R.id.tvForgotPasswordLogin);

        // Forgot password
        tvForgotpassword.setOnClickListener(v -> gotoForgotPasswordFragment());

        // Signup link
        tvSignupLink.setOnClickListener(v -> gotoSignupFragment());

        // Login button
        btnLogin.setOnClickListener(v -> {
            fbs = FirebaseServices.getInstance();
            String username = etUsername.getText().toString().trim();
            String password = etPassword.getText().toString().trim();

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(getActivity(), "Some fields are empty!", Toast.LENGTH_SHORT).show();
                return;
            }

            fbs.getAuth().signInWithEmailAndPassword(username, password)
                    .addOnCompleteListener(getActivity(), task -> {
                        if (task.isSuccessful()) {
                            Toast.makeText(getActivity(), "Login successful!", Toast.LENGTH_SHORT).show();
                            FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                            ft.replace(R.id.frameLayout, new Admin());
                            ft.commit();
                        } else {
                            Toast.makeText(getActivity(), "Login failed! Check username or password.", Toast.LENGTH_SHORT).show();
                        }
                    });
        });

    }

    private void gotoSignupFragment() {
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frameLayout, new signup1());
        ft.commit();
    }

    private void gotoForgotPasswordFragment() {
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frameLayout, new ForgotPassword());
        ft.commit();
    }
}
