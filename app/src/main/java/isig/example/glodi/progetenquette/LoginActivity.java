package isig.example.glodi.progetenquette;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;

public class LoginActivity extends AppCompatActivity {

    private Button btnchauffeur;

    private TextView btnpropsave;
    private Button btnprop;

    private EditText edtusername_proprietaire;
    private EditText edtpassword_proprietaire;
    DbHelper db=new DbHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnchauffeur=(Button) findViewById(R.id.btnloginchauffeur);

        edtusername_proprietaire=(EditText) findViewById(R.id.txtusernamechauffeur);
        edtpassword_proprietaire=(EditText) findViewById(R.id.txtpasswordchauffeur);

        btnchauffeur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(db.testeloginuser(edtusername_proprietaire.getText().toString(),edtpassword_proprietaire.getText().toString())!=1){
                    Intent intent=new Intent(LoginActivity.this,AdminActivity.class);
                    startActivity(intent);
                    Animatoo.animateSwipeRight(LoginActivity.this);

                }else{
                    Toast.makeText(getApplicationContext(),"Echec de connection !!!",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
