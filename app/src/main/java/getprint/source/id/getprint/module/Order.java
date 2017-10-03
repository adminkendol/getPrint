package getprint.source.id.getprint.module;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONException;

import java.util.ArrayList;

import getprint.source.id.getprint.MainActivity;
import getprint.source.id.getprint.R;
import getprint.source.id.getprint.adapters.Plugin;
import getprint.source.id.getprint.menu.DrawerActivity;

/**
 * Created by Chandra on 11/3/2016.
 */
public class Order extends DrawerActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order);
        Intent iin= getIntent();
        final Bundle b = iin.getExtras();
        final String prod_id = (String) b.get("prod_id");
        final String img = (String) b.get("img");
        final String label = (String) b.get("label");
        final String sizeName = (String) b.get("sizeName");
        final String price = (String) b.get("price");
        final String bentuk_text = (String) b.get("bentuk");
        TextView ukuranId = (TextView) findViewById(R.id.ukuranId);
        TextView labelBentuk = (TextView) findViewById(R.id.labelBentuk);
        ImageView imgBentuk=(ImageView) findViewById(R.id.imgBentuk);
        ImageView imgUkuran=(ImageView) findViewById(R.id.imgUkuran);
        ukuranId.setText(sizeName+" - "+price);
        Log.d("IMGsrc:", img);
        ImageView imgSrc=(ImageView) findViewById(R.id.imageProd);
        //Resources resources = this.getResources();
        //int source = resources.getIdentifier(img, "drawable", this.getPackageName());
        //imgSrc.setImageResource(source);
        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.loading);
        Picasso.with(this).load(img)
                .into(imgSrc, new com.squareup.picasso.Callback() {
                    @Override
                    public void onSuccess() {
                        if (progressBar != null) {
                            progressBar.setVisibility(View.GONE);
                        }
                    }

                    @Override
                    public void onError() {

                    }
                });
        ImageView balik=(ImageView) findViewById(R.id.balik_item);
        ImageView cross=(ImageView) findViewById(R.id.cross);
        balik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Order.this, MainActivity.class);
                startActivity(intent);
            }
        });
        cross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Order.this, MainActivity.class);
                startActivity(intent);
            }
        });
        EditText bentukId=(EditText)findViewById(R.id.bentukId);
        bentukId.setText(bentuk_text);
        Log.d("BENTUK=",bentuk_text);
        /*SPINNER BAHAN*/
        /*Spinner sBahan = (Spinner) findViewById(R.id.spinnerBahan);
        final EditText bahanid = (EditText) findViewById(R.id.bahanId);
        Plugin bahan = new Plugin();
        ArrayList<String>[] responseBahan =null;
        try {
            responseBahan = bahan.getBahan();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        final TextView labelBahan=(TextView)findViewById(R.id.labelBahan);
        ArrayList<String> bahan_name = responseBahan[0];
        final ArrayList<String> bahan_id = responseBahan[1];
        ArrayAdapter<String> adp1Bahan = new ArrayAdapter<String>
                (this, R.layout.spinner_item, bahan_name);
        sBahan.setAdapter(adp1Bahan);
        sBahan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                bahanid.setText(bahan_id.get(arg2).toString());
                labelBahan.setVisibility(View.VISIBLE);
            }
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
                labelBahan.setVisibility(View.GONE);
            }
        });*/
        /*END SPINNER BAHAN*/
        /*SPINNER FINISHING*/
        Spinner sFinishing = (Spinner) findViewById(R.id.spinnerFinishing);
        final EditText finishingid = (EditText) findViewById(R.id.finishingId);
        Plugin finishing = new Plugin();
        ArrayList<String>[] responseFinishing =null;
        try {
            responseFinishing = finishing.getFinishing();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ArrayList<String> finishing_name = responseFinishing[0];
        final ArrayList<String> finishing_id = responseFinishing[1];
        ArrayAdapter<String> adp1Finishing = new ArrayAdapter<String>
                (this, R.layout.spinner_item, finishing_name);
        sFinishing.setAdapter(adp1Finishing);
        sFinishing.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                finishingid.setText(finishing_id.get(arg2).toString());
            }
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });
        /*END SPINNER BAHAN*/
        /*SPINNER PESANAN*/
        Spinner sPesanan = (Spinner) findViewById(R.id.spinnerPesanan);
        final EditText pesananid = (EditText) findViewById(R.id.pesananId);
        Plugin pesanan = new Plugin();
        ArrayList<String>[] responsePesanan =null;
        try {
            responsePesanan = pesanan.getPesanan();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ArrayList<String> pesanan_name = responsePesanan[0];
        final ArrayList<String> pesanan_id = responsePesanan[1];
        ArrayAdapter<String> adp1Pesanan = new ArrayAdapter<String>
                (this, R.layout.spinner_item, pesanan_name);
        sPesanan.setAdapter(adp1Pesanan);
        sPesanan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                pesananid.setText(pesanan_id.get(arg2).toString());
            }
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });
        /*END SPINNER PESANAN*/
        /*SPINNER DESAIN*/
        Spinner sDesain = (Spinner) findViewById(R.id.spinnerDesain);
        final EditText desainid = (EditText) findViewById(R.id.desainId);
        Plugin desain = new Plugin();
        ArrayList<String>[] responseDesain =null;
        try {
            responseDesain = desain.getDesain();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ArrayList<String> desain_name = responseDesain[0];
        final ArrayList<String> desain_id = responseDesain[1];
        ArrayAdapter<String> adp1Desain = new ArrayAdapter<String>
                (this, R.layout.spinner_item, desain_name);
        sDesain.setAdapter(adp1Desain);
        sDesain.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                desainid.setText(desain_id.get(arg2).toString());
            }
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });
        /*END SPINNER PESANAN*/

        //ImageView tomsize=(ImageView) findViewById(R.id.tomSize);
        imgUkuran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Order.this, Sizeprice.class);
                intent.putExtra("label", String.valueOf("Sticker Bulat"));
                intent.putExtra("img", String.valueOf(img));
                intent.putExtra("label", String.valueOf(label));
                intent.putExtra("bentuk", String.valueOf(bentuk_text));
                startActivity(intent);
            }
        });
        imgBentuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Order.this, Design.class);
                intent.putExtra("label", label);
                intent.putExtra("img", String.valueOf(img));
                intent.putExtra("label", String.valueOf(label));
                intent.putExtra("sizeName", sizeName);
                intent.putExtra("price", price);
                intent.putExtra("bentuk", String.valueOf(bentuk_text));
                startActivity(intent);
            }
        });
    }

}
