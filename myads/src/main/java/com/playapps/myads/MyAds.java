package com.playapps.myads;

import android.annotation.SuppressLint;
import android.content.Context;
import android.icu.text.SymbolTable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.RequestFuture;
import com.android.volley.toolbox.Volley;

import org.apache.http.conn.ConnectTimeoutException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParserException;

import java.net.ConnectException;
import java.net.MalformedURLException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.security.PublicKey;
import java.util.Arrays;
import java.util.List;

import static android.content.ContentValues.TAG;


public class MyAds {


    @SuppressLint("StaticFieldLeak")
    private static Context context;
    private static String packageName;
    private static  String url = "https://eservicespk.ahmadsaeed.net/app/api/adslib?package=com.playapps.ads";


    public static void Initialize(Context c, String p) {
        context = c;
        packageName = p;
    }

    public static void setUrl(String u) {
        url = u;
    }

    public static void getAdIds(Response.Listener<JSONArray> listener) {

        RequestQueue queue = Volley.newRequestQueue(context);
        JsonArrayRequest req = new JsonArrayRequest(Request.Method.GET, url, null, listener, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error instanceof NoConnectionError) {
                    ConnectivityManager cm = (ConnectivityManager) context
                            .getSystemService(Context.CONNECTIVITY_SERVICE);
                    NetworkInfo activeNetwork = null;
                    if (cm != null) {
                        activeNetwork = cm.getActiveNetworkInfo();
                    }
                    if (activeNetwork != null && activeNetwork.isConnectedOrConnecting()) {
                        Toast.makeText(context, "Server is not connected to internet.",
                                Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(context, "Your device is not connected to internet.",
                                Toast.LENGTH_SHORT).show();
                    }
                }
//                else if (error instanceof NetworkError || error.getCause() instanceof ConnectException
//                        || (error.getCause().getMessage() != null
//                        && error.getCause().getMessage().contains("connection"))) {
//                    Toast.makeText(context, "Your device is not connected to internet.",
//                            Toast.LENGTH_SHORT).show();
//                } else if (error.getCause() instanceof MalformedURLException) {
//                    Toast.makeText(context, "Bad Request.", Toast.LENGTH_SHORT).show();
//                } else if (error instanceof ParseError || error.getCause() instanceof IllegalStateException
//                        || error.getCause() instanceof JSONException
//                        || error.getCause() instanceof XmlPullParserException) {
//                    Toast.makeText(context, "Parse Error (because of invalid json or xml).",
//                            Toast.LENGTH_SHORT).show();
//                } else if (error.getCause() instanceof OutOfMemoryError) {
//                    Toast.makeText(context, "Out Of Memory Error.", Toast.LENGTH_SHORT).show();
//                } else if (error instanceof AuthFailureError) {
//                    Toast.makeText(context, "server couldn't find the authenticated request.",
//                            Toast.LENGTH_SHORT).show();
//                } else if (error instanceof ServerError || error.getCause() instanceof ServerError) {
//                    Toast.makeText(context, "Server is not responding.", Toast.LENGTH_SHORT).show();
//                } else if (error instanceof TimeoutError || error.getCause() instanceof SocketTimeoutException
//                        || error.getCause() instanceof ConnectTimeoutException
//                        || error.getCause() instanceof SocketException
//                        || (error.getCause().getMessage() != null
//                        && error.getCause().getMessage().contains("Connection timed out"))) {
//                    Toast.makeText(context, "Connection timeout error",
//                            Toast.LENGTH_SHORT).show();
//                } else {
//                    Toast.makeText(context, "An unknown error occurred.",
//                            Toast.LENGTH_SHORT).show();
//                }
            }
        });
        queue.add(req);
    }

    public static String[] ParseAdmobBannerIds(JSONArray response) {
        String[] ids = {"wait", "2", "3"};
        for (int i = 1; i <= 3; i++) {
            try {
                Log.i("res: Admob Banner " + i + " : ", response.getJSONObject(0).getString("a-b" + i));
                ids[i - 1] = response.getJSONObject(0).getString("a-b" + i);

            } catch (JSONException e) {
                Log.i("Myads: Parse Error: ", "Can not Parse Response");
                e.printStackTrace();
            }
        }
        return ids;
    }

    public static String getAdNetwork(JSONArray response){
        try{
            return response.getJSONObject(0).getString("network");
        }
        catch (JSONException e) {
        Log.i("Myads: Parse Error: ", "Can not Parse Response");
        e.printStackTrace();
        return "null";
    }

    }

    public static String[] ParseAdmobInterstitialIds(JSONArray response) {
        String[] ids = {"wait", "2", "3"};
        for (int i = 1; i <= 3; i++) {
            try {
                Log.i("res: Admob Banner " + i + " : ", response.getJSONObject(0).getString("a-b" + i));
                ids[i - 1] = response.getJSONObject(0).getString("a-i" + i);

            } catch (JSONException e) {
                Log.i("Myads: Parse Error: ", "Can not Parse Response");
                e.printStackTrace();
            }
        }
        return ids;
    }

    public static String[] parseFanInterstitialIds(JSONArray response) {
        String[] ids = {"wait", "2", "3"};
        for (int i = 1; i <= 3; i++) {
            try {
                Log.i("res: Admob Banner " + i + " : ", response.getJSONObject(0).getString("a-b" + i));
                ids[i - 1] = response.getJSONObject(0).getString("f-i" + i);

            } catch (JSONException e) {
                Log.i("Myads: Parse Error: ", "Can not Parse Response");
                e.printStackTrace();
            }
        }
        return ids;
    }
    public static String[] parseFanBannerIds(JSONArray response) {
        String[] ids = {"wait", "2", "3"};
        for (int i = 1; i <= 3; i++) {
            try {
                Log.i("res: Admob Banner " + i + " : ", response.getJSONObject(0).getString("a-b" + i));
                ids[i - 1] = response.getJSONObject(0).getString("f-b" + i);

            } catch (JSONException e) {
                Log.i("Myads: Parse Error: ", "Can not Parse Response");
                e.printStackTrace();
            }
        }
        return ids;
    }



}
