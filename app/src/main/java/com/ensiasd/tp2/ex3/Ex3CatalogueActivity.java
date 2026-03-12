package com.ensiasd.tp2.ex3;

import android.os.Bundle;
import android.view.*;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.AlertDialog;

import com.ensiasd.tp2.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class Ex3CatalogueActivity extends AppCompatActivity {

    private ListView listViewProduits;
    private FloatingActionButton fabAjouter;
    private ArrayList<String> nomsProduits;
    private ArrayList<String> prixProduits;
    private ProduitAdapter adapter;

    // Position sélectionnée pour le menu contextuel
    private int selectedPosition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ex3_catalogue);

        // Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listViewProduits = findViewById(R.id.listViewProduits);
        fabAjouter       = findViewById(R.id.fabAjouter);

        // Données initiales
        nomsProduits = new ArrayList<>();
        prixProduits = new ArrayList<>();

        nomsProduits.add("Produit 1"); prixProduits.add("10,00 €");
        nomsProduits.add("Produit 2"); prixProduits.add("20,00 €");
        nomsProduits.add("Produit 3"); prixProduits.add("30,00 €");

        adapter = new ProduitAdapter();
        listViewProduits.setAdapter(adapter);

        // Enregistrer le menu contextuel sur la ListView
        registerForContextMenu(listViewProduits);

        // FAB - Ajouter un produit
        fabAjouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int n = nomsProduits.size() + 1;

                nomsProduits.add("Produit " + n);
                prixProduits.add((n * 10) + ",00 €");

                adapter.notifyDataSetChanged();

                Toast.makeText(
                        Ex3CatalogueActivity.this,
                        "Produit " + n + " ajouté",
                        Toast.LENGTH_SHORT
                ).show();
            }
        });
    }

    // ── Menu AppBar ─────────────────────────────────────────────
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_catalogue, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.menu_panier) {

            Toast.makeText(this,"Panier ouvert",Toast.LENGTH_SHORT).show();
            return true;

        } else if (id == R.id.menu_parametres) {

            Toast.makeText(this,"Paramètres",Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // ── Menu contextuel (appui long) ─────────────────────────────
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {

        super.onCreateContextMenu(menu, v, menuInfo);

        getMenuInflater().inflate(R.menu.menu_contextuel, menu);

        AdapterView.AdapterContextMenuInfo info =
                (AdapterView.AdapterContextMenuInfo) menuInfo;

        selectedPosition = info.position;

        menu.setHeaderTitle(nomsProduits.get(selectedPosition));
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        int id = item.getItemId();

        // Voir détails
        if (id == R.id.ctx_voir_details) {

            Toast.makeText(
                    this,
                    "Détails : " +
                            nomsProduits.get(selectedPosition) +
                            " - " +
                            prixProduits.get(selectedPosition),
                    Toast.LENGTH_SHORT
            ).show();
        }

        // Modifier produit
        else if (id == R.id.ctx_modifier) {

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Modifier le produit");

            LayoutInflater inflater = getLayoutInflater();
            View dialogView = inflater.inflate(R.layout.dialog_modifier_produit, null);

            builder.setView(dialogView);

            EditText etNom  = dialogView.findViewById(R.id.etNomProduit);
            EditText etPrix = dialogView.findViewById(R.id.etPrixProduit);

            // remplir avec les anciennes valeurs
            etNom.setText(nomsProduits.get(selectedPosition));
            etPrix.setText(prixProduits.get(selectedPosition));

            builder.setPositiveButton("Enregistrer", (dialog, which) -> {

                String nouveauNom  = etNom.getText().toString();
                String nouveauPrix = etPrix.getText().toString();

                nomsProduits.set(selectedPosition, nouveauNom);
                prixProduits.set(selectedPosition, nouveauPrix);

                adapter.notifyDataSetChanged();

                Toast.makeText(
                        Ex3CatalogueActivity.this,
                        "Produit modifié",
                        Toast.LENGTH_SHORT
                ).show();
            });

            builder.setNegativeButton("Annuler", null);

            builder.show();
        }

        // Supprimer
        else if (id == R.id.ctx_supprimer) {

            nomsProduits.remove(selectedPosition);
            prixProduits.remove(selectedPosition);

            adapter.notifyDataSetChanged();

            Toast.makeText(
                    this,
                    "Produit supprimé",
                    Toast.LENGTH_SHORT
            ).show();
        }

        return true;
    }

    // ── Adapter personnalisé ─────────────────────────────────────
    private class ProduitAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return nomsProduits.size();
        }

        @Override
        public Object getItem(int pos) {
            return nomsProduits.get(pos);
        }

        @Override
        public long getItemId(int pos) {
            return pos;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if (convertView == null) {

                convertView = getLayoutInflater().inflate(
                        R.layout.item_produit,
                        parent,
                        false
                );
            }

            TextView tvNom  = convertView.findViewById(R.id.tvNomProduit);
            TextView tvPrix = convertView.findViewById(R.id.tvPrixProduit);

            tvNom.setText(nomsProduits.get(position));
            tvPrix.setText(prixProduits.get(position));

            return convertView;
        }
    }
}