package isig.example.glodi.progetenquette;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    DbHelper dbHelper;
    private Button btnsaveenquete;
    private EditText nom;
    private EditText adresse;
    private EditText conatct;
    private EditText niveauetude;
    private Spinner reponse1;
    private Spinner reponse2;
    private Spinner reponse3;
    private Spinner reponse4;
    private Spinner reponse5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnsaveenquete=(Button) findViewById(R.id.btn_bus_save);

        nom=(EditText) findViewById(R.id.txtnom);
        adresse=(EditText) findViewById(R.id.txtadresse);
        conatct=(EditText) findViewById(R.id.txtcontact);
        niveauetude=(EditText) findViewById(R.id.spinnerNiveauEtude);

        reponse1=(Spinner) findViewById(R.id.spinnerReponse1);
        reponse2=(Spinner) findViewById(R.id.spinnerReponse2);
        reponse3=(Spinner) findViewById(R.id.spinnerReponse3);
        reponse4=(Spinner) findViewById(R.id.spinnerReponse4);
        reponse5=(Spinner) findViewById(R.id.spinnerReponse5);

        loadSpinner(reponse1);
        loadSpinner(reponse2);
        loadSpinner(reponse3);
        loadSpinner(reponse4);
        loadSpinner(reponse5);


        btnsaveenquete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveToLocalStorage(nom.getText().toString(),
                                   adresse.getText().toString(),
                                   conatct.getText().toString(),
                                   niveauetude.getText().toString(),
                                   reponse1.getSelectedItem().toString(),
                                   reponse2.getSelectedItem().toString(),
                                   reponse3.getSelectedItem().toString(),
                                   reponse4.getSelectedItem().toString(),
                                   reponse5.getSelectedItem().toString());
                Toast.makeText(MainActivity.this,"Succefully",Toast.LENGTH_LONG).show();
            }
        });


    }

    private void saveToLocalStorage(String name, String adresse, String contact, String niveauEtude,
                                    String reponse1,String reponse2,
                                    String reponse3,String reponse4,String reponse5)
    {
        try{
            dbHelper=new DbHelper(this);
            SQLiteDatabase database=dbHelper.getWritableDatabase();
            dbHelper.saveTolocalDatabase_client(name, adresse, contact, niveauEtude,
                    reponse1,reponse2,reponse3,
                    reponse4,reponse5,database);
            dbHelper.close();

        }catch (Exception e){
            e.printStackTrace();

        }
    }

    void loadSpinner(Spinner sp){
        // (1) get a reference to the spinner

// (2) create a simple static list of strings
        List<String> spinnerArray = new ArrayList<>();
        spinnerArray.add("Oui");
        spinnerArray.add("Non");

// (3) create an adapter from the list
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_spinner_item,
                spinnerArray
        );

//adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

// (4) set the adapter on the spinner
        sp.setAdapter(adapter);

    }


}
