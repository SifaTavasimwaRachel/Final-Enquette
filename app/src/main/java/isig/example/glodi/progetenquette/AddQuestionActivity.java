package isig.example.glodi.progetenquette;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class AddQuestionActivity extends AppCompatActivity {

    DbHelper dbHelper;
    private Button btnsavequestion;
    private EditText designationquestion;
    private Spinner rubrique;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_question);

        btnsavequestion=(Button) findViewById(R.id.button_save_question);

        designationquestion=(EditText) findViewById(R.id.txtdesignationquestion);
        rubrique=(Spinner) findViewById(R.id.txtrefrubrique);
        loadSpinner(rubrique);


        btnsavequestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveToLocalStorage(designationquestion.getText().toString(),rubrique.getSelectedItem().toString());
                Toast.makeText(AddQuestionActivity.this,"Succefully",Toast.LENGTH_LONG).show();
            }
        });


    }


    private void saveToLocalStorage(String designation, String rubrique)
    {
        try{
            dbHelper=new DbHelper(this);
            SQLiteDatabase database=dbHelper.getWritableDatabase();
            dbHelper.saveTolocalDatabase_question(designation,rubrique,database);
            dbHelper.close();

        }catch (Exception e){
            e.printStackTrace();

        }
    }



    void loadSpinner(Spinner sp){
        // (1) get a reference to the spinner

// (2) create a simple static list of strings
        List<String> spinnerArray = new ArrayList<>();
        spinnerArray.add("INDENTITE");
        spinnerArray.add("PROPREMENDITE");
        spinnerArray.add("CURIOSITE");

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
