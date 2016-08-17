package com.example.ilan.proyectofinal;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }
    public void Agregar (View v)
    {
        EditText edt1=(EditText)findViewById(R.id.editText9);
        String ejercicio= edt1.getText().toString();
        try {
            OkHttpClient client = new OkHttpClient();
            String url = "http://10.152.2.77/AgregarEjercicio.php?Nombre="+ejercicio;
            JSONObject json = new JSONObject();
            json.put("Nombre", ejercicio);

            RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json.toString());

            Request request = new Request.Builder()
                    .url(url)
                    .build();

            Response response = client.newCall(request).execute();
            Log.d("Response", response.body().string());
            Log.d("aa",json.toString());
            Toast.makeText(Main2Activity.this, "El Ejercicio ha sido agregado", Toast.LENGTH_SHORT).show();
        } catch (IOException | JSONException e) {
            Log.d("Error", e.getMessage());
        }
    }
    public void volver(View v)
    {
        Intent nuevaActivity = new Intent(this, Menu.class);
        startActivity(nuevaActivity);
    }
}
