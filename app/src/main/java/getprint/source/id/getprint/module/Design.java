package getprint.source.id.getprint.module;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import getprint.source.id.getprint.R;
import getprint.source.id.getprint.menu.DrawerActivity;

/**
 * Created by Chandra on 11/20/2016.
 */
public class Design extends DrawerActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.design);
        Intent iin = getIntent();
        Bundle b = iin.getExtras();
        final String img = (String) b.get("img");
        final String label = (String) b.get("label");
        final String sizeName = (String) b.get("sizeName");
        final String price = (String) b.get("price");
        ImageView imgSrc=(ImageView) findViewById(R.id.imageProd);
        Resources resources = this.getResources();
        int source = resources.getIdentifier(img, "drawable", this.getPackageName());
        imgSrc.setImageResource(source);
        ImageView balik=(ImageView) findViewById(R.id.balik_item);
        ImageView cross=(ImageView) findViewById(R.id.cross);
        ImageView bentuk_bulat=(ImageView) findViewById(R.id.bentuk_bulat);
        ImageView bentuk_kotak=(ImageView) findViewById(R.id.bentuk_kotak);
        ImageView bentuk_pp=(ImageView) findViewById(R.id.bentuk_pp);
        ImageView bentuk_oval=(ImageView) findViewById(R.id.bentuk_oval);
        //ImageView bentuk_design=(ImageView) findViewById(R.id.bentuk_design);
        balik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Design.this, Order.class);
                intent.putExtra("label", label);
                intent.putExtra("img", String.valueOf(img));
                intent.putExtra("label", String.valueOf(label));
                intent.putExtra("sizeName", sizeName);
                intent.putExtra("price", price);
                intent.putExtra("bentuk", "");
                startActivity(intent);
            }
        });
        cross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Design.this, Order.class);
                intent.putExtra("label", label);
                intent.putExtra("img", String.valueOf(img));
                intent.putExtra("label", String.valueOf(label));
                intent.putExtra("sizeName", sizeName);
                intent.putExtra("price", price);
                intent.putExtra("bentuk", "");
                startActivity(intent);
            }
        });
        bentuk_bulat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Design.this, Order.class);
                intent.putExtra("label", label);
                intent.putExtra("img", String.valueOf(img));
                intent.putExtra("label", String.valueOf(label));
                intent.putExtra("sizeName", sizeName);
                intent.putExtra("price", price);
                intent.putExtra("bentuk", "Bulat");
                startActivity(intent);
            }
        });
        bentuk_kotak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Design.this, Order.class);
                intent.putExtra("label", label);
                intent.putExtra("img", String.valueOf(img));
                intent.putExtra("label", String.valueOf(label));
                intent.putExtra("sizeName", sizeName);
                intent.putExtra("price", price);
                intent.putExtra("bentuk", "Kotak");
                startActivity(intent);
            }
        });
        bentuk_pp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Design.this, Order.class);
                intent.putExtra("label", label);
                intent.putExtra("img", String.valueOf(img));
                intent.putExtra("label", String.valueOf(label));
                intent.putExtra("sizeName", sizeName);
                intent.putExtra("price", price);
                intent.putExtra("bentuk", "Persegi Panjang");
                startActivity(intent);
            }
        });
        bentuk_oval.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Design.this, Order.class);
                intent.putExtra("label", label);
                intent.putExtra("img", String.valueOf(img));
                intent.putExtra("label", String.valueOf(label));
                intent.putExtra("sizeName", sizeName);
                intent.putExtra("price", price);
                intent.putExtra("bentuk", "Oval");
                startActivity(intent);
            }
        });
        /*bentuk_design.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Design.this, Upload_design.class);
                intent.putExtra("label", label);
                intent.putExtra("img", String.valueOf(img));
                intent.putExtra("label", String.valueOf(label));
                intent.putExtra("sizeName", sizeName);
                intent.putExtra("price", price);
                intent.putExtra("bentuk", "Custom");
                startActivity(intent);
            }
        });*/
    }
}
