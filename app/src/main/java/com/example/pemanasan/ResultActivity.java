package com.example.pemanasan;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {

    private ImageView imgResultFoto;
    private TextView tvResultNama, tvResultKelas,tvResultHariTanggal, tvResultJenisKelamin ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        imgResultFoto = findViewById(R.id.imgResultFoto);
        tvResultNama = findViewById(R.id.tvResultNama);
        tvResultKelas = findViewById(R.id.tvResultKelas);
        tvResultHariTanggal = findViewById(R.id.tvResultHariTanggal);
        tvResultJenisKelamin = findViewById(R.id.tvResultJenisKelamin);

        try {


            // Ambil data dari intent
            String nama = getIntent().getStringExtra("NAMA");
            String kelas = getIntent().getStringExtra("KELAS");
            String hariTanggal = getIntent().getStringExtra("HARITANGGAL");
            String jenisKelamin = getIntent().getStringExtra("JENIS_KELAMIN");
            String fotoUri = getIntent().getStringExtra("FOTO_URI");

            // Debug log
            Log.d("DEBUG", "Data diterima: Nama=" + nama + ", Kelas=" + kelas + ", Gender=" + jenisKelamin + ", URI=" + fotoUri);

            // Tampilkan data
            tvResultNama.setText("Nama: " + nama);
            tvResultKelas.setText("Kelas: " + kelas);
            tvResultHariTanggal.setText("Kelas: " + hariTanggal);
            tvResultJenisKelamin.setText("Jenis Kelamin: " + jenisKelamin);

            if (fotoUri != null) {
                imgResultFoto.setImageURI(Uri.parse(fotoUri));
            } else {
                imgResultFoto.setImageResource(R.drawable.ic_launcher_foreground); // Placeholder jika gambar tidak ada
            }
        } catch (Exception e) {
            Log.e("ERROR", "Terjadi kesalahan: " + e.getMessage());
            finish(); // Tutup aktivitas jika ada error
        }

        Button back = findViewById(R.id.button);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Membuat Intent untuk kembali ke MainActivity
                Intent intent = new Intent(ResultActivity.this, MainActivity.class);
                startActivity(intent);
                // Optional: menambahkan animasi transisi
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });

    }
}