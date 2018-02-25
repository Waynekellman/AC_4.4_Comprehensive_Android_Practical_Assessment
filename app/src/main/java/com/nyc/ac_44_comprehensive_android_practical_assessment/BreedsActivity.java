package com.nyc.ac_44_comprehensive_android_practical_assessment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.nyc.ac_44_comprehensive_android_practical_assessment.model.Breed;
import com.nyc.ac_44_comprehensive_android_practical_assessment.network.BreedsApi;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BreedsActivity extends AppCompatActivity implements View.OnClickListener{

    private SharedPreferences sharedPreferences;
    private Breed breed;
    private Retrofit retrofit;
    private ImageView terrier, spaniel, retriever, poodle;
    private LinearLayout terrier_layout, spaniel_layout, retriever_layout, poodle_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breeds);
        sharedPreferences = getSharedPreferences(LoginActivity.SHARED_PREF_KEY,MODE_PRIVATE);
        if (sharedPreferences.getString("username","").equals("")){
            Intent intent = new Intent(BreedsActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
        terrier = findViewById(R.id.terrier);
        spaniel = findViewById(R.id.spaniel);
        retriever = findViewById(R.id.retriever);
        poodle = findViewById(R.id.poodle);
        terrier_layout = findViewById(R.id.terrier_layout);
        spaniel_layout = findViewById(R.id.spaniel_layout);
        retriever_layout = findViewById(R.id.retriever_layout);
        poodle_layout = findViewById(R.id.poodle_layout);

        terrier_layout.setOnClickListener(this);
        spaniel_layout.setOnClickListener(this);
        retriever_layout.setOnClickListener(this);
        poodle_layout.setOnClickListener(this);




        retrofit = new Retrofit.Builder()
                .baseUrl("https://dog.ceo/api/breed/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        getImg("terrier");
        getImg("spaniel");
        getImg("retriever");
        getImg("poodle");



    }

    private void getImg(String breedName) {
        BreedsApi breedService = retrofit.create(BreedsApi.class);
        Call<Breed> getBreedimg = breedService.getBreed(breedName);
        getBreedimg.enqueue(new Callback<Breed>() {
            @Override
            public void onResponse(Call<Breed> call, Response<Breed> response) {

                breed = response.body();

                if (breed.getMessage().contains("terrier")) {
                    Picasso.with(BreedsActivity.this).load(breed.getMessage()).into(terrier);
                }
                if (breed.getMessage().contains("spaniel")) {
                    Picasso.with(BreedsActivity.this).load(breed.getMessage()).into(spaniel);
                }
                if (breed.getMessage().contains("retriever")) {
                    Picasso.with(BreedsActivity.this).load(breed.getMessage()).into(retriever);
                }
                if (breed.getMessage().contains("poodle")) {
                    Picasso.with(BreedsActivity.this).load(breed.getMessage()).into(poodle);
                }
            }

            @Override
            public void onFailure(Call<Breed> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.terrier_layout:

                Intent intentTerrier =new Intent(BreedsActivity.this, DogsActivity.class);
                intentTerrier.putExtra("breedName", "terrier");
                startActivity(intentTerrier);
                break;
            case R.id.spaniel_layout:

                Intent intentSpaniel =new Intent(BreedsActivity.this, DogsActivity.class);
                intentSpaniel.putExtra("breedName", "spaniel");
                startActivity(intentSpaniel);
                break;
            case R.id.retriever_layout:

                Intent intentRetriever =new Intent(BreedsActivity.this, DogsActivity.class);
                intentRetriever.putExtra("breedName", "retriever");
                startActivity(intentRetriever);
                break;
            case R.id.poodle_layout:

                Intent intentPoodle =new Intent(BreedsActivity.this, DogsActivity.class);
                intentPoodle.putExtra("breedName", "poodle");
                startActivity(intentPoodle);
                break;
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
                Intent intent = new Intent(BreedsActivity.this, LoginActivity.class);
                intent.putExtra("logout", true);
                startActivity(intent);

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
