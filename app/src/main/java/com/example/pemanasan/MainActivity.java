package com.example.pemanasan;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.*;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final int PICK_IMAGE = 1;
    private ImageView imgFoto;
    private EditText etNama, etKelas, etHariTanggal;
    private RadioGroup rgJenisKelamin;
    private Button btnUploadFoto, btnSimpan;
    private Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inisialisasi view
        imgFoto = findViewById(R.id.imgFoto);
        etNama = findViewById(R.id.etNama);
        etKelas = findViewById(R.id.etKelas);
        etHariTanggal = findViewById(R.id.etHariTanggal);
        rgJenisKelamin = findViewById(R.id.rgJenisKelamin);
        btnUploadFoto = findViewById(R.id.btnUploadFoto);
        btnSimpan = findViewById(R.id.btnSimpan);

        // Tombol upload foto
        btnUploadFoto.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent, PICK_IMAGE);
        });

        // Tombol simpan
        btnSimpan.setOnClickListener(view -> {
            try {
                String nama = etNama.getText().toString();
                String kelas = etKelas.getText().toString();
                String hariTanggal = etHariTanggal.getText().toString();
                int selectedGenderId = rgJenisKelamin.getCheckedRadioButtonId();

                // Validasi input
                if (imageUri == null) {
                    Toast.makeText(this, "Harap upload foto!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (nama.isEmpty()) {
                    Toast.makeText(this, "Harap isi nama!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (kelas.isEmpty()) {
                    Toast.makeText(this, "Harap isi kelas!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (hariTanggal.isEmpty()) {
                    Toast.makeText(this, "Harap isi hari tanggal!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (selectedGenderId == -1) {
                    Toast.makeText(this, "Harap pilih jenis kelamin!", Toast.LENGTH_SHORT).show();
                    return;
                }

                String jenisKelamin = ((RadioButton) findViewById(selectedGenderId)).getText().toString();

                // Debug log
                Log.d("DEBUG", "Nama: " + nama + ", Kelas: " + kelas + ", Gender: " + jenisKelamin + ", URI: " + imageUri);

                // Pindah ke halaman baru
                Intent intent = new Intent(MainActivity.this, ResultActivity.class);
                intent.putExtra("NAMA", nama);
                intent.putExtra("KELAS", kelas);
                intent.putExtra("HARITANGGAL", hariTanggal);
                intent.putExtra("JENIS_KELAMIN", jenisKelamin);
                intent.putExtra("FOTO_URI", imageUri.toString());
                startActivity(intent);
            } catch (Exception e) {
                Log.e("ERROR", "Terjadi kesalahan: " + e.getMessage());
                Toast.makeText(this, "Terjadi kesalahan, silakan coba lagi.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK && data != null) {
            imageUri = data.getData();
            if (imageUri != null) {
                imgFoto.setImageURI(imageUri);
            } else {
                Toast.makeText(this, "Gagal memilih gambar", Toast.LENGTH_SHORT).show();
            }
 }
}
}