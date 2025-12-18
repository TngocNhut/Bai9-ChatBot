package com.tngocnhat.chatbot;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ChatActivity extends AppCompatActivity {
    private RecyclerView rvMessages;
    private EditText etMessage;
    private Button btnSend;
    private TextView tvChatHeader;

    private MessageAdapter adapter;
    private ChatDatabaseHelper dbHelper;

    private String sessionUser;
    private String userRole;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        // Get intent extras
        sessionUser = getIntent().getStringExtra("SESSION_USER");
        userRole = getIntent().getStringExtra("USER_ROLE");

        rvMessages = findViewById(R.id.rvMessages);
        etMessage = findViewById(R.id.etMessage);
        btnSend = findViewById(R.id.btnSend);
        tvChatHeader = findViewById(R.id.tvChatHeader);

        dbHelper = new ChatDatabaseHelper(this);

        // Set header title
        if (userRole.equals("manager")) {
            tvChatHeader.setText("Chat with " + sessionUser);
        } else {
            tvChatHeader.setText("Customer Support");
        }

        // Setup RecyclerView
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rvMessages.setLayoutManager(layoutManager);

        loadMessages();

        // Send button click
        btnSend.setOnClickListener(v -> sendMessage());
    }

    private void loadMessages() {
        List<Message> messages = dbHelper.getMessagesForSession(sessionUser);
        adapter = new MessageAdapter(messages, userRole);
        rvMessages.setAdapter(adapter);

        // Scroll to bottom
        if (messages.size() > 0) {
            rvMessages.scrollToPosition(messages.size() - 1);
        }
    }

    private void sendMessage() {
        String messageContent = etMessage.getText().toString().trim();
        if (TextUtils.isEmpty(messageContent)) {
            Toast.makeText(this, "Please enter a message", Toast.LENGTH_SHORT).show();
            return;
        }

        // Add message to database
        dbHelper.addMessage(sessionUser, userRole, messageContent);

        // Clear input
        etMessage.setText("");

        // Reload messages
        List<Message> messages = dbHelper.getMessagesForSession(sessionUser);
        adapter.updateMessages(messages);

        // Scroll to bottom
        rvMessages.scrollToPosition(messages.size() - 1);
    }
}
