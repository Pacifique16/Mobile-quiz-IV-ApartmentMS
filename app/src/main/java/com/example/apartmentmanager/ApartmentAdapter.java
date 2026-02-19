package com.example.apartmentmanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import java.util.List;

public class ApartmentAdapter extends ArrayAdapter<Apartment> {
    private Context context;
    private List<Apartment> apartments;
    private OnApartmentActionListener listener;

    public interface OnApartmentActionListener {
        void onEdit(Apartment apartment);
        void onDelete(Apartment apartment);
    }

    public ApartmentAdapter(Context context, List<Apartment> apartments, OnApartmentActionListener listener) {
        super(context, 0, apartments);
        this.context = context;
        this.apartments = apartments;
        this.listener = listener;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.apartment_item, parent, false);
        }

        Apartment apartment = apartments.get(position);

        TextView tvUnitNumber = convertView.findViewById(R.id.tvUnitNumber);
        TextView tvSquareFootage = convertView.findViewById(R.id.tvSquareFootage);
        TextView tvDailyRent = convertView.findViewById(R.id.tvDailyRent);
        TextView tvIsAirBnb = convertView.findViewById(R.id.tvIsAirBnb);
        TextView tvAllowsPets = convertView.findViewById(R.id.tvAllowsPets);
        Button btnEdit = convertView.findViewById(R.id.btnEdit);
        Button btnDelete = convertView.findViewById(R.id.btnDelete);

        tvUnitNumber.setText("Unit: " + apartment.getUnitNumber());
        tvSquareFootage.setText("Sq Ft: " + apartment.getSquareFootage());
        tvDailyRent.setText(String.format("Daily Rent: $%.2f", apartment.getDailyRent()));
        tvIsAirBnb.setText("AirBnb: " + (apartment.getIsAirBnb() ? "Yes" : "No"));
        tvAllowsPets.setText("Pets: " + (apartment.getAllowsPets() ? "Yes" : "No"));

        btnEdit.setOnClickListener(v -> listener.onEdit(apartment));
        btnDelete.setOnClickListener(v -> listener.onDelete(apartment));

        return convertView;
    }
}
