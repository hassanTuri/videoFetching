package com.mad.videofetching;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        RecyclerView recyclerView = findViewById(R.id.reyclerview);


        database.getReference("Pashto/English/Reading").get().addOnCompleteListener(task -> {
            if(task.isSuccessful()){
                List<VideoRecycler.VideoModel> list = new ArrayList<>();
                for(DataSnapshot child : task.getResult().getChildren()){
                    Map<String, Object> map = (Map<String, Object>) child.getValue();
                    list.add(new VideoRecycler.VideoModel((String) map.get("name"), (String) map.get("url")));
                }

                VideoRecycler adapter = new VideoRecycler(list);
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this, RecyclerView.VERTICAL, false));

            }
        });

    }
}