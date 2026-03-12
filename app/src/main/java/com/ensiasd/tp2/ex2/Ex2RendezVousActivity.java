package com.ensiasd.tp2.ex2;

import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import com.ensiasd.tp2.R;
import java.util.Arrays;
import java.util.List;

public class Ex2RendezVousActivity extends AppCompatActivity {

    private EditText etTitreRdv;
    private DatePicker datePickerRdv;
    private TimePicker timePickerRdv;
    private Spinner spinnerType;
    private Button btnValider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ex2_rendezvous);

        etTitreRdv    = findViewById(R.id.etTitreRdv);
        datePickerRdv = findViewById(R.id.datePickerRdv);
        timePickerRdv = findViewById(R.id.timePickerRdv);
        spinnerType   = findViewById(R.id.spinnerType);
        btnValider    = findViewById(R.id.btnValider);

        // Format 24h
        timePickerRdv.setIs24HourView(true);

        // Spinner : types de rendez-vous
        List<String> types = Arrays.asList("Médical", "Professionnel", "Personnel");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, types);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerType.setAdapter(adapter);

        btnValider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String titre = etTitreRdv.getText().toString().trim();
                if (titre.isEmpty()) {
                    etTitreRdv.setError("Le titre est requis");
                    return;
                }

                int jour  = datePickerRdv.getDayOfMonth();
                int mois  = datePickerRdv.getMonth() + 1;
                int annee = datePickerRdv.getYear();

                int heure   = timePickerRdv.getHour();
                int minutes = timePickerRdv.getMinute();

                String type = spinnerType.getSelectedItem().toString();

                String msg = "Rendez-vous enregistré !\n"
                        + "Titre : " + titre + "\n"
                        + "Date : " + jour + "/" + mois + "/" + annee + "\n"
                        + "Heure : " + String.format("%02d:%02d", heure, minutes) + "\n"
                        + "Type : " + type;

                Toast.makeText(Ex2RendezVousActivity.this, msg, Toast.LENGTH_LONG).show();
            }
        });
    }
}
