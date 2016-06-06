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
import android.util.DisplayMetrics;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class level_two extends mainMenu {

    ImageView planeView, cloud1, cloud2, cloud3, cloud4;
    TextView UP, DOWN, LEFT, RIGHT, command;
    Button runbtn;
    ImageButton home;

    final Context context = this;
    float  w = 1.0f, x = 1.0f,y = 1.0f,z = 1.0f;
    TextView textOnCreate, textOnWindowFocusChanged;
    LinearLayout mainScreen;
    int[] locationOnScreen, cloud1loc, cloud2loc, cloud3loc, cloud4loc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_two);

        //Calculate Position of imageview on screen
        mainScreen = (LinearLayout)findViewById(R.id.mainscreen);
        textOnCreate = (TextView)findViewById(R.id.textView);
        textOnWindowFocusChanged = (TextView)findViewById(R.id.textView2);
        cloud1 = (ImageView)findViewById(R.id.imageView3);
        cloud2 = (ImageView) findViewById(R.id.stormcloud);
        cloud3 = (ImageView) findViewById(R.id.imageView2);
        cloud4 = (ImageView) findViewById(R.id.imageView4);

        //Image view controls
        UP = (TextView)findViewById(R.id.upFn);
        DOWN = (TextView)findViewById(R.id.downFn);
        LEFT = (TextView)findViewById(R.id.leftFn);
        RIGHT = (TextView)findViewById(R.id.rightFn);

        command = (TextView)findViewById(R.id.command);
        UP.setOnTouchListener(new ChoiceTouchListener());
        DOWN.setOnTouchListener(new ChoiceTouchListener());
        LEFT.setOnTouchListener(new ChoiceTouchListener());
        RIGHT.setOnTouchListener(new ChoiceTouchListener());
        command.setOnDragListener(new ChoiceDragListener());
        planeView = (ImageView) findViewById(R.id.imageView);
        runbtn = (Button)findViewById(R.id.runBtn);

        home = (ImageButton) findViewById(R.id.homeBtn2);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent = new Intent(level_two.this, mainMenu.class);
                startActivity(intent);

            }
        });


        TranslateAnimation translation;
        translation = new TranslateAnimation(0f, 0f, 0f, 10f);
        translation.setDuration(900);
        translation.setFillAfter(true);
        translation.setRepeatCount(1);
        translation.setRepeatMode(Animation.REVERSE);
        translation.setInterpolator(new AccelerateInterpolator());
        cloud1.startAnimation(translation);

        translation.setAnimationListener(new TranslateAnimation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                animation.setRepeatMode(Animation.REVERSE);
                cloud1.startAnimation(animation);
                readLocation(textOnWindowFocusChanged, "onWindowFocusChanged()");
            }
        });

        TranslateAnimation translation2;
        translation2 = new TranslateAnimation(0f, 0f, 0f, 20f);
        translation2.setDuration(500);
        translation2.setFillAfter(true);
        translation2.setRepeatCount(1);
        translation2.setRepeatMode(Animation.REVERSE);
        translation2.setInterpolator(new AccelerateInterpolator());
        cloud2.startAnimation(translation2);

        translation2.setAnimationListener(new TranslateAnimation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                readLocation(textOnWindowFocusChanged, "onWindowFocusChanged()");
                animation.setRepeatMode(Animation.REVERSE);
                cloud2.startAnimation(animation);
            }
        });

        TranslateAnimation translation3;
        translation3 = new TranslateAnimation(0f, 0f, 0f, -20f);
        translation3.setDuration(1000);
        translation3.setFillAfter(true);
        translation3.setRepeatCount(1);
        translation3.setRepeatMode(Animation.REVERSE);
        translation3.setInterpolator(new AccelerateInterpolator());
        cloud3.startAnimation(translation3);

        translation3.setAnimationListener(new TranslateAnimation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                animation.setRepeatMode(Animation.REVERSE);
                readLocation(textOnWindowFocusChanged, "onWindowFocusChanged()");
                cloud3.startAnimation(animation);
            }
        });

        TranslateAnimation translation4;
        translation4 = new TranslateAnimation(0f, 0f, 0f, -150f);
        translation4.setDuration(2000);
        translation4.setFillAfter(true);
        translation4.setRepeatCount(1);
        translation.setRepeatMode(Animation.REVERSE);
        translation4.setInterpolator(new AccelerateInterpolator());
        cloud4.startAnimation(translation4);

        translation4.setAnimationListener(new TranslateAnimation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {
                readLocation(textOnWindowFocusChanged, "onWindowFocusChanged()");
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                readLocation(textOnWindowFocusChanged, "onWindowFocusChanged()");
                animation.setRepeatMode(Animation.REVERSE);
                cloud4.startAnimation(animation);
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
            }
            else {
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
                    readLocation(textOnWindowFocusChanged, "onWindowFocusChanged()");
                    return true;

                case DragEvent.ACTION_DRAG_ENTERED:
                    readLocation(textOnWindowFocusChanged, "onWindowFocusChanged()");
                    //no action necessary
                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    readLocation(textOnWindowFocusChanged, "onWindowFocusChanged()");
                    //no action necessary
                    break;
                case DragEvent.ACTION_DROP:
                    readLocation(textOnWindowFocusChanged, "onWindowFocusChanged()");
                    //handle the dragged view being dropped over a target view
                    final View view = (View) event.getLocalState();
                    //stop displaying the view where it was before it was dragged
                    view.setVisibility(View.INVISIBLE);
                    //view dragged item is being dropped on
                    final TextView dropTarget = (TextView) v;
                    //view being dragged and dropped
                    final TextView dropped = (TextView) view;
                    //update the text in the target view to reflect the data being dropped
                    dropTarget.setText(dropped.getText());
                    //make it bold to highlight the fact that an item has been dropped
                    dropTarget.setTypeface(Typeface.DEFAULT_BOLD);


                    if (dropTarget.getText().equals("UP")) {
                            runbtn.setOnClickListener(new View.OnClickListener() {

                                @Override
                                public void onClick(View v) {
                                    w = w - 100.0f;
                                    x =  x - 100.0f;

                                    ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(planeView, "translationY", w);
                                    objectAnimator.addListener(new Animator.AnimatorListener() {
                                        @Override
                                        public void onAnimationStart(Animator animation) {

                                        }

                                        @Override
                                        public void onAnimationEnd(Animator animation) {

                                            readLocation(textOnWindowFocusChanged, "onWindowFocusChanged()");

                                            if(locationOnScreen[1] < 140 && locationOnScreen[0] == 32
                                                    || locationOnScreen[0] == 33 || locationOnScreen[0] == 133 ){



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
                                                                Intent intent = new Intent(level_two.this, com.example.edwardlafontant.gameindividualassignment4.level_two.class);
                                                                startActivity(intent);
                                                            }
                                                        })
                                                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                                            public void onClick(DialogInterface dialog, int which) {
                                                                finish();
                                                                Intent intent = new Intent(level_two.this, mainMenu.class);
                                                                startActivity(intent);
                                                            }
                                                        })
                                                        .setIcon(android.R.drawable.ic_dialog_alert)
                                                        .show();
                                            }

                                            if (locationOnScreen[1] < 140) {
                                                x = x + 100.0f;
                                                w = w + 100.0f;

                                                ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(planeView, "translationY", x);
                                                objectAnimator.addListener(new Animator.AnimatorListener() {
                                                    @Override
                                                    public void onAnimationStart(Animator animation) {

                                                    }

                                                    @Override
                                                    public void onAnimationEnd(Animator animation) {
                                                        readLocation(textOnWindowFocusChanged, "onWindowFocusChanged()");


                                                    }

                                                    @Override
                                                    public void onAnimationCancel(Animator animation) {

                                                    }

                                                    @Override
                                                    public void onAnimationRepeat(Animator animation) {

                                                    }
                                                });
                                                objectAnimator.setDuration(1000);
                                                objectAnimator.start();
                                            }
                                        }

                                        @Override
                                        public void onAnimationCancel(Animator animation) {

                                        }

                                        @Override
                                        public void onAnimationRepeat(Animator animation) {

                                        }
                                    });
                                    objectAnimator.setDuration(1000);
                                    objectAnimator.start();


                                    RIGHT.setVisibility(TextView.VISIBLE);
                                    DOWN.setVisibility(TextView.VISIBLE);
                                    LEFT.setVisibility(TextView.VISIBLE);
                                    UP.setVisibility(TextView.VISIBLE);

                                    //reset(view);
                                    dropTarget.setTag(null);

                                    readLocation(textOnWindowFocusChanged, "onWindowFocusChanged()");

                                }


                            });

                    }

                    if (dropTarget.getText().equals("LEFT")) {

                        runbtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                readLocation(textOnWindowFocusChanged, "onWindowFocusChanged()");

                                z = z - 100.0f;
                                y = y - 100.0f;



                                ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(planeView, "translationX", z);
                                objectAnimator.addListener(new Animator.AnimatorListener() {
                                    @Override
                                    public void onAnimationStart(Animator animation) {

                                    }

                                    @Override
                                    public void onAnimationEnd(Animator animation) {
                                        readLocation(textOnWindowFocusChanged, "onWindowFocusChanged()");

                                        float zi = z + 100.0f;


                                        if (locationOnScreen[0] < 0) {

                                            z = z + 100.0f;
                                            y = y + 100.0f;


                                            ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(planeView, "translationX", zi);
                                            objectAnimator.addListener(new Animator.AnimatorListener() {
                                                @Override
                                                public void onAnimationStart(Animator animation) {

                                                }

                                                @Override
                                                public void onAnimationEnd(Animator animation) {


                                                }

                                                @Override
                                                public void onAnimationCancel(Animator animation) {

                                                }

                                                @Override
                                                public void onAnimationRepeat(Animator animation) {

                                                }

                                            });
                                            objectAnimator.setDuration(1000);
                                            objectAnimator.start();

                                            readLocation(textOnWindowFocusChanged, "onWindowFocusChanged()");

                                        }

                                    }

                                    @Override
                                    public void onAnimationCancel(Animator animation) {

                                    }

                                    @Override
                                    public void onAnimationRepeat(Animator animation) {

                                    }
                                });
                                objectAnimator.setDuration(1000);
                                objectAnimator.start();


                                RIGHT.setVisibility(TextView.VISIBLE);
                                DOWN.setVisibility(TextView.VISIBLE);
                                LEFT.setVisibility(TextView.VISIBLE);
                                UP.setVisibility(TextView.VISIBLE);

                                //reset(view);
                                dropTarget.setTag(null);

                                readLocation(textOnWindowFocusChanged, "onWindowFocusChanged()");

                            }


                        });

                    }

                    if (dropTarget.getText().equals("DOWN")) {




                        runbtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                x =  x + 100.0f;
                                w = w + 100.0f;

                                ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(planeView, "translationY", x);
                                objectAnimator.addListener(new Animator.AnimatorListener() {
                                    @Override
                                    public void onAnimationStart(Animator animation) {

                                    }

                                    @Override
                                    public void onAnimationEnd(Animator animation) {

                                        readLocation(textOnWindowFocusChanged, "onWindowFocusChanged()");


                                        if (locationOnScreen[1] > 950) {
                                            x = x - 100.0f;
                                            w = w - 100.0f;

                                            ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(planeView, "translationY", x);
                                            objectAnimator.addListener(new Animator.AnimatorListener() {
                                                @Override
                                                public void onAnimationStart(Animator animation) {

                                                }

                                                @Override
                                                public void onAnimationEnd(Animator animation) {
                                                    readLocation(textOnWindowFocusChanged, "onWindowFocusChanged()");

                                                }

                                                @Override
                                                public void onAnimationCancel(Animator animation) {

                                                }

                                                @Override
                                                public void onAnimationRepeat(Animator animation) {

                                                }
                                            });
                                            objectAnimator.setDuration(1000);
                                            objectAnimator.start();
                                            readLocation(textOnWindowFocusChanged, "onWindowFocusChanged()");

                                        }

                                    }

                                    @Override
                                    public void onAnimationCancel(Animator animation) {

                                    }

                                    @Override
                                    public void onAnimationRepeat(Animator animation) {

                                    }
                                });
                                objectAnimator.setDuration(1000);
                                objectAnimator.start();


                                RIGHT.setVisibility(TextView.VISIBLE);
                                DOWN.setVisibility(TextView.VISIBLE);
                                LEFT.setVisibility(TextView.VISIBLE);
                                UP.setVisibility(TextView.VISIBLE);

                                //reset(view);
                                dropTarget.setTag(null);
                                readLocation(textOnWindowFocusChanged, "onWindowFocusChanged()");
                            }


                        });

                    }
                    if (dropTarget.getText().equals("RIGHT")) {

                        runbtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                y = y + 100.0f;
                                z = z + 100.0f;


                                ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(planeView, "translationX", y);
                                objectAnimator.addListener(new Animator.AnimatorListener() {
                                    public Context ctx;

                                    @Override
                                    public void onAnimationStart(Animator animation) {

                                    }

                                    @Override
                                    public void onAnimationEnd(Animator animation) {
                                        readLocation(textOnWindowFocusChanged, "onWindowFocusChanged()");

                                        if (locationOnScreen[0] >= 1133) {
                                            MediaPlayer mp = MediaPlayer.create(context, R.raw.file);
                                            mp.start();
                                            ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(planeView, "translationX", y + 100.0f);
                                            objectAnimator.setDuration(1000);
                                            objectAnimator.start();

                                            ImageView imageView = new ImageView(getApplicationContext());
                                            imageView.setBackgroundResource(R.drawable.successanimation);
                                            AnimationDrawable anim = (AnimationDrawable) imageView.getBackground();
                                            anim.start();

                                            AlertDialog dialog = new AlertDialog.Builder(level_two.this)
                                                    .setView(imageView)
                                                    .setMessage("You have successfully made it through the storm clouds!")
                                                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                                        @Override
                                                        public void onClick(DialogInterface dialog, int which) {

                                                            finish();
                                                            Intent intent = new Intent(level_two.this, mainMenu.class);
                                                            startActivity(intent);
                                                            dialog.dismiss();
                                                            mainMenu.attempts = 3;
                                                        }
                                                    }).create();

                                            dialog.show();

                                        }

                                    }

                                    @Override
                                    public void onAnimationCancel(Animator animation) {

                                    }

                                    @Override
                                    public void onAnimationRepeat(Animator animation) {

                                    }
                                });


                                objectAnimator.setDuration(1000);
                                objectAnimator.start();


                                RIGHT.setVisibility(TextView.VISIBLE);
                                DOWN.setVisibility(TextView.VISIBLE);
                                LEFT.setVisibility(TextView.VISIBLE);
                                UP.setVisibility(TextView.VISIBLE);

                                //dropTarget.setText("Enter A Command");
                                dropTarget.setTag(null);

                                readLocation(textOnWindowFocusChanged, "onWindowFocusChanged()");

                            }


                        });

                    }

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
            UP.setVisibility(TextView.VISIBLE);
            command.setText("Enter A Command");
            UP.setTag(null);
            command.setTypeface(Typeface.DEFAULT);
            command.setOnDragListener(new ChoiceDragListener());
        }


    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        // TODO Auto-generated method stub
        super.onWindowFocusChanged(hasFocus);

        readLocation(textOnWindowFocusChanged, "onWindowFocusChanged()");
    }

    private void readLocation(TextView tv, String status){
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int offsetX = displayMetrics.widthPixels - mainScreen.getMeasuredWidth();
        int offsetY = displayMetrics.heightPixels - mainScreen.getMeasuredHeight();

        int[] locationInWindow = new int[2];
        planeView.getLocationInWindow(locationInWindow);
        locationOnScreen = new int[2];
        cloud1loc = new int[2];
        cloud2loc = new int [2];
        cloud3loc = new int[2];
        cloud4loc = new int[2];
        planeView.getLocationOnScreen(locationOnScreen);
        cloud1.getLocationOnScreen(cloud1loc);
        cloud2.getLocationOnScreen(cloud2loc);
        cloud3.getLocationOnScreen(cloud3loc);
        cloud4.getLocationOnScreen(cloud4loc);

        tv.setText(
                "\n" + status + "\n"
                        + "getLocationInWindow() - " + locationInWindow[0] + " : " + locationInWindow[1] + "\n"
                        + "getLocationOnScreen() - " + locationOnScreen[0] + " : " + locationOnScreen[1] + "\n"
                        + "Offset x: y - " + offsetX + " : " + offsetY);

        if(planeView.getTop()  == cloud4.getTop()){

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
                            Intent intent = new Intent(com.example.edwardlafontant.gameindividualassignment4.level_two.this, com.example.edwardlafontant.gameindividualassignment4.level_two.class);
                            startActivity(intent);
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                            Intent intent = new Intent(com.example.edwardlafontant.gameindividualassignment4.level_two.this, mainMenu.class);
                            startActivity(intent);
                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();

        }
    }
}





















