package lib.com.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.Toast;

import com.jakewharton.rxbinding.view.RxView;

import java.util.concurrent.TimeUnit;

import rx.functions.Action1;

public class MainActivity extends AppCompatActivity {

    private Button button ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById( R.id.bt ) ;

        RxView.clicks( button )
                .throttleFirst( 2 , TimeUnit.SECONDS )   //两秒钟之内只取一个点击事件，防抖操作
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        Toast.makeText(MainActivity.this, "click!!", Toast.LENGTH_SHORT).show();
                    }
                }) ;

        button = (Button) findViewById( R.id.bt ) ;

        //监听长按时间
        RxView.longClicks( button)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        Toast.makeText(MainActivity.this, "long click  ！！", Toast.LENGTH_SHORT).show();
                    }
                }) ;

        //跳转到listActivity
        RxView.clicks( findViewById( R.id.bt2 ))
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        startActivity( new Intent( MainActivity.this , ListActivity.class ));
                    }
                }) ;

        //跳转到LoginActivity
        RxView.clicks( findViewById( R.id.bt3 ))
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        startActivity( new Intent( MainActivity.this , LoginActivity.class ));
                    }
                }) ;

        //跳转到DebounceActivity
        RxView.clicks( findViewById( R.id.debounce ))
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        startActivity( new Intent( MainActivity.this , DebounceActivity.class ));
                    }
                }) ;

        //跳转到TextViewActivity
        RxView.clicks( findViewById( R.id.textView ))
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        startActivity( new Intent( MainActivity.this , TextViewActivity.class ));
                    }
                }) ;
    }
}
