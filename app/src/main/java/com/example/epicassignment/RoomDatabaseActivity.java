package com.example.epicassignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class RoomDatabaseActivity extends AppCompatActivity {

    Toolbar toolbar;

    EditText editText;
    Button button;
    RecyclerView noteList;

    List<NoteData> dataList = new ArrayList<>();
    LinearLayoutManager linearLayoutManager;
    RoomDB database;
    NoteAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_database);

        toolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Room Database");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        editText = findViewById(R.id.note_input);
        button = findViewById(R.id.note_add_btn);
        noteList = findViewById(R.id.note_list);

        database = RoomDB.getInstance(this);

        dataList = database.noteDao().getAll();

        linearLayoutManager = new LinearLayoutManager(this);
        noteList.setLayoutManager(linearLayoutManager);
        adapter = new NoteAdapter(RoomDatabaseActivity.this, dataList);
        noteList.setAdapter(adapter);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = editText.getText().toString().trim();

                if(!text.isEmpty()) {
                    NoteData data = new NoteData();
                    data.setNote(text);
                    database.noteDao().insert(data);
                    editText.setText("");

                    dataList.clear();
                    dataList.addAll(database.noteDao().getAll());
                    adapter.notifyDataSetChanged();
                }
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp()
    {
        onBackPressed();
        return true;
    }
}