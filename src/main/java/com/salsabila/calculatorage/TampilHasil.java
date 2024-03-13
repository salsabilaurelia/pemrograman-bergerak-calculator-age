package com.salsabila.calculatorage;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TampilHasil extends AppCompatActivity {
    TextView etNama, tvDate, showAge;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampil_hasil);

        etNama = findViewById(R.id.etNama);
        tvDate = findViewById(R.id.tvDate);
        showAge = findViewById(R.id.showAge);

        Intent intent = getIntent();
        if (intent != null) {
            String nama = intent.getStringExtra("nama");
            String tanggalLahir = intent.getStringExtra("tanggal_lahir");
            etNama.setText("Nama: " + nama);
            tvDate.setText("Tanggal Lahir: " + tanggalLahir);

            try {
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                Date birthDate = sdf.parse(tanggalLahir);

                Calendar dob = Calendar.getInstance();
                dob.setTime(birthDate);

                Calendar today = Calendar.getInstance();

                int years = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);
                int months = today.get(Calendar.MONTH) - dob.get(Calendar.MONTH);
                int days = today.get(Calendar.DAY_OF_MONTH) - dob.get(Calendar.DAY_OF_MONTH);

                if (months < 0 || (months == 0 && days < 0)) {
                    years--;
                    if (months < 0) {
                        months += 12;
                    }
                }

                showAge.setText("Umur: " + years + " tahun, " + months + " bulan, " + days + " hari");
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }
}
