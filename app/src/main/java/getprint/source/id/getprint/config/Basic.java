package getprint.source.id.getprint.config;

import android.app.Application;


/**
 * Created by Chandra on 5/15/2016.
 * config API
 * Warning don't edit or remove it
 */
public class Basic extends Application {

    private String VERSION="1.0.0";
    private String ADDRESS ="http://kertasputih.isocorp.co.id/";
    private String LOGIN ="api/v1/login";
    private String REGISTER ="api/v1/register";
    private String CITY ="api/v1/kota";
    private String PROV ="api/v1/provinsi";
    private String PROFILE ="api/v1/profile";
    private String SELPROD="api/v1/product";
    private String SELBENTUK="api/v1/shape";
    private String SELBAHAN="api/v1/material";
    private String SELFINISH="api/v1/finishing";
    private String SELSIZE="api/v1/size";
    private String ORDER="api/v1/order";
    private String CONFIRM="api/v1/confirm";
    private String SELBANK="api/v1/bank";
    private String STORDER="api/v1/status_order";
    private String response;

    public String getVer(){return VERSION;}
    public String getUrl(){return ADDRESS;}
    public String getLogin(){return ADDRESS+LOGIN;}
    public String getREGISTER(){return ADDRESS+REGISTER;}
    public String getCITY(){return ADDRESS+CITY;}
    public String getPROV(){return ADDRESS+PROV;}
    public String getPROFILE(){return ADDRESS+PROFILE;}
    public String getSELPROD(){return ADDRESS+SELPROD;}
    public String getSELBENTUK(){return ADDRESS+SELBENTUK;}
    public String getSELBAHAN(){return ADDRESS+SELBAHAN;}
    public String getSELFINISH(){return ADDRESS+SELFINISH;}
    public String getSELSIZE(){return ADDRESS+SELSIZE;}
    public String getORDER(){return ADDRESS+ORDER;}
    public String getCONFIRM(){return ADDRESS+CONFIRM;}
    public String getSELBANK(){return ADDRESS+SELBANK;}
    public String getSTORDER(){return ADDRESS+STORDER;}

