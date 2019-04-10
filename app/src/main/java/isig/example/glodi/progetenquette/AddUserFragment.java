package isig.example.glodi.progetenquette;


import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddUserFragment extends Fragment {


    public AddUserFragment() {
        // Required empty public constructor
    }

    DbHelper dbHelper;
    private Button btnsaveuser;
    private EditText username;
    private EditText password;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_add_user, container, false);
        btnsaveuser=view.findViewById(R.id.button_save_user);
        username=view.findViewById(R.id.txtusername);
        password=view.findViewById(R.id.txtpassaword);


        btnsaveuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveToLocalStorage(username.getText().toString(), password.getText().toString());
            }
        });


        return  view;
    }

    private void saveToLocalStorage(String username, String password)
    {
        try{
            dbHelper=new DbHelper(getActivity());
            SQLiteDatabase database=dbHelper.getWritableDatabase();
            dbHelper.saveTolocalDatabase_user(username,password,database);
            dbHelper.close();

        }catch (Exception e){
            e.printStackTrace();

        }
    }

}
