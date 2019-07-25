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

public class Frag3 extends Fragment {
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
            fajrTv7,dhuhrTv7,asrTv7,maghribTv7,ishaTv7,dateTv7;
    AutoCompleteTextView search;
    Button btnsearch;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        View view=inflater.inflate(R.layout.frag_3,container,false);
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

