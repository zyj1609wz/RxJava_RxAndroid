package com.app;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText editText ;
    private SharedPreferences.Editor editor ;
    private SharedPreferences preferences ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText) findViewById( R.id.et) ;
        preferences = PreferenceManager.getDefaultSharedPreferences( MainActivity.this ) ;
        editor = preferences.edit() ;

        preferences.registerOnSharedPreferenceChangeListener(new SharedPreferences.OnSharedPreferenceChangeListener() {
            @Override
            public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
                Toast.makeText( MainActivity.this , "listen--key: " + key + " value: " + sharedPreferences.getString( key , "") , Toast.LENGTH_SHORT ).show();
            }
        });

        //点击
        findViewById( R.id.tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String value = editText.getText().toString() ;
                editor.putString( "key" , value  ) ;
                editor.commit() ;
            }
        });
    }
}
