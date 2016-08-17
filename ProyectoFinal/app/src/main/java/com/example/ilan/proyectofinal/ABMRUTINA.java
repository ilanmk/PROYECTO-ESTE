package com.example.ilan.proyectofinal;

import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class ABMRUTINA extends AppCompatActivity {
    public ArrayList<String> ejercicio =new ArrayList<String>();
    final String urlejercicio="http:/10.152.2.77/SelectRutina.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abmrutin);
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
            Spinner s = (Spinner) findViewById(R.id.spinner5);

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

    public void eliminar (View v)
    { Spinner s = (Spinner) findViewById(R.id.spinner5);

        String hola=s.getSelectedItem().toString();
        try {
            OkHttpClient client = new OkHttpClient();
            String url = "http://10.152.2.77/BorrarRutina.php?Nombre1="+hola;
            JSONObject json = new JSONObject();
            json.put("Nombre1", hola);

            RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json.toString());

            Request request = new Request.Builder()
                    .url(url)
                    .build();

            Response response = client.newCall(request).execute();
            Log.d("Response", response.body().string());
            Log.d("aa",json.toString());
            Toast.makeText(ABMRUTINA.this, "El Ejercicio ha sido eliminado", Toast.LENGTH_SHORT).show();
            ejercicio.remove(hola);
            ArrayAdapter<CharSequence> adapter =
                    new ArrayAdapter<CharSequence>(getApplicationContext(), android.R.layout.simple_spinner_item );
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
    public void alta(View v)
    {
        ArrayAdapter<CharSequence> adapter =
                new ArrayAdapter<CharSequence>(getApplicationContext(), android.R.layout.simple_spinner_item );
        Spinner s = (Spinner) findViewById(R.id.spinner5);
        String hola=s.getSelectedItem().toString();
        EditText edt1=(EditText)findViewById(R.id.editText11);
        EditText edt2=(EditText)findViewById(R.id.editText12);
        EditText edt3=(EditText)findViewById(R.id.editText13);
        String Calificacion= edt1.getText().toString();
        String nombre=edt2.getText().toString();
        String descripcion=edt3.getText().toString();
        if (Calificacion!="") {
            try {
                OkHttpClient client = new OkHttpClient();
                String url = "http://10.152.2.77/AgregarRutina.php?Calificacion=" + Calificacion+"&&Nombre="+nombre+"&&Descripcion="+descripcion;
                JSONObject json = new JSONObject();
                json.put("Calificacion", Calificacion);

                RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json.toString());

                Request request = new Request.Builder()
                        .url(url)
                        .build();

                Response response = client.newCall(request).execute();
                Log.d("Response", response.body().string());
                Log.d("aa", json.toString());
                Toast.makeText(ABMRUTINA.this, "El Ejercicio ha sido agregado", Toast.LENGTH_SHORT).show();
                ejercicio.add(hola);

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
        }}
        public void Modificar (View v)
    {
        Spinner s = (Spinner) findViewById(R.id.spinner5);
        String hola=s.getSelectedItem().toString();
        EditText edt1=(EditText)findViewById(R.id.editText11);
        EditText edt2=(EditText)findViewById(R.id.editText12);
        EditText edt3=(EditText)findViewById(R.id.editText13);
        String Calificacion= edt1.getText().toString();
        String nombre=edt2.getText().toString();
        String descripcion=edt3.getText().toString();
        if(Calificacion!=""&&nombre!=""&&descripcion!="")
        {
            try {
                OkHttpClient client = new OkHttpClient();
                String url = "http://10.152.2.77/ModificarRutina.php?Calificacion="+Calificacion+"&&Nombre="+nombre+"&&Descripcion="+descripcion+"&&Nombre1="+hola;


                JSONObject json = new JSONObject();
                json.put("Calificacion", Calificacion);
                json.put("Nombre1", hola);

                RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json.toString());

                Request request = new Request.Builder()
                        .url(url)
                        .build();

                Response response = client.newCall(request).execute();
                Log.d("Response", response.body().string());
                Log.d("aa",json.toString());
                Toast.makeText(ABMRUTINA.this, "El Ejercicio ha sido modificado", Toast.LENGTH_SHORT).show();
                ejercicio.remove(hola);
                ejercicio.add(nombre);
                ArrayAdapter<CharSequence> adapter =
                        new ArrayAdapter<CharSequence>(getApplicationContext(), android.R.layout.simple_spinner_item );
                Object[]obj=ejercicio.toArray();
                String[]strejercicos= Arrays.copyOf(obj,obj.length,String[].class);
                for (int i=0;i<ejercicio.size();i++)

                {
                    adapter.add(strejercicos[i]);
                }

                s.setAdapter(adapter);
            }
            catch(IOException | JSONException e) {
                Log.d("Error", e.getMessage());
            }}

    }
    }

