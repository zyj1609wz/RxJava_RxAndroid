package lib.com.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import lib.com.myapplication.netI.UserI;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    String Tag = "Retrofit" ;

    private TextView textView ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //  HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        //  httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        //  OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build();

        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();

        textView = (TextView) findViewById( R.id.tv );

        //http://www.app.zhaoaiqiao.com/web2/
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl( "http://www.app.zhaoaiqiao.com/web2" )
                .addConverterFactory( GsonConverterFactory.create())
                .build();
        UserI userBiz = retrofit.create(UserI.class);
        final Call<String> call = userBiz.getUsers();

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(final Call<String> call, final Response<String> response) {
                Log.e(Tag, "normalGet:" + response.body().toString() + "" );
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this, "success:" + response.body() , Toast.LENGTH_SHORT).show();
                        Log.e(Tag, "normalGet:" + response.body().toString() + "" );
                        textView.setText( response.body().toString() );
                    }
                });
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(MainActivity.this, "failc" + t.toString() , Toast.LENGTH_SHORT).show();
                textView.setText( t.toString() );
            }
        });

    }
}
