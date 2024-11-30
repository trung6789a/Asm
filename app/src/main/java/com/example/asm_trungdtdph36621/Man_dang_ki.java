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

public class Man_dang_ki extends AppCompatActivity {
Button btnCreate;
    private FirebaseAuth mAth;
    EditText email, pass;
    Context context = this;
TextView txtLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_dang_ki);
        mAth = FirebaseAuth.getInstance();
        email = findViewById(R.id.edUserNames);
        pass = findViewById(R.id.edPasswords);
        btnCreate = findViewById(R.id.btnLCreat);
        txtLogin = findViewById(R.id.txtLogin);
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emails = email.getText().toString().trim();
                String passs = pass.getText().toString().trim();
                mAth.createUserWithEmailAndPassword(emails,passs).addOnCompleteListener((Activity) context, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            FirebaseUser user = mAth.getCurrentUser();
                            Toast.makeText(context, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                            finish();
                        }else {
                            Toast.makeText(context, "Đang kí thất bai", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        txtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Man_dang_ki.this, Login.class);
                startActivity(intent);
            }
        });

    }
}