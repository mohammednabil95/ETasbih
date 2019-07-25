package com.nabil.myapplication.fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.nabil.myapplication.AppController;
import com.nabil.myapplication.R;

import org.json.JSONException;
import org.json.JSONObject;

public class Frag4 extends Fragment {
    private static final String TAG="tag";
    Context context;
    String tag_json_obj = "json_obj_req";
    String url;
    TextView fajrTv,dhuhrTv,asrTv,maghribTv,ishaTv,locationTv,dateTv,
            fajrTv2,dhuhrTv2,asrTv2,maghribTv2,ishaTv2,dateTv2,
            fajrTv3,dhuhrTv3,asrTv3,maghribTv3,ishaTv3,dateTv3,
            fajrTv4,dhuhrTv4,asrTv4,maghribTv4,ishaTv4,dateTv4,
            fajrTv5,dhuhrTv5,asrTv5,maghribTv5,ishaTv5,dateTv5,
            fajrTv6,dhuhrTv6,asrTv6,maghribTv6,ishaTv6,dateTv6,
            fajrTv7,dhuhrTv7,asrTv7,maghribTv7,ishaTv7,dateTv7,
            fajrTv8,dhuhrTv8,asrTv8,maghribTv8,ishaTv8,dateTv8,
            fajrTv9,dhuhrTv9,asrTv9,maghribTv9,ishaTv9,dateTv9,
            fajrTv10,dhuhrTv10,asrTv10,maghribTv10,ishaTv10,dateTv10,
            fajrTv11,dhuhrTv11,asrTv11,maghribTv11,ishaTv11,dateTv11,
            fajrTv12,dhuhrTv12,asrTv12,maghribTv12,ishaTv12,dateTv12,
            fajrTv13,dhuhrTv13,asrTv13,maghribTv13,ishaTv13,dateTv13,
            fajrTv14,dhuhrTv14,asrTv14,maghribTv14,ishaTv14,dateTv14,
            fajrTv15,dhuhrTv15,asrTv15,maghribTv15,ishaTv15,dateTv15,
            fajrTv16,dhuhrTv16,asrTv16,maghribTv16,ishaTv16,dateTv16,
            fajrTv17,dhuhrTv17,asrTv17,maghribTv17,ishaTv17,dateTv17,
            fajrTv18,dhuhrTv18,asrTv18,maghribTv18,ishaTv18,dateTv18,
            fajrTv19,dhuhrTv19,asrTv19,maghribTv19,ishaTv19,dateTv19,
            fajrTv20,dhuhrTv20,asrTv20,maghribTv20,ishaTv20,dateTv20,
            fajrTv21,dhuhrTv21,asrTv21,maghribTv21,ishaTv21,dateTv21,
            fajrTv22,dhuhrTv22,asrTv22,maghribTv22,ishaTv22,dateTv22,
            fajrTv23,dhuhrTv23,asrTv23,maghribTv23,ishaTv23,dateTv23,
            fajrTv24,dhuhrTv24,asrTv24,maghribTv24,ishaTv24,dateTv24,
            fajrTv25,dhuhrTv25,asrTv25,maghribTv25,ishaTv25,dateTv25,
            fajrTv26,dhuhrTv26,asrTv26,maghribTv26,ishaTv26,dateTv26,
            fajrTv27,dhuhrTv27,asrTv27,maghribTv27,ishaTv27,dateTv27,
            fajrTv28,dhuhrTv28,asrTv28,maghribTv28,ishaTv28,dateTv28,
            fajrTv29,dhuhrTv29,asrTv29,maghribTv29,ishaTv29,dateTv29,
            fajrTv30,dhuhrTv30,asrTv30,maghribTv30,ishaTv30,dateTv30;
    AutoCompleteTextView search;
    Button btnsearch;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        View view=inflater.inflate(R.layout.frag_4,container,false);
        fajrTv=view.findViewById(R.id.fajrTv);
        dhuhrTv=view.findViewById(R.id.dhuhrTv);
        asrTv=view.findViewById(R.id.asrTv);
        maghribTv=view.findViewById(R.id.maghribTv);
        ishaTv=view.findViewById(R.id.ishaTv);
        locationTv=view.findViewById(R.id.locationTv);
        dateTv=view.findViewById(R.id.dateTv);

        fajrTv2=view.findViewById(R.id.fajrTv2);
        dhuhrTv2=view.findViewById(R.id.dhuhrTv2);
        asrTv2=view.findViewById(R.id.asrTv2);
        maghribTv2=view.findViewById(R.id.maghribTv2);
        ishaTv2=view.findViewById(R.id.ishaTv2);
        dateTv2=view.findViewById(R.id.dateTv2);

        fajrTv3=view.findViewById(R.id.fajrTv3);
        dhuhrTv3=view.findViewById(R.id.dhuhrTv3);
        asrTv3=view.findViewById(R.id.asrTv3);
        maghribTv3=view.findViewById(R.id.maghribTv3);
        ishaTv3=view.findViewById(R.id.ishaTv3);
        dateTv3=view.findViewById(R.id.dateTv3);

        fajrTv4=view.findViewById(R.id.fajrTv4);
        dhuhrTv4=view.findViewById(R.id.dhuhrTv4);
        asrTv4=view.findViewById(R.id.asrTv4);
        maghribTv4=view.findViewById(R.id.maghribTv4);
        ishaTv4=view.findViewById(R.id.ishaTv4);
        dateTv4=view.findViewById(R.id.dateTv4);

