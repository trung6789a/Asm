package com.example.asm_trungdtdph36621;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {
    Button btnLogin;
    TextView txtCreat;

    EditText txtuser, txtpasss;
    TextView txtquen;

    private FirebaseAuth mAuth;
    Context context = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btnLogin = findViewById(R.id.btnLogin);
        txtuser = findViewById(R.id.edUserName);
        txtpasss = findViewById(R.id.edPassword);
        txtCreat = findViewById(R.id.txtCreat);
        mAuth = FirebaseAuth.getInstance();
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = txtuser.getText().toString().trim();
                String pass = txtpasss.getText().toString().trim();
                mAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener((Activity) context, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if ((task.isSuccessful())) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(context, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(Login.this, MainActivity.class));
                        } else {
                            Toast.makeText(context, "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        txtCreat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, Man_dang_ki.class);
                startActivity(intent);
            }
        });
    }
}