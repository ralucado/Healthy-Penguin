package com.webs.asciipenguins.healthypenguin;

        import android.app.Activity;
        import android.content.Context;
        import android.content.Intent;
        import android.content.SharedPreferences;
        import android.os.Bundle;
        import android.os.Handler;
        import android.util.Log;
        import android.widget.Button;
        import android.widget.ImageView;
        import android.view.View;
        import android.view.View.OnClickListener;


public class MainActivity extends Activity {

    public static final String CHANGING_STATE_KEY = "changingState";
    public static final String EATING_KEY = "eating";
    public static final String STATE_KEY = "state";
    Button button;
    Button button2;
    ImageView image;
    int frames = 10;
    float deltaTime = 1.0f/frames;

    Handler handler;

    SharedPreferences prefs;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        final Context context = this;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        image = (ImageView) findViewById(R.id.imageView1);

        button = (Button) findViewById(R.id.btnChangeImage);
        button.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(context, statsActivity.class);
                startActivity(intent);

            }

        });

        button2 = (Button) findViewById(R.id.btnChangeImageStats);

        button2.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(context, GridActivity.class);
                startActivity(intent);

            }

        });

        handler = new Handler();

        final Runnable r = new Runnable() {
            public void run() {
                handler.postDelayed(this, (long) (deltaTime*1000)); // 10 frames
                updatePenguin();
            }
        };

        handler.postDelayed(r, (long) (deltaTime*1000));

        prefs = this.getSharedPreferences("penguin", Context.MODE_PRIVATE);
    }


    public enum PenguinState {
        bad,
        die,
        eating,
        fun,
        gnye,
        happy,
        moving,
        sad
    }

    PenguinState state = PenguinState.eating;
    int currentAnimation = 0;
    float elapsed = 0;

    float[] badTimer = {0.5f,0.5f};
    int[] badId = {R.drawable.badpen,R.drawable.badpen2};

    float[] dieTimer = {0.2f,0.2f};
    int[] dieId = {R.drawable.diepen1,R.drawable.diepen2};

    float[] eatingTimer = {0.5f,0.7f,0.4f,0.2f,0.2f,0.2f,0.2f,0.2f};
    int[] eatingId = {R.drawable.eatingpen,R.drawable.eatingpen2,R.drawable.eatingpen3,R.drawable.eatingpen4,R.drawable.eatingpen3,R.drawable.eatingpen4,R.drawable.eatingpen3,R.drawable.eatingpen4};

    float[] gnyeTimer = {0.2f,0.2f};
    int[] gnyeId = {R.drawable.gnyepen,R.drawable.gnyepen2};

    float[] happyTimer = {0.2f,0.2f};
    int[] happyId = {R.drawable.happypen,R.drawable.happypen2};

    float[] movingTimer = {0.2f,0.2f,0.2f,0.2f};
    int[] movingId = {R.drawable.movingpen,R.drawable.movingpen2,R.drawable.movingpen3,R.drawable.movingpen4};

    float[] sadTimer = {0.2f,0.2f};
    int[] sadId = {R.drawable.sadpen,R.drawable.sadpen2};

    private void updatePenguin() {
        state = PenguinState.values()[prefs.getInt(STATE_KEY,PenguinState.fun.ordinal())];
        if (prefs.getBoolean(EATING_KEY,false)) state = PenguinState.eating;
        if (prefs.getBoolean(CHANGING_STATE_KEY,true)) {
            currentAnimation = 0;
            elapsed = 0;
            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean(CHANGING_STATE_KEY, false).apply();
        }
        elapsed += deltaTime;
        switch (state) {
            case bad:
                if (elapsed > badTimer[currentAnimation]) {
                    currentAnimation = (currentAnimation+1)%2;
                    elapsed = 0;
                    image.setImageResource(badId[currentAnimation]);
                }
                break;
            case die:
                if (elapsed > dieTimer[currentAnimation]) {
                    currentAnimation = (currentAnimation+1)%2;
                    elapsed = 0;
                    image.setImageResource(dieId[currentAnimation]);
                }
                break;
            case eating:
                if (elapsed > eatingTimer[currentAnimation]) {
                    currentAnimation = (currentAnimation+1);
                    if (currentAnimation >= 8) {
                        SharedPreferences.Editor editor = prefs.edit();
                        editor.putBoolean(EATING_KEY, false).apply();
                        currentAnimation = 0;
                        elapsed = 0;
                    }
                    elapsed = 0;
                    image.setImageResource(eatingId[currentAnimation]);
                }
                break;
            case fun:
                image.setImageResource(R.drawable.funpen);
                break;
            case gnye:
                if (elapsed > gnyeTimer[currentAnimation]) {
                    currentAnimation = (currentAnimation+1)%2;
                    elapsed = 0;
                    image.setImageResource(gnyeId[currentAnimation]);
                }
                break;
            case happy:
                if (elapsed > happyTimer[currentAnimation]) {
                    currentAnimation = (currentAnimation+1)%2;
                    elapsed = 0;
                    image.setImageResource(happyId[currentAnimation]);
                }
                break;
            case moving:
                if (elapsed > movingTimer[currentAnimation]) {
                    currentAnimation = (currentAnimation+1)%4;
                    elapsed = 0;
                    image.setImageResource(movingId[currentAnimation]);
                }
                break;
            case sad:
                if (elapsed > sadTimer[currentAnimation]) {
                    currentAnimation = (currentAnimation+1)%2;
                    elapsed = 0;
                    image.setImageResource(sadId[currentAnimation]);
                }
                break;
            default:
                break;
        }

        // image.setImageResource(R.drawable.penguin2);
    }


}
