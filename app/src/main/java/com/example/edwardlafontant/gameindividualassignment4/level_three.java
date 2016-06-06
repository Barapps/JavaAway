package com.example.edwardlafontant.gameindividualassignment4;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.app.AlertDialog;
import android.content.ClipData;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class level_three extends AppCompatActivity {
    private TextView option1, option2, option3, choice1, choice2, choice3;
    Button runbtn, reset;
    Animation animation;
    ImageView planeView;
    ImageButton home;

    final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_three);


        planeView = (ImageView)findViewById(R.id.imageView);

        //views to drag
        option1 = (TextView) findViewById(R.id.option_1);
        option2 = (TextView) findViewById(R.id.option_2);
        option3 = (TextView) findViewById(R.id.option_3);

        //views to drop onto
        choice1 = (TextView) findViewById(R.id.choice_1);
        choice2 = (TextView) findViewById(R.id.choice_2);
        choice3 = (TextView) findViewById(R.id.choice_3);

        runbtn = (Button) findViewById(R.id.runBtn);

        //set touch listeners
        option1.setOnTouchListener(new ChoiceTouchListener());
        option2.setOnTouchListener(new ChoiceTouchListener());
        option3.setOnTouchListener(new ChoiceTouchListener());

        //set drag listeners
        choice1.setOnDragListener(new ChoiceDragListener());
        choice2.setOnDragListener(new ChoiceDragListener());
        choice3.setOnDragListener(new ChoiceDragListener());

        home = (ImageButton)findViewById(R.id.homeBtn1);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();

                Intent intent = new Intent(level_three.this, mainMenu.class);
                startActivity(intent);

            }
        });

        runbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(choice1.getText().equals("Turn off Auto Pilot") && choice2.getText().equals("Deploy Landing Gear")
                        && choice3.getText().equals("Land the plane safely") )
                {
                    ImageView imageView = new ImageView(getApplicationContext());
                    imageView.setBackgroundResource(R.drawable.successanimation);
                    AnimationDrawable anim = (AnimationDrawable) imageView.getBackground();
                    anim.start();
                    animation = AnimationUtils.loadAnimation(level_three.this, R.anim.landingtranslation1);
                    animation.setFillAfter(true);
                    planeView.startAnimation(animation);
                    animation.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {

                            MediaPlayer mp = MediaPlayer.create(context, R.raw.file);
                            mp.start();

                            ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(planeView, "translationX", 200f);
                            objectAnimator.setDuration(1000);
                            objectAnimator.start();

                                    ImageView imageView = new ImageView(getApplicationContext());
                                    imageView.setBackgroundResource(R.drawable.successanimation);
                                    AnimationDrawable anim = (AnimationDrawable) imageView.getBackground();
                                    anim.start();

                                    AlertDialog dialog = new AlertDialog.Builder(level_three.this)
                                            .setView(imageView)
                                            .setMessage("You have successfully landed the plane!")
                                            .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {

                                                    finish();
                                                    Intent intent = new Intent(level_three.this, mainMenu.class);
                                                    startActivity(intent);
                                                    dialog.dismiss();
                                                    mainMenu.attempts = 3;
                                                }
                                            }).create();

                                    dialog.show();
                                }

                        @Override
                        public void onAnimationRepeat(Animation animation) {
                        }
                    });
                } else {

                    animation = AnimationUtils.loadAnimation(level_three.this, R.anim.takeofffail);
                    planeView.startAnimation(animation);

                    animation.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            planeView.setImageResource(R.drawable.fireanimationfile);
                            planeView.setBackgroundResource(R.drawable.fireanimationfile);
                            AnimationDrawable anim = (AnimationDrawable) planeView.getBackground();
                            anim.start();

                            new AlertDialog.Builder(context)
                                    .setTitle("You Crashed")
                                    .setMessage("Would you like to try again?")
                                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            finish();
                                            Intent intent = new Intent(level_three.this, level_one_takeoff.class);
                                            startActivity(intent);
                                        }
                                    })
                                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            finish();
                                            Intent intent = new Intent(level_three.this, mainMenu.class);
                                            startActivity(intent);
                                        }
                                    })
                                    .setIcon(android.R.drawable.ic_dialog_alert)
                                    .show();
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {

                        }
                    });
                }
            }
        });

        reset = (Button)findViewById(R.id.restbtn3);

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
                Intent intent = new Intent(level_three.this, level_three.class);
                startActivity(intent);
            }
        });
        }

    private final class ChoiceTouchListener implements View.OnTouchListener {

        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                //setup drag
                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
                //start dragging the item touched
                view.startDrag(data, shadowBuilder, view, 0);

                return true;
            } else {
                return false;
            }
        }
    }

    private class ChoiceDragListener implements View.OnDragListener {

        @Override
        public boolean onDrag(View v, DragEvent event) {
            //handle drag events

            switch (event.getAction()) {
                case DragEvent.ACTION_DRAG_STARTED:
                    return true;

                case DragEvent.ACTION_DRAG_ENTERED:
                    //no action necessary
                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    //no action necessary
                    break;
                case DragEvent.ACTION_DROP:
                    //handle the dragged view being dropped over a target view
                    View view = (View) event.getLocalState();
                    //stop displaying the view where it was before it was dragged
                    view.setVisibility(View.INVISIBLE);
                    //view dragged item is being dropped on
                    TextView dropTarget = (TextView) v;
                    //view being dragged and dropped
                    TextView dropped = (TextView) view;
                    //update the text in the target view to reflect the data being dropped
                    dropTarget.setText(dropped.getText());
                    //make it bold to highlight the fact that an item has been dropped
                    dropTarget.setTypeface(Typeface.DEFAULT_BOLD);
                    //if an item has already been dropped here, there will be a tag
                    Object tag = dropTarget.getTag();
                    //if there is already an item here, set it back visible in its original place
                    if (tag != null) {
                        //the tag is the view id already dropped here
                        int existingID = (Integer) tag;
                        //set the original view visible again
                        findViewById(existingID).setVisibility(View.VISIBLE);
                    }
                    //set the tag in the target view to the ID of the view being dropped
                    dropTarget.setTag(dropped.getId());

                    break;
                case DragEvent.ACTION_DRAG_ENDED:
                    //no action necessary
                    break;
                default:
                    break;
            }

            return true;
        }
    }

    public void reset(View view)
    {
        view.setVisibility(TextView.VISIBLE);

        view.setTag(null);

        view.setOnDragListener(new ChoiceDragListener());
    }
}
