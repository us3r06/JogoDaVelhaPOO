package com.hdanyllo.jogodavelha;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.hdanyllo.jogodavelha.R;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    protected HashMap<Integer, Integer> positionMapping = new HashMap<>();
    protected HashMap<TicTacToe.Moves, Integer> moveImageMapping = new HashMap<>();
    protected TicTacToe ticTatToe = new TicTacToe();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        positionMapping.put(R.id.topLeft, 0);
        positionMapping.put(R.id.topMiddle, 1);
        positionMapping.put(R.id.topRight, 2);
        positionMapping.put(R.id.centerLeft, 3);
        positionMapping.put(R.id.centerMiddle, 4);
        positionMapping.put(R.id.centerRight, 5);
        positionMapping.put(R.id.bottomLeft, 6);
        positionMapping.put(R.id.bottomMiddle, 7);
        positionMapping.put(R.id.bottomRight, 8);

        moveImageMapping.put(TicTacToe.Moves.X, R.drawable.tic_tac_toe_x);
        moveImageMapping.put(TicTacToe.Moves.O, R.drawable.tic_tac_toe_o);

        setNextMoveIndicator();
    }

    public void doMove(View view) {
        if(ticTatToe.hasFinished()) {
            return;
        }

        TicTacToe.Moves nextMove = ticTatToe.getNextMove();
        ImageView image = (ImageView) view;

        try {
            ticTatToe.move(positionMapping.get(image.getId()));
            image.setImageResource(moveImageMapping.get(nextMove));
        } catch (SamePositionMoveException e) {
            Toast.makeText(getApplicationContext(), "Tentando jogar na mesma posição??", Toast.LENGTH_SHORT).show();
            return;
        } catch (PositionBeyondBoundariesException e) {
            Toast.makeText(getApplicationContext(), "Tentando jogar fora dos limites. Como conseguiu isso???", Toast.LENGTH_SHORT).show();
            return;
        }

        if(ticTatToe.hasFinished()) {
            if(ticTatToe.hasWinner()) {
                Toast.makeText(getApplicationContext(), "Finalizado", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), "Deu velha!", Toast.LENGTH_SHORT).show();
            }
        } else {
            setNextMoveIndicator();
        }
    }

    public void restartGame(View view) {
        ticTatToe = new TicTacToe();

        ((ImageView) findViewById(R.id.topLeft)).setImageResource(android.R.color.transparent);
        ((ImageView) findViewById(R.id.topMiddle)).setImageResource(android.R.color.transparent);
        ((ImageView) findViewById(R.id.topRight)).setImageResource(android.R.color.transparent);
        ((ImageView) findViewById(R.id.centerLeft)).setImageResource(android.R.color.transparent);
        ((ImageView) findViewById(R.id.centerMiddle)).setImageResource(android.R.color.transparent);
        ((ImageView) findViewById(R.id.centerRight)).setImageResource(android.R.color.transparent);
        ((ImageView) findViewById(R.id.bottomLeft)).setImageResource(android.R.color.transparent);
        ((ImageView) findViewById(R.id.bottomMiddle)).setImageResource(android.R.color.transparent);
        ((ImageView) findViewById(R.id.bottomRight)).setImageResource(android.R.color.transparent);

        setNextMoveIndicator();
    }

    protected void setNextMoveIndicator() {
        ((ImageView) findViewById(R.id.nextMove)).setImageResource(moveImageMapping.get(ticTatToe.getNextMove()));
    }
}
