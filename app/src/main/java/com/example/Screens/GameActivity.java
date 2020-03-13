package com.example.Screens;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import br.com.entities.Game;
import br.com.entities.StoreUser;
import br.com.entities.User;

public class GameActivity extends BasicMenuAcitivy {


    // Cards Computer
    ImageView imgCardComputer1, imgCardComputer2, imgCardComputer3, imgCardComputer4, imgCardComputer5;

    //Cards User
    ImageView imgCardUser1, imgCardUser2, imgCardUser3, imgCardUser4, imgCardUser5;


    TextView contWin, contLoss, contSumCards;

    Button buttonHint, buttonStand;

    private ArrayList<ImageView> listCards;

    private ArrayList<Integer> listIdCards;

    //Numbers of plays
    private int playsUser = 0;
    private int playsComputer = 0;


    // Controller of indice image
    private int contImageViewUser = 5;
    private int contImageViewComputer = 0;


    private List<Integer> listIDComputer;

    // References
    private User user;
    private Game game;


    //Controller of wins and loss user
    public static int win = 0;
    public static int loss = 0;

    private int idImageComputerVisibleFinal = 0;

    private int sumAsUser = 0;

    private int sumAsPC = 0;
    private int sumAsPCCARD1 = 0;


    private int sumCardsUser = 0;

    private List<Integer> listAs;

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
        imgCardComputer3 = findViewById(R.id.imageViewCardPC3);
        imgCardComputer4 = findViewById(R.id.imageViewCardPC4);
        imgCardComputer5 = findViewById(R.id.imageViewCardPC5);


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
        listCards.add(imgCardComputer3);
        listCards.add(imgCardComputer4);
        listCards.add(imgCardComputer5);

        listCards.add(imgCardUser1);
        listCards.add(imgCardUser2);
        listCards.add(imgCardUser3);
        listCards.add(imgCardUser4);
        listCards.add(imgCardUser5);

        setCardsImage();

        buttonHint = (Button) findViewById(R.id.buttonPurchase);
        buttonStand = (Button) findViewById(R.id.buttonStand);


        StoreUser storeUser = StoreUser.getInstance();

        user = storeUser.checkIsActive();
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
        listIdCards.add(R.drawable.dezouros);
        listIdCards.add(R.drawable.damadepaus);
        listIdCards.add(R.drawable.valetedepaus);
        listIdCards.add(R.drawable.reideespadas);

        View view = null;
        listAs = new ArrayList<>();

        listIDComputer = new ArrayList<>();


        actionButtonHold(null);
        actionButtonHold(null);
        intialCard();

        //   checkPlayerWin();
        setTextWinLoss();
    }


    private void checkPlayerWin() {


        if (playsUser > 21) {
            if (sumAsUser >= 1) {
                for (int i = 0; i < sumAsUser; i++) {
                    playsUser -= 10;
                }
                contSumCards.setText(String.valueOf(playsUser));
                sumAsUser = 0;
                return;
            }
        }


        Game game = new Game();

        int resultGame = 50;


        if (Game.checkStand) {
            playComputer();
            resultGame = game.resultGame(playsUser, playsComputer);
            //   Log.i("ResultGame", String.valueOf(resultGame));
            Log.i("Caiu no stand:", "User:" + String.valueOf(playsUser) + " PC:" + String.valueOf(playsComputer));
            resultGameWinLoss(resultGame);
        }

        if (playsUser == 21) {
            playComputer();
            resultGame = game.resultGame(playsUser, playsComputer);
            Log.i("if igual a 21:", "User:" + String.valueOf(playsUser) + " PC:" + String.valueOf(playsComputer));
            resultGameWinLoss(resultGame);
        } else if (playsUser > 21) {
            playComputer();
            resultGame = game.resultGame(playsUser, playsComputer);

            Log.i("if > 21:", "User:" + String.valueOf(playsUser) + " PC:" + String.valueOf(playsComputer));
            resultGameWinLoss(resultGame);
        }
    }

    private void resultGameWinLoss(int resultGame) {

        if (resultGame == 0) {
            win++;
            msgWinLoss(getString(R.string.userBlackJack));
            visibleCardComputer();
        } else if (resultGame == 1) {
            msgWinLoss(getString(R.string.empate));
            visibleCardComputer();
        } else if (resultGame == 2) {
            loss++;
            msgWinLoss(getString(R.string.pcWin));
            visibleCardComputer();

        } else if (resultGame == 3) {
            loss++;
            msgWinLoss(getString(R.string.empatePCUserEstouraram));
            visibleCardComputer();
        } else if (resultGame == 4) {
            loss++;
            msgWinLoss(getString(R.string.voceEstourou));
            visibleCardComputer();
        }

    }

    private void msgWinLoss(String msg) {
        buttonHint.setEnabled(false);
        buttonStand.setEnabled(false);
        user = StoreUser.getInstance().updateWinLoss(user, win, loss);
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        Game.checkStand = false;
        setTextWinLoss();
    }

    public void restartGame(View view) {

        Intent it = new Intent(this, GameActivity.class);
        startActivity(it);
        finish();
        listCards.get(0).setImageResource(listIdCards.get(idImageComputerVisibleFinal));


    }

    public void actionStand(View view) {

        buttonHint.setEnabled(false);
        Game.checkStand = true;

        checkPlayerWin();
        setTextWinLoss();

        Log.i("user", user.toString());

    }

    private void setTextWinLoss() {
        contLoss.setText(String.valueOf(user.getLoss()));
        contWin.setText(String.valueOf(user.getWin()));
    }


    public void actionButtonHold(View view) {

        int id = game.generateCards();


        for (int i = 0; i < listIdCards.size(); i++) {
            if (i == id) {
                listCards.get(contImageViewUser).setImageResource(listIdCards.get(i));
            }
        }

        int valorCarta = 0;

        if (id >= 10) {
            valorCarta += 10;
        } else {
            valorCarta = id + 1;
        }

        if (id == 0) {
            valorCarta = 11;
            sumAsUser++;
        }

        playsUser += valorCarta;

        /*
        if (sumAsUser == 1) {
            playsUser += valorCarta + 10;

            if (playsUser > 21) {
                if (sumAsUser >= 1) {
                    playsUser -= 10;
                    sumAsUser = 0;
                }
            }

        } else {
            if (playsUser > 21) {
                if (sumAsUser >= 1) {
                    playsUser -= 10;
                    sumAsUser = 0;
                } else {
                    playsUser += valorCarta;
                }
            } else {
                playsUser += valorCarta;
            }
        }

*/

        contImageViewUser++;

        if (contImageViewUser == 10) {
            contImageViewUser = 5;
        }
        contSumCards.setText(String.valueOf(playsUser));
        checkPlayerWin();
    }

    private void intialCard() {
        int id1 = game.generateCards();
        listIDComputer.add(id1);

        if (contImageViewComputer == 0) {
            listCards.get(contImageViewComputer).setImageResource(listIdCards.get(id1));

        }
        int valorCarta = 0;

        if (id1 >= 10) {
            valorCarta = 10;
        } else {
            valorCarta = id1 + 1;
        }

        if (id1 == 0) {
            playsComputer += valorCarta + 10;
            sumAsPCCARD1++;
        } else {
            playsComputer += valorCarta;
        }

        contImageViewComputer++;
    }


    private void playComputer() {

        Random jogadasPC = new Random();

        int jogadas = jogadasPC.nextInt(5);

        Log.i("jogadas", String.valueOf(jogadas));


        if (jogadas == 0) {
            jogadas++;
        }

        int valorCarta = 0;

        for (int i = 0; i < jogadas; i++) {

            int id2 = game.generateCards();

            listIDComputer.add(id2);
            if (id2 >= 10) {
                valorCarta = 10;
            } else {
                valorCarta = id2 + 1;
            }

            if (id2 == 0) {
                valorCarta = 11;
                sumAsPC++;
            }

            playsComputer += valorCarta;
            /*
            if (sumAsPC == 1) {
                playsComputer += valorCarta + 10;
                if (playsComputer > 21) {
                    if (sumAsPC >= 1) {
                        playsComputer -= 10;
                        sumAsPC = 0;
                    }
                }
            } else {
                if (playsComputer > 21) {
                    if (sumAsPC >= 1) {
                        playsComputer -= 10;
                        sumAsPC = 0;
                    } else {
                        playsComputer += valorCarta;
                    }
                } else {
                    playsComputer += valorCarta;
                }
            }

            if (sumAsPCCARD1 == 1) {
                if (playsComputer > 21) {
                    playsComputer -= 10;
                    sumAsPCCARD1 = 0;
                }
            }

             */

            contImageViewComputer++;
            Log.i("Soma das cartas:", String.valueOf(playsComputer));
            if (playsComputer >= 17) {
                break;
            }

        }

        if (playsComputer > 21) {
            sumAsPC += sumAsPCCARD1;
            if (sumAsPC >= 1) {
                for (int i = 0; i < sumAsPC; i++) {
                    playsComputer -= 10;
                }
            }
        }

        Log.i("teste AS PC", String.valueOf(sumAsPC));

    }


    // Set Cards Computer and set cards user
    private void setCardsImage() {

        for (ImageView img : listCards) {
            img.setImageResource(R.drawable.verso);
        }
    }

    private void visibleCardComputer() {
        int j = 0;
        for (int i = 0; i < listIDComputer.size(); i++) {
            listCards.get(j).setImageResource(listIdCards.get(listIDComputer.get(i)));
            j++;
        }

        for (int i = 0; i < 5; i++) {
            listCards.get(i).setVisibility(View.VISIBLE);
        }
    }

}
