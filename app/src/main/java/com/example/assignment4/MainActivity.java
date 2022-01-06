package com.example.assignment4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private PlayerModel myModel;
    private PlayerAdapter playerServer = null;
    private RecyclerView playerRecycler = null;
    private GestureDetectorCompat detector = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myModel = PlayerModel.getSingleton();
        playerServer = new PlayerAdapter(myModel);
        playerRecycler = findViewById(R.id.teamPlayeRosterRV);
        playerRecycler.setAdapter(playerServer);
        LinearLayoutManager myManager = new LinearLayoutManager(this);
        playerRecycler.setLayoutManager(myManager);

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
TextView instructionsTV= findViewById(R.id.instructionsTV);
instructionsTV.setText("Instructions: \n 1) Start Clicking on player name to add to the teams.\n  2)Click  on reset to add players back to list.\n 3) Click on team buttons to view the players list.\n 4) Click on add botton to add new players");
    }

    private class RecyclerViewOnGestureListener extends GestureDetector.SimpleOnGestureListener {
        TextView currentChoiceTV= findViewById(R.id.currentChoiceTV);
        int currentTeam=1;
        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            View view = playerRecycler.findChildViewUnder(e.getX(), e.getY());
            if (view != null) {
                RecyclerView.ViewHolder holder = playerRecycler.getChildViewHolder(view);
                if (holder instanceof PlayerAdapter.PlayerViewHolder) {
                    int position = holder.getAdapterPosition();
                    // handle single tap
                    Log.d("click", "clicked on item " + position);
                    PlayerModel m = PlayerModel.getSingleton();

                    switch(currentTeam) {
                        case 1:
                            currentChoiceTV.setText(m.teamNames.get(1)+"choosing team");
                            m.addPlayerToTeam(position,0);
                            currentTeam=2;
                            playerServer.notifyItemRemoved(position);
                            break;
                        case 2:
                            currentChoiceTV.setText(m.teamNames.get(2)+"choosing team");
                            m.addPlayerToTeam(position,1);
                            currentTeam=3;
                            playerServer.notifyItemRemoved(position);
                            break;
                        case 3:
                            currentChoiceTV.setText(m.teamNames.get(0)+"choosing team");
                            m.addPlayerToTeam(position,2);
                            currentTeam=1;
                            playerServer.notifyItemRemoved(position);
                            break;

                    }




                    return true; // Use up the tap gesture
                }
            }
            // we didn't handle the gesture so pass it on
            return false;
        }
    }
    public void reset(View view) throws InterruptedException {
        myModel.reset();
        //Thread.sleep(2000);
        Toast.makeText(getApplicationContext(),"Resetting Players List",Toast.LENGTH_SHORT).show();
        playerServer.notifyDataSetChanged();

    }
    public void openRoster1(View v){
        Intent intent=new Intent(this, RosterActivity.class);
        intent.putExtra("team",0);
        startActivity(intent);
    }
    public void openRoster2(View v){
        Intent intent=new Intent(this, RosterActivity.class);
        intent.putExtra("team",1);
        startActivity(intent);
    }
    public void openRoster3(View v){
        Intent intent=new Intent(this, RosterActivity.class);
        intent.putExtra("team",2);
        startActivity(intent);
    }

    public void addPlayerActivity(View view){
        Intent intent=new Intent(this, AddPlayerActivity.class);
        startActivity(intent);
    }
}