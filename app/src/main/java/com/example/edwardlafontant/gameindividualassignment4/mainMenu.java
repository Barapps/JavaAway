package com.example.edwardlafontant.gameindividualassignment4;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;


public class mainMenu extends AppCompatActivity {

    ImageButton level_one, level_two, level_three;
    ImageView title;
    Animation animation;
    ImageButton exit;

    static int attempts = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        title = (ImageView)findViewById(R.id.titlebar);


        level_one = (ImageButton) findViewById(R.id.levelone);
        level_two = (ImageButton) findViewById(R.id.leveltwo);
        level_three = (ImageButton) findViewById(R.id.levelthree);

        level_two.setAlpha(0.5f);
        level_three.setAlpha(0.5f);

        exit = (ImageButton)findViewById(R.id.exitBtn);

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               finish();
                System.exit(0);

            }
        });

        level_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent = new Intent(mainMenu.this, level_one_takeoff.class);
                startActivity(intent);

            }
        });

        level_two.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();

                Toast.makeText(mainMenu.this, "You must complete level 1 to access this level!", Toast.LENGTH_SHORT).show();


            }
        });

        level_three.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();

                Toast.makeText(mainMenu.this, "You must complete level 2 to access this level!", Toast.LENGTH_SHORT).show();

            }
        });

        if (attempts > 1) {

            level_two.setAlpha(1.0f);


            level_two.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mainMenu.this, level_two.class);
                    startActivity(intent);
                }


            });

            level_three.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    Toast.makeText(mainMenu.this, "You must complete level 2 to access this level!", Toast.LENGTH_SHORT).show();

                }
            });

            if (attempts > 2) {

                level_three.setAlpha(1.0f);


                level_three.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(mainMenu.this, level_three.class);
                        startActivity(intent);
                    }
                });

            }
        }


    }
}
