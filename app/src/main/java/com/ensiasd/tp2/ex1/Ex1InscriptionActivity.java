package com.ensiasd.tp2.ex1;

import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import com.ensiasd.tp2.R;

public class Ex1InscriptionActivity extends AppCompatActivity {

    private EditText etNom, etPrenom, etEmail, etPassword;
    private DatePicker datePickerBirth;
    private RadioGroup rgGender;
    private CheckBox cbConditions;
    private Button btnInscrire;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ex1_inscription);

        etNom        = findViewById(R.id.etNom);
        etPrenom     = findViewById(R.id.etPrenom);
        etEmail      = findViewById(R.id.etEmail);
        etPassword   = findViewById(R.id.etPassword);
        datePickerBirth = findViewById(R.id.datePickerBirth);
        rgGender     = findViewById(R.id.rgGender);
        cbConditions = findViewById(R.id.cbConditions);
        btnInscrire  = findViewById(R.id.btnInscrire);

        btnInscrire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nom    = etNom.getText().toString().trim();
                String prenom = etPrenom.getText().toString().trim();
                String email  = etEmail.getText().toString().trim();
                String mdp    = etPassword.getText().toString().trim();

                // Validation simple
                if (nom.isEmpty()) {
                    etNom.setError("Le nom est requis");
                    return;
                }
                if (prenom.isEmpty()) {
                    etPrenom.setError("Le prénom est requis");
                    return;
                }
                if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    etEmail.setError("Email invalide");
                    return;
                }
                if (mdp.isEmpty() || mdp.length() < 6) {
                    etPassword.setError("Mot de passe trop court (min 6 caractères)");
                    return;
                }
                if (!cbConditions.isChecked()) {
                    Toast.makeText(Ex1InscriptionActivity.this,
                            "Veuillez accepter les conditions d'utilisation", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Date
                int jour = datePickerBirth.getDayOfMonth();
                int mois = datePickerBirth.getMonth() + 1;
                int annee = datePickerBirth.getYear();

                // Genre
                int selectedId = rgGender.getCheckedRadioButtonId();
                RadioButton rb = findViewById(selectedId);
                String genre = rb != null ? rb.getText().toString() : "";

                String msg = "Inscription réussie !\n" + nom + " " + prenom +
                        "\nEmail: " + email +
                        "\nNé(e) le: " + jour + "/" + mois + "/" + annee +
                        "\nGenre: " + genre;

                Toast.makeText(Ex1InscriptionActivity.this, msg, Toast.LENGTH_LONG).show();
            }
        });
    }
}
