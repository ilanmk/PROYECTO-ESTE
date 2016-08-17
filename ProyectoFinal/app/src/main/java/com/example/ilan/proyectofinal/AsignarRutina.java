package com.example.ilan.proyectofinal;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
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

public class AsignarRutina extends AppCompatActivity {
    ArrayList<String> ejercicios=new ArrayList<String>();
    final String urlejercicio="http://10.152.2.77/SelectEjercicio.php";
    ArrayList<String> usuarios=new ArrayList<String>();
    final String urlusuario="http://10.152.2.77/SelectInstructor.php";
    public String idEjercicio="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asignar_rutina);
        new EjerciciosTask().execute(urlejercicio);
        new UsuariosTask().execute(urlusuario);


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


            Spinner spin = (Spinner) findViewById(R.id.spinner7);

            spin.setAdapter(adapter);
            return ejercicios;
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
                    .url(urlusuario)
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

                String Nombre = jsonResultado.getString("Nombre");

                String usu=Nombre;
                usuarios.add(usu);
            }
            Object[]obj=usuarios.toArray();
            String[]strusuarios= Arrays.copyOf(obj,obj.length,String[].class);
            ArrayAdapter<CharSequence> adapter =
                    new ArrayAdapter<CharSequence>(getApplicationContext(), android.R.layout.simple_spinner_item );
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            for (int i=0;i<usuarios.size();i++)
            {
                adapter.add(strusuarios[i]);
            }


            Spinner spin = (Spinner) findViewById(R.id.spinner6);

            spin.setAdapter(adapter);
            return usuarios;
        }
    }
    public void Asignar(View v)
    {
        EditText edt1=(EditText)findViewById(R.id.editText14);
        String uno=edt1.getText().toString();
        EditText edt2=(EditText)findViewById(R.id.editText15);
        String dos=edt2.getText().toString();
        if (uno == ""|| dos == "")
        {
            Toast.makeText(AsignarRutina.this, "Ingrese los datos correctamente", Toast.LENGTH_SHORT).show();
        }
        else
        {
            try {
                OkHttpClient client = new OkHttpClient();
                String url = "http://10.152.2.77/AgregarEjRutina.php?Peso="+uno+"&Series="+dos+"&Ejercicio="+idEjercicio;
                JSONObject json = new JSONObject();
                json.put("Peso", uno);
                json.put("Series", dos);
                json.put("Ejercicio", idEjercicio);


                RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json.toString());

                Request request = new Request.Builder()
                        .url(url)
                        .build();

                Response response = client.newCall(request).execute();
                Log.d("Response", response.body().string());
                Log.d("aa",json.toString());
                Toast.makeText(AsignarRutina.this, "La rutina ha sido agregada", Toast.LENGTH_SHORT).show();
            } catch (IOException | JSONException e) {
                Log.d("Error", e.getMessage());
            }
        }
    }
}
