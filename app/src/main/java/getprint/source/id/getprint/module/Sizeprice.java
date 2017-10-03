package getprint.source.id.getprint.module;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import getprint.source.id.getprint.R;
import getprint.source.id.getprint.adapters.SpecialAdapter;
import getprint.source.id.getprint.config.Basic;
import getprint.source.id.getprint.menu.DrawerActivity;

/**
 * Created by Chandra on 11/8/2016.
 */
public class Sizeprice extends DrawerActivity{

    private ProgressDialog pDialog;
    private ListView lv;
    ArrayList<HashMap<String, String>> sizeList;
    private String img;
    private String label;
    private String bentuk;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.size);
        Intent iin= getIntent();
        Bundle b = iin.getExtras();
        img = (String) b.get("img");
        label = (String) b.get("label");
        bentuk = (String) b.get("bentuk");
        ImageView balik=(ImageView)findViewById(R.id.balik);
        balik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Sizeprice.this, Order.class);
                intent.putExtra("img", String.valueOf(img));
                intent.putExtra("label", String.valueOf(label));
                intent.putExtra("sizeName", "6cm");
                intent.putExtra("price", "Rp.45.000,-/100pcs");
                intent.putExtra("bentuk", bentuk);
                startActivity(intent);
            }
        });
        sizeList = new ArrayList<>();
        lv = (ListView) findViewById(R.id.list);
        new GetSizePrice().execute();
    }
    private class GetSizePrice extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Sizeprice.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();

        }
        @Override
        protected Void doInBackground(Void... arg0) {
            Basic config = new Basic();
            String response = config.getDumSize();
            JSONObject jsonObj = null;
            try {
                jsonObj = new JSONObject(response);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            JSONObject jsonObjA = null;
            try {
                jsonObjA = jsonObj.getJSONObject("data");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            JSONArray data_size =null;
            try {
                data_size = jsonObjA.getJSONArray("ukuran");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            RelativeLayout sizeLay = (RelativeLayout) findViewById(R.id.sizeLay);
            for (int i = 0; i < data_size.length(); i++) {
                JSONObject c = null;
                try {
                    c = data_size.getJSONObject(i);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                String size_id=null;
                String size_name=null;
                String pcs=null;
                String price=null;
                try {
                    size_id=c.getString("ukuran_id");
                    size_name=c.getString("size_name");
                    pcs=c.getString("pcs");
                    price=c.getString("price_idr");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                HashMap<String, String> sizes = new HashMap<>();
                sizes.put("sizeName",size_name);
                sizes.put("price",price+"/"+pcs+"pcs");
                sizeList.add(sizes);
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();
            /**
             * Updating parsed JSON data into ListView
             * */
            ListAdapter adapter = new SpecialAdapter(
                    Sizeprice.this, sizeList,
                    R.layout.list_size, new String[]{"sizeName", "price"}, new int[]{R.id.sizeName,R.id.price}, new String[]{img,label,bentuk});
            lv.setAdapter(adapter);
        }
    }


}