    public String getDumMenu(){
        response="{\"data\":{\"menu\":[{\"menu_id\":\"2\",\"menu_name\":\"HOME\",\"mob_href\":\"MainActivity\",\"icon\":\"home\"},{\"menu_id\":\"2\",\"menu_name\":\"TRANSAKSI\",\"mob_href\":\"#\",\"icon\":\"wallet\"},{\"menu_id\":\"5\",\"menu_name\":\"DAFTAR\",\"mob_href\":\"module.Signin\",\"icon\":\"door\"},{\"menu_id\":\"8\",\"menu_name\":\"PROFILE\",\"mob_href\":\"module.Tab_pengaturan\",\"icon\":\"daftar\"},{\"menu_id\":\"108\",\"menu_name\":\"INFO/BANTUAN\",\"mob_href\":\"module.Help\",\"icon\":\"info\"}]},\"success\":1}";
        return response;
    }
    public String getDumSize(){
        response="{\"data\":{\"ukuran\":[{\"ukuran_id\":\"2\",\"ukuran_name\":\"Bulat\",\"size_name\":\"2cm\",\"pcs\":100,\"price\":85000,\"price_idr\":\"Rp.85.000,-\"},{\"ukuran_id\":\"3\",\"ukuran_name\":\"Bulat\",\"size_name\":\"3cm\",\"pcs\":100,\"price\":85000,\"price_idr\":\"Rp.85.000,-\"},{\"ukuran_id\":\"4\",\"ukuran_name\":\"Bulat\",\"size_name\":\"4cm\",\"pcs\":100,\"price\":30000,\"price_idr\":\"Rp.30.000,-\"},{\"ukuran_id\":\"5\",\"ukuran_name\":\"Bulat\",\"size_name\":\"5cm\",\"pcs\":100,\"price\":37500,\"price_idr\":\"Rp.37.500,-\"}]},\"success\":1}";
        return response;
    }
    public String getDumBentuk(){
        response="{\"data\":{\"bentuk\":[{\"bentuk_id\":\"2\",\"bentuk_name\":\"Bulat\"},{\"bentuk_id\":\"3\",\"bentuk_name\":\"Persegi Panjang\"}]},\"success\":1}";
        return response;
    }
    public String getDumFinishing(){
        response="{\"data\":{\"finishing\":[{\"finishing_id\":\"1\",\"finishing_name\":\"Laminasi Glossy / Doff\"}]},\"success\":1}";
        return response;
    }
    public String getDumBahan(){
        response="{\"data\":{\"bahan\":[{\"bahan_id\":\"1\",\"bahan_name\":\"Bontax\"},{\"bahan_id\":\"2\",\"bahan_name\":\"Vynil\"}]},\"success\":1}";
        return response;
    }
    public String getDumDesign(){
        response="{\"data\":{\"design\":[{\"design_id\":\"1\",\"design_name\":\"Belum Ada\"},{\"design_id\":\"2\",\"design_name\":\"Sudah Ada / Buat Desain\"}]},\"success\":1}";
        return response;
    }
    public String getDumHome(){
        response="{\"data\":{\"homepage\":[{\"prod_id\":\"2\",\"prod_name\":\"Buku\",\"mob_href\":\"#\",\"banner\":\"buku\",\"label\":\"Buku\"},{\"prod_id\":\"5\",\"prod_name\":\"Hang Tag\",\"mob_href\":\"#\",\"banner\":\"hangtag\",\"label\":\"Hang Tag\"},{\"prod_id\":\"8\",\"prod_name\":\"Kalender\",\"mob_href\":\"#\",\"banner\":\"kalender\",\"label\":\"Kalender\"},{\"prod_id\":\"108\",\"prod_name\":\"Kartu Nama\",\"mob_href\":\"#\",\"banner\":\"kartunama\",\"label\":\"Kartu Nama\"},{\"prod_id\":\"109\",\"prod_name\":\"Packaging\",\"mob_href\":\"#\",\"banner\":\"packaging\",\"label\":\"Packaging\"},{\"prod_id\":\"110\",\"prod_name\":\"Stiker\",\"mob_href\":\"#\",\"banner\":\"stiker\",\"label\":\"Stiker\"}]},\"success\":1}";
        return response;
    }
    public String getDumCity(){
        response="{\"total\":38,\"per_page\":1000,\"current_page\":1,\"last_page\":1,\"next_page_url\":null,\"prev_page_url\":null,\"from\":1,\"to\":38,\"data\":[{\"id\":284,\"nama\":\"PACITAN KAB\",\"thumbnail\":null},{\"id\":285,\"nama\":\"PONOROGO KAB\",\"thumbnail\":null},{\"id\":286,\"nama\":\"TRENGGALEK KAB\",\"thumbnail\":null},{\"id\":287,\"nama\":\"TULUNGAGUNG KAB\",\"thumbnail\":null},{\"id\":288,\"nama\":\"BLITAR KAB\",\"thumbnail\":null},{\"id\":289,\"nama\":\"KEDIRI KAB\",\"thumbnail\":null},{\"id\":290,\"nama\":\"MALANG KAB\",\"thumbnail\":null},{\"id\":291,\"nama\":\"LUMAJANG KAB\",\"thumbnail\":null},{\"id\":292,\"nama\":\"JEMBER KAB\",\"thumbnail\":null},{\"id\":293,\"nama\":\"BANYUWANGI KAB\",\"thumbnail\":null},{\"id\":294,\"nama\":\"BONDOWOSO KAB\",\"thumbnail\":null},{\"id\":295,\"nama\":\"SITOBONDO KAB\",\"thumbnail\":null},{\"id\":296,\"nama\":\"PROBOLINGGO KAB\",\"thumbnail\":null},{\"id\":297,\"nama\":\"PASURUAN KAB\",\"thumbnail\":null},{\"id\":298,\"nama\":\"SIDOARJO KAB\",\"thumbnail\":null},{\"id\":299,\"nama\":\"MOJOKERTO KAB\",\"thumbnail\":null},{\"id\":300,\"nama\":\"JOMBANG KAB\",\"thumbnail\":null},{\"id\":301,\"nama\":\"NGANJUK KAB\",\"thumbnail\":null},{\"id\":302,\"nama\":\"MADIUN KAB\",\"thumbnail\":null},{\"id\":303,\"nama\":\"MAGETAN KAB\",\"thumbnail\":null},{\"id\":304,\"nama\":\"NGAWI KAB\",\"thumbnail\":null},{\"id\":305,\"nama\":\"BOJONEGORO KAB\",\"thumbnail\":null},{\"id\":306,\"nama\":\"TUBAN KAB\",\"thumbnail\":null},{\"id\":307,\"nama\":\"LAMONGAN KAB\",\"thumbnail\":null},{\"id\":308,\"nama\":\"GRESIK KAB\",\"thumbnail\":null},{\"id\":309,\"nama\":\"BANGKALAN KAB\",\"thumbnail\":null},{\"id\":310,\"nama\":\"SAMPANG KAB\",\"thumbnail\":null},{\"id\":311,\"nama\":\"PAMEKASAN KAB\",\"thumbnail\":null},{\"id\":312,\"nama\":\"SUMENEP KAB\",\"thumbnail\":null},{\"id\":313,\"nama\":\"KEDIRI KOTA\",\"thumbnail\":null},{\"id\":314,\"nama\":\"BLITAR KOTA\",\"thumbnail\":null},{\"id\":315,\"nama\":\"MALANG KOTA\",\"thumbnail\":null},{\"id\":316,\"nama\":\"PROBOLINGGO KOTA\",\"thumbnail\":null},{\"id\":317,\"nama\":\"PASURUAN KOTA\",\"thumbnail\":null},{\"id\":318,\"nama\":\"MOJOKERTO KOTA\",\"thumbnail\":null},{\"id\":319,\"nama\":\"MADIUN KOTA\",\"thumbnail\":null},{\"id\":320,\"nama\":\"SURABAYA KOTA\",\"thumbnail\":null},{\"id\":321,\"nama\":\"BATU KOTA\",\"thumbnail\":null}]}";
        return response;
    }








}
