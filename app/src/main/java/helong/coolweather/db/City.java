package helong.coolweather.db;
import org.litepal.crud.DataSupport;
/**
 * Created by helong02 on 2017/7/24.
 */

public class City extends   DataSupport{
    private int id;
    private String cityName;
    private  int cityCode;
    private int province;
    public int getId(){
        return id;
    }
    public  void setId(int id_1){
        this.id=id_1;
    }
    public  int getCityCode(){
        return  cityCode;
    }
    public  void setCityCode(int cityCode_1){
        this.cityCode=cityCode_1;
    }
    public String getCityName(){
        return cityName;
    }
    public  void setCityName(String cityName_1){
        this.cityName=cityName_1;
    }
    public int getProvince(){
        return province;
    }
    public void setProvince(int p_id){
        this.province=p_id;
    }

}
