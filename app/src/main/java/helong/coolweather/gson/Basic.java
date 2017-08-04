package helong.coolweather.gson;

import com.google.gson.annotations.SerializedName;

/**
 * Created by helong02 on 2017/8/3.
 */

public class Basic {
    @SerializedName("city")
    public String cityName;
    @SerializedName("id")
    public String weatherId;
    public  Update update;
    public class  Update{
        @SerializedName("loc")
        public String updateTime;
    }

}
