package com.bbt.sampleproject.ui.viewholder;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.bbt.sampleproject.R;
import com.bbt.sampleproject.data.network.models.GameSession;
import com.bbt.sampleproject.presentation.OnGameSessionClickedListener;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GameSessionViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.card_game_session_title)
    TextView mCardGameTitle;

    public GameSessionViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(final GameSession gameSession, final OnGameSessionClickedListener onGameSessionClickedListener) {
        mCardGameTitle.setText(gameSession.getName());

        if (onGameSessionClickedListener != null) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onGameSessionClickedListener.onClicked(gameSession);
                }
            });
        }
    }
}
