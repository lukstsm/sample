package com.bbt.sampleproject.ui.adapter;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bbt.sampleproject.R;
import com.bbt.sampleproject.data.network.models.GameSession;
import com.bbt.sampleproject.presentation.OnGameSessionClickedListener;
import com.bbt.sampleproject.ui.viewholder.GameSessionViewHolder;

import java.util.ArrayList;
import java.util.List;


public class GameSessionAdapter extends RecyclerView.Adapter<GameSessionViewHolder> {

    private final OnGameSessionClickedListener mOnGameSessionClickedListener;
    private List<GameSession> mGameSessions = new ArrayList<>();

    public GameSessionAdapter(List<GameSession> gameSessions, OnGameSessionClickedListener onGameSessionClickedListener) {
        mOnGameSessionClickedListener = onGameSessionClickedListener;

        if (gameSessions != null) {
            mGameSessions = gameSessions;
        }
    }

    @Override
    public GameSessionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_game_session, parent, false);
        return new GameSessionViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(GameSessionViewHolder holder, int position) {
        GameSession gameSession = getItem(position);
        holder.bind(gameSession, mOnGameSessionClickedListener);
    }

    @Override
    public int getItemCount() {
        return mGameSessions.size();
    }

    public GameSession getItem(int position) {
        return mGameSessions.get(position);
    }

}
