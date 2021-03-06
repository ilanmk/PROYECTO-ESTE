package com.example.ilan.proyectofinal;

import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
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

public class ABMEJERCICIOS extends AppCompatActivity {
    public ArrayList<String> ejercicio =new ArrayList<String>();
    final String urlejercicio="http:/10.152.2.77/SelectEjercicio.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abmejercicios);
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
        {ArrayAdapter<CharSequence> adapter =
                new ArrayAdapter<CharSequence>(getApplicationContext(), android.R.layout.simple_spinner_item );
            Spinner s = (Spinner) findViewById(R.id.spinner4);

            JSONArray jsonEjercicios = new JSONArray(JSONstr);
            for (int i = 0; i < jsonEjercicios.length(); i++) {
                JSONObject jsonResultado = jsonEjercicios.getJSONObject(i);

                String Nombre = jsonResultado.getString("Nombre");

                String Instruido=Nombre;
                ejercicio.add(Instruido);
            }
            Object[]obj=ejercicio.toArray();
            String[]strejercicos= Arrays.copyOf(obj,obj.length,String[].class);


            for (int i=0;i<ejercicio.size();i++)
            {
                adapter.add(strejercicos[i]);
            }




            s.setAdapter(adapter);
            return ejercicio;
        }
    }
    public void agregar (View v)
    {ArrayAdapter<CharSequence> adapter =
            new ArrayAdapter<CharSequence>(getApplicationContext(), android.R.layout.simple_spinner_item );
        Spinner s = (Spinner) findViewById(R.id.spinner4);
        EditText edt1=(EditText)findViewById(R.id.editText10);
        String ejercicioo= edt1.getText().toString();
        if (ejercicioo!="") {
            try {
                OkHttpClient client = new OkHttpClient();
                String url = "http://10.152.2.77/AgregarEjercicio.php?Nombre=" + ejercicioo;
                JSONObject json = new JSONObject();
                json.put("Nombre", ejercicioo);

                RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json.toString());

                Request request = new Request.Builder()
                        .url(url)
                        .build();

                Response response = client.newCall(request).execute();
                Log.d("Response", response.body().string());
                Log.d("aa", json.toString());
                Toast.makeText(ABMEJERCICIOS.this, "El Ejercicio ha sido agregado", Toast.LENGTH_SHORT).show();

                ejercicio.add(ejercicioo);

                Object[]obj=ejercicio.toArray();
                String[]strejercicos= Arrays.copyOf(obj,obj.length,String[].class);
                for (int i=0;i<ejercicio.size();i++)
                {
                    adapter.add(strejercicos[i]);
                }

                s.setAdapter(adapter);
            } catch (IOException | JSONException e) {
                Log.d("Error", e.getMessage());
            }
        }
    }
    public void eliminar (View v)
    {ArrayAdapter<CharSequence> adapter =
            new ArrayAdapter<CharSequence>(getApplicationContext(), android.R.layout.simple_spinner_item );
        Spinner s = (Spinner) findViewById(R.id.spinner4);

        String hola=s.getSelectedItem().toString();
        try {
            OkHttpClient client = new OkHttpClient();
            String url = "http://10.152.2.77/borrarejercicio.php?Nombre="+hola;
            JSONObject json = new JSONObject();
            json.put("Nombre", hola);

            RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json.toString());

            Request request = new Request.Builder()
                    .url(url)
                    .build();

            Response response = client.newCall(request).execute();
            Log.d("Response", response.body().string());
            Log.d("aa",json.toString());
            Toast.makeText(ABMEJERCICIOS.this, "El Ejercicio ha sido eliminado", Toast.LENGTH_SHORT).show();

            ejercicio.remove(hola);
            Object[]obj=ejercicio.toArray();
            String[]strejercicos= Arrays.copyOf(obj,obj.length,String[].class);
            for (int i=0;i<ejercicio.size();i++)
            {
                adapter.add(strejercicos[i]);
            }

            s.setAdapter(adapter);


        } catch (IOException | JSONException e) {
            Log.d("Error", e.getMessage());
        }
    }
    public void modificar (View v)
    {
        Spinner s = (Spinner) findViewById(R.id.spinner4);
        EditText edt=(EditText)findViewById(R.id.editText10);
        String yaesta=s.getSelectedItem().toString();
        String nuevo=edt.getText().toString();
        if(nuevo!="")
        {
        try {
            OkHttpClient client = new OkHttpClient();
            String url = "http://10.152.2.77/ModificarEjercicio.php?Nombre="+nuevo+"&Nombre1="+yaesta;


            JSONObject json = new JSONObject();
            json.put("Nombre", nuevo);
            json.put("Nombre1", yaesta);

            RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json.toString());

            Request request = new Request.Builder()
                    .url(url)
                    .build();

            Response response = client.newCall(request).execute();
            Log.d("Response", response.body().string());
            Log.d("aa",json.toString());
            Toast.makeText(ABMEJERCICIOS.this, "El Ejercicio ha sido modificado", Toast.LENGTH_SHORT).show();
            ejercicio.remove(yaesta);
            ejercicio.add(nuevo);
            ArrayAdapter<CharSequence> adapter =
                    new ArrayAdapter<CharSequence>(getApplicationContext(), android.R.layout.simple_spinner_item );
            Object[]obj=ejercicio.toArray();
            String[]strejercicos= Arrays.copyOf(obj,obj.length,String[].class);
            for (int i=0;i<ejercicio.size();i++)

            {
                adapter.add(strejercicos[i]);
            }

            s.setAdapter(adapter);}
        catch(IOException | JSONException e) {
            Log.d("Error", e.getMessage());
        }}


    }


}