        fajrTv5=view.findViewById(R.id.fajrTv5);
        dhuhrTv5=view.findViewById(R.id.dhuhrTv5);
        asrTv5=view.findViewById(R.id.asrTv5);
        maghribTv5=view.findViewById(R.id.maghribTv5);
        ishaTv5=view.findViewById(R.id.ishaTv5);
        dateTv5=view.findViewById(R.id.dateTv5);

        fajrTv6=view.findViewById(R.id.fajrTv6);
        dhuhrTv6=view.findViewById(R.id.dhuhrTv6);
        asrTv6=view.findViewById(R.id.asrTv6);
        maghribTv6=view.findViewById(R.id.maghribTv6);
        ishaTv6=view.findViewById(R.id.ishaTv6);
        dateTv6=view.findViewById(R.id.dateTv6);

        fajrTv7=view.findViewById(R.id.fajrTv7);
        dhuhrTv7=view.findViewById(R.id.dhuhrTv7);
        asrTv7=view.findViewById(R.id.asrTv7);
        maghribTv7=view.findViewById(R.id.maghribTv7);
        ishaTv7=view.findViewById(R.id.ishaTv7);
        dateTv7=view.findViewById(R.id.dateTv7);

        fajrTv8=view.findViewById(R.id.fajrTv8);
        dhuhrTv8=view.findViewById(R.id.dhuhrTv8);
        asrTv8=view.findViewById(R.id.asrTv8);
        maghribTv8=view.findViewById(R.id.maghribTv8);
        ishaTv8=view.findViewById(R.id.ishaTv8);
        dateTv8=view.findViewById(R.id.dateTv8);

        fajrTv9=view.findViewById(R.id.fajrTv9);
        dhuhrTv9=view.findViewById(R.id.dhuhrTv9);
        asrTv9=view.findViewById(R.id.asrTv9);
        maghribTv9=view.findViewById(R.id.maghribTv9);
        ishaTv9=view.findViewById(R.id.ishaTv9);
        dateTv9=view.findViewById(R.id.dateTv9);

        fajrTv10=view.findViewById(R.id.fajrTv10);
        dhuhrTv10=view.findViewById(R.id.dhuhrTv10);
        asrTv10=view.findViewById(R.id.asrTv10);
        maghribTv10=view.findViewById(R.id.maghribTv10);
        ishaTv10=view.findViewById(R.id.ishaTv10);
        dateTv10=view.findViewById(R.id.dateTv10);

        fajrTv11=view.findViewById(R.id.fajrTv11);
        dhuhrTv11=view.findViewById(R.id.dhuhrTv11);
        asrTv11=view.findViewById(R.id.asrTv11);
        maghribTv11=view.findViewById(R.id.maghribTv11);
        ishaTv11=view.findViewById(R.id.ishaTv11);
        dateTv11=view.findViewById(R.id.dateTv11);

        fajrTv12=view.findViewById(R.id.fajrTv12);
        dhuhrTv12=view.findViewById(R.id.dhuhrTv12);
        asrTv12=view.findViewById(R.id.asrTv12);
        maghribTv12=view.findViewById(R.id.maghribTv12);
        ishaTv12=view.findViewById(R.id.ishaTv12);
        dateTv12=view.findViewById(R.id.dateTv12);

        fajrTv13=view.findViewById(R.id.fajrTv13);
        dhuhrTv13=view.findViewById(R.id.dhuhrTv13);
        asrTv13=view.findViewById(R.id.asrTv13);
        maghribTv13=view.findViewById(R.id.maghribTv13);
        ishaTv13=view.findViewById(R.id.ishaTv13);
        dateTv13=view.findViewById(R.id.dateTv13);

        fajrTv14=view.findViewById(R.id.fajrTv14);
        dhuhrTv14=view.findViewById(R.id.dhuhrTv14);
        asrTv14=view.findViewById(R.id.asrTv14);
        maghribTv14=view.findViewById(R.id.maghribTv14);
        ishaTv14=view.findViewById(R.id.ishaTv14);
        dateTv14=view.findViewById(R.id.dateTv14);

        fajrTv15=view.findViewById(R.id.fajrTv15);
        dhuhrTv15=view.findViewById(R.id.dhuhrTv15);
        asrTv15=view.findViewById(R.id.asrTv15);
        maghribTv15=view.findViewById(R.id.maghribTv15);
        ishaTv15=view.findViewById(R.id.ishaTv15);
        dateTv15=view.findViewById(R.id.dateTv15);

        fajrTv16=view.findViewById(R.id.fajrTv16);
        dhuhrTv16=view.findViewById(R.id.dhuhrTv16);
        asrTv16=view.findViewById(R.id.asrTv16);
        maghribTv16=view.findViewById(R.id.maghribTv16);
        ishaTv16=view.findViewById(R.id.ishaTv16);
        dateTv16=view.findViewById(R.id.dateTv16);

        fajrTv17=view.findViewById(R.id.fajrTv17);
        dhuhrTv17=view.findViewById(R.id.dhuhrTv17);
        asrTv17=view.findViewById(R.id.asrTv17);
        maghribTv17=view.findViewById(R.id.maghribTv17);
        ishaTv17=view.findViewById(R.id.ishaTv17);
        dateTv17=view.findViewById(R.id.dateTv17);

        fajrTv18=view.findViewById(R.id.fajrTv18);
        dhuhrTv18=view.findViewById(R.id.dhuhrTv18);
        asrTv18=view.findViewById(R.id.asrTv18);
        maghribTv18=view.findViewById(R.id.maghribTv18);
        ishaTv18=view.findViewById(R.id.ishaTv18);
        dateTv18=view.findViewById(R.id.dateTv18);

