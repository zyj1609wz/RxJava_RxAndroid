package app.com.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func0;

public class DeferActivity extends AppCompatActivity {

    String i = "10" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_defer);

        i = "11 " ;

        Observable<String> defer = Observable.defer(new Func0<Observable<String>>() {
            @Override
            public Observable<String> call() {
                return Observable.just( i ) ;
            }
        }) ;

        Observable test = Observable.just(  i ) ;

        i = "12" ;

        defer.subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                Log.v( "rx_defer  " , "" + s ) ;
            }
        }) ;

        test.subscribe(new Action1() {
            @Override
            public void call(Object o) {
                Log.v( "rx_just " , "" + o ) ;
            }
        }) ;
    }
}
