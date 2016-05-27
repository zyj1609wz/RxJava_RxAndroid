package app.com.myapplication;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.concurrent.TimeUnit;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

public class TimerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        //5秒后输出 hello world , 然后显示一张图片
        Observable.timer( 5 , TimeUnit.SECONDS )
                .observeOn(AndroidSchedulers.mainThread() )
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        System.out.println( "timer--hello world " +  aLong );
                        findViewById( R.id.image).setVisibility(View.VISIBLE );
                    }
                }) ;
    }
}
