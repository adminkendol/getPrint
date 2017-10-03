package getprint.source.id.getprint.module;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import getprint.source.id.getprint.R;
import getprint.source.id.getprint.menu.DrawerActivity;

/**
 * Created by Chandra on 11/20/2016.
 */
public class Upload_design extends DrawerActivity {
    private String img;
    private String label;
    private String bentuk;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.upload_design);
        Intent iin= getIntent();
        Bundle b = iin.getExtras();
        img = (String) b.get("img");
        label = (String) b.get("label");
        bentuk = (String) b.get("bentuk");
        ImageView balik=(ImageView)findViewById(R.id.balik);
        ImageView next=(ImageView)findViewById(R.id.next);
        balik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Upload_design.this, Design.class);
                intent.putExtra("img", String.valueOf(img));
                intent.putExtra("label", String.valueOf(label));
                intent.putExtra("sizeName", "6cm");
                intent.putExtra("price", "Rp.45.000,-/100pcs");
                intent.putExtra("bentuk", bentuk);
                startActivity(intent);
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Upload_design.this, Form_design.class);
                intent.putExtra("img", String.valueOf(img));
                intent.putExtra("label", String.valueOf(label));
                intent.putExtra("sizeName", "6cm");
                intent.putExtra("price", "Rp.45.000,-/100pcs");
                intent.putExtra("bentuk", bentuk);
                startActivity(intent);
            }
        });
    }
}
