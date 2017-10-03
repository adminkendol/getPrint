package getprint.source.id.getprint.module;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import getprint.source.id.getprint.MainActivity;
import getprint.source.id.getprint.R;
import getprint.source.id.getprint.config.Session;

/**
 * Created by Chandra on 11/12/2016.
 */
public class Signin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signin);
        Session session =new Session(this);
        Button login =(Button)findViewById(R.id.login);
        Button register =(Button)findViewById(R.id.register);
        String token =session.getToken();
        Log.d("TOKEN:", token);
        Class klas=null;
        if(token!=""){
            login.setText("Profile");
            klas = Tab_pengaturan.class;
        }else{
            login.setText("Log In");
            klas = Login.class;
        }
        final Class finalKlas = klas;
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(Signin.this, finalKlas);
                startActivity(intent);
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(Signin.this, Daftar.class);
                startActivity(intent);
            }
        });
    }
    public void onBackPressed(){
        Intent intent;
        intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
