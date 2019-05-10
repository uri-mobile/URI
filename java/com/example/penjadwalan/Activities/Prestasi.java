package com.example.penjadwalan.Activities.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.penjadwalan.Activities.adapter.adapterP;
import com.example.penjadwalan.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Prestasi extends AppCompatActivity {

    private static final String URL_anggota = "http://192.168.1.11/prestasi.php";

    List<Prestasis> prestasisList;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prestasi);


        prestasisList = new ArrayList<>();
        adapterP adapter = new adapterP(this, prestasisList);
        recyclerView = (RecyclerView) findViewById(R.id.mreclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);


        loadanggota();

    }

    public void loadanggota() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_anggota,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            //konvert string to json
                            JSONArray array = new JSONArray(response);

                            for (int i = 0; i < array.length(); i++) {
                                JSONObject anggota = array.getJSONObject(i);

                                prestasisList.add(new Prestasis(
                                        anggota.getInt("id"),
                                        anggota.getString("event"),
                                        anggota.getString("emas"),
                                        anggota.getString("perak"),
                                        anggota.getString("perunggu")
                                ));
                            }

                            adapterP adapter = new adapterP(Prestasi.this, prestasisList);
                            recyclerView.setAdapter(adapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        Volley.newRequestQueue(this).add(stringRequest);
    }
}
