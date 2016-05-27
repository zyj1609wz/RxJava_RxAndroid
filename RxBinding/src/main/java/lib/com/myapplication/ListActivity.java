package lib.com.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.jakewharton.rxbinding.widget.RxAdapterView;

import java.util.ArrayList;
import java.util.List;

import rx.functions.Action1;

public class ListActivity extends AppCompatActivity {

    ListView listView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        listView = (ListView) findViewById( R.id.listview );

        List<String> list = new ArrayList<>() ;
        for ( int i = 0 ; i < 40 ; i++ ){
            list.add( "sss" + i ) ;
        }

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1 );
        adapter.addAll( list );

        listView.setAdapter( adapter );

        //item click event
        RxAdapterView.itemClicks( listView )
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        Toast.makeText(ListActivity.this, "item click " + integer , Toast.LENGTH_SHORT).show();
                    }
                }) ;

        //item long click
        RxAdapterView.itemLongClicks( listView)
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        Toast.makeText(ListActivity.this, "item long click " + integer , Toast.LENGTH_SHORT).show();
                    }
                }) ;
    }
}
