package com.webs.asciipenguins.healthypenguin;

        import android.app.Activity;
        import android.content.Context;
        import android.content.Intent;
        import android.os.Bundle;
        import android.util.Log;
        import android.widget.Button;
        import android.widget.ImageView;
        import android.view.View;
        import android.view.View.OnClickListener;

public class MainActivity extends Activity {

    Button button;
    Button button2;
    ImageView image;

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
                //image.setImageResource(R.drawable.penguin2);
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


    }


}
