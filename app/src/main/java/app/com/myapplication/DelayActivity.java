package app.com.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.functions.Action1;

public class DelayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delay);

        Observable<String> observable = Observable.just( "1" , "2" , "3" , "4" , "5" , "6" , "7" , "8" ) ;

        //延迟数据发射的时间，仅仅延时一次，也就是发射第一个数据前延时。发射后面的数据不延时
        observable.delay( 3 , TimeUnit.SECONDS )  //延迟3秒钟
                .subscribe(new Action1() {
                    @Override
                    public void call(Object o) {
                        System.out.println("delay-- " + o);
                    }
                }) ;

    }
}
