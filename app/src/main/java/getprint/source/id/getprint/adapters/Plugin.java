package getprint.source.id.getprint.adapters;


import android.util.Log;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import getprint.source.id.getprint.config.Basic;
import getprint.source.id.getprint.config.JSONParser;

/**
 * Created by Chandra on 11/7/2016.
 */
public class Plugin {
    JSONParser jParser = new JSONParser();
    public ArrayList<String>[] getBentuk() throws JSONException {
        Basic config = new Basic();
        String bentuk = config.getDumBentuk();
        JSONObject jsonObj = new JSONObject(bentuk);
        JSONObject jsonObjA = jsonObj.getJSONObject("data");
        JSONArray data_bentuk = jsonObjA.getJSONArray("bentuk");

        int h;
        final ArrayList<String> bentuk_name = new ArrayList<String>();
        final ArrayList<String> bentuk_id = new ArrayList<String>();
        for (h = 0; h < data_bentuk.length(); h++) {
            JSONObject ber = null;
            try {
                ber = data_bentuk.getJSONObject(h);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                bentuk_name.add(ber.getString("bentuk_name"));
                bentuk_id.add(ber.getString("bentuk_id"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        ArrayList<String>[] bentukS= new ArrayList[]{bentuk_name, bentuk_id};
        return bentukS;
    }
    public ArrayList<String>[] getBahan() throws JSONException {
        Basic config = new Basic();
        String bahan = config.getDumBahan();
        JSONObject jsonObj = new JSONObject(bahan);
        JSONObject jsonObjA = jsonObj.getJSONObject("data");
        JSONArray data_bahan = jsonObjA.getJSONArray("bahan");

        int h;
        final ArrayList<String> bahan_name = new ArrayList<String>();
        final ArrayList<String> bahan_id = new ArrayList<String>();
        for (h = 0; h < data_bahan.length(); h++) {
            JSONObject ber = null;
            try {
                ber = data_bahan.getJSONObject(h);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                bahan_name.add(ber.getString("bahan_name"));
                bahan_id.add(ber.getString("bahan_id"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        ArrayList<String>[] bahanS= new ArrayList[]{bahan_name, bahan_id};
        return bahanS;
    }
    public ArrayList<String>[] getFinishing() throws JSONException {
        Basic config = new Basic();
        String finishing = config.getDumFinishing();
        JSONObject jsonObj = new JSONObject(finishing);
        JSONObject jsonObjA = jsonObj.getJSONObject("data");
        JSONArray data_finishing = jsonObjA.getJSONArray("finishing");

        int h;
        final ArrayList<String> finishing_name = new ArrayList<String>();
        final ArrayList<String> finishing_id = new ArrayList<String>();
        for (h = 0; h < data_finishing.length(); h++) {
            JSONObject ber = null;
            try {
                ber = data_finishing.getJSONObject(h);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                finishing_name.add(ber.getString("finishing_name"));
                finishing_id.add(ber.getString("finishing_id"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        ArrayList<String>[] bahanS= new ArrayList[]{finishing_name, finishing_id};
        return bahanS;
    }
    public ArrayList<String>[] getPesanan() throws JSONException {
        int h;
        final ArrayList<String> pesanan_name = new ArrayList<String>();
        final ArrayList<String> pesanan_id = new ArrayList<String>();
        for (h = 1; h < 11; h++) {
            pesanan_name.add(String.valueOf(h*100)+"pcs");
            pesanan_id.add(String.valueOf(h * 100));
        }
        ArrayList<String>[] pesananS= new ArrayList[]{pesanan_name, pesanan_id};
        return pesananS;
    }
    public ArrayList<String>[] getDesain() throws JSONException {
        Basic config = new Basic();
        String desain = config.getDumDesign();
        JSONObject jsonObj = new JSONObject(desain);
        JSONObject jsonObjA = jsonObj.getJSONObject("data");
        JSONArray data_desain = jsonObjA.getJSONArray("design");

        int h;
        final ArrayList<String> desain_name = new ArrayList<String>();
        final ArrayList<String> desain_id = new ArrayList<String>();
        for (h = 0; h < data_desain.length(); h++) {
            JSONObject ber = null;
            try {
                ber = data_desain.getJSONObject(h);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                desain_name.add(ber.getString("design_name"));
                desain_id.add(ber.getString("design_id"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        ArrayList<String>[] desainS= new ArrayList[]{desain_name, desain_id};
        return desainS;
    }
    public String getCity(String prov_id) {
        Basic config = new Basic();
        String urlCity = config.getCITY();
        List<NameValuePair> parameter = new ArrayList<NameValuePair>();
        parameter.add(new BasicNameValuePair("provinsi_id", prov_id));
        //parameter.add(new BasicNameValuePair("loc", "1"));
        Log.d("URL DATA CITY", urlCity);
        try {
            JSONObject jsonCity = jParser.makeHttpRequest(urlCity, "GET", parameter);
            if(String.valueOf(jsonCity).equals("null")){
                return "Exception Caught";
            }else {
                JSONArray list_city = jsonCity.getJSONArray("data");
                Log.d("RESULT DATA CITY", String.valueOf(list_city));
                return String.valueOf(list_city);
            }
        }catch (JSONException e) {
            e.printStackTrace();
            Log.d("URL DATA ERROR", String.valueOf(e));
            return "Exception Caught";
        }
    }
    public String getProv() {
        Basic config = new Basic();
        String urlProv = config.getPROV();
        List<NameValuePair> parameter = new ArrayList<NameValuePair>();
        parameter.add(new BasicNameValuePair("provinsi_id", "35"));
        Log.d("URL DATA CITY", urlProv);
        try {
            JSONObject jsonProv = jParser.makeHttpRequest(urlProv, "GET", parameter);
            if(String.valueOf(jsonProv).equals("null")){
                return "Exception Caught";
            }else {
                JSONObject data = jsonProv.getJSONObject("data");
                JSONArray list_prov =data.getJSONArray("data");
                Log.d("RESULT DATA PROV", String.valueOf(data));
                return String.valueOf(list_prov);
            }
        }catch (JSONException e) {
            e.printStackTrace();
            Log.d("URL DATA ERROR", String.valueOf(e));
            return "Exception Caught";
        }
    }
    /*public String getCity() {
        Basic config = new Basic();
        String City =config.getDumCity();
        JSONObject jsonCity=null;
        try {
            jsonCity=new JSONObject(City);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d("JSON CITY A:", String.valueOf(jsonCity));
        JSONArray list = null;
        try {
            list = jsonCity.getJSONArray("data");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d("JSON CITY:" , String.valueOf(list));
        return String.valueOf(list);
    }*/
    public static final String md5(final String s) {
        try {
            // Create MD5 Hash
            MessageDigest digest = java.security.MessageDigest
                    .getInstance("MD5");
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();

            // Create Hex String
            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < messageDigest.length; i++) {
                String h = Integer.toHexString(0xFF & messageDigest[i]);
                while (h.length() < 2)
                    h = "0" + h;
                hexString.append(h);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
    public String getProfile(String token){
        Basic config = new Basic();
        String urlProfile = config.getPROFILE();
        List<NameValuePair> parameter = new ArrayList<NameValuePair>();
        parameter.add(new BasicNameValuePair("token", token));
        try {
            JSONObject jsonProfile = jParser.makeHttpRequest(urlProfile, "GET", parameter);
            Log.d("DATA PROFILE", "REQUEST:"+urlProfile+"|PARAMS:"+parameter+"|RESPONSE:"+String.valueOf(jsonProfile));
            if(String.valueOf(jsonProfile).equals("null")){
                return "Exception Caught";
            }else {
                JSONObject list_profile =jsonProfile.getJSONObject("data");
                Log.d("RESULT DATA PROFILE", "REQUEST:"+urlProfile+"|PARAMS:"+parameter+"|RESPONSE:"+String.valueOf(list_profile));
                return String.valueOf(list_profile);
            }
        }catch (JSONException e) {
            e.printStackTrace();
            Log.d("URL DATA ERROR", String.valueOf(e));
            return "Exception Caught";
        }
    }
    public String hitLogin(List<NameValuePair> parameter){
        Basic config = new Basic();
        String urlLogin = config.getLogin();
        JSONObject jsonLogin = jParser.makeHttpRequest(urlLogin, "POST", parameter);
        Log.d("DATA LOGIN", "REQUEST:" + urlLogin + "|PARAMS:" + parameter + "|RESPONSE:" + String.valueOf(jsonLogin));
        if(String.valueOf(jsonLogin).equals("null")){
            return "Exception Caught";
        }else {
            return String.valueOf(jsonLogin);
        }
    }
    public String getPod() {
        Basic config = new Basic();
        String url = config.getSELPROD();
        List<NameValuePair> parameter = new ArrayList<NameValuePair>();
        parameter.add(new BasicNameValuePair("token", "123"));
        Log.d("URL DATA PROD", url);
        JSONObject json = jParser.makeHttpRequest(url, "GET", parameter);
        return String.valueOf(json);
    }
}
