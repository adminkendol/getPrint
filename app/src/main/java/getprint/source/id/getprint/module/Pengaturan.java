package getprint.source.id.getprint.module;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import getprint.source.id.getprint.MainActivity;
import getprint.source.id.getprint.R;
import getprint.source.id.getprint.adapters.Plugin;
import getprint.source.id.getprint.config.Session;

/**
 * Created by Chandra on 11/2/2016.
 */
public class Pengaturan extends Fragment {
    String data;
    Context context;
    LinearLayout llProfile;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    public Pengaturan(String data) {
        // Required empty public constructor
        this.data=data;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.form_pengaturan, container, false);
        context = container.getContext();
        llProfile=(LinearLayout)view.findViewById(R.id.llProfile);
        final Session session =new Session(context);
        final String token =session.getToken();
        TextView logout = (TextView)view.findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                session.setToken("");
                Intent intent = new Intent(context, MainActivity.class);
                context.startActivity(intent);
            }
        });
        new GetProf(token, view).execute();
        return view;
    }
    class GetProf extends AsyncTask<String, Void, String> {
                private ProgressDialog pDialog;
                String token;
                View view;

                public GetProf(String token, View view) {
                    this.token = token;
                    this.view = view;
                }

                @Override
                protected void onPreExecute() {
                    super.onPreExecute();
                    pDialog = ProgressDialog.show(context, null, null, true);
                    pDialog.setContentView(R.layout.loading);
                    TextView textProg = (TextView) pDialog.findViewById(R.id.textProg);
                    textProg.setText("Get Profile...");
                    pDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    pDialog.show();
                }

                @Override
                protected String doInBackground(String... sText) {
                    Plugin fire = new Plugin();
                    String profile = fire.getProfile(token);
                    Log.d("STRING PROFILE:", profile);
                    JSONObject dataProfile = null;
                    try {
                        dataProfile = new JSONObject(profile);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    return String.valueOf(dataProfile);
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
                    Log.d("RESULT Profile:", String.valueOf(result));
                    if ((result.equals("Exception Caught")) || (String.valueOf(result).equals("null"))) {
                        Snackbar snackbar = Snackbar
                                .make(llProfile, "Koneksi gagal, coba lagi !", Snackbar.LENGTH_INDEFINITE)
                                .setAction("Retry", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        Intent intentB = new Intent(context, Tab_pengaturan.class);
                                        startActivity(intentB);
                                    }
                                });
                        View snackBarView = snackbar.getView();
                        snackBarView.setBackgroundColor(Color.RED);
                        snackbar.show();
                    } else {
                        JSONObject resProfile = null;
                        try {
                            resProfile = new JSONObject(result);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        String id = null;
                        String name = null;
                        String contact_name = null;
                        String email = null;
                        String address = null;
                        String city = null;
                        String postal_code = null;
                        String phone = null;
                        String img_profile = null;
                        String is_active = null;
                        String is_verified = null;
                        String is_suspended = null;
                        String role = null;
                        try {
                            id = resProfile.getString("id");
                            name = resProfile.getString("name");
                            contact_name = resProfile.getString("contact_name");
                            email = resProfile.getString("email");
                            address = resProfile.getString("address");
                            city = resProfile.getString("city");
                            postal_code = resProfile.getString("postal_code");
                            phone = resProfile.getString("phone");
                            img_profile = resProfile.getString("img_profile");
                            is_active = resProfile.getString("is_active");
                            is_verified = resProfile.getString("is_verified");
                            is_suspended = resProfile.getString("is_suspended");
                            role = resProfile.getString("role");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        EditText input_name = (EditText) view.findViewById(R.id.input_name);
                        EditText input_email = (EditText) view.findViewById(R.id.input_email);
                        EditText input_phone = (EditText) view.findViewById(R.id.input_phone);
                        EditText input_address = (EditText) view.findViewById(R.id.input_address);
                        EditText input_gender = (EditText) view.findViewById(R.id.input_gender);
                        input_name.setText(name);
                        input_email.setText(email);
                        input_phone.setText(phone);
                        input_address.setText(address);

                    }
                }
            }
}
