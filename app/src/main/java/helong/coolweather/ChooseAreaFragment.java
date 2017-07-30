package helong.coolweather;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.litepal.crud.DataSupport;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import helong.coolweather.db.City;
import helong.coolweather.db.County;
import helong.coolweather.db.Province;
import helong.coolweather.util.HttpUtil;
import helong.coolweather.util.Utility;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by helong02 on 2017/7/26.
 */

public class ChooseAreaFragment extends Fragment {

    private String adress="http://guolin.tech/api/china";
    public static final int LEVEL_PROVINCE=0;
    public static final int LEVEL_CTTY=1;
    public static final int LEVEL_COUNTY=2;
    private ProgressDialog  progressDialog;
    private TextView titleText;
    private Button backButton;
    private ListView listView;
    private ArrayAdapter<String> adapter;
    private List<String>dataList=new ArrayList<>();
    /*
    province list
     */
    private List<Province> provincesList;
    /*
    City list
     */
    private  List<City>cityList;
    /*
    county list
     */
    private  List<County>countyList;
    /*
    choose province
     */
    private Province selectedProvince;
    /*
    choose City
     */
    private City selectedCity;
    /*
    choose the level
     */
    private int currentLevel;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup contaniner, Bundle savedInstanceState){
          View view =inflater.inflate(R.layout.choose_area,contaniner,false);
        titleText=(TextView)view.findViewById(R.id.title_text);
        backButton=(Button) view.findViewById(R.id.back_button);
        listView=(ListView) view.findViewById(R.id.list_view);
        adapter=new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1,dataList);
        listView.setAdapter(adapter);
        return view;

    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent,View view,int position, long id ){
                if(currentLevel==LEVEL_PROVINCE){
                    selectedProvince=provincesList.get(position);
                    queryCities();
                }
                else if (currentLevel==LEVEL_CTTY){
                    selectedCity=cityList.get(position);
                    queryCounties();
                }
                else{

                }
            }
        });
        backButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if (currentLevel==LEVEL_COUNTY){
                    queryCities();
                }else if(currentLevel==LEVEL_CTTY){
                    queryProvinces();
                }else {

                }

            }
        });
        queryProvinces();

    }
    private void queryProvinces(){
        titleText.setText("中国");
        backButton.setVisibility(View.GONE);
        provincesList= DataSupport.findAll(Province.class);
        if(provincesList.size()>0){
            dataList.clear();
            for (Province province:provincesList){
                dataList.add(province.getProvinceName());
            }
            adapter.notifyDataSetChanged();
            listView.setSelection(0);
            currentLevel=LEVEL_PROVINCE;
        }else{
           queryFromServer(adress,"province");
        }

    }
    private void queryCities(){
        titleText.setText(selectedProvince.getProvinceName());
        backButton.setVisibility(View.VISIBLE);
        cityList=DataSupport.where("provinceid=?",String.valueOf(selectedProvince.getId())).find(City.class);
        if(cityList.size()>0){
            dataList.clear();
            for(City city:cityList){
                dataList.add(city.getCityName());
            }
            adapter.notifyDataSetChanged();
            listView.setSelection(0);
            currentLevel=LEVEL_CTTY;
        }else {
            int province=selectedProvince.getProvinceCode();

            queryFromServer(adress+"/"+province,"city");
        }


    }
    private void queryCounties(){
        titleText.setText(selectedCity.getCityName());
        backButton.setVisibility(View.VISIBLE);
        countyList=DataSupport.where("cityid=?",String.valueOf(selectedCity.getId())).find(County.class);
        if(countyList.size()>0){
            dataList.clear();
            for(County county:countyList){
                dataList.add(county.getCountyName());
            }
            adapter.notifyDataSetChanged();
            listView.setSelection(View.VISIBLE);
            currentLevel=LEVEL_COUNTY;
        }else {
            int provincecode=selectedProvince.getProvinceCode();
            int citycode=selectedCity.getCityCode();
            queryFromServer(adress+"/"+provincecode+"/"+citycode,"county");
        }

    }
    private void queryFromServer(String  address,final String type){
        showProgressDialog();
        HttpUtil.sendOkHttpRequest(address, new Callback() {


            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String respnoseText=response.body().string();
                boolean result=false;
                if ("province".equals(type)){
                    result= Utility.handleProvinceResponse(respnoseText);
                }else if("city".equals(type)){
                    result=Utility.handleCityResponse(respnoseText,selectedProvince.getId());
                }else if("county".equals(type)){
                    result=Utility.handleCountyResponse(respnoseText,selectedCity.getId());
                }else {

                }
                if(result){
                    getActivity().runOnUiThread(new Runnable(){
                        @Override
                        public void run(){
                            closeProgressDialog();
                            if("prvince".equals(type)){
                                queryProvinces();
                            }else if ("city".equals(type)){
                                queryCities();
                            }else if ("county".equals(type)){
                                queryCounties();
                            }else {

                            }

                        }

                    });
                }


            }
            @Override
            public void onFailure(Call call,IOException e){
                //通过 runOnUiThread（）方法回到主线程处理逻辑
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        closeProgressDialog();
                        Toast.makeText(getContext(),"加载失败",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });


    }
    private void showProgressDialog(){
        if(progressDialog==null){
            progressDialog=new ProgressDialog(getContext());
            progressDialog.setMessage("正在加载...");
            progressDialog.setCanceledOnTouchOutside(false);
        }
        progressDialog.show();

    }
    private void closeProgressDialog(){
        if (progressDialog!=null){
            progressDialog.dismiss();
        }

    }
}
