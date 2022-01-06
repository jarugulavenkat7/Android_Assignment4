package com.example.assignment4;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RosterActivity extends AppCompatActivity {
    private PlayerModel myModel;
    private TeamPlayerAdapter teamPlayerServer = null;
    private PlayerAdapter playerServer = null;
    private RecyclerView playerRecycler = null;
    private GestureDetectorCompat detector = null;
    private ArrayList<PlayerModel.Player> teamPlayers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.roster);
        Intent ini = getIntent();
        int teamNumber = ini.getIntExtra("team", 0);

        PlayerModel theModel = PlayerModel.getSingleton();
        teamPlayers = theModel.teams.get(teamNumber);
        int teamNumberToDisplay = teamNumber + 1;
        TextView rosterForTeamTV = findViewById(R.id.rosterForTeamTV);
        rosterForTeamTV.setText("Roaster for team " + theModel.teamNames.get(teamNumber));

        myModel = PlayerModel.getSingleton();
        teamPlayerServer = new TeamPlayerAdapter(teamPlayers);
        playerRecycler = findViewById(R.id.teamPlayeRosterRV);
        playerRecycler.setAdapter(teamPlayerServer);
        LinearLayoutManager myManager = new LinearLayoutManager(this);
        playerRecycler.setLayoutManager(myManager);


        setTotalPrice();


        // Make a Listener for taps
        detector = new GestureDetectorCompat(this,
                new RecyclerViewOnGestureListener());
        // add the listener to the recycler
        playerRecycler.addOnItemTouchListener(new RecyclerView.SimpleOnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                return detector.onTouchEvent(e);
            }
        });


    }

    private class RecyclerViewOnGestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            View view = playerRecycler.findChildViewUnder(e.getX(), e.getY());
            if (view != null) {
                RecyclerView.ViewHolder holder = playerRecycler.getChildViewHolder(view);
                if (holder instanceof TeamPlayerAdapter.TeamPlayerViewHolder) {
                    int position = holder.getAdapterPosition();
                    // handle single tap
                    Log.d("click", "clicked on item " + position);
                    myModel = PlayerModel.getSingleton();
                    playerServer = new PlayerAdapter(myModel);
                    myModel.available.add(teamPlayers.get(position));
                    playerServer.notifyItemInserted(myModel.available.size() - 1);
                    teamPlayers.remove(position);
                    teamPlayerServer.notifyItemRemoved(position);
                    setTotalPrice();
                    return true; // Use up the tap gesture
                }
            }
            // we didn't handle the gesture so pass it on
            return false;
        }
    }

    public void done(View view) {
        // setResult(resultCode, ini);
        finish();
    }
    public void setTotalPrice(){
        TextView totalPriceTV = findViewById(R.id.totalPriceTV);
        double totalPrice=0;
        for(PlayerModel.Player teamPlayer:teamPlayers) {
            totalPrice+=teamPlayer.price;

        }
        totalPriceTV.setText(String.valueOf(totalPrice));
    }
}
