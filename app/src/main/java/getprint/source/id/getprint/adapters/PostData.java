package getprint.source.id.getprint.adapters;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import getprint.source.id.getprint.MainActivity;
import getprint.source.id.getprint.R;
import getprint.source.id.getprint.config.Basic;
import getprint.source.id.getprint.config.Session;
import getprint.source.id.getprint.module.Thanks;


/**
 * Created by Chandra on 10/8/2016.
 */
public class PostData extends AsyncTask<String, Void, String> {
    Context context;
    List<NameValuePair> Paramsx;
    ProgressDialog pDialog;
    String action;
    String nama;
    public PostData(Context context, List<NameValuePair> Paramsx, String action, String nameX) {
        this.context=context;
        this.Paramsx=Paramsx;
        this.action=action;
        this.nama=nameX;
        //progress.show();
    }



    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pDialog = ProgressDialog.show(context, null, null, true);
        pDialog.setContentView(R.layout.loading);
        TextView textProg=(TextView)pDialog.findViewById(R.id.textProg);
        textProg.setText("Submit Data...");
        pDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        pDialog.show();
    }
    @Override
    protected String doInBackground(String... Params) {
        //return null;
        Basic config = new Basic();
        String url = null;
        if(action.equals("user")) {
            url = config.getREGISTER();
        }
        if(action.equals("login")) {
            url = config.getLogin();
        }
        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost(url);

        HttpResponse response = null;
        String temp =null;
        try {
            // Add your data
            //List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
            //nameValuePairs.add(new BasicNameValuePair("myHttpData", ""));
            httppost.setEntity(new UrlEncodedFormEntity(Paramsx));

            // Execute HTTP Post Request
            response = httpclient.execute(httppost);

            temp = EntityUtils.toString(response.getEntity());

            Log.d("POST URL:",url+" PARAM:"+Paramsx+" RESPONSE:"+temp);

        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
        } catch (IOException e) {
            // TODO Auto-generated catch block
        }
        //return response;
        return temp;
    }
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        //Log.d("MSG:", String.valueOf(result));
        pDialog.dismiss();
        Intent intent = null;
        if(action.equals("login")) {
            JSONObject res = null;
            try {
                res = new JSONObject(result);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            Log.d("MSG:", String.valueOf(res));
            String success = null;
            String message = null;
            try {
                success = res.getString("status");
                message = res.getString("message");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            JSONObject data = null;
            try {
                data = res.getJSONObject("data");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            String token = null;
            String pengguna_id = null;
            try {
                token = data.getString("token");
                pengguna_id = data.getString("pengguna_id");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            Log.d("MSG DETAIL:", String.valueOf(success) + "|" + message + "|" + String.valueOf(data) + "|" + token + "|" + pengguna_id);
            if(success.equals("success")){
                Session session = new Session(context);
                session.setToken(token);
                intent = new Intent(context, MainActivity.class);
            }else{
                pDialog.onBackPressed();
            }


        }else {
            intent = new Intent(context, Thanks.class);
        }
        intent.putExtra("nama", nama);
        context.startActivity(intent);

    }

}
