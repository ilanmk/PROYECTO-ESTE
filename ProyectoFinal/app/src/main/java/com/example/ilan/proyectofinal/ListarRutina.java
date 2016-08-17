package com.example.ilan.proyectofinal;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Adapter;
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

public class ListarRutina extends AppCompatActivity {
          ListView lv;
    ArrayList<String> rutinacompleta=new ArrayList<>();
    String urlrutinacompleta="http://10.152.2.77/SelectCosasRutina.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_rutina);
        lv=(ListView)findViewById(R.id.lista);
        new  SelectRutina().execute(urlrutinacompleta);
    }

    class SelectRutina extends AsyncTask<String, Void, ArrayList<String>> {
        private OkHttpClient client = new OkHttpClient();


        protected void onPostExecute(ArrayList<String> rutinacompletaResult) {
            super.onPostExecute(rutinacompletaResult);



        }

        @Override
        protected ArrayList<String> doInBackground(String... params) {



            Request request = new Request.Builder()
                    .url(urlrutinacompleta)
                    .build();
            try {
                Response response = client.newCall(request).execute();
                return parsearResultado(response.body().string());

            } catch (IOException | JSONException e) {
                Log.d("Error", e.getMessage());
                return new ArrayList<String>();
            }

        }

        ArrayList<String> parsearResultado(String JSONstr) throws JSONException
        { Object[]obj=rutinacompleta.toArray();
           ArrayList hola=new ArrayList();
            JSONArray jsonEjercicios = new JSONArray(JSONstr);
            for (int i = 0; i < jsonEjercicios.length(); i++) {
                JSONObject jsonResultado = jsonEjercicios.getJSONObject(i);

                String ej1 = jsonResultado.getString("Calificacion");
                String ej2 = jsonResultado.getString("Nombre");
                String ej3 = jsonResultado.getString("Descripcion");
                String todo = ej1+" |" +ej2+" |" +ej3;
                hola.add(todo);

            }

            String[]strejercicos= Arrays.copyOf(obj,obj.length,String[].class);
            lv.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_multiple_choice,hola));
            for (int i=0;i<rutinacompleta.size();i++)
            {

            }





            return rutinacompleta;
        }
    }
}
