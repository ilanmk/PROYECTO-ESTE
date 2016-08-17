package com.example.ilan.proyectofinal;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class AgRutina extends AppCompatActivity {
    ArrayList<String> ejercicio =new ArrayList<String>();
    final String urlejercicio="http:/10.152.2.77/SelectEjercicio.php";
    int cont=0;
    String ej1="";
    String ej2="";
    String ej3="";
    String ej4="";
    String ej5="";
    String ej6="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ag_rutina);
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        new AgregarEjercicio().execute(urlejercicio);


    }
    class AgregarEjercicio extends AsyncTask<String, Void, ArrayList<String>> {
        private OkHttpClient client = new OkHttpClient();

        protected void onPostExecute(ArrayList<String> ejercicioResult) {
            super.onPostExecute(ejercicioResult);

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

        ArrayList<String> parsearResultado(String JSONstr) throws JSONException
        {

            JSONArray jsonEjercicios = new JSONArray(JSONstr);
            for (int i = 0; i < jsonEjercicios.length(); i++) {
                JSONObject jsonResultado = jsonEjercicios.getJSONObject(i);

                String Nombre = jsonResultado.getString("Nombre");

                String Instruido=Nombre;
                ejercicio.add(Instruido);
            }
            Object[]obj=ejercicio.toArray();
            String[]strejercicos= Arrays.copyOf(obj,obj.length,String[].class);
            ArrayAdapter<CharSequence> adapter =
                    new ArrayAdapter<CharSequence>(getApplicationContext(), android.R.layout.simple_spinner_item );
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            for (int i=0;i<ejercicio.size();i++)
            {
                adapter.add(strejercicos[i]);
            }


            Spinner s = (Spinner) findViewById(R.id.spinner3);

            s.setAdapter(adapter);
            return ejercicio;
        }
    }
    public void terminar(View v)
    {
        try {
            OkHttpClient client = new OkHttpClient();
            String url = "http://10.152.2.77/AgregarEjRutina.php?Nombre1="+ej1+"&Nombre2="+ej2+"&Nombre3="+ej3+"&Nombre4="+ej4+"&Nombre5="+ej5+"&Nombre6="+ej6;
            JSONObject json = new JSONObject();
            json.put("Nombre1", ej1);
            json.put("Nombre2", ej2);
            json.put("Nombre3", ej3);
            json.put("Nombre4", ej4);
            json.put("Nombre5", ej5);
            json.put("Nombre6", ej6);

            RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json.toString());

            Request request = new Request.Builder()
                    .url(url)
                    .build();

            Response response = client.newCall(request).execute();
            Log.d("Response", response.body().string());
            Log.d("aa",json.toString());
            Toast.makeText(AgRutina.this, "La rutina ha sido agregada", Toast.LENGTH_SHORT).show();
        } catch (IOException | JSONException e) {
            Log.d("Error", e.getMessage());
        }
    }
    public void AgregarRutina (View v)
    { Spinner spinner=(Spinner) findViewById(R.id.spinner3);
        cont++;
        String ejercicio= spinner.getSelectedItem().toString();
        if(cont==1)
        {
            Button btnt=(Button)findViewById(R.id.btnTerminar) ;
            btnt.setVisibility(View.VISIBLE);
            ej1=ejercicio;
        }
        if (cont==2)
        {ej2=ejercicio;}
        if(cont==3)
        {
            ej3=ejercicio;
        }
        if(cont==4)
        {ej4=ejercicio;}
        if(cont==5)
        {ej5=ejercicio;}
        if(cont==6)
        {
            ej6=ejercicio;
            Button btnr=(Button)findViewById(R.id.btnRutina);
            btnr.setVisibility(View.INVISIBLE);
        }




    }
    public void volver(View v)
    {
        Intent nuevaActivity = new Intent(this, Menu.class);
        startActivity(nuevaActivity);
    }

}
