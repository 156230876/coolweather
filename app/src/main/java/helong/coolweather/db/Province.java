package helong.coolweather.db;

import org.litepal.crud.DataSupport;
/**
 * Created by helong02 on 2017/7/24.
 */

public class Province extends   DataSupport{
    private int id;
    private String provinceName;
    private  int provinceCode;
    public int getId(){
        return id;
    }
    public  void setId(int id_1){
        this.id=id_1;
    }
    public  int getProvinceCode(){
        return  provinceCode;
    }
    public  void setProvinceCode(int provinceCode_1){
        this.provinceCode=provinceCode_1;
    }
    public String getProvinceName(){
        return provinceName;
    }
    public  void setProvinceName(String provinceName_1){
        this.provinceName=provinceName_1;
    }

}
