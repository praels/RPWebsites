package sg.edu.rp.c346.rpwebsites;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    Spinner spnCat;
    Spinner spnSub;
    Button btnGo;
    ArrayList<String> alCat;
    ArrayAdapter<String> aaCat;
    ArrayList<String> alSub;
    ArrayAdapter<String>aaSub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spnCat = findViewById(R.id.spinnerCategory);
        spnSub = findViewById(R.id.spinnerSub);
        btnGo = findViewById(R.id.buttonGo);
        alCat = new ArrayList<>();
        alSub = new ArrayList<>();

        String[] strCat = getResources().getStringArray(R.array.cat);
        alCat.addAll(Arrays.asList(strCat));
        aaCat = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,alCat);
        spnCat.setAdapter(aaCat);

        String[] strSub = getResources().getStringArray(R.array.sub);
        alSub.addAll(Arrays.asList(strSub));
        aaSub = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, alSub);
        spnSub.setAdapter(aaSub);

        final String urlHome = "https://www.rp.edu.sg/";
        final String urlStu = "https://www.rp.edu.sg/student-life";
        final String urlDMSD = "https://www.rp.edu.sg/soi/full-time-diplomas/details/r47";
        final String urlDIT = "https://www.rp.edu.sg/soi/full-time-diplomas/details/r12";

        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(spnCat.getSelectedItemPosition() == 0 && spnSub.getSelectedItemPosition() == 0){
                    Intent intentHome = new Intent(getBaseContext(), Website.class);
                    intentHome.putExtra("Url",urlHome);
                    startActivity(intentHome);
                }else if(spnCat.getSelectedItemPosition()==0&&spnSub.getSelectedItemPosition()==1){
                    Intent intentStu = new Intent(getBaseContext(), Website.class);
                    intentStu.putExtra("Url",urlStu);
                    startActivity(intentStu);
                }else if(spnCat.getSelectedItemPosition() == 1&&spnSub.getSelectedItemPosition()==2){
                    Intent intentDMSD = new Intent(getBaseContext(), Website.class);
                    intentDMSD.putExtra("Url",urlDMSD);
                    startActivity(intentDMSD);
                }else{
                    Intent intentDIT = new Intent(getBaseContext(), Website.class);
                    intentDIT.putExtra("Url",urlDIT);
                    startActivity(intentDIT);
                }
            }
        });

    }
    @Override
    protected void onPause() {
        super.onPause();

        int intCat = spnCat.getSelectedItemPosition();
        int intSub = spnSub.getSelectedItemPosition();

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor prefEdit = prefs.edit();
        prefEdit.putInt("Cat",intCat);
        prefEdit.putInt("Sub",intSub);
        prefEdit.commit();
    }
    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        int Cat = prefs.getInt("Cat",0);
        int Sub = prefs.getInt("Sub",0);
        spnCat.setSelection(Cat);
        spnSub.setSelection(Sub);

    }
}