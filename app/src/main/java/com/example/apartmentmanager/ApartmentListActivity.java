package com.example.apartmentmanager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import java.util.List;

public class ApartmentListActivity extends AppCompatActivity implements ApartmentAdapter.OnApartmentActionListener {
    private ListView listView;
    private Button btnAddNew;
    private DatabaseHelper dbHelper;
    private ApartmentAdapter adapter;
    private List<Apartment> apartments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apartment_list);

        dbHelper = new DatabaseHelper(this);
        listView = findViewById(R.id.listView);
        btnAddNew = findViewById(R.id.btnAddNew);

        btnAddNew.setOnClickListener(v -> {
            Intent intent = new Intent(this, ApartmentActivity.class);
            startActivity(intent);
        });

        loadApartments();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadApartments();
    }

    private void loadApartments() {
        apartments = dbHelper.getAllApartments();
        adapter = new ApartmentAdapter(this, apartments, this);
        listView.setAdapter(adapter);
    }

    @Override
    public void onEdit(Apartment apartment) {
        Intent intent = new Intent(this, ApartmentActivity.class);
        intent.putExtra("apartmentId", apartment.getApartmentID());
        startActivity(intent);
    }

    @Override
    public void onDelete(Apartment apartment) {
        new AlertDialog.Builder(this)
                .setTitle("Delete Apartment")
                .setMessage("Are you sure you want to delete this apartment?")
                .setPositiveButton("Yes", (dialog, which) -> {
                    dbHelper.deleteApartment(apartment.getApartmentID());
                    Toast.makeText(this, "Apartment deleted", Toast.LENGTH_SHORT).show();
                    loadApartments();
                })
                .setNegativeButton("No", null)
                .show();
    }
}
