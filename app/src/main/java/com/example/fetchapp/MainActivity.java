package com.example.fetchapp;

import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fetchapp.adapters.ItemAdapter;
import com.example.fetchapp.models.Item;
import com.example.fetchapp.network.ApiService;
import com.example.fetchapp.network.RetrofitClient;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ItemAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        fetchData();
    }

    private void fetchData() {
        ApiService apiService = RetrofitClient.getClient().create(ApiService.class);
        apiService.getItems().enqueue(new Callback<List<Item>>() {
            @Override
            public void onResponse(Call<List<Item>> call, Response<List<Item>> response) {
                if (response.isSuccessful()) {
                    List<Item> items = response.body();
                    List<Item> filtered = new ArrayList<>();
                    for (Item item : items) {
                        if (item.name != null && !item.name.trim().isEmpty()) {
                            filtered.add(item);
                        }
                    }
                    Collections.sort(filtered, Comparator
                            .comparingInt((Item i) -> i.listId)
                            .thenComparing(i -> i.name));

                    adapter = new ItemAdapter(filtered);
                    recyclerView.setAdapter(adapter);
                } else {
                    showError();
                }
            }

            @Override
            public void onFailure(Call<List<Item>> call, Throwable t) {
                showError();
            }

            private void showError() {
                Toast.makeText(MainActivity.this, "Error fetching data", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
