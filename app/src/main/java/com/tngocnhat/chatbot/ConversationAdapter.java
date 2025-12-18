package com.tngocnhat.chatbot;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ConversationAdapter extends RecyclerView.Adapter<ConversationAdapter.ConversationViewHolder> {
    private List<String> sessions;
    private OnConversationClickListener listener;

    public interface OnConversationClickListener {
        void onConversationClick(String sessionUser);
    }

    public ConversationAdapter(List<String> sessions, OnConversationClickListener listener) {
        this.sessions = sessions;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ConversationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_conversation, parent, false);
        return new ConversationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ConversationViewHolder holder, int position) {
        String sessionUser = sessions.get(position);
        holder.bind(sessionUser, listener);
    }

    @Override
    public int getItemCount() {
        return sessions.size();
    }

    static class ConversationViewHolder extends RecyclerView.ViewHolder {
        TextView tvSessionName;

        ConversationViewHolder(@NonNull View itemView) {
            super(itemView);
            tvSessionName = itemView.findViewById(R.id.tvSessionName);
        }

        void bind(String sessionUser, OnConversationClickListener listener) {
            tvSessionName.setText(sessionUser);
            itemView.setOnClickListener(v -> listener.onConversationClick(sessionUser));
        }
    }
}
