package com.ensiasd.tp2.ex5;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.ensiasd.tp2.R;

public class Ex5ScrollDialogActivity extends AppCompatActivity {

    private Button btnVoirPlus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ex5_scrollview);

        btnVoirPlus = findViewById(R.id.btnVoirPlus);

        // Bouton "Voir Plus" → Dialog informatif
        btnVoirPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                afficherDialogInformatif();
            }
        });
    }

    /** Dialog informatif (simple OK) */
    private void afficherDialogInformatif() {
        new AlertDialog.Builder(this)
                .setTitle("Informations")
                .setMessage("Ce document décrit les conditions d'utilisation de notre application.\n\n"
                        + "Veuillez les consulter régulièrement.")
                .setPositiveButton("OK", null)
                .show();
    }

    /** Dialog de confirmation quitter (appelé via onBackPressed) */
    private void afficherDialogConfirmation() {
        new AlertDialog.Builder(this)
                .setTitle("Quitter")
                .setMessage("Voulez-vous quitter cette page ?")
                .setPositiveButton("OK", (dialog, which) -> finish())
                .setNegativeButton("Annuler", null)
                .show();
    }

    @Override
    public void onBackPressed() {
        // Interception du bouton Retour → Dialog de confirmation
        afficherDialogConfirmation();
    }
}
