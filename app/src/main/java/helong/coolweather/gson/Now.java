package helong.coolweather.gson;

import com.google.gson.annotations.SerializedName;

/**
 * Created by helong02 on 2017/8/3.
 */

public class Now {
    @SerializedName("tmp")
    public  String temperature;
    @SerializedName("cond")
    public More more;
    public class  More{
        @SerializedName("txt")
        public  String  info;
    }
}
