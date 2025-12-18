package com.tngocnhat.chatbot;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    private EditText etGuestName;
    private Button btnLoginGuest;
    private Button btnLoginManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etGuestName = findViewById(R.id.etGuestName);
        btnLoginGuest = findViewById(R.id.btnLoginGuest);
        btnLoginManager = findViewById(R.id.btnLoginManager);

        btnLoginGuest.setOnClickListener(v -> {
            String guestName = etGuestName.getText().toString().trim();
            if (TextUtils.isEmpty(guestName)) {
                Toast.makeText(this, "Please enter your name", Toast.LENGTH_SHORT).show();
                return;
            }

            // Go to chat activity as guest
            Intent intent = new Intent(LoginActivity.this, ChatActivity.class);
            intent.putExtra("SESSION_USER", guestName);
            intent.putExtra("USER_ROLE", "guest");
            startActivity(intent);
        });

        btnLoginManager.setOnClickListener(v -> {
            // Go to manager activity
            Intent intent = new Intent(LoginActivity.this, ManagerActivity.class);
            startActivity(intent);
        });
    }
}
