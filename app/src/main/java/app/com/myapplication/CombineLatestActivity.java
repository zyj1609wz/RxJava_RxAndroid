package app.com.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func2;

public class CombineLatestActivity extends AppCompatActivity {

    Observable<String> observable1 ;
    int nn = 0 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_combine_latest);

        observable1 = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(final Subscriber<? super String> subscriber){
                findViewById( R.id.bt).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        subscriber.onNext( "0s1" ) ;
                    }
                });
            }
        }) ;

        Observable<String> observable2 = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(final Subscriber<? super String> subscriber) {
                findViewById( R.id.bt2).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        nn = nn + 1 ;
                        subscriber.onNext( "55" + nn ) ;
                    }
                });
            }
        }) ;

        Observable.combineLatest(observable1, observable2, new Func2<String, String, String>() {
            @Override
            public String call(String s, String s2) {
                return s + s2 ;
            }
        }).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                Log.v(  "com-- " ,  s ) ;
            }
        }) ;

    }


}
