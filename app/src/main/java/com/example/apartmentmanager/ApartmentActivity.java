package com.example.apartmentmanager;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class ApartmentActivity extends AppCompatActivity {
    private EditText etUnitNumber, etSquareFootage, etRentAmount, etLocation;
    private CheckBox cbIsAirBnb, cbAllowsPets;
    private Button btnSave;
    private DatabaseHelper dbHelper;
    private Integer apartmentId = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apartment);

        dbHelper = new DatabaseHelper(this);
        
        etUnitNumber = findViewById(R.id.etUnitNumber);
        etSquareFootage = findViewById(R.id.etSquareFootage);
        etRentAmount = findViewById(R.id.etRentAmount);
        etLocation = findViewById(R.id.etLocation);
        cbIsAirBnb = findViewById(R.id.cbIsAirBnb);
        cbAllowsPets = findViewById(R.id.cbAllowsPets);
        btnSave = findViewById(R.id.btnSave);

        apartmentId = getIntent().getIntExtra("apartmentId", -1);
        if (apartmentId != -1) {
            loadApartmentData();
            btnSave.setText("Update");
        }

        btnSave.setOnClickListener(v -> saveApartment());
    }

    private void loadApartmentData() {
        Apartment apartment = dbHelper.getApartment(apartmentId);
        if (apartment != null) {
            etUnitNumber.setText(apartment.getUnitNumber());
            etSquareFootage.setText(String.valueOf(apartment.getSquareFootage()));
            etRentAmount.setText(String.valueOf(apartment.getRentAmount()));
            etLocation.setText(apartment.getLocation());
            cbIsAirBnb.setChecked(apartment.getIsAirBnb());
            cbAllowsPets.setChecked(apartment.getAllowsPets());
        }
    }

    private void saveApartment() {
        String unitNumber = etUnitNumber.getText().toString().trim();
        String squareFootage = etSquareFootage.getText().toString().trim();
        String rentAmount = etRentAmount.getText().toString().trim();
        String location = etLocation.getText().toString().trim();

        if (unitNumber.isEmpty() || squareFootage.isEmpty() || rentAmount.isEmpty() || location.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        Apartment apartment = new Apartment();
        apartment.setUnitNumber(unitNumber);
        apartment.setSquareFootage(Float.parseFloat(squareFootage));
        apartment.setRentAmount(Double.parseDouble(rentAmount));
        apartment.setLocation(location);
        apartment.setIsAirBnb(cbIsAirBnb.isChecked());
        apartment.setAllowsPets(cbAllowsPets.isChecked());

        if (apartmentId != -1) {
            apartment.setApartmentID(apartmentId);
            dbHelper.updateApartment(apartment);
            Toast.makeText(this, "Apartment updated", Toast.LENGTH_SHORT).show();
        } else {
            dbHelper.addApartment(apartment);
            Toast.makeText(this, "Apartment added", Toast.LENGTH_SHORT).show();
        }
        finish();
    }
}
