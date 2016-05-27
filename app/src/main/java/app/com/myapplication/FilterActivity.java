package app.com.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

public class FilterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);


        Observable observable = Observable.just( 1 , 2 , 3 , 4 , 5 , 6 , 7 ) ;
        observable.filter(new Func1<Integer , Boolean>() {
            @Override
            public Boolean call(Integer o) {
                //数据大于4的时候才会被发送
                return o > 4 ;
            }
        })
                .subscribe(new Action1() {
                    @Override
                    public void call(Object o) {
                        System.out.println( "filter-- " +  o );
                    }
                })   ;

    }
}
