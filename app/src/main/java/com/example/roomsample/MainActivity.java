package com.example.roomsample;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private AppDatabase db;
    private MedicationDao medicationDao;
    private EditText medicationNameInput;
    private EditText medicationDosageInput;
    private Button saveButton;
    private TextView medicationList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // UI要素の取得
        medicationNameInput = findViewById(R.id.medication_name);
        medicationDosageInput = findViewById(R.id.medication_dosage);
        saveButton = findViewById(R.id.save_button);
        medicationList = findViewById(R.id.medication_list);

        // データベースとDAOの取得
        db = AppDatabase.getDatabase(getApplicationContext());
        medicationDao = db.medicationDao();

        // 薬の保存ボタンのクリックリスナー
        saveButton.setOnClickListener(v -> saveMedication());

        // 保存された薬のリストを表示
        displayMedications();
    }

    private void saveMedication() {
        String name = medicationNameInput.getText().toString();
        int dosage = Integer.parseInt(medicationDosageInput.getText().toString());

        Medication medication = new Medication();
        medication.name = name;
        medication.dosage = dosage;

        // データベースに挿入（バックグラウンドスレッドで処理）
        new Thread(() -> {
            medicationDao.insertMedication(medication);
            runOnUiThread(this::displayMedications);  // メインスレッドでリストを更新
        }).start();
    }

    private void displayMedications() {
        // データベースからすべての薬情報を取得（バックグラウンドスレッド）
        new Thread(() -> {
            List<Medication> medications = medicationDao.getAllMedications();
            StringBuilder displayText = new StringBuilder();
            for (Medication medication : medications) {
                displayText.append("名前: ").append(medication.name)
                        .append(", 服用量: ").append(medication.dosage).append("\n");
            }
            runOnUiThread(() -> medicationList.setText(displayText.toString()));
        }).start();
    }
}