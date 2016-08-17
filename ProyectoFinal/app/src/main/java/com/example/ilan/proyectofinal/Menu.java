package com.example.ilan.proyectofinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }
    public void agregarrutina(View v)
    {
        Intent nuevaActivity = new Intent(this, AgRutina.class);
startActivity(nuevaActivity);
    }
    public void agregarejercicio(View v)
    {
        Intent nuevaActivity = new Intent(this, Main2Activity.class);
        startActivity(nuevaActivity);
    }
    public void altainstruido(View v)
    {
        Intent nuevaActivity = new Intent(this, MainActivity.class);
        startActivity(nuevaActivity);

    }
    public void rutinas(View v)
    { Intent nuevaActivity = new Intent(this, RutinInstructor.class);
        startActivity(nuevaActivity);

    }
    public void ABMEJ(View v)
    {
        Intent nuevaActivity = new Intent(this, ABMEJERCICIOS.class);
        startActivity(nuevaActivity);
    }
    public void ABMRUT (View v)
    {
        Intent nuevaActivity = new Intent(this, ABMRUTINA.class);
        startActivity(nuevaActivity);
    }
}
