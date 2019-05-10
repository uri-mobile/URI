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
import com.example.penjadwalan.Activities.adapter.adapter;
import com.example.penjadwalan.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Anggota extends AppCompatActivity {

    private static final String URL_anggota = "http://192.168.1.11/anggota.php";

    List<Anggotas> anggotasList;
    RecyclerView recyclerView;
    com.example.penjadwalan.Activities.adapter.adapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anggota);


        anggotasList = new ArrayList<>();
        adapter adapter = new adapter(this,anggotasList);
        recyclerView = (RecyclerView) findViewById(R.id.reclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);


        loadanggota();

    }

    public void loadanggota(){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_anggota,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            //konvert string to json
                            JSONArray array = new JSONArray(response);

                            for (int i = 0; i < array.length(); i++) {
                                JSONObject anggota = array.getJSONObject(i);

                                anggotasList.add(new Anggotas(
                                        anggota.getInt("id"),
                                        anggota.getString("nama"),
                                        anggota.getString("angkatan"),
                                        anggota.getString("prodi"),
                                        anggota.getString("img")
                                ));
                            }

                            adapter adapter = new adapter(Anggota.this, anggotasList);
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
