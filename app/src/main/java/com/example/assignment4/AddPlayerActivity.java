package com.example.assignment4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class AddPlayerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_player);



    }

    public void addPlayer(View view){

            EditText playerNameET = findViewById(R.id.playerNameET);
            EditText nickNameET = findViewById(R.id.nickNameET);
            EditText playerPriceET = findViewById(R.id.playerPriceET);
            PlayerModel myModel = PlayerModel.getSingleton();

            if(playerNameET.getText().toString().isEmpty()||nickNameET.getText().toString().isEmpty()||
                    playerPriceET.getText().toString().isEmpty()){
                TextView tv= findViewById(R.id.errorTV);
                tv.setText("Please fill all the details");
            }
            else {
                myModel.available.add(new PlayerModel.Player(playerNameET.getText().toString(),
                        nickNameET.getText().toString(),
                        Double.parseDouble(playerPriceET.getText().toString()), 0));
                PlayerAdapter playerServer = new PlayerAdapter(myModel);
                playerServer.notifyItemInserted(myModel.available.size() - 1);
                Toast.makeText(getApplicationContext(), "Added New Player", Toast.LENGTH_SHORT).show();


                finish();
            }

    }
}
