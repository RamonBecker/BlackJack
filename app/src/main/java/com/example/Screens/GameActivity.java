package com.example.Screens;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

import br.com.entities.Game;
import br.com.entities.StoreUser;
import br.com.entities.User;

public class GameActivity extends BasicMenuAcitivy {


    // Cards Computer
    ImageView imgCardComputer1, imgCardComputer2, imgCardComputer3;

    //Cards User
    ImageView imgCardUser1, imgCardUser2, imgCardUser3, imgCardUser4, imgCardUser5;


    TextView contWin, contLoss, contSumCards;

    Button buttonHint;

    private ArrayList<ImageView> listCards;

    private ArrayList<Integer> listIdCards;
    //Numbers of plays
    private int playsUser = 0;
    private int playsComputer = 0;

    private int contImageViewUser = 2;
    private int contImageViewComputer = 0;

    private Game game;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        contWin = (TextView) findViewById(R.id.textViewContadorVitoria);
        contLoss = (TextView) findViewById(R.id.textViewContadorDerrota);
        contSumCards = (TextView) findViewById(R.id.textViewSumCards);

        //Cards Computer
        imgCardComputer1 = findViewById(R.id.imageViewCardPC1);
        imgCardComputer2 = findViewById(R.id.imageViewCardPC2);


        //Cards User

        imgCardUser1 = findViewById(R.id.imageViewCard1);
        imgCardUser2 = findViewById(R.id.imageViewCard2);
        imgCardUser3 = findViewById(R.id.imageViewCard3);
        imgCardUser4 = findViewById(R.id.imageViewCard4);
        imgCardUser5 = findViewById(R.id.imageViewCard5);

        //Add img computer e user
        listCards = new ArrayList<>();

        listCards.add(imgCardComputer1);
        listCards.add(imgCardComputer2);


        listCards.add(imgCardUser1);
        listCards.add(imgCardUser2);
        listCards.add(imgCardUser3);
        listCards.add(imgCardUser4);
        listCards.add(imgCardUser5);

        setCardsImage();

        buttonHint = (Button) findViewById(R.id.buttonPurchase);


        StoreUser storeUser = StoreUser.getInstance();

        User user = storeUser.checkIsActive();
        getSupportActionBar().setTitle(user.getName());


        //instance to Game
        game = new Game();

        //Add IDS of cards
        listIdCards = new ArrayList<>();

        listIdCards.add(R.drawable.asdepaus);
        listIdCards.add(R.drawable.doisdepaus);
        listIdCards.add(R.drawable.tresouros);
        listIdCards.add(R.drawable.quatrodeouros);
        listIdCards.add(R.drawable.cincodeespadas);
        listIdCards.add(R.drawable.seisdeespadas);
        listIdCards.add(R.drawable.setedecopas);
        listIdCards.add(R.drawable.oitodecopas);
        listIdCards.add(R.drawable.novedeouros);
        listIdCards.add(R.drawable.damadepaus);
        listIdCards.add(R.drawable.dezouros);
        listIdCards.add(R.drawable.valetedepaus);
        listIdCards.add(R.drawable.reideespadas);

        View view = null;

        actionButtonHold(null);
        actionButtonHold(null);

    }


    public void actionButtonHold(View view){

        int id = game.generateCards();
        //Log.i("id",String.valueOf(id));

        for (int i = 0 ; i < listIdCards.size(); i++){
             if(i == id){
                listCards.get(contImageViewUser).setImageResource(listIdCards.get(i));
             }
        }

        if(id >= 10){
            playsUser += 10;
        }else{
            playsUser += id + 1;
        }

        contImageViewUser++;

        if(contImageViewUser == 8){
            contImageViewUser = 3;
        }

        contSumCards.setText(String.valueOf(playsUser));
    }


    private void playComputer(){

        int id = game.generateCards();
        Log.i("id",String.valueOf(id));

    }

    // Set Cards Computer and set cards user
    private void setCardsImage(){

        for (ImageView img:listCards) {
            img.setImageResource(R.drawable.verso);
        }
    }

}
