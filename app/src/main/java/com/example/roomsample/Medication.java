package com.example.roomsample;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Medication {
    @PrimaryKey(autoGenerate = true)
    public int id;             // 自動生成されるID
    public String name;        // 薬の名前
    public int dosage;         // 服用量
    public String frequency;   // 服用頻度
    public long time;          // 服用時間（Unixタイムスタンプ）
    public String reminder;    // リマインダー情報
}
