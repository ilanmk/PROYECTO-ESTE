package com.example.ilan.proyectofinal;

import android.app.DownloadManager;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



    }




    public void Hombre (View v)
    {

        RadioButton rdbmuj=(RadioButton)findViewById(R.id.radioButton2);
        rdbmuj.setChecked(false);
        RadioButton rdbHom=(RadioButton)findViewById(R.id.radioButton);
        rdbHom.setChecked(true);

    }
    public void Mujer (View v)
    {
        RadioButton rdbHomb=(RadioButton)findViewById(R.id.radioButton);
        rdbHomb.setChecked(false);
        RadioButton rdbmuj=(RadioButton)findViewById(R.id.radioButton2);
        rdbmuj.setChecked(true);

    }
    public void SI (View v)
    { RadioButton rdbHomb=(RadioButton)findViewById(R.id.radioButton3);
        rdbHomb.setChecked(true);
        RadioButton rdbmuj=(RadioButton)findViewById(R.id.radioButton4);
        rdbmuj.setChecked(false);}

    public void NO (View v)
    {
        RadioButton rdbmuj=(RadioButton)findViewById(R.id.radioButton4);
        rdbmuj.setChecked(true);
        RadioButton rdbHom=(RadioButton)findViewById(R.id.radioButton3);
        rdbHom.setChecked(false);
    }
    public void Registrar (View v)

    {String sexo=""; String errores="";String fuealgym="";
        boolean ok=true;
        String fechita="";
        EditText nom=(EditText)findViewById(R.id.editText);
        String nombre=nom.getText().toString();
        int largonom=nombre.length();
        boolean nvacio=nombre.isEmpty();
        boolean oka=true;
        for (int i=0;i<largonom;i++)
        {char[] letra=nombre.toCharArray();
            String hola=String.valueOf(letra[i]);

            String regx = "[a-zA-Z]";
            Pattern pattern = Pattern.compile(regx,Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(hola);


            boolean estabien=matcher.find();
            if (nvacio==false) {
                if(estabien==false)
                {
                    ok=false;
                    oka=false;
                    nom.setBackgroundColor(Color.RED);
                }
                else

                {
                    nom.setBackgroundColor(Color.GREEN);
                }


            }
            else{ok=false;
                nom.setBackgroundColor(Color.RED);}}
        EditText ap=(EditText)findViewById(R.id.editText2);
        String apellido=ap.getText().toString();
        int largoap=apellido.length();
        boolean avacio=apellido.isEmpty();
        boolean okaa=true;
        for (int i=0;i<largoap;i++)
        {char[] letra=apellido.toCharArray();
            String hola=String.valueOf(letra[i]);

            String regx = "[a-zA-Z]";
            Pattern pattern = Pattern.compile(regx,Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(hola);


            boolean estabien=matcher.find();
            if (nvacio==false) {
                if(estabien==false)
                {
                    ok=false;
                    okaa=false;
                    ap.setBackgroundColor(Color.RED);
                }
                else

                {
                    ap.setBackgroundColor(Color.GREEN);
                }


            }
            else{ok=false;
                ap.setBackgroundColor(Color.RED);}}
        EditText mai=(EditText)findViewById(R.id.editText3);
        String mail=mai.getText().toString();
        int largomail=mail.length();
        boolean okpunto=false;
        boolean okarroba=false;
        for (int i=0;i<largomail;i++)
        {
            char[]letra =mail.toCharArray();
            char hola=letra[i];
            if (hola=='.')
            {okpunto=true;
                mai.setBackgroundColor(Color.GREEN);}
            if (hola=='@')
            {
                okarroba=true;
                mai.setBackgroundColor(Color.GREEN);
            }

        }
        if (okpunto==false)
        {ok=false;
            mai.setBackgroundColor(Color.RED);
        }
        if(okarroba==false)
        {ok=false;
            mai.setBackgroundColor(Color.RED);}
        EditText con=(EditText)findViewById(R.id.editText4);
        String contraseña=con.getText().toString();
        int largocon=contraseña.length();
        if(largocon<5)
        {
            ok=false;
            con.setBackgroundColor(Color.RED);
            Toast.makeText(MainActivity.this, "La contraseña debe tener minimo 5 caracteres", Toast.LENGTH_SHORT).show();
        }
        else
        {con.setBackgroundColor(Color.GREEN);}
        EditText pes=(EditText)findViewById(R.id.editText6);
        String peso=pes.getText().toString();
        if (peso.length()>0) {
            int pesint = Integer.parseInt(peso);
            if (pesint < 20 || pesint > 300 || peso == "") {
                ok = false;
                pes.setBackgroundColor(Color.RED);

            } else {
                pes.setBackgroundColor(Color.GREEN);
            }
        }
        else
        {pes.setBackgroundColor(Color.RED);}

        EditText fech=(EditText)findViewById(R.id.editText5) ;
        String fecha=fech.getText().toString();
        int largo=fecha.length();
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String inputDate = fecha;
        try {

            formatter.setLenient(false);
            Date date = formatter.parse(inputDate);
            fech.setBackgroundColor(Color.GREEN);
            Calendar cal=Calendar.getInstance();
            Date nuevafecha=cal.getTime();

            if(date.after(nuevafecha))
            {ok=false;
                fech.setBackgroundColor(Color.RED);

            }
            fechita=fech.getText().toString();
        } catch (ParseException e) {
            e.printStackTrace();
            ok=false;
            fech.setBackgroundColor(Color.RED);
        }



        EditText alt=(EditText)findViewById(R.id.editText7);
        String altura=alt.getText().toString();
        int largoaltura=altura.length();
        boolean vacio=altura.isEmpty();
        if (vacio==false) {
            int altint = Integer.parseInt(altura);
            if (altint < 60 || altint > 250) {
                ok = false;
                alt.setBackgroundColor(Color.RED);
            } else {
                alt.setBackgroundColor(Color.GREEN);
            }
        }
        else
        {alt.setBackgroundColor(Color.RED);}
        RadioButton rdbSex=(RadioButton)findViewById(R.id.radioButton);
        if (rdbSex.isChecked()==true)
        {
            sexo="Hombre";
        }
        if ( rdbSex.isChecked()==false)
        { sexo="Mujer";}
        EditText comp=(EditText)findViewById(R.id.editText8);
        String complicaciones=comp.getText().toString();
        RadioButton rdbsi=(RadioButton)findViewById(R.id.radioButton3);
        if (rdbsi.isChecked()==true)
        {
            fuealgym="si";
        }
        if(rdbsi.isChecked()==false)
        { fuealgym="no";}
        if(ok==true)
        {
            Toast.makeText(MainActivity.this, "El instruido ha sido agregado", Toast.LENGTH_SHORT).show();
            try {
                OkHttpClient client = new OkHttpClient();
                String url ="http://10.152.2.77/AgregarInstruido.php?Nombre="+nombre+"&Apellido="+apellido+"&Mail="+mail+"&Contraseña="+contraseña+"&Fecha="+fechita+"&Peso=+"+peso+"&Altura="+altura+"&Sexo="+sexo+"&Complicaciones="+complicaciones+"&FueAlGym="+fuealgym;
                JSONObject json = new JSONObject();
                json.put("Nombre", nombre);
                json.put("Apellido", apellido);
                json.put("Mail", mail);
                json.put("Contrasena", contraseña);
                json.put("Fecha", fechita);
                json.put("Peso", peso);
                json.put("Altura", altura);
                json.put("Sexo", sexo);
                json.put("Complicaciones", complicaciones);
                json.put("FueAlGym", fuealgym);
                RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json.toString());

                Request request = new Request.Builder()
                        .url(url)
                        .build();

                Response response = client.newCall(request).execute();
                Log.d("Response", response.body().string());
            } catch (IOException | JSONException e) {
                Log.d("Error", e.getMessage());
            }
        }
        else
        {
            Toast.makeText(MainActivity.this, "la cagaste", Toast.LENGTH_SHORT).show();
        }
    }
    public void volver(View v)
    {
        Intent nuevaActivity = new Intent(this, Menu.class);
        startActivity(nuevaActivity);
    }
}
