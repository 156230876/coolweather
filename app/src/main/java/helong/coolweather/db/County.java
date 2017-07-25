package helong.coolweather.db;

import org.litepal.crud.DataSupport;
/**
 * Created by helong02 on 2017/7/24.
 */

public class County extends   DataSupport{
    private int id;
    private String countyName;
    private  int countyCode;
    private int cityId;
    private int weatherId;
    public int getId(){
        return id;
    }
    public  void setId(int id_1){
        this.id=id_1;
    }
    public  int getCountyCode(){
        return  countyCode;
    }
    public  void setCountyCode(int countyCode_1){
        this.countyCode=countyCode_1;
    }
    public String getCountyName(){
        return countyName;
    }
    public  void setCountyName(String countyName_1){
        this.countyName=countyName_1;
    }
    public int getCityId(){
        return cityId;
    }
    public void setCityId(int cityId_1){
        this.cityId=cityId_1;
    }
    public int getWeatherId(){
        return weatherId;
    }
    public void setWeatherId(int WeatherId_1){
        this.weatherId=WeatherId_1;
    }

}
