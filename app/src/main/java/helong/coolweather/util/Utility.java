package helong.coolweather.util;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import helong.coolweather.db.City;
import helong.coolweather.db.County;
import helong.coolweather.db.Province;

/**
 * Created by helong02 on 2017/7/25.
 */

public class Utility {
    String TAG="parseResponseClass";
    /**
     * 解析省级数据并写入数据库
     */
    public static boolean handleProvinceResponse(String response){
        if(!TextUtils.isEmpty(response)){
            try{
                JSONArray allProvince=new JSONArray(response);
                for(int i=0;i<allProvince.length();i++){
                    JSONObject objectProvince=allProvince.getJSONObject(i);
                    Province province=new Province();
                    province.setProvinceCode(objectProvince.getInt("id"));
                    province.setProvinceName(objectProvince.getString("name"));
                    province.save();
                }
                return true;
            }catch (JSONException e){
                e.printStackTrace();
                Log.d(TAG, "handleProvinceResponse:prase fail ");
            }
        }
        return false;
    }

    /**
     * 解析城市数据
     * @param response 返回的json数据
     * @param provinceId 所在省的id
     * @return
     */
    public static boolean handleCityResponse(String response,int provinceId){
        if(!TextUtils.isEmpty(response)){
            try {
                JSONArray allCities=new JSONArray(response);
                for(int i=0;i<allCities.length();i++){
                    JSONObject objectCity=allCities.getJSONObject(i);
                    City city=new City();
                    city.setCityCode(objectCity.getInt("id"));
                    city.setCityName(objectCity.getString("name"));
                    city.setProvince(provinceId);
                    city.save();
                }
                return true;
            }catch(JSONException e){
                e.printStackTrace();
                Log.d(TAG, "handleCityResponse: parse fail");
            }
        }
        return false;
    }
    /**
    *解析县级天气数据
     */
    public static boolean handleCountyResponse(String response,int cityId){
        if(!TextUtils.isEmpty(response)){
            try {
                JSONArray allCounties=new JSONArray(response);
                for(int i=0;i<allCounties.length();i++){
                    JSONObject objectCounty=allCounties.getJSONObject(i);
                    County county=new County();
                    //county.setCountyCode(objectCounty.getInt("id"));
                    county.setCountyName(objectCounty.getString("name"));
                    county.setWeatherId(objectCounty.getInt("weather_id"));
                    county.setCityId(cityId);
                    county.save();
                }
                return true;
            }catch (JSONException e){
                e.printStackTrace();
                Log.d(TAG, "handleCountyResponse: parse fail");
            }
        }
        return false;
    }
}
