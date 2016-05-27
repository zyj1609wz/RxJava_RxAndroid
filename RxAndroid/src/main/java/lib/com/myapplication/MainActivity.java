package lib.com.myapplication;

import android.os.Bundle;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Process;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.exceptions.OnErrorThrowable;
import rx.functions.Func0;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "RxAndroidSamples";
    private Looper backgroundLooper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BackgroundThread backgroundThread = new BackgroundThread();
        backgroundThread.start();
        backgroundLooper = backgroundThread.getLooper();

        findViewById(R.id.button_run_scheduler).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                onRunSchedulerExampleButtonClicked();
            }
        });

    }

    void onRunSchedulerExampleButtonClicked() {
        sampleObservable()
                // Run on a background thread
                .subscribeOn( AndroidSchedulers.from(backgroundLooper))
                // Be notified on the main thread
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<String>() {
                    @Override public void onCompleted() {
                        Log.d(TAG, "onCompleted()");
                    }

                    @Override public void onError(Throwable e) {
                        Log.e(TAG, "onError()", e);
                    }

                    @Override public void onNext(String string) {
                        Log.d(TAG, "onNext(" + string + ")");
                    }
                });
    }

    static rx.Observable<String> sampleObservable() {
        return rx.Observable.defer(new Func0<rx.Observable<String>>() {
            @Override public rx.Observable<String> call() {
                try {
                    // Do some long running operation
                    Thread.sleep(TimeUnit.SECONDS.toMillis(5));
                } catch (InterruptedException e) {
                    throw OnErrorThrowable.from(e);
                }
                return Observable.just("one", "two", "three", "four", "five");
            }
        });
    }

    static class BackgroundThread extends HandlerThread {
        BackgroundThread() {
            super("SchedulerSample-BackgroundThread", Process.THREAD_PRIORITY_BACKGROUND);
        }

    }
}
