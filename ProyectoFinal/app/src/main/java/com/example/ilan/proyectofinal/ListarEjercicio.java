package com.example.ilan.proyectofinal;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListarEjercicio extends AppCompatActivity {
    ArrayList<String> ejercicios=new ArrayList<String>();
    final String urlejercicio="http://10.152.2.77/SelectEjercicio.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_ejercicio);
        new EjerciciosTask().execute(urlejercicio);
    }
    class EjerciciosTask extends AsyncTask<String, Void, ArrayList<String>> {
        private OkHttpClient client = new OkHttpClient();

        protected void onPostExecute(ArrayList<String> ejerciciosResult) {
            super.onPostExecute(ejerciciosResult);
        }

        @Override
        protected ArrayList<String> doInBackground(String... params) {



            Request request = new Request.Builder()
                    .url(urlejercicio)
                    .build();
            try {
                Response response = client.newCall(request).execute();
                return parsearResultado(response.body().string());

            } catch (IOException | JSONException e) {
                Log.d("Error", e.getMessage());
                return new ArrayList<String>();
            }

        }

        ArrayList<String> parsearResultado(String JSONstr) throws JSONException {

            JSONArray jsonEjercicios = new JSONArray(JSONstr);
            for (int i = 0; i < jsonEjercicios.length(); i++) {
                JSONObject jsonResultado = jsonEjercicios.getJSONObject(i);

                String Nombre = jsonResultado.getString("Nombre");

                String eje=Nombre;
                ejercicios.add(eje);
            }
            Object[]obj=ejercicios.toArray();
            String[]strejercicios= Arrays.copyOf(obj,obj.length,String[].class);
            ArrayAdapter<CharSequence> adapter =
                    new ArrayAdapter<CharSequence>(getApplicationContext(), android.R.layout.simple_spinner_item );
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            for (int i=0;i<ejercicios.size();i++)
            {
                adapter.add(strejercicios[i]);
            }


            ListView spin = (ListView) findViewById(R.id.listView);

            spin.setAdapter(adapter);
            return ejercicios;
        }
    }
}
