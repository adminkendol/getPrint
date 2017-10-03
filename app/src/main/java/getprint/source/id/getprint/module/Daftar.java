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
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import getprint.source.id.getprint.MainActivity;
import getprint.source.id.getprint.R;
import getprint.source.id.getprint.adapters.Plugin;
import getprint.source.id.getprint.adapters.PostData;
import getprint.source.id.getprint.adapters.SpecialAdapterB;

/**
 * Created by Chandra on 11/9/2016.
 */
public class Daftar extends AppCompatActivity{

    private String label;
    private ListView lv;
    private ListView lvB;
    ArrayList<HashMap<String, String>> cityList;
    ArrayList<HashMap<String, String>> provList;
    private RelativeLayout rlForm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        setContentView(R.layout.daftar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        ImageView logo =(ImageView) findViewById(R.id.tv_header_title);
        logo.setVisibility(View.GONE);
        /*Intent iin= getIntent();
        Bundle b = iin.getExtras();
        label = (String) b.get("title");*/
        TextView mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        mTitle.setVisibility(View.VISIBLE);
        toolbar.setTitle("");
        mTitle.setText("Daftar");

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(Daftar.this, MainActivity.class);
                startActivity(intent);
            }
        });
        RelativeLayout pilih_prov_button=(RelativeLayout)findViewById(R.id.rlProv);
        pilih_prov_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lvB.setVisibility(View.VISIBLE);
            }
        });
        rlForm =(RelativeLayout) findViewById(R.id.rlForm);
        RelativeLayout pilih_button=(RelativeLayout)findViewById(R.id.rlCity);
        pilih_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lv.setVisibility(View.VISIBLE);
            }
        });
        provList = new ArrayList<>();
        cityList = new ArrayList<>();
        lvB = (ListView) findViewById(R.id.listProv);
        lv = (ListView) findViewById(R.id.listCity);
        lvB.setOnTouchListener(new ListView.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        v.getParent().requestDisallowInterceptTouchEvent(true);
                        break;
                    case MotionEvent.ACTION_UP:
                        v.getParent().requestDisallowInterceptTouchEvent(false);
                        break;
                }
                v.onTouchEvent(event);
                return true;
            }
        });
        lv.setOnTouchListener(new ListView.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        v.getParent().requestDisallowInterceptTouchEvent(true);
                        break;
                    case MotionEvent.ACTION_UP:
                        v.getParent().requestDisallowInterceptTouchEvent(false);
                        break;
                }
                v.onTouchEvent(event);
                return true;
            }
        });
        new GetProv().execute();

        TextView submit =(TextView)findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText name=(EditText)findViewById(R.id.nama);
                TextView cityText= (TextView)findViewById(R.id.city_id);
                TextView emailText= (TextView)findViewById(R.id.email_reg);
                TextView passText= (TextView)findViewById(R.id.password_reg);
                TextView alamatText= (TextView)findViewById(R.id.alamat_reg);
                TextView kontakText= (TextView)findViewById(R.id.kontak_reg);

                String nameX= String.valueOf(name.getText());
                String cityX= String.valueOf(cityText.getText());
                String emailX= String.valueOf(emailText.getText());
                String passX= String.valueOf(passText.getText());
                String md5Pass = md5(passX);
                String alamatX= String.valueOf(alamatText.getText());
                String kontakX= String.valueOf(kontakText.getText());
                String[] arg ={nameX,cityX,emailX,passX,alamatX,kontakX};
                if (!validate(arg)) {
                    onSuck(1);
                }else {
                    List<NameValuePair> param = new ArrayList<NameValuePair>();
                    param.add(new BasicNameValuePair("name", nameX));
                    param.add(new BasicNameValuePair("city", cityX));
                    param.add(new BasicNameValuePair("email", emailX));
                    param.add(new BasicNameValuePair("password", md5Pass));
                    param.add(new BasicNameValuePair("address", alamatX));
                    param.add(new BasicNameValuePair("contact_name", kontakX));
                    Log.d("param Reg:", String.valueOf(param));
                    new PostData(Daftar.this,param,"user",nameX).execute();
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
        if(arg[0].isEmpty() || arg[1].isEmpty() || arg[2].isEmpty() || arg[3].isEmpty() || arg[4].isEmpty()|| arg[5].isEmpty()){
            valid = false;
        }else{
            valid = true;
        }
        /*if(!android.util.Patterns.EMAIL_ADDRESS.matcher(emailT).matches()){
            valid = false;
        }else{
            valid = true;
        }*/

        return valid;
    }
    public void onSuck(int s) {
        String msg="";
        if(s==1){
            msg="Submit error, please check parameter !";
        }else{
            msg="Submit error, please check internet connection !";
        }
        Snackbar snackbar = Snackbar.make(rlForm, msg, Snackbar.LENGTH_LONG);
        View snackBarView = snackbar.getView();
        snackBarView.setBackgroundColor(Color.RED);
        snackbar.show();
    }

    public void onBackPressed(){
        Intent intent;
        intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    class GetProv extends AsyncTask<String, Void, String> {
        private ProgressDialog pDialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            /*pDialog = new ProgressDialog(Daftar.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);*/

            pDialog = ProgressDialog.show(Daftar.this, null, null, true);
            pDialog.setContentView(R.layout.loading);
            TextView textProg=(TextView)pDialog.findViewById(R.id.textProg);
            textProg.setText("Get Province...");
            pDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            pDialog.show();
        }
        @Override
        protected String doInBackground(String... sText) {
            Plugin fire = new Plugin();
            String prov = fire.getProv();
            Log.d("STRING PROV:", prov);
            JSONArray dataProv = null;
            try {
                dataProv = new JSONArray(prov);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            //Log.d("STRING ARRAY CITY:", String.valueOf(dataCity));

            return String.valueOf(dataProv);
        }
        @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();
            /**
             * Updating parsed JSON data into ListView
             * */
            Log.d("RESULT CITY:", String.valueOf(result));
            if((result.equals("Exception Caught"))||(String.valueOf(result).equals("null"))) {
                Snackbar snackbar = Snackbar
                        .make(rlForm, "Koneksi gagal, coba lagi !", Snackbar.LENGTH_INDEFINITE)
                        .setAction("Retry", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intentB = new Intent(Daftar.this, Daftar.class);
                                intentB.putExtra("title", label);
                                startActivity(intentB);
                            }
                        });
                View snackBarView = snackbar.getView();
                //snackBarView.setBackgroundColor(Color.parseColor("#00c799"));
                snackBarView.setBackgroundColor(Color.RED);
                snackbar.show();
            } else {
                JSONArray resProv =null;
                try {
                    resProv = new JSONArray(result);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                for (int i = 0; i < resProv.length(); i++) {
                    JSONObject c = null;
                    try {
                        c = resProv.getJSONObject(i);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    String id=null;
                    String name=null;
                    try {
                        id=c.getString("id");
                        name=c.getString("nama");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    HashMap<String, String> prov = new HashMap<>();
                    prov.put("provName",name);
                    prov.put("provId", id);
                    //Log.d("GET ARRAY PROV:", String.valueOf(prov));
                    provList.add(prov);
                }
                ListAdapter adapterB = new SpecialAdapterB(
                        Daftar.this, provList,
                        R.layout.list_prov, new String[]{"provName", "provId"}, new int[]{R.id.provName, R.id.provId}, new String[]{"", "", ""});
                lvB.setAdapter(adapterB);
                lvB.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String prov_name =((TextView)view.findViewById(R.id.provName)).getText().toString();
                        String prov_id =((TextView)view.findViewById(R.id.provId)).getText().toString();
                        TextView textPilihProv=(TextView)Daftar.this.findViewById(R.id.textPilihProv);
                        TextView pid=(TextView)Daftar.this.findViewById(R.id.prov_id);
                        textPilihProv.setText(prov_name);
                        pid.setText(prov_id);
                        lvB.setVisibility(View.GONE);
                        //Log.d("PROV NAME SELECTED:",city_name);
                        new GetCity(prov_id).execute();
                    }
                });
            }
        }
    }
    class GetCity extends AsyncTask<String, Void, String> {
        private ProgressDialog pDialog;
        String prov_id;
        TextView textView6;
        public GetCity(String prov_id) {
            this.prov_id=prov_id;
            this.textView6=(TextView)Daftar.this.findViewById(R.id.textView6);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = ProgressDialog.show(Daftar.this, null, null, true);
            pDialog.setContentView(R.layout.loading);
            TextView textProg=(TextView)pDialog.findViewById(R.id.textProg);
            textProg.setText("Get City...");
            pDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            pDialog.show();
            pDialog.show();
        }
        @Override
        protected String doInBackground(String... sText) {
            Plugin fire = new Plugin();
            String city = fire.getCity(prov_id);
            Log.d("STRING CITY:", city);
            JSONArray dataCity = null;
            try {
                dataCity = new JSONArray(city);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            Log.d("STRING ARRAY CITY:", String.valueOf(dataCity));

            return String.valueOf(dataCity);
        }
        @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();
            /**
             * Updating parsed JSON data into ListView
             * */
            RelativeLayout rlForm =(RelativeLayout) findViewById(R.id.rlForm);
            Log.d("RESULT CITY:", String.valueOf(result));
            if((result.equals("Exception Caught"))||(String.valueOf(result).equals("null"))) {
                Snackbar snackbar = Snackbar
                        .make(rlForm, "Koneksi gagal, coba lagi !", Snackbar.LENGTH_INDEFINITE)
                        .setAction("Retry", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intentB = new Intent(Daftar.this, Daftar.class);
                                intentB.putExtra("title", label);
                                startActivity(intentB);
                            }
                        });
                View snackBarView = snackbar.getView();
                snackBarView.setBackgroundColor(Color.RED);
                snackbar.show();
            } else {
                JSONArray resCity =null;
                try {
                    resCity = new JSONArray(result);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                cityList.clear();
                textView6.setText("Pilih");
                for (int i = 0; i < resCity.length(); i++) {
                    JSONObject c = null;
                    try {
                        c = resCity.getJSONObject(i);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    String id=null;
                    String name=null;
                    try {
                        id=c.getString("id");
                        name=c.getString("nama");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    HashMap<String, String> kota = new HashMap<>();
                    kota.put("cityName",name);
                    kota.put("cityId", id);
                    //Log.d("GET ARRAY CITY:", String.valueOf(kota));
                    cityList.add(kota);
                }
                ListAdapter adapter = new SpecialAdapterB(
                        Daftar.this, cityList,
                        R.layout.list_city, new String[]{"cityName", "cityId"}, new int[]{R.id.cityName, R.id.cityId}, new String[]{"", "",""});
                lv.setAdapter(adapter);
                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String city_name =((TextView)view.findViewById(R.id.cityName)).getText().toString();
                        String city_id =((TextView)view.findViewById(R.id.cityId)).getText().toString();
                        TextView cId=(TextView)Daftar.this.findViewById(R.id.city_id);
                        textView6.setText(city_name);
                        cId.setText(city_id);
                        lv.setVisibility(View.GONE);
                        //Log.d("CITY NAME SELECTED:",city_name);
                    }
                });
            }
        }
    }

}


