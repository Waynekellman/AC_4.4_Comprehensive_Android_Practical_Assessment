package com.nyc.ac_44_comprehensive_android_practical_assessment;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.nyc.ac_44_comprehensive_android_practical_assessment.controller.BreedAdapter;
import com.nyc.ac_44_comprehensive_android_practical_assessment.model.BreedImgList;
import com.nyc.ac_44_comprehensive_android_practical_assessment.network.BreedsApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DogsActivity extends AppCompatActivity {

    private static final String TAG = "DogsActivity";
    private Retrofit retrofit;
    private String breedName;
    private RecyclerView recyclerView;
    private BreedAdapter breedAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dogs);
        recyclerView = findViewById(R.id.recyclerview);

        Intent intent = getIntent();
        if (intent != null) {
            breedName = intent.getStringExtra("breedName");
            Log.d(TAG, "onCreate: " + intent.getStringExtra("breedName"));
        } else {
            breedName = "";
        }

        breedAdapter = new BreedAdapter();
        GridLayoutManager layoutManager = new GridLayoutManager(this,2, LinearLayoutManager.VERTICAL,false);
        recyclerView.setAdapter(breedAdapter);
        recyclerView.setLayoutManager(layoutManager);

        retrofit = new Retrofit.Builder()
                .baseUrl("https://dog.ceo/api/breed/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        if (!breedName.equals("")){
            BreedsApi breedService = retrofit.create(BreedsApi.class);
            Call<BreedImgList> breedImgListCall = breedService.getList(breedName);
            breedImgListCall.enqueue(new Callback<BreedImgList>() {
                @Override
                public void onResponse(Call<BreedImgList> call, Response<BreedImgList> response) {
                    if (response.isSuccessful()) {
                        breedAdapter.setBreedImgUrl(response.body().getMessage());
                    }
                }

                @Override
                public void onFailure(Call<BreedImgList> call, Throwable t) {
                    t.printStackTrace();
                }
            });
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection


        switch (item.getItemId()) {
            case R.id.logout_menu:
                Intent intent = new Intent(DogsActivity.this, LoginActivity.class);
                intent.putExtra("logout", true);
                startActivity(intent);

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
