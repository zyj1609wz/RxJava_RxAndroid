package lib.com.myapplication.netI;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by ${zyj} on 2016/6/1.
 */
public interface UserI {
        @GET("/PeopleServlet")
        Call<String> getUsers();
}
