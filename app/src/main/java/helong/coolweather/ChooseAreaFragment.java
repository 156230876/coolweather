package helong.coolweather;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import helong.coolweather.db.City;
import helong.coolweather.db.County;
import helong.coolweather.db.Province;

/**
 * Created by helong02 on 2017/7/26.
 */

public class ChooseAreaFragment extends Fragment {

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
        titleText=view.findViewById(R.id.title_text);
        backButton=view.findViewById(R.id.back_button);
        listView=view.findViewById(R.id.list_view);
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

    }
    private void queryCities(){

    }
    private void queryCounties(){

    }
    private void queryFromServer(String  address,final String type){

    }
    private void showProgressDialog(){

    }
    private void closeProgressDialog(){

    }
}
