package app.com.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import rx.Observable;
import rx.Observer;
import rx.functions.Action1;

public class DoOnNextActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Observable observable = Observable.just( "1" , "2" , "3" , "4" ) ;
        observable.doOnNext(new Action1() {
            @Override
            public void call(Object o) {
                System.out.println( "doOnNext--缓存数据" + o  );
            }
        })
                .subscribe(new Observer() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Object o) {
                        System.out.println( "onNext--" + o  );
                    }
                }) ;
    }
}
