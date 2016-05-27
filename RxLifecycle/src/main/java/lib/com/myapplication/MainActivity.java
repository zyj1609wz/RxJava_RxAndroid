package lib.com.myapplication;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import java.util.concurrent.TimeUnit;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class MainActivity extends RxAppCompatActivity {
    TextView textView ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textView);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent( MainActivity.this , StopActivity.class));
            }
        });

        //循环发送数字
        Observable.interval(0, 1, TimeUnit.SECONDS)
                .subscribeOn( Schedulers.io())
                .compose(this.<Long>bindToLifecycle())   //这个订阅关系跟Activity绑定，Observable 和activity生命周期同步
                .observeOn( AndroidSchedulers.mainThread())
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        System.out.println("lifecycle--" + aLong);
                        textView.setText( "" + aLong );
                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("life-- Destory");
    }
}
