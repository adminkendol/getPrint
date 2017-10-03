package getprint.source.id.getprint;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import getprint.source.id.getprint.adapters.Plugin;
import getprint.source.id.getprint.adapters.RecyclerViewAdapter;
import getprint.source.id.getprint.menu.DrawerActivity;

public class MainActivity extends DrawerActivity {
    @Override
     protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Intent intent = new Intent(this, Signin.class);
        //startActivity(intent);
        ImageView basket = (ImageView) findViewById(R.id.basket);
        /*basket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Cart.class);
                intent.putExtra("label", String.valueOf("Cart"));
                startActivity(intent);
            }
        });*/
        ReadDataTask m= (ReadDataTask) new ReadDataTask(this).execute();
    }

    class ReadDataTask extends AsyncTask<String, Void, String> {
        ProgressDialog pDialog;
        MainActivity mainActivity;
        public ReadDataTask(MainActivity mainActivity) {
            this.mainActivity=mainActivity;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(mainActivity);
            pDialog.setMessage("Mohon Tunggu..");
            pDialog.setIndeterminate(true);
            pDialog.setCancelable(true);
            pDialog.show();
        }
        @Override
        protected String doInBackground(String... sText) {
            String returnResult = BuildTable();
            return returnResult;
        }
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            pDialog.dismiss();

            LinearLayout ll =(LinearLayout) findViewById(R.id.ll_home);
            if( (result.equalsIgnoreCase("Exception Caught"))||(result.equalsIgnoreCase("null"))) {
                Log.d("RESULT API", "data:" + String.valueOf(result) + ":END");
                Toast.makeText(mainActivity, "Unable to connect to server,please check your internet connection!", Toast.LENGTH_LONG).show();
                Snackbar snackbar = Snackbar.make(ll, "Load data gagal", Snackbar.LENGTH_INDEFINITE);
                snackbar.setAction("Retry", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ReadDataTask m= (ReadDataTask) new ReadDataTask(mainActivity).execute();
                    }
                }).show();
                snackbar.show();
            }else if (result.equalsIgnoreCase("no results")) {
                Toast.makeText(mainActivity, "Data empty", Toast.LENGTH_LONG).show();
                Snackbar snackbar = Snackbar.make(ll, "Load data gagal", Snackbar.LENGTH_INDEFINITE);
                snackbar.setAction("Retry", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ReadDataTask m= (ReadDataTask) new ReadDataTask(mainActivity).execute();
                    }
                }).show();
                snackbar.show();
            } else {
                Log.d("RESULT HOME:", String.valueOf(result));
                JSONArray dataHome = null;
                JSONObject data = null;
                try {
                    data = new JSONObject(result);
                    //Log.d("RESULT HOME:", String.valueOf(data));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                JSONArray dataA= null;
                try {
                    dataA = data.getJSONArray("data");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                Log.d("Array data JSON HOME", String.valueOf(dataA));
                try {
                    String[] prod_id=new String[dataA.length()];
                    String[] prod_name=new String[dataA.length()];
                    String[] banner=new String[dataA.length()];
                    String[] label=new String[dataA.length()];
                    Log.d("Array data Home", String.valueOf(dataA));

                    for (int i = 0; i < dataA.length(); i++) {
                        JSONObject c = dataA.getJSONObject(i);
                        prod_id[i] = c.getString("id");
                        prod_name[i] = c.getString("product_name");
                        banner[i] = c.getString("thumbnail").toLowerCase();
                        label[i] = c.getString("product_name");
                        //Log.d("Array data Banner", c.getString("banner"));
                    }
                    RecyclerViewAdapter adapter = new RecyclerViewAdapter(mainActivity,prod_id,banner,label);
                    RecyclerView myView = (RecyclerView) mainActivity.findViewById(R.id.titik);
                    Log.d("CEK T", String.valueOf(myView));
                    myView.setHasFixedSize(true);
                    myView.setAdapter(adapter);
                    LinearLayoutManager llm = new LinearLayoutManager(mainActivity);
                    llm.setOrientation(LinearLayoutManager.VERTICAL);
                    myView.setLayoutManager(llm);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        private String BuildTable() {
            //Basic config = new Basic();
            //String data = config.getDumHome();
            Plugin api = new Plugin();
            String data = api.getPod();
            return data;
        }
    }

}