        fajrTv19=view.findViewById(R.id.fajrTv19);
        dhuhrTv19=view.findViewById(R.id.dhuhrTv19);
        asrTv19=view.findViewById(R.id.asrTv19);
        maghribTv19=view.findViewById(R.id.maghribTv19);
        ishaTv19=view.findViewById(R.id.ishaTv19);
        dateTv19=view.findViewById(R.id.dateTv19);

        fajrTv20=view.findViewById(R.id.fajrTv20);
        dhuhrTv20=view.findViewById(R.id.dhuhrTv20);
        asrTv20=view.findViewById(R.id.asrTv20);
        maghribTv20=view.findViewById(R.id.maghribTv20);
        ishaTv20=view.findViewById(R.id.ishaTv20);
        dateTv20=view.findViewById(R.id.dateTv20);

        fajrTv21=view.findViewById(R.id.fajrTv21);
        dhuhrTv21=view.findViewById(R.id.dhuhrTv21);
        asrTv21=view.findViewById(R.id.asrTv21);
        maghribTv21=view.findViewById(R.id.maghribTv21);
        ishaTv21=view.findViewById(R.id.ishaTv21);
        dateTv21=view.findViewById(R.id.dateTv21);

        fajrTv22=view.findViewById(R.id.fajrTv22);
        dhuhrTv22=view.findViewById(R.id.dhuhrTv22);
        asrTv22=view.findViewById(R.id.asrTv22);
        maghribTv22=view.findViewById(R.id.maghribTv22);
        ishaTv22=view.findViewById(R.id.ishaTv22);
        dateTv22=view.findViewById(R.id.dateTv22);

        fajrTv23=view.findViewById(R.id.fajrTv23);
        dhuhrTv23=view.findViewById(R.id.dhuhrTv23);
        asrTv23=view.findViewById(R.id.asrTv23);
        maghribTv23=view.findViewById(R.id.maghribTv23);
        ishaTv23=view.findViewById(R.id.ishaTv23);
        dateTv23=view.findViewById(R.id.dateTv23);

        fajrTv24=view.findViewById(R.id.fajrTv24);
        dhuhrTv24=view.findViewById(R.id.dhuhrTv24);
        asrTv24=view.findViewById(R.id.asrTv24);
        maghribTv24=view.findViewById(R.id.maghribTv24);
        ishaTv24=view.findViewById(R.id.ishaTv24);
        dateTv24=view.findViewById(R.id.dateTv24);

        fajrTv25=view.findViewById(R.id.fajrTv25);
        dhuhrTv25=view.findViewById(R.id.dhuhrTv25);
        asrTv25=view.findViewById(R.id.asrTv25);
        maghribTv25=view.findViewById(R.id.maghribTv25);
        ishaTv25=view.findViewById(R.id.ishaTv25);
        dateTv25=view.findViewById(R.id.dateTv25);

        fajrTv26=view.findViewById(R.id.fajrTv26);
        dhuhrTv26=view.findViewById(R.id.dhuhrTv26);
        asrTv26=view.findViewById(R.id.asrTv26);
        maghribTv26=view.findViewById(R.id.maghribTv26);
        ishaTv26=view.findViewById(R.id.ishaTv26);
        dateTv26=view.findViewById(R.id.dateTv26);

        fajrTv27=view.findViewById(R.id.fajrTv27);
        dhuhrTv27=view.findViewById(R.id.dhuhrTv27);
        asrTv27=view.findViewById(R.id.asrTv27);
        maghribTv27=view.findViewById(R.id.maghribTv27);
        ishaTv27=view.findViewById(R.id.ishaTv27);
        dateTv27=view.findViewById(R.id.dateTv27);

        fajrTv28=view.findViewById(R.id.fajrTv28);
        dhuhrTv28=view.findViewById(R.id.dhuhrTv28);
        asrTv28=view.findViewById(R.id.asrTv28);
        maghribTv28=view.findViewById(R.id.maghribTv28);
        ishaTv28=view.findViewById(R.id.ishaTv28);
        dateTv28=view.findViewById(R.id.dateTv28);

        fajrTv29=view.findViewById(R.id.fajrTv29);
        dhuhrTv29=view.findViewById(R.id.dhuhrTv29);
        asrTv29=view.findViewById(R.id.asrTv29);
        maghribTv29=view.findViewById(R.id.maghribTv29);
        ishaTv29=view.findViewById(R.id.ishaTv29);
        dateTv29=view.findViewById(R.id.dateTv29);

        fajrTv30=view.findViewById(R.id.fajrTv30);
        dhuhrTv30=view.findViewById(R.id.dhuhrTv30);
        asrTv30=view.findViewById(R.id.asrTv30);
        maghribTv30=view.findViewById(R.id.maghribTv30);
        ishaTv30=view.findViewById(R.id.ishaTv30);
        dateTv30=view.findViewById(R.id.dateTv30);






