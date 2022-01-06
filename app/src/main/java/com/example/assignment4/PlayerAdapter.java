package com.example.assignment4;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class PlayerAdapter extends RecyclerView.Adapter<PlayerAdapter.PlayerViewHolder> {
    @NonNull
    @Override
    public PlayerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = (View) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.player_text_view, parent, false);
        PlayerViewHolder vh = new PlayerViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull PlayerViewHolder holder, int position) {
        ConstraintLayout holderCV = (ConstraintLayout) holder.itemView;
        TextView playerTV = holderCV.findViewById(R.id.playerTV);
        playerTV.setText(theModel.available.get(position).name);
        TextView playerNickNameTV = holderCV.findViewById(R.id.playerNickNameTV);
        playerNickNameTV.setText(theModel.available.get(position).nickName);
        TextView playerPriceTV = holderCV.findViewById(R.id.playerPriceTV);
        playerPriceTV.setText("$"+String.valueOf(theModel.available.get(position).price));

    }

    @Override
    public int getItemCount() {

        return theModel.available.size();
    }


    private PlayerModel theModel;

    public PlayerAdapter(PlayerModel inputModel) {
        super();
        theModel = PlayerModel.getSingleton();
    }

    public static class PlayerViewHolder extends RecyclerView.ViewHolder {
        public String moreUsefulThings = "";

        public PlayerViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        public PlayerViewHolder(@NonNull View itemView, String abit) {
            super(itemView);
            moreUsefulThings = abit;
        }
    }

}
