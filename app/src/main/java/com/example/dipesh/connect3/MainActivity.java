package com.example.dipesh.connect3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    // 0=Yellow 1-Red 2-Blank
    int active_player=0;
    int[] game_state={2,2,2,2,2,2,2,2,2};
    int[][] win_positions={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    boolean is_active=true;
    boolean gameOver=false;
    static int count_moves=0;
    @SuppressLint("ResourceAsColor")
    public void dropIn(View v){

        ImageView counter=(ImageView) v;

        int tag=Integer.parseInt(counter.getTag().toString());


        if(game_state[tag]==2 && is_active){
            count_moves++;
            counter.setTranslationY(-1000f);
            game_state[tag]=active_player;
            if(active_player==0){
                counter.setImageResource(R.drawable.yellow);

                counter.animate().translationYBy(1000f).rotation(2000f).setDuration(500);

                active_player=1;
            }else{
                counter.setImageResource(R.drawable.red);

                counter.animate().translationYBy(1000f).rotation(2000f).setDuration(500);

                active_player=0;

            }

            for(int[] win_position:win_positions){
                if(game_state[win_position[0]]==game_state[win_position[1]] && game_state[win_position[1]]==game_state[win_position[2]] &&
                    game_state[win_position[0]]!=2){

                    LinearLayout linearLayout=findViewById(R.id.linearLayout);
                    String winner;
                    linearLayout.setBackgroundColor(Color.rgb(0,0,255));
                    if(game_state[win_position[0]]==0){
                        winner="Yellow";
                        linearLayout.setBackgroundColor(Color.rgb(255,255,0));

                    }else{
                        winner="Red";
                        linearLayout.setBackgroundColor(Color.rgb(255,0,0));


                    }
                    is_active=false;
                    gameOver=true;
                    System.out.println("Player :"+game_state[win_position[0]] + " wins");

                    TextView winnerView=findViewById(R.id.winnerTextView);

                    linearLayout.setVisibility(View.VISIBLE);

                    winnerView.setText(winner+" has won");

                }
            }
            System.out.println("count Moves: "+count_moves);

            if(count_moves==9 && gameOver==false){
                gameOver=true;
                for(int i:game_state){
                    //game not over
                    if(i==2){
                        gameOver=false;
                        break;


                    }
                }
                if(gameOver){
                    is_active=false;
                    TextView winnerView=findViewById(R.id.winnerTextView);
                    LinearLayout linearLayout=findViewById(R.id.linearLayout);
                    linearLayout.setVisibility(View.VISIBLE);
                    winnerView.setText("Match Draw");
                }
            }

            System.out.println("DropIn running");

        }else{
            System.out.println("Already Placed");
        }




    }

    public void playAgain(View view){
        LinearLayout linearLayout=findViewById(R.id.linearLayout);
        linearLayout.setVisibility(View.INVISIBLE);
        is_active=true;
        count_moves=0;
        gameOver=false;

        active_player=0;
        for(int i=0;i<game_state.length;i++){
            game_state[i]=2;
        }

        GridLayout gridLayout=findViewById(R.id.gridLayout);
        for(int i=0;i<gridLayout.getChildCount();i++){
            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}