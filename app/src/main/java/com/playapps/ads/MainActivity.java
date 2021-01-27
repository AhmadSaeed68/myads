package com.playapps.ads;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.playapps.myads.MyAds;

import org.apache.http.conn.ConnectTimeoutException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParserException;

import java.lang.reflect.Array;
import java.net.ConnectException;
import java.net.MalformedURLException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.Arrays;
import java.util.List;



public class MainActivity extends AppCompatActivity {

    //ads
    public static  String[] admobBannerIds;
    public static  String[] admobInterstitialIds;
    public static  String[] fanBannerIds;
    public static  String[] fanInterstitialIds;
    public static  String adNetwork;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        MyAds.Initialize(this, getPackageName());
        MyAds.setUrl("https://eservicespk.ahmadsaeed.net/app/api/adslib?package=com.playapps.ads");
        MyAds.getAdIds(
                  response -> {

                      admobBannerIds = MyAds.ParseAdmobBannerIds(response);
                      admobInterstitialIds = MyAds.ParseAdmobInterstitialIds(response);
                      fanBannerIds = MyAds.parseFanBannerIds(response);
                      fanInterstitialIds = MyAds.parseFanInterstitialIds(response);
                      adNetwork = MyAds.getAdNetwork(response);

                      Log.i("aaaaaaaaaa", "netowrk: "+adNetwork);
                  }

        );




    }
}