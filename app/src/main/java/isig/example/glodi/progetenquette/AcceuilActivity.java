package isig.example.glodi.progetenquette;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;

public class AcceuilActivity extends AppCompatActivity {

    private android.support.v7.widget.CardView  btnclient;
    private android.support.v7.widget.CardView  btnadmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acceuil);

        btnclient=(CardView) findViewById(R.id.fromclient);
        btnadmin=(CardView) findViewById(R.id.fromadmin);


        btnclient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(AcceuilActivity.this,MainActivity.class);
                startActivity(intent);
                Animatoo.animateSwipeRight(AcceuilActivity.this);
            }
        });

        btnadmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(AcceuilActivity.this,LoginActivity.class);
                startActivity(intent);
                Animatoo.animateSwipeRight(AcceuilActivity.this);
            }
        });
    }
}
