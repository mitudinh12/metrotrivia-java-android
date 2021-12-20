package fi.tudinhmetropolia.metrotrivia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void profileButton(View view){
        Intent p = new Intent(this, fi.tudinhmetropolia.metrotrivia.ProfileScreen.class);
        startActivity(p);
    }

    public void gameButton(View view){
        Intent g = new Intent(this, fi.tudinhmetropolia.metrotrivia.ChoseModeActivity.class);
        startActivity(g);
    }

}
