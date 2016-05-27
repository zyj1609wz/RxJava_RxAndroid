package lib.com.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.jakewharton.rxbinding.widget.RxTextView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class DebounceActivity extends AppCompatActivity {

    private EditText editText ;
    private ListView listView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_debounce);

        editText = (EditText) findViewById( R.id.editText );
        listView = (ListView) findViewById( R.id.listview );

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1 );
        listView.setAdapter( adapter );

        RxTextView.textChanges( editText )
                .debounce( 600 , TimeUnit.MILLISECONDS )
                .map(new Func1<CharSequence, String>() {
                    @Override
                    public String call(CharSequence charSequence) {
                        //get the keyword
                        String key = charSequence.toString() ;
                        return key ;
                    }
                })
                .observeOn( Schedulers.io() )
                .map(new Func1<String, List<String>>() {
                    @Override
                    public List<String> call(String keyWord ) {
                        //get list
                        List<String> dataList = new ArrayList<String>() ;
                        if ( ! TextUtils.isEmpty( keyWord )){
                            for ( String s : getData()  ) {
                                if (s != null) {
                                    if (s.contains(keyWord)) {
                                        dataList.add(s);
                                    }
                                }
                            }
                        }
                        return dataList ;
                    }
                })
                .observeOn( AndroidSchedulers.mainThread() )
                .subscribe(new Action1<List<String>>() {
                    @Override
                    public void call(List<String> strings) {
                        adapter.clear();
                        adapter.addAll( strings );
                        adapter.notifyDataSetChanged();
                    }
                }) ;
    }

    public  List<String> getData(){
        List<String> list = new ArrayList<>() ;
        list.add( "a" ) ;
        list.add( "aab" ) ;
        list.add( "aaabc" ) ;
        list.add( "aaaaabcd" ) ;
        list.add( "aaaaaabcde" ) ;
        list.add( "aaaaaaac" ) ;
        list.add( "aaaaaaaacd" ) ;
        list.add( "aaaaaaaaacdef" ) ;
        list.add( "aaaaaaaaaabc" ) ;
        list.add( "bcd" ) ;
        list.add( "bcdef" ) ;
        list.add( "cd" ) ;
        list.add( "cde" ) ;
        list.add( "cdefg" ) ;
        list.add( "dddkkk5662" ) ;
        list.add( "bbs" ) ;
        list.add( "7b4s" ) ;
        list.add( "jjjsssd995556214613" ) ;
        list.add( "58350675" ) ;

        return  list ;
    }
}
