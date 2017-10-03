package getprint.source.id.getprint.module;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import getprint.source.id.getprint.MainActivity;
import getprint.source.id.getprint.R;

/**
 * Created by Chandra on 11/11/2016.
 */
public class Thanks extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thankreg);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        ImageView logo =(ImageView) findViewById(R.id.tv_header_title);
        logo.setVisibility(View.GONE);
        TextView mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        mTitle.setVisibility(View.VISIBLE);
        toolbar.setTitle("");
        mTitle.setText("Registrasi Selesai");

        setSupportActionBar(toolbar);
        Intent iin= getIntent();
        Bundle b = iin.getExtras();
        String label = (String) b.get("nama");
        TextView nama =(TextView)findViewById(R.id.labelName);
        nama.setText(label);
        TextView submit =(TextView)findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(Thanks.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
