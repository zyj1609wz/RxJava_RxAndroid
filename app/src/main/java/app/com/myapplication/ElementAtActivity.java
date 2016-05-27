package app.com.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import rx.Observable;
import rx.functions.Action1;

public class ElementAtActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_element_at);

        //elementAt() 发送数据序列中第n个数据 ，序列号从0开始
        //如果该序号大于数据序列中的最大序列号，则会抛出异常，程序崩溃
        //所以在用elementAt操作符的时候，要注意判断发送的数据序列号是否越界

        Observable observable7 = Observable.just( 1 , 2 , 3 , 4 , 5 , 6 , 7 ) ;
        observable7.elementAt( 3 )
                .subscribe(new Action1() {
                    @Override
                    public void call(Object o) {
                        System.out.println( "elementAt-- " +  o );
                    }
                })   ;

        //elementAtOrDefault( int n , Object default ) 发送数据序列中第n个数据 ，序列号从0开始。
        //如果序列中没有该序列号，则发送默认值
        Observable observable9 = Observable.just( 1 , 2 , 3 , 4 , 5 ) ;
        observable9.elementAtOrDefault(  8 , 666  )
                .subscribe(new Action1() {
                    @Override
                    public void call(Object o) {
                        System.out.println( "elementAtOrDefault-- " +  o );
                    }
                })   ;


    }
}
