package au.edu.monash.infotech.myroo.model;

import au.edu.monash.infotech.myroo.view.WeatherInfo;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherService {
    @GET("data/2.5/weather")
    Call<WeatherInfo> getWeather(@Query("id") String id, @Query("appid") String key);
}
