package com.nyc.ac_44_comprehensive_android_practical_assessment;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class PhotoActivity extends AppCompatActivity {

    private String breedName;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
        imageView = findViewById(R.id.dog_photo);
        Intent intent = getIntent();
        if (intent != null) {
            breedName = intent.getStringExtra("breedName");
        } else {
            breedName = "";
        }
        if(!breedName.equals("")){
            Picasso.with(PhotoActivity.this).load(breedName)
                    .fit()
                    .centerInside()
                    .into(imageView);

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
                Intent intent = new Intent(PhotoActivity.this, LoginActivity.class);
                intent.putExtra("logout", true);
                startActivity(intent);

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
