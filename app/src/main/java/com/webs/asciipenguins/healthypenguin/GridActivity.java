package com.webs.asciipenguins.healthypenguin;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import android.widget.GridView;
import android.widget.AdapterView;

public class GridActivity  extends Activity {

    SharedPreferences prefs;

    public void onCreate(Bundle savedInstanceState) {

        final int [] prgmImages={       R.drawable.water,   R.drawable.coke,   R.drawable.candy,    R.drawable.pizza,       R.drawable.apple,   R.drawable.beans,   R.drawable.vegetables,  R.drawable.egg, R.drawable.meat,R.drawable.fish,R.drawable.milk,R.drawable.bread,   R.drawable.pasta,   R.drawable.cereals, R.drawable.potato};
        final String [] prgmNameList={  "Agua",             "Refrescos",        "Dulces",           "Picsa",                "Fruta",            "Legumbres",        "Verduras",             "Huevos",       "Carne",        "Pescado",      "Làcticos",     "Pan",              "Pasta",            "Cereales"        ,"Patatas"};

        prefs = this.getSharedPreferences("penguin", Context.MODE_PRIVATE);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.icongrid_layout);

        GridView gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(new ImageAdapter(this));

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Toast.makeText(GridActivity.this, "Yum Yum! " + prgmNameList[position], Toast.LENGTH_SHORT).show();
                SharedPreferences.Editor editor = prefs.edit();
                editor.putBoolean(MainActivity.EATING_KEY, true);
                editor.putBoolean(MainActivity.CHANGING_STATE_KEY, true);
                switch (prgmImages[position]) {
                    case R.drawable.water:
                        editor.putInt(MainActivity.STATE_KEY, MainActivity.PenguinState.fun.ordinal());
                        break;
                    case R.drawable.apple:
                        editor.putInt(MainActivity.STATE_KEY, MainActivity.PenguinState.happy.ordinal());
                        break;

                }
                editor.commit();
            }
        });
    }


}
