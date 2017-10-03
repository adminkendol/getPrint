package getprint.source.id.getprint.module;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import getprint.source.id.getprint.MainActivity;
import getprint.source.id.getprint.R;
import getprint.source.id.getprint.adapters.Plugin;
import getprint.source.id.getprint.config.Session;

/**
 * Created by Chandra on 11/12/2016.
 */
public class Login extends AppCompatActivity {
    private RelativeLayout rlLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        setContentView(R.layout.login);
        rlLogin =(RelativeLayout) findViewById(R.id.rlLogin);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        ImageView logo = (ImageView) findViewById(R.id.tv_header_title);
        logo.setVisibility(View.GONE);

        TextView mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        mTitle.setVisibility(View.VISIBLE);
        toolbar.setTitle("");
        mTitle.setText("Masuk");

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(Login.this, MainActivity.class);
                startActivity(intent);
            }
        });

        TextView submit =(TextView)findViewById(R.id.submitLogin);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText akun=(EditText)findViewById(R.id.akun);
                EditText password=(EditText)findViewById(R.id.password);
                String akunX= String.valueOf(akun.getText());
                String passX= String.valueOf(password.getText());
                String passXX=md5(passX);
                String[] arg ={akunX,passX};
                if (!validate(arg)) {
                    onSuck("Submit error, please check parameter !");
                }else {
                    /*List<NameValuePair> param = new ArrayList<NameValuePair>();
                    param.add(new BasicNameValuePair("email", akunX));
                    param.add(new BasicNameValuePair("password", passXX));
                    Log.d("param Login:", String.valueOf(param));*/

                    List<NameValuePair> parameter = new ArrayList<NameValuePair>();
                    parameter.add(new BasicNameValuePair("email", akunX));
                    parameter.add(new BasicNameValuePair("password", passXX));
                    //new PostData(Login.this,param,"login","").execute();
                    new HitSignIn(parameter).execute();
                }
            }
        });
    }
    public String md5(String pass){
        Plugin encode = new Plugin();
        return encode.md5(pass);
    }
    public boolean validate(String[] arg) {
        boolean valid = true;
        if(arg[0].isEmpty() || arg[1].isEmpty()){
            valid = false;
        }else{
            valid = true;
        }

        return valid;
    }
    public void onSuck(String msg) {
        Snackbar snackbar = Snackbar.make(rlLogin, msg, Snackbar.LENGTH_LONG);
        View snackBarView = snackbar.getView();
        snackBarView.setBackgroundColor(Color.RED);
        snackbar.show();
    }

    public void onBackPressed(){
        Intent intent;
        intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    class HitSignIn extends AsyncTask<String, Void, String> {
        private ProgressDialog pDialog;
        String token;
        View view;
        List<NameValuePair> parameter;
        public HitSignIn(List<NameValuePair> parameter) {
            this.parameter=parameter;
        }


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = ProgressDialog.show(Login.this, null, null, true);
            pDialog.setContentView(R.layout.loading);
            TextView textProg = (TextView) pDialog.findViewById(R.id.textProg);
            textProg.setText("Get Profile...");
            pDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            pDialog.show();
        }

        @Override
        protected String doInBackground(String... sText) {
            Plugin fire = new Plugin();
            String login = fire.hitLogin(parameter);
            Log.d("STRING LOGIN:", login);
            JSONObject dataLogin = null;
            try {
                dataLogin = new JSONObject(login);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return String.valueOf(dataLogin);
        }

        @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            if (pDialog.isShowing())
                pDialog.dismiss();
            Log.d("RESULT LOGIN:", String.valueOf(result));
            if ((result.equals("Exception Caught")) || (String.valueOf(result).equals("null"))) {
                onSuck("Koneksi gagal, coba lagi !");
            } else {
                JSONObject resLogin = null;
                try {
                    resLogin = new JSONObject(result);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                String tokenX=null;
                String status=null;
                String message=null;
                JSONObject data=null;
                try {
                    status=resLogin.getString("status");
                    message=resLogin.getString("message");
                    data = resLogin.getJSONObject("data");

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                if(status.equals("success")){
                    try {
                        tokenX = data.getString("token");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    Session session = new Session(Login.this);
                    session.setToken(tokenX);
                    Intent intent = new Intent(Login.this, MainActivity.class);
                    Login.this.startActivity(intent);
                }else if(status.equals("error")){
                    onSuck(message);
                }
            }
        }
    }
}
