package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.project.databinding.ActivityForgottenPasswordBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class ForgottenPassword extends AppCompatActivity {
    ActivityForgottenPasswordBinding binding;
    ProgressDialog dialog;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityForgottenPasswordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        auth= FirebaseAuth.getInstance();

        dialog =new ProgressDialog(ForgottenPassword.this);
        dialog.setCancelable(false);
        dialog.setMessage("Cargando...");

        binding.forgotbtn.setOnClickListener(view -> {
            forgotPassword();
        });

    }
    private Boolean validateEmail() {
        String val = binding.email.getText().toString();
        if (val.isEmpty()) {
            binding.email.setError("Field cannot be empty");
            return false;
        } else {
            binding.email.setError(null);
            return true;
        }
    }

    private void forgotPassword() {
        if (!validateEmail()){
            return;
        }
        dialog.show();
        auth.sendPasswordResetEmail(binding.email.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                dialog.dismiss();
                if(task.isSuccessful()){
                    startActivity(new Intent(ForgottenPassword.this, RegistrarActivity.class));
                    finish();
                    Toast.makeText(ForgottenPassword.this, "Porfavor checa tu correo", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(ForgottenPassword.this, "Entra un correo válido",Toast.LENGTH_SHORT).show();
                }

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(ForgottenPassword.this, e.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }
}