package com.ensiasd.tp2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.ensiasd.tp2.ex1.Ex1InscriptionActivity;
import com.ensiasd.tp2.ex2.Ex2RendezVousActivity;
import com.ensiasd.tp2.ex3.Ex3CatalogueActivity;
import com.ensiasd.tp2.ex4.Ex4DashboardActivity;
import com.ensiasd.tp2.ex5.Ex5ScrollDialogActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btnEx1).setOnClickListener(v ->
                startActivity(new Intent(this, Ex1InscriptionActivity.class)));

        findViewById(R.id.btnEx2).setOnClickListener(v ->
                startActivity(new Intent(this, Ex2RendezVousActivity.class)));

        findViewById(R.id.btnEx3).setOnClickListener(v ->
                startActivity(new Intent(this, Ex3CatalogueActivity.class)));

        findViewById(R.id.btnEx4).setOnClickListener(v ->
                startActivity(new Intent(this, Ex4DashboardActivity.class)));

        findViewById(R.id.btnEx5).setOnClickListener(v ->
                startActivity(new Intent(this, Ex5ScrollDialogActivity.class)));
    }
}
