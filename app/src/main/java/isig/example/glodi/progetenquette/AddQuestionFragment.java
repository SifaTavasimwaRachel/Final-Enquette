package isig.example.glodi.progetenquette;


import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddQuestionFragment extends Fragment {


    public AddQuestionFragment() {
        // Required empty public constructor
    }

//    android:background="@color/editTextBG"
//    android:hint="Rubrique"
    DbHelper dbHelper;
    private Button btnsavequestion;
    private EditText designationquestion;
    private Spinner rubrique;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_add_question, container, false);

        btnsavequestion=view.findViewById(R.id.button_save_question);

        designationquestion=view.findViewById(R.id.txtdesignationquestion);
        rubrique=view.findViewById(R.id.txtrefrubrique);

        loadSpinner(rubrique);


        btnsavequestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveToLocalStorage(designationquestion.getText().toString(),rubrique.getSelectedItem().toString());
            }
        });


        // Inflate the layout for this fragment
        return view;
    }

    private void saveToLocalStorage(String designation, String rubrique)
    {
        try{
            dbHelper=new DbHelper(getActivity());
            SQLiteDatabase database=dbHelper.getWritableDatabase();
            dbHelper.saveTolocalDatabase_question(designation,rubrique,database);
            dbHelper.close();
            //Toast.makeText(AddQuestionFragment.this,"Succefully",Toast.LENGTH_LONG).show();

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
                getActivity(),
                android.R.layout.simple_spinner_item,
                spinnerArray
        );

//adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

// (4) set the adapter on the spinner
        sp.setAdapter(adapter);

    }


}
