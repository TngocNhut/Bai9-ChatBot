package com.tngocnhat.chatbot;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ManagerActivity extends AppCompatActivity {
    private RecyclerView rvConversations;
    private ConversationAdapter adapter;
    private ChatDatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);

        rvConversations = findViewById(R.id.rvConversations);
        dbHelper = new ChatDatabaseHelper(this);

        rvConversations.setLayoutManager(new LinearLayoutManager(this));

        loadConversations();
    }

    private void loadConversations() {
        List<String> sessions = dbHelper.getAllSessions();
        adapter = new ConversationAdapter(sessions, sessionUser -> {
            // Open chat activity for this session
            Intent intent = new Intent(ManagerActivity.this, ChatActivity.class);
            intent.putExtra("SESSION_USER", sessionUser);
            intent.putExtra("USER_ROLE", "manager");
            startActivity(intent);
        });
        rvConversations.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Reload conversations when returning to this activity
        loadConversations();
    }
}
