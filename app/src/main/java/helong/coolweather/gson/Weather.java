package helong.coolweather.gson;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by helong02 on 2017/8/3.
 */

public class Weather {
    public String status;
    public Basic basic;
    public AQI aqi;
    @SerializedName("daily_forecast")
    public List<Forecast> forecastList;
    public Now now;
    public Suggestion suggestion;

}
