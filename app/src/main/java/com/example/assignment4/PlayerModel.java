package com.example.assignment4;

import java.util.ArrayList;

public class PlayerModel {
    ArrayList<String> teamNames=new ArrayList<>();
    public static class Player {
        String name;
        String nickName;
        double price;
        int image;

        public Player(String name,String nickName,double price) {
            this.name = name;
            this.nickName=nickName;
            this.price=price;

        }
        public Player(String name,String nickName,double price,int image) {
            this.name = name;
            this.nickName=nickName;
            this.price=price;
            this.image=image;

        }

    }

    public ArrayList<Player> available;
    public ArrayList<ArrayList<Player>> teams;

    public void addPlayerToTeam(int position, int team) {

        ArrayList<Player> singleTeam = teams.get(team);
        Player player = available.get(position);
        available.remove(position);
        singleTeam.add(player);


    }

    public void reset() {
//        PlayerModel myModel=new PlayerModel();
//PlayerAdapter playerServer=new PlayerAdapter(myModel);
//playerServer.notifyDataSetChanged();

//TeamPlayerAdapter teamPlayerServer=new TeamPlayerAdapter(myModel);

        for (ArrayList<Player> team : teams) {
            available.addAll(team);
            //team.clear();
        }


        teams.get(0).clear();
        teams.get(1).clear();
        teams.get(2).clear();


    }

    static private PlayerModel theModel = null;

    static public PlayerModel getSingleton() {
        if (theModel == null) {
            theModel = new PlayerModel();
        }
        return theModel;
    }

    private PlayerModel() {
        available = new ArrayList<Player>();
        loadItems();
        teams = new ArrayList<ArrayList<Player>>();
        ArrayList<Player> team1 = new ArrayList<Player>();
        ArrayList<Player> team2 = new ArrayList<Player>();
        ArrayList<Player> team3 = new ArrayList<Player>();
        teams.add(team1);
        teams.add(team2);
        teams.add(team3);
    }

    private void loadItems() {
        available.add(new Player("Sachin","Master Blaster",100,R.drawable.i1));
        available.add(new Player("Dhoni","Mahi",150,R.drawable.i2));
        available.add(new Player("Kohli","Chiku",180,R.drawable.i3));
        available.add(new Player("Sehwag","Viru",150,R.drawable.i4));
        available.add(new Player("Warner","Lloyd",120,R.drawable.i5));
        available.add(new Player("Gayle","GayleStorm",130,R.drawable.i6));
        available.add(new Player("Dhawan","Gabbar",125,R.drawable.i7));
        available.add(new Player("Rohit","Hitman",140,R.drawable.i8));
        available.add(new Player("Ganguly","Dada",120,R.drawable.i9));
        teamNames.add("Super 11");
        teamNames.add("Duper 11");
        teamNames.add("Warriors 11");

    }


}
