package app.com.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func2;

public class ScanActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);

        Observable observable = Observable.just( 1 , 2 , 3 , 4 , 5  ) ;
        observable.scan(new Func2<Integer,Integer,Integer>() {
            @Override
            public Integer call(Integer o, Integer o2) {
                return o + o2 ;
            }
        })
          .subscribe(new Action1() {
              @Override
              public void call(Object o) {
                  System.out.println( "scan-- " +  o );
              }
          })   ;

    }
}
