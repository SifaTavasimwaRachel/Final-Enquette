package isig.example.glodi.progetenquette;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddUserActivity extends AppCompatActivity {

    DbHelper dbHelper;
    private Button btnsaveuser;
    private EditText username;
    private EditText password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);


        btnsaveuser=(Button) findViewById(R.id.button_save_user);
        username=(EditText) findViewById(R.id.txtusername);
        password=(EditText) findViewById(R.id.txtpassaword);


        btnsaveuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveToLocalStorage(username.getText().toString(), password.getText().toString());
                Toast.makeText(AddUserActivity.this,"Succefully",Toast.LENGTH_LONG).show();
            }
        });

    }

    private void saveToLocalStorage(String username, String password)
    {
        try{
            dbHelper=new DbHelper(this);
            SQLiteDatabase database=dbHelper.getWritableDatabase();
            dbHelper.saveTolocalDatabase_user(username,password,database);
            dbHelper.close();

        }catch (Exception e){
            e.printStackTrace();

        }
    }



}