        search=view.findViewById(R.id.search);
        String[] cities=getResources().getStringArray(R.array.cities);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,cities);
        search.setAdapter(adapter);
        btnsearch=view.findViewById(R.id.btnsearch);
        btnsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mLocation=search.getText().toString().trim();
                keyboard();
                if (mLocation.isEmpty()){
                    Toast.makeText(getActivity(),"Please enter city name",Toast.LENGTH_SHORT).show();
                }else {
                    url="https://muslimsalat.com/"+mLocation+"/yearly.json?key=fcd9df43c1ede992f580a75150a85f67";
                    //url="https://muslimsalat.com/"+mLocation+"/weekly.json?key=fcd9df43c1ede992f580a75150a85f67";
                    searchLocation();
                }
            }
        });

        search.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                keyboard();
                search.setCursorVisible(true);
                String selectdata=parent.getItemAtPosition(position).toString();
                url="https://muslimsalat.com/"+selectdata+"/yearly.json?key=fcd9df43c1ede992f580a75150a85f67";
                searchLocation();
            }
        });



        return view;
    }

    private void keyboard() {
        View v=getActivity().getCurrentFocus();
        if (v!=null){
            InputMethodManager imm= (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(v.getWindowToken(),0);
        }
    }

    private void searchLocation() {
        final ProgressDialog pDialog = new ProgressDialog(getActivity());
        pDialog.setMessage("Loading...");
        pDialog.show();
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                url, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        //Log.d(TAG, response.toString());
                        //Toast.makeText(context,response.toString(),Toast.LENGTH_SHORT).show();
                        try {
                            String country=response.get("country").toString();
                            String state=response.get("state").toString();
                            String city=response.get("city").toString();
                            String location=country +", "+ state +", "+ city;

                            String date=response.getJSONArray("items").getJSONObject(0).get("date_for").toString();

                            String mfajr=response.getJSONArray("items").getJSONObject(0).get("fajr").toString();
                            String mdhuhr=response.getJSONArray("items").getJSONObject(0).get("dhuhr").toString();
                            String masr=response.getJSONArray("items").getJSONObject(0).get("asr").toString();
                            String mmaghrib=response.getJSONArray("items").getJSONObject(0).get("maghrib").toString();
                            String misha=response.getJSONArray("items").getJSONObject(0).get("isha").toString();


                            fajrTv.setText(mfajr);
                            dhuhrTv.setText(mdhuhr);
                            asrTv.setText(masr);
                            maghribTv.setText(mmaghrib);
                            ishaTv.setText(misha);


                            locationTv.setText(location);
                            dateTv.setText(date);

                            String date2=response.getJSONArray("items").getJSONObject(1).get("date_for").toString();
                            String mfajr2=response.getJSONArray("items").getJSONObject(1).get("fajr").toString();
                            String mdhuhr2=response.getJSONArray("items").getJSONObject(1).get("dhuhr").toString();
                            String masr2=response.getJSONArray("items").getJSONObject(1).get("asr").toString();
                            String mmaghrib2=response.getJSONArray("items").getJSONObject(1).get("maghrib").toString();
                            String misha2=response.getJSONArray("items").getJSONObject(1).get("isha").toString();
                            dateTv2.setText(date2);
                            fajrTv2.setText(mfajr2);
                            dhuhrTv2.setText(mdhuhr2);
                            asrTv2.setText(masr2);
                            maghribTv2.setText(mmaghrib2);
                            ishaTv2.setText(misha2);

                            String date3=response.getJSONArray("items").getJSONObject(2).get("date_for").toString();
                            String mfajr3=response.getJSONArray("items").getJSONObject(2).get("fajr").toString();
                            String mdhuhr3=response.getJSONArray("items").getJSONObject(2).get("dhuhr").toString();
                            String masr3=response.getJSONArray("items").getJSONObject(2).get("asr").toString();
                            String mmaghrib3=response.getJSONArray("items").getJSONObject(2).get("maghrib").toString();
                            String misha3=response.getJSONArray("items").getJSONObject(2).get("isha").toString();
                            dateTv3.setText(date3);
                            fajrTv3.setText(mfajr3);
                            dhuhrTv3.setText(mdhuhr3);
                            asrTv3.setText(masr3);
                            maghribTv3.setText(mmaghrib3);
                            ishaTv3.setText(misha3);

                            String date4=response.getJSONArray("items").getJSONObject(3).get("date_for").toString();
                            String mfajr4=response.getJSONArray("items").getJSONObject(3).get("fajr").toString();
                            String mdhuhr4=response.getJSONArray("items").getJSONObject(3).get("dhuhr").toString();
                            String masr4=response.getJSONArray("items").getJSONObject(3).get("asr").toString();
                            String mmaghrib4=response.getJSONArray("items").getJSONObject(3).get("maghrib").toString();
                            String misha4=response.getJSONArray("items").getJSONObject(3).get("isha").toString();
                            dateTv4.setText(date4);
                            fajrTv4.setText(mfajr4);
                            dhuhrTv4.setText(mdhuhr4);
                            asrTv4.setText(masr4);
                            maghribTv4.setText(mmaghrib4);
                            ishaTv4.setText(misha4);

                            String date5=response.getJSONArray("items").getJSONObject(4).get("date_for").toString();
                            String mfajr5=response.getJSONArray("items").getJSONObject(4).get("fajr").toString();
                            String mdhuhr5=response.getJSONArray("items").getJSONObject(4).get("dhuhr").toString();
                            String masr5=response.getJSONArray("items").getJSONObject(4).get("asr").toString();
                            String mmaghrib5=response.getJSONArray("items").getJSONObject(4).get("maghrib").toString();
                            String misha5=response.getJSONArray("items").getJSONObject(4).get("isha").toString();
                            dateTv5.setText(date5);
                            fajrTv5.setText(mfajr5);
                            dhuhrTv5.setText(mdhuhr5);
                            asrTv5.setText(masr5);
                            maghribTv5.setText(mmaghrib5);
                            ishaTv5.setText(misha5);

                            String date6=response.getJSONArray("items").getJSONObject(5).get("date_for").toString();
                            String mfajr6=response.getJSONArray("items").getJSONObject(5).get("fajr").toString();
                            String mdhuhr6=response.getJSONArray("items").getJSONObject(5).get("dhuhr").toString();
                            String masr6=response.getJSONArray("items").getJSONObject(5).get("asr").toString();
                            String mmaghrib6=response.getJSONArray("items").getJSONObject(5).get("maghrib").toString();
                            String misha6=response.getJSONArray("items").getJSONObject(5).get("isha").toString();
                            dateTv6.setText(date6);
                            fajrTv6.setText(mfajr6);
                            dhuhrTv6.setText(mdhuhr6);
                            asrTv6.setText(masr6);
                            maghribTv6.setText(mmaghrib6);
                            ishaTv6.setText(misha6);

                            String date7=response.getJSONArray("items").getJSONObject(6).get("date_for").toString();
                            String mfajr7=response.getJSONArray("items").getJSONObject(6).get("fajr").toString();
                            String mdhuhr7=response.getJSONArray("items").getJSONObject(6).get("dhuhr").toString();
                            String masr7=response.getJSONArray("items").getJSONObject(6).get("asr").toString();
                            String mmaghrib7=response.getJSONArray("items").getJSONObject(6).get("maghrib").toString();
                            String misha7=response.getJSONArray("items").getJSONObject(6).get("isha").toString();
                            dateTv7.setText(date7);
                            fajrTv7.setText(mfajr7);
                            dhuhrTv7.setText(mdhuhr7);
                            asrTv7.setText(masr7);
                            maghribTv7.setText(mmaghrib7);
                            ishaTv7.setText(misha7);

                            String date8=response.getJSONArray("items").getJSONObject(7).get("date_for").toString();
                            String mfajr8=response.getJSONArray("items").getJSONObject(7).get("fajr").toString();
                            String mdhuhr8=response.getJSONArray("items").getJSONObject(7).get("dhuhr").toString();
                            String masr8=response.getJSONArray("items").getJSONObject(7).get("asr").toString();
                            String mmaghrib8=response.getJSONArray("items").getJSONObject(7).get("maghrib").toString();
                            String misha8=response.getJSONArray("items").getJSONObject(7).get("isha").toString();
                            dateTv8.setText(date8);
                            fajrTv8.setText(mfajr8);
                            dhuhrTv8.setText(mdhuhr8);
                            asrTv8.setText(masr8);
                            maghribTv8.setText(mmaghrib8);
                            ishaTv8.setText(misha8);

                            String date9=response.getJSONArray("items").getJSONObject(8).get("date_for").toString();
                            String mfajr9=response.getJSONArray("items").getJSONObject(8).get("fajr").toString();
                            String mdhuhr9=response.getJSONArray("items").getJSONObject(8).get("dhuhr").toString();
                            String masr9=response.getJSONArray("items").getJSONObject(8).get("asr").toString();
                            String mmaghrib9=response.getJSONArray("items").getJSONObject(8).get("maghrib").toString();
                            String misha9=response.getJSONArray("items").getJSONObject(8).get("isha").toString();
                            dateTv9.setText(date9);
                            fajrTv9.setText(mfajr9);
                            dhuhrTv9.setText(mdhuhr9);
                            asrTv9.setText(masr9);
                            maghribTv9.setText(mmaghrib9);
                            ishaTv9.setText(misha9);

                            String date10=response.getJSONArray("items").getJSONObject(9).get("date_for").toString();
                            String mfajr10=response.getJSONArray("items").getJSONObject(9).get("fajr").toString();
                            String mdhuhr10=response.getJSONArray("items").getJSONObject(9).get("dhuhr").toString();
                            String masr10=response.getJSONArray("items").getJSONObject(9).get("asr").toString();
                            String mmaghrib10=response.getJSONArray("items").getJSONObject(9).get("maghrib").toString();
                            String misha10=response.getJSONArray("items").getJSONObject(9).get("isha").toString();
                            dateTv10.setText(date10);
                            fajrTv10.setText(mfajr10);
                            dhuhrTv10.setText(mdhuhr10);
                            asrTv10.setText(masr10);
                            maghribTv10.setText(mmaghrib10);
                            ishaTv10.setText(misha10);

                            String date11=response.getJSONArray("items").getJSONObject(10).get("date_for").toString();
                            String mfajr11=response.getJSONArray("items").getJSONObject(10).get("fajr").toString();
                            String mdhuhr11=response.getJSONArray("items").getJSONObject(10).get("dhuhr").toString();
                            String masr11=response.getJSONArray("items").getJSONObject(10).get("asr").toString();
                            String mmaghrib11=response.getJSONArray("items").getJSONObject(10).get("maghrib").toString();
                            String misha11=response.getJSONArray("items").getJSONObject(10).get("isha").toString();
                            dateTv11.setText(date11);
                            fajrTv11.setText(mfajr11);
                            dhuhrTv11.setText(mdhuhr11);
                            asrTv11.setText(masr11);
                            maghribTv11.setText(mmaghrib11);
                            ishaTv11.setText(misha11);

                            String date12=response.getJSONArray("items").getJSONObject(11).get("date_for").toString();
                            String mfajr12=response.getJSONArray("items").getJSONObject(11).get("fajr").toString();
                            String mdhuhr12=response.getJSONArray("items").getJSONObject(11).get("dhuhr").toString();
                            String masr12=response.getJSONArray("items").getJSONObject(11).get("asr").toString();
                            String mmaghrib12=response.getJSONArray("items").getJSONObject(11).get("maghrib").toString();
                            String misha12=response.getJSONArray("items").getJSONObject(11).get("isha").toString();
                            dateTv12.setText(date12);
                            fajrTv12.setText(mfajr12);
                            dhuhrTv12.setText(mdhuhr12);
                            asrTv12.setText(masr12);
                            maghribTv12.setText(mmaghrib12);
                            ishaTv12.setText(misha12);

                            String date13=response.getJSONArray("items").getJSONObject(12).get("date_for").toString();
                            String mfajr13=response.getJSONArray("items").getJSONObject(12).get("fajr").toString();
                            String mdhuhr13=response.getJSONArray("items").getJSONObject(12).get("dhuhr").toString();
                            String masr13=response.getJSONArray("items").getJSONObject(12).get("asr").toString();
                            String mmaghrib13=response.getJSONArray("items").getJSONObject(12).get("maghrib").toString();
                            String misha13=response.getJSONArray("items").getJSONObject(12).get("isha").toString();
                            dateTv13.setText(date13);
                            fajrTv13.setText(mfajr13);
                            dhuhrTv13.setText(mdhuhr13);
                            asrTv13.setText(masr13);
                            maghribTv13.setText(mmaghrib13);
                            ishaTv13.setText(misha13);

                            String date14=response.getJSONArray("items").getJSONObject(13).get("date_for").toString();
                            String mfajr14=response.getJSONArray("items").getJSONObject(13).get("fajr").toString();
                            String mdhuhr14=response.getJSONArray("items").getJSONObject(13).get("dhuhr").toString();
                            String masr14=response.getJSONArray("items").getJSONObject(13).get("asr").toString();
                            String mmaghrib14=response.getJSONArray("items").getJSONObject(13).get("maghrib").toString();
                            String misha14=response.getJSONArray("items").getJSONObject(13).get("isha").toString();
                            dateTv14.setText(date14);
                            fajrTv14.setText(mfajr14);
                            dhuhrTv14.setText(mdhuhr14);
                            asrTv14.setText(masr14);
                            maghribTv14.setText(mmaghrib14);
                            ishaTv14.setText(misha14);

                            String date15=response.getJSONArray("items").getJSONObject(14).get("date_for").toString();
                            String mfajr15=response.getJSONArray("items").getJSONObject(14).get("fajr").toString();
                            String mdhuhr15=response.getJSONArray("items").getJSONObject(14).get("dhuhr").toString();
                            String masr15=response.getJSONArray("items").getJSONObject(14).get("asr").toString();
                            String mmaghrib15=response.getJSONArray("items").getJSONObject(14).get("maghrib").toString();
                            String misha15=response.getJSONArray("items").getJSONObject(14).get("isha").toString();
                            dateTv15.setText(date15);
                            fajrTv15.setText(mfajr15);
                            dhuhrTv15.setText(mdhuhr15);
                            asrTv15.setText(masr15);
                            maghribTv15.setText(mmaghrib15);
                            ishaTv15.setText(misha15);

                            String date16=response.getJSONArray("items").getJSONObject(15).get("date_for").toString();
                            String mfajr16=response.getJSONArray("items").getJSONObject(15).get("fajr").toString();
                            String mdhuhr16=response.getJSONArray("items").getJSONObject(15).get("dhuhr").toString();
                            String masr16=response.getJSONArray("items").getJSONObject(15).get("asr").toString();
                            String mmaghrib16=response.getJSONArray("items").getJSONObject(15).get("maghrib").toString();
                            String misha16=response.getJSONArray("items").getJSONObject(15).get("isha").toString();
                            dateTv16.setText(date16);
                            fajrTv16.setText(mfajr16);
                            dhuhrTv16.setText(mdhuhr16);
                            asrTv16.setText(masr16);
                            maghribTv16.setText(mmaghrib16);
                            ishaTv16.setText(misha16);

                            String date17=response.getJSONArray("items").getJSONObject(16).get("date_for").toString();
                            String mfajr17=response.getJSONArray("items").getJSONObject(16).get("fajr").toString();
                            String mdhuhr17=response.getJSONArray("items").getJSONObject(16).get("dhuhr").toString();
                            String masr17=response.getJSONArray("items").getJSONObject(16).get("asr").toString();
                            String mmaghrib17=response.getJSONArray("items").getJSONObject(16).get("maghrib").toString();
                            String misha17=response.getJSONArray("items").getJSONObject(16).get("isha").toString();
                            dateTv17.setText(date17);
                            fajrTv17.setText(mfajr17);
                            dhuhrTv17.setText(mdhuhr17);
                            asrTv17.setText(masr17);
                            maghribTv17.setText(mmaghrib17);
                            ishaTv17.setText(misha17);

                            String date18=response.getJSONArray("items").getJSONObject(17).get("date_for").toString();
                            String mfajr18=response.getJSONArray("items").getJSONObject(17).get("fajr").toString();
                            String mdhuhr18=response.getJSONArray("items").getJSONObject(17).get("dhuhr").toString();
                            String masr18=response.getJSONArray("items").getJSONObject(17).get("asr").toString();
                            String mmaghrib18=response.getJSONArray("items").getJSONObject(17).get("maghrib").toString();
                            String misha18=response.getJSONArray("items").getJSONObject(17).get("isha").toString();
                            dateTv18.setText(date18);
                            fajrTv18.setText(mfajr18);
                            dhuhrTv18.setText(mdhuhr18);
                            asrTv18.setText(masr18);
                            maghribTv18.setText(mmaghrib18);
                            ishaTv18.setText(misha18);

                            String date19=response.getJSONArray("items").getJSONObject(18).get("date_for").toString();
                            String mfajr19=response.getJSONArray("items").getJSONObject(18).get("fajr").toString();
                            String mdhuhr19=response.getJSONArray("items").getJSONObject(18).get("dhuhr").toString();
                            String masr19=response.getJSONArray("items").getJSONObject(18).get("asr").toString();
                            String mmaghrib19=response.getJSONArray("items").getJSONObject(18).get("maghrib").toString();
                            String misha19=response.getJSONArray("items").getJSONObject(18).get("isha").toString();
                            dateTv19.setText(date19);
                            fajrTv19.setText(mfajr19);
                            dhuhrTv19.setText(mdhuhr19);
                            asrTv19.setText(masr19);
                            maghribTv19.setText(mmaghrib19);
                            ishaTv19.setText(misha19);

                            String date20=response.getJSONArray("items").getJSONObject(19).get("date_for").toString();
                            String mfajr20=response.getJSONArray("items").getJSONObject(19).get("fajr").toString();
                            String mdhuhr20=response.getJSONArray("items").getJSONObject(19).get("dhuhr").toString();
                            String masr20=response.getJSONArray("items").getJSONObject(19).get("asr").toString();
                            String mmaghrib20=response.getJSONArray("items").getJSONObject(19).get("maghrib").toString();
                            String misha20=response.getJSONArray("items").getJSONObject(19).get("isha").toString();
                            dateTv20.setText(date20);
                            fajrTv20.setText(mfajr20);
                            dhuhrTv20.setText(mdhuhr20);
                            asrTv20.setText(masr20);
                            maghribTv20.setText(mmaghrib20);
                            ishaTv20.setText(misha20);

                            String date21=response.getJSONArray("items").getJSONObject(20).get("date_for").toString();
                            String mfajr21=response.getJSONArray("items").getJSONObject(20).get("fajr").toString();
                            String mdhuhr21=response.getJSONArray("items").getJSONObject(20).get("dhuhr").toString();
                            String masr21=response.getJSONArray("items").getJSONObject(20).get("asr").toString();
                            String mmaghrib21=response.getJSONArray("items").getJSONObject(20).get("maghrib").toString();
                            String misha21=response.getJSONArray("items").getJSONObject(20).get("isha").toString();
                            dateTv21.setText(date21);
                            fajrTv21.setText(mfajr21);
                            dhuhrTv21.setText(mdhuhr21);
                            asrTv21.setText(masr21);
                            maghribTv21.setText(mmaghrib21);
                            ishaTv21.setText(misha21);

                            String date22=response.getJSONArray("items").getJSONObject(21).get("date_for").toString();
                            String mfajr22=response.getJSONArray("items").getJSONObject(21).get("fajr").toString();
                            String mdhuhr22=response.getJSONArray("items").getJSONObject(21).get("dhuhr").toString();
                            String masr22=response.getJSONArray("items").getJSONObject(21).get("asr").toString();
                            String mmaghrib22=response.getJSONArray("items").getJSONObject(21).get("maghrib").toString();
                            String misha22=response.getJSONArray("items").getJSONObject(21).get("isha").toString();
                            dateTv22.setText(date22);
                            fajrTv22.setText(mfajr22);
                            dhuhrTv22.setText(mdhuhr22);
                            asrTv22.setText(masr22);
                            maghribTv22.setText(mmaghrib22);
                            ishaTv22.setText(misha22);

                            String date23=response.getJSONArray("items").getJSONObject(22).get("date_for").toString();
                            String mfajr23=response.getJSONArray("items").getJSONObject(22).get("fajr").toString();
                            String mdhuhr23=response.getJSONArray("items").getJSONObject(22).get("dhuhr").toString();
                            String masr23=response.getJSONArray("items").getJSONObject(22).get("asr").toString();
                            String mmaghrib23=response.getJSONArray("items").getJSONObject(22).get("maghrib").toString();
                            String misha23=response.getJSONArray("items").getJSONObject(22).get("isha").toString();
                            dateTv23.setText(date23);
                            fajrTv23.setText(mfajr23);
                            dhuhrTv23.setText(mdhuhr23);
                            asrTv23.setText(masr23);
                            maghribTv23.setText(mmaghrib23);
                            ishaTv23.setText(misha23);

                            String date24=response.getJSONArray("items").getJSONObject(23).get("date_for").toString();
                            String mfajr24=response.getJSONArray("items").getJSONObject(23).get("fajr").toString();
                            String mdhuhr24=response.getJSONArray("items").getJSONObject(23).get("dhuhr").toString();
                            String masr24=response.getJSONArray("items").getJSONObject(23).get("asr").toString();
                            String mmaghrib24=response.getJSONArray("items").getJSONObject(23).get("maghrib").toString();
                            String misha24=response.getJSONArray("items").getJSONObject(23).get("isha").toString();
                            dateTv24.setText(date24);
                            fajrTv24.setText(mfajr24);
                            dhuhrTv24.setText(mdhuhr24);
                            asrTv24.setText(masr24);
                            maghribTv24.setText(mmaghrib24);
                            ishaTv24.setText(misha24);

                            String date25=response.getJSONArray("items").getJSONObject(24).get("date_for").toString();
                            String mfajr25=response.getJSONArray("items").getJSONObject(24).get("fajr").toString();
                            String mdhuhr25=response.getJSONArray("items").getJSONObject(24).get("dhuhr").toString();
                            String masr25=response.getJSONArray("items").getJSONObject(24).get("asr").toString();
                            String mmaghrib25=response.getJSONArray("items").getJSONObject(24).get("maghrib").toString();
                            String misha25=response.getJSONArray("items").getJSONObject(24).get("isha").toString();
                            dateTv25.setText(date25);
                            fajrTv25.setText(mfajr25);
                            dhuhrTv25.setText(mdhuhr25);
                            asrTv25.setText(masr25);
                            maghribTv25.setText(mmaghrib25);
                            ishaTv25.setText(misha25);

                            String date26=response.getJSONArray("items").getJSONObject(25).get("date_for").toString();
                            String mfajr26=response.getJSONArray("items").getJSONObject(25).get("fajr").toString();
                            String mdhuhr26=response.getJSONArray("items").getJSONObject(25).get("dhuhr").toString();
                            String masr26=response.getJSONArray("items").getJSONObject(25).get("asr").toString();
                            String mmaghrib26=response.getJSONArray("items").getJSONObject(25).get("maghrib").toString();
                            String misha26=response.getJSONArray("items").getJSONObject(25).get("isha").toString();
                            dateTv26.setText(date26);
                            fajrTv26.setText(mfajr26);
                            dhuhrTv26.setText(mdhuhr26);
                            asrTv26.setText(masr26);
                            maghribTv26.setText(mmaghrib26);
                            ishaTv26.setText(misha26);

                            String date27=response.getJSONArray("items").getJSONObject(26).get("date_for").toString();
                            String mfajr27=response.getJSONArray("items").getJSONObject(26).get("fajr").toString();
                            String mdhuhr27=response.getJSONArray("items").getJSONObject(26).get("dhuhr").toString();
                            String masr27=response.getJSONArray("items").getJSONObject(26).get("asr").toString();
                            String mmaghrib27=response.getJSONArray("items").getJSONObject(26).get("maghrib").toString();
                            String misha27=response.getJSONArray("items").getJSONObject(26).get("isha").toString();
                            dateTv27.setText(date27);
                            fajrTv27.setText(mfajr27);
                            dhuhrTv27.setText(mdhuhr27);
                            asrTv27.setText(masr27);
                            maghribTv27.setText(mmaghrib27);
                            ishaTv27.setText(misha27);

                            String date28=response.getJSONArray("items").getJSONObject(27).get("date_for").toString();
                            String mfajr28=response.getJSONArray("items").getJSONObject(27).get("fajr").toString();
                            String mdhuhr28=response.getJSONArray("items").getJSONObject(27).get("dhuhr").toString();
                            String masr28=response.getJSONArray("items").getJSONObject(27).get("asr").toString();
                            String mmaghrib28=response.getJSONArray("items").getJSONObject(27).get("maghrib").toString();
                            String misha28=response.getJSONArray("items").getJSONObject(27).get("isha").toString();
                            dateTv28.setText(date28);
                            fajrTv28.setText(mfajr28);
                            dhuhrTv28.setText(mdhuhr28);
                            asrTv28.setText(masr28);
                            maghribTv28.setText(mmaghrib28);
                            ishaTv28.setText(misha28);

                            String date29=response.getJSONArray("items").getJSONObject(28).get("date_for").toString();
                            String mfajr29=response.getJSONArray("items").getJSONObject(28).get("fajr").toString();
                            String mdhuhr29=response.getJSONArray("items").getJSONObject(28).get("dhuhr").toString();
                            String masr29=response.getJSONArray("items").getJSONObject(28).get("asr").toString();
                            String mmaghrib29=response.getJSONArray("items").getJSONObject(28).get("maghrib").toString();
                            String misha29=response.getJSONArray("items").getJSONObject(28).get("isha").toString();
                            dateTv29.setText(date29);
                            fajrTv29.setText(mfajr29);
                            dhuhrTv29.setText(mdhuhr29);
                            asrTv29.setText(masr29);
                            maghribTv29.setText(mmaghrib29);
                            ishaTv29.setText(misha29);

                            String date30=response.getJSONArray("items").getJSONObject(29).get("date_for").toString();
                            String mfajr30=response.getJSONArray("items").getJSONObject(29).get("fajr").toString();
                            String mdhuhr30=response.getJSONArray("items").getJSONObject(29).get("dhuhr").toString();
                            String masr30=response.getJSONArray("items").getJSONObject(29).get("asr").toString();
                            String mmaghrib30=response.getJSONArray("items").getJSONObject(29).get("maghrib").toString();
                            String misha30=response.getJSONArray("items").getJSONObject(29).get("isha").toString();
                            dateTv30.setText(date30);
                            fajrTv30.setText(mfajr30);
                            dhuhrTv30.setText(mdhuhr30);
                            asrTv30.setText(masr30);
                            maghribTv30.setText(mmaghrib30);
                            ishaTv30.setText(misha30);





                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        pDialog.hide();
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                // hide the progress dialog
                Toast.makeText(getActivity(),"Error",Toast.LENGTH_LONG).show();

                pDialog.hide();
            }
        });

// Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);

    }


    public void onClick(View v) {
        getActivity().onBackPressed();
    }


}

