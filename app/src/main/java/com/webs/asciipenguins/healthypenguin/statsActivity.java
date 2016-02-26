package com.webs.asciipenguins.healthypenguin;

        import java.util.ArrayList;

        import android.os.Bundle;
        import android.app.Activity;
        import android.content.Context;
        import android.view.Menu;

        import android.widget.ListView;

public class statsActivity extends Activity {

    ListView lv;
    Context context;

    ArrayList prgmName;
    public static int [] prgmImages={ R.drawable.bread, R.drawable.candy, R.drawable.cereals, R.drawable.croissant,R.drawable.i5,R.drawable.i1,R.drawable.i1,R.drawable.i1,R.drawable.i1,R.drawable.i1,R.drawable.i1,R.drawable.i1,R.drawable.i1,R.drawable.i1,R.drawable.i1,R.drawable.i1};
    public static String [] prgmNameList={"Agua","Refrescos", "Dulces","Picsa","Fruta","Legumbres", "Verduras","Huevos","Carne","Pescado","Làcticos","Pan","Pasta","Cereales","Arroz","Patatas"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stats_layout);

        context=this;

        lv=(ListView) findViewById(R.id.listView);
        lv.setAdapter(new CustomAdapter(this, prgmNameList,prgmImages));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

}