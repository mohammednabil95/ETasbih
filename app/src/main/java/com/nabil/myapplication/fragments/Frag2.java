package com.nabil.myapplication.fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.InputType;
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

public class Frag2 extends Fragment {
    private static final String TAG="tag";
    Context context;
    String tag_json_obj = "json_obj_req";
    String url;
    TextView fajrTv,dhuhrTv,asrTv,maghribTv,ishaTv,locationTv,dateTv;
    AutoCompleteTextView search;
    Button btnsearch;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        View view=inflater.inflate(R.layout.frag_2,container,false);
        fajrTv=view.findViewById(R.id.fajrTv);
        dhuhrTv=view.findViewById(R.id.dhuhrTv);
        asrTv=view.findViewById(R.id.asrTv);
        maghribTv=view.findViewById(R.id.maghribTv);
        ishaTv=view.findViewById(R.id.ishaTv);
        locationTv=view.findViewById(R.id.locationTv);
        dateTv=view.findViewById(R.id.dateTv);


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
                    url="https://muslimsalat.com/"+mLocation+".json?key=fcd9df43c1ede992f580a75150a85f67";
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
                url="https://muslimsalat.com/"+selectdata+".json?key=fcd9df43c1ede992f580a75150a85f67";
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

