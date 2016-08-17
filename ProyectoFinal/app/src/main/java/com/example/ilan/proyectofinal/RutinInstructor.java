package com.example.ilan.proyectofinal;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class RutinInstructor extends AppCompatActivity {
    ArrayList<String> instruidos=new ArrayList<String>();
    ArrayList<String> rutinas=new ArrayList<>();

    ArrayList<String> rutinacompleta=new ArrayList<>();
    final String urlinstructor="http://10.152.2.77/SelectInstructor.php";
    final String urlrutina="http://10.152.2.77/SelectRutina.php";

    String ej1="";
    String ej2="";
    String ej3="";
    String ej4="";
    String ej5="";
    String ej6="";
    public static TextView ejj1;
    public static TextView ejj2;
    public static TextView ejj3;
    public static  TextView ejj4;
    public static  TextView ejj5;
    public static TextView ejj6;
    public String urlrutinacompleta="http://10.152.2.77/SelectRutinaCompleta.php?id=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rutin_instructor);

        setContentView(R.layout.activity_rutinas_instructor);
        ejj1=(TextView) findViewById(R.id.textView8);
        ejj2=(TextView) findViewById(R.id.textView9);
        ejj3=(TextView) findViewById(R.id.textView10);
        ejj4=(TextView) findViewById(R.id.textView11);
        ejj5=(TextView) findViewById(R.id.textView12);
        ejj6=(TextView) findViewById(R.id.textView23);
        new UsuariosTask().execute(urlinstructor);
        new RutinasTask().execute(urlrutina);
        Spinner spinner=(Spinner) findViewById(R.id.spinner);
        String ejercicio= spinner.getSelectedItem().toString();
        urlrutinacompleta="http://10.152.2.77/SelectRutinaCompleta.php?id="+ejercicio;
        new SelectRutina().execute(urlrutinacompleta);



    }

    //public void onItemSelected(ArrayList<String> parentView, View selectedItemView, int position, long id)
    //{
    //"edt".setText(rutinas[position]);
    //}
    class SelectRutina extends AsyncTask<String, Void, ArrayList<String>> {
        private OkHttpClient client = new OkHttpClient();


        protected void onPostExecute(ArrayList<String> rutinacompletaResult) {
            super.onPostExecute(rutinacompletaResult);

            RutinasInstructor.ejj1.setText(rutinacompletaResult.get(0));
            RutinasInstructor.ejj2.setText(rutinacompletaResult.get(1));
            RutinasInstructor.ejj3.setText(rutinacompletaResult.get(2));
            RutinasInstructor.ejj4.setText(rutinacompletaResult.get(3));
            RutinasInstructor.ejj5.setText(rutinacompletaResult.get(4));
            RutinasInstructor.ejj6.setText(rutinacompletaResult.get(5));

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
        {

            JSONArray jsonEjercicios = new JSONArray(JSONstr);
            for (int i = 0; i < jsonEjercicios.length(); i++) {
                JSONObject jsonResultado = jsonEjercicios.getJSONObject(i);

                ej1 = jsonResultado.getString("Nombre1");
                ej2 = jsonResultado.getString("Nombre2");
                ej3 = jsonResultado.getString("Nombre3");
                ej4 = jsonResultado.getString("Nombre4");
                ej5 = jsonResultado.getString("Nombre5");
                ej6 = jsonResultado.getString("Nombre6");


            }
            Object[]obj=rutinacompleta.toArray();
            String[]strejercicos= Arrays.copyOf(obj,obj.length,String[].class);
            ArrayAdapter<CharSequence> adapter =
                    new ArrayAdapter<CharSequence>(getApplicationContext(), android.R.layout.simple_spinner_item );
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            for (int i=0;i<rutinacompleta.size();i++)
            {
                adapter.add(strejercicos[i]);
            }


            Spinner s = (Spinner) findViewById(R.id.spinner3);

            s.setAdapter(adapter);
            return rutinacompleta;
        }
    }


    class UsuariosTask extends AsyncTask<String, Void, ArrayList<String>> {
        private OkHttpClient client = new OkHttpClient();

        protected void onPostExecute(ArrayList<String> usuariosResult) {
            super.onPostExecute(usuariosResult);
        }

        @Override
        protected ArrayList<String> doInBackground(String... params) {



            Request request = new Request.Builder()
                    .url(urlinstructor)
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

            JSONArray jsonUsuarios = new JSONArray(JSONstr);
            for (int i = 0; i < jsonUsuarios.length(); i++) {
                JSONObject jsonResultado = jsonUsuarios.getJSONObject(i);

                String Nombre = jsonResultado.getString("Nombre");
                String Apellido=jsonResultado.getString("Apellido");
                String Instruido=Nombre+" "+Apellido;
                instruidos.add(Instruido);
            }
            Object[]obj=instruidos.toArray();
            String[]strinstruidos= Arrays.copyOf(obj,obj.length,String[].class);
            ArrayAdapter<CharSequence> adapter =
                    new ArrayAdapter<CharSequence>(getApplicationContext(), android.R.layout.simple_spinner_item );
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            for (int i=0;i<instruidos.size();i++)
            {
                adapter.add(strinstruidos[i]);
            }


            Spinner s = (Spinner) findViewById(R.id.spinner2);

            s.setAdapter(adapter);
            return instruidos;
        }
    }
    class RutinasTask extends AsyncTask<String, Void, ArrayList<String>> {
        private OkHttpClient client = new OkHttpClient();

        protected void onPostExecute(ArrayList<String> usuariosResult) {
            super.onPostExecute(usuariosResult);
        }

        @Override
        protected ArrayList<String> doInBackground(String... params) {



            Request request = new Request.Builder()
                    .url(urlrutina)
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

            JSONArray jsonUsuarios = new JSONArray(JSONstr);
            for (int i = 0; i < jsonUsuarios.length(); i++) {
                JSONObject jsonResultado = jsonUsuarios.getJSONObject(i);

                String Nombre = jsonResultado.getString("idRutinas");

                String ruti=Nombre;
                rutinas.add(ruti);
            }
            Object[]obj=rutinas.toArray();
            String[]strinstruidos= Arrays.copyOf(obj,obj.length,String[].class);
            ArrayAdapter<CharSequence> adapter =
                    new ArrayAdapter<CharSequence>(getApplicationContext(), android.R.layout.simple_spinner_item );
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            for (int i=0;i<rutinas.size();i++)
            {
                adapter.add(strinstruidos[i]);
            }


            Spinner spin = (Spinner) findViewById(R.id.spinner);

            spin.setAdapter(adapter);
            return rutinas;
        }
    }
    public void volver(View v)
    {
        Intent nuevaActivity = new Intent(this, Menu.class);
        startActivity(nuevaActivity);
    }



}
