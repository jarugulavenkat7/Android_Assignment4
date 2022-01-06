package com.example.assignment4;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TeamPlayerAdapter extends RecyclerView.Adapter<TeamPlayerAdapter.TeamPlayerViewHolder>{
    private ArrayList<PlayerModel.Player> playerList;
    @NonNull
    @Override
    public TeamPlayerAdapter.TeamPlayerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = (View) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.team_player_text_view, parent, false);
        TeamPlayerAdapter.TeamPlayerViewHolder vh = new TeamPlayerAdapter.TeamPlayerViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull TeamPlayerAdapter.TeamPlayerViewHolder holder, int position) {
        ConstraintLayout holderCV = (ConstraintLayout) holder.itemView;
        TextView playerTV = holderCV.findViewById(R.id.teamPlayerTV);
        playerTV.setText(this.playerList.get(position).name);
        TextView playerNickNameTV = holderCV.findViewById(R.id.teamPlayerNickNameTV);
        playerNickNameTV.setText(this.playerList.get(position).nickName);
        TextView playerPriceTV = holderCV.findViewById(R.id.teamPlayerPriceTV);
        playerPriceTV.setText("$"+String.valueOf(this.playerList.get(position).price));
       ImageView imageView=holderCV.findViewById(R.id.i0);
       if(this.playerList.get(position).image!=0) {
           imageView.setImageResource(this.playerList.get(position).image);
       }
       else{
           imageView.setImageResource(R.drawable.i0);
       }

    }

    @Override
    public int getItemCount() {
        return playerList.size();
    }



    public TeamPlayerAdapter(ArrayList<PlayerModel.Player> inputList) {
        super();
        this.playerList =inputList ;
    }

    public static class TeamPlayerViewHolder extends RecyclerView.ViewHolder {

        public String moreUsefulThings = "";

        public TeamPlayerViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        public TeamPlayerViewHolder(@NonNull View itemView, String abit) {
            super(itemView);
            moreUsefulThings = abit;
        }
    }
}
