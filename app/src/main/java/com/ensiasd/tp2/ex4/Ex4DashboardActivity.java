package com.ensiasd.tp2.ex4;

import android.os.Bundle;
import android.view.*;
import android.widget.*;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.ensiasd.tp2.R;
import java.util.Arrays;
import java.util.List;

public class Ex4DashboardActivity extends AppCompatActivity {

    private ListView listActivites;
    private Button btnModifierProfil, btnParametres, btnDeconnexion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ex4_dashboard);

        // Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Tableau de Bord");
        }

        listActivites     = findViewById(R.id.listActivites);
        btnModifierProfil = findViewById(R.id.btnModifierProfil);
        btnParametres     = findViewById(R.id.btnParametres);
        btnDeconnexion    = findViewById(R.id.btnDeconnexion);

        // Activités récentes
        List<String> activites = Arrays.asList(
                "Connexion réussie",
                "Email modifié",
                "Lecture du dernier article",
                "Profil mis à jour",
                "Paramètres modifiés"
        );
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_list_item_1, activites);
        listActivites.setAdapter(adapter);

        // Boutons
        btnModifierProfil.setOnClickListener(v ->
                Toast.makeText(this, "Modifier Profil", Toast.LENGTH_SHORT).show());

        btnParametres.setOnClickListener(v ->
                Toast.makeText(this, "Paramètres", Toast.LENGTH_SHORT).show());

        btnDeconnexion.setOnClickListener(v -> confirmerDeconnexion());
    }

    private void confirmerDeconnexion() {
        new AlertDialog.Builder(this)
                .setTitle("Déconnexion")
                .setMessage("Voulez-vous vraiment vous déconnecter ?")
                .setPositiveButton("Oui", (dialog, which) ->
                        Toast.makeText(this, "Déconnecté !", Toast.LENGTH_SHORT).show())
                .setNegativeButton("Annuler", null)
                .show();
    }

    // ── Menu AppBar ──────────────────────────────────────────────────────────
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_dashboard, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_modifier_profil) {
            Toast.makeText(this, "Modifier Profil", Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == R.id.menu_deconnexion) {
            confirmerDeconnexion();
            return true;
        } else if (id == R.id.menu_aide) {
            Toast.makeText(this, "Aide", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
