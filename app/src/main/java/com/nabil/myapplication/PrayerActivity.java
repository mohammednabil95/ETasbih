package com.nabil.myapplication;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Address;
import android.location.Geocoder;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.nabil.myapplication.model.Prayer;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PrayerActivity extends AppCompatActivity {
    Dialog dialog;
    TextView fajrTv,dhuhrTv,asrTv,maghribTv,ishaTv,dateTv,locationTv;
    Button btnMethod,btnSearch;
    AutoCompleteTextView search;
    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prayer);

        btnMethod = findViewById(R.id.btnMethod);
        btnSearch=findViewById(R.id.btnSearch);
        fajrTv=findViewById(R.id.fajrTv);
        dhuhrTv=findViewById(R.id.dhuhrTv);
        asrTv=findViewById(R.id.asrTv);
        maghribTv=findViewById(R.id.maghribTv);
        ishaTv=findViewById(R.id.ishaTv);
        dateTv=findViewById(R.id.dateTv);
        locationTv=findViewById(R.id.locationTv);
        search=findViewById(R.id.search);
        linearLayout=findViewById(R.id.linearLayout);
        dialog=new Dialog(this);

        String[] cities=getResources().getStringArray(R.array.cities);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(PrayerActivity.this,android.R.layout.simple_list_item_1,cities);
        search.setAdapter(adapter);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        btnMethod.setOnClickListener(v -> openMethod());

        btnSearch.setOnClickListener(v -> SearchAPI());

        search.setOnItemClickListener((parent, view, position, id) -> {
            keyboard();
            search.setCursorVisible(true);
            String selectedData=parent.getItemAtPosition(position).toString();
            ArrayAPI(selectedData);
        });

        linearLayout.setOnClickListener(v -> hideSoftKeyboard(PrayerActivity.this));
    }

    private void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) activity.getSystemService(
                        Activity.INPUT_METHOD_SERVICE);
        View focusedView = PrayerActivity.this.getCurrentFocus();
        if (focusedView!=null) {
            inputMethodManager.hideSoftInputFromWindow(
                    activity.getCurrentFocus().getWindowToken(), 0);
        }
    }

    @Override
    public void onBackPressed() {
        finish();
        startActivity(new Intent(PrayerActivity.this, MainActivity.class));
        Animatoo.animateSlideRight(PrayerActivity.this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
            startActivity(new Intent(PrayerActivity.this, MainActivity.class));
            Animatoo.animateSlideRight(PrayerActivity.this);
        }
        return super.onOptionsItemSelected(item);
    }

    private void ArrayAPI(String passData){
        SharedPreferences sharedPreferences=getSharedPreferences("METHOD", Context.MODE_PRIVATE);
        SharedPreferences sharedPreferences1=getSharedPreferences("SCHOOL", Context.MODE_PRIVATE);
        int method = sharedPreferences.getInt("key",4);
        int school = sharedPreferences1.getInt("keyS",0);
        keyboard();
        if (passData.isEmpty()){
            Toast.makeText(PrayerActivity.this,"Please enter city name",Toast.LENGTH_SHORT).show();
        }else {
            checkConnection();
            Retrofit retrofit=new Retrofit.Builder()
                    .baseUrl(APIInterface.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            APIInterface apiInterface=retrofit.create(APIInterface.class);
            Call<Prayer> call=apiInterface.getPrayer(passData,method,school);
            call.enqueue(new Callback<Prayer>() {
                @RequiresApi(api = Build.VERSION_CODES.O)
                @Override
                public void onResponse(@NonNull Call<Prayer> call, @NonNull Response<Prayer> response) {
                    Prayer prayer=response.body();

                    String fajrMain=prayer.getData().getTimings().getFajr();
                    String dhuhrMain=prayer.getData().getTimings().getDhuhr();
                    String asrMain=prayer.getData().getTimings().getAsr();
                    String maghribMain=prayer.getData().getTimings().getMaghrib();
                    String ishaMain=prayer.getData().getTimings().getIsha();
                    String dateMain=prayer.getData().getDate().getReadable();
                    double locationMain1=prayer.getData().getMeta().getLatitude();
                    double locationMain2=prayer.getData().getMeta().getLongitude();

                    SimpleDateFormat sdfFajr = new SimpleDateFormat("HH:mm");
                    try{
                        Date date = sdfFajr.parse(fajrMain);
                        SimpleDateFormat sdf2Fajr = new SimpleDateFormat("hh:mm aa");
                        String fajrResult=sdf2Fajr.format(date);
                        fajrTv.setText(fajrResult);
                    }catch(ParseException e){
                        e.printStackTrace();
                    }

                    SimpleDateFormat sdfDhuhr = new SimpleDateFormat("HH:mm");
                    try{
                        Date date = sdfDhuhr.parse(dhuhrMain);
                        SimpleDateFormat sdf2Dhuhr = new SimpleDateFormat("hh:mm aa");
                        String dhuhrResult=sdf2Dhuhr.format(date);
                        dhuhrTv.setText(dhuhrResult);
                    }catch(ParseException e){
                        e.printStackTrace();
                    }

                    SimpleDateFormat sdfAsr = new SimpleDateFormat("HH:mm");
                    try{
                        Date date = sdfAsr.parse(asrMain);
                        SimpleDateFormat sdf2Asr = new SimpleDateFormat("hh:mm aa");
                        String AsrResult=sdf2Asr.format(date);
                        asrTv.setText(AsrResult);
                    }catch(ParseException e){
                        e.printStackTrace();
                    }

                    SimpleDateFormat sdfMaghrib = new SimpleDateFormat("HH:mm");
                    try{
                        Date date = sdfMaghrib.parse(maghribMain);
                        SimpleDateFormat sdf2Maghrib = new SimpleDateFormat("hh:mm aa");
                        String maghribResult=sdf2Maghrib.format(date);
                        maghribTv.setText(maghribResult);
                    }catch(ParseException e){
                        e.printStackTrace();
                    }

                    SimpleDateFormat sdfIsha = new SimpleDateFormat("HH:mm");
                    try{
                        Date date = sdfIsha.parse(ishaMain);
                        SimpleDateFormat sdf2Isha = new SimpleDateFormat("hh:mm aa");
                        String ishaResult=sdf2Isha.format(date);
                        ishaTv.setText(ishaResult);
                    }catch(ParseException e){
                        e.printStackTrace();
                    }

                    Geocoder geocoder = new Geocoder(PrayerActivity.this, Locale.getDefault());
                    String result = null;
                    try {
                        List < Address > addressList = geocoder.getFromLocation(locationMain1, locationMain2, 1);
                        if (addressList != null && addressList.size() != 0) {
                            Address address = addressList.get(0);
                            StringBuilder sb = new StringBuilder();
                            for (int i = 0; i < address.getMaxAddressLineIndex(); i++) {
                                sb.append(address.getAddressLine(i));
                            }
                            sb.append(address.getLocality()).append(", ");
                            sb.append(address.getCountryName());
                            result = sb.toString();
                        }
                    } catch (IOException e) {
                        Log.e("Location Address Loader", "Unable connect to Geocoder", e);
                    }

                    dateTv.setText(dateMain);
                    locationTv.setText(result);

                }

                @Override
                public void onFailure(Call<Prayer> call, Throwable t) {
                    //Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_LONG).show();
                }
            });
        }
    }

    private void checkConnection() {
        ConnectivityManager manager= (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork=manager.getActiveNetworkInfo();
        if (null!=activeNetwork){
            if (activeNetwork.getType()==ConnectivityManager.TYPE_WIFI){
                final ProgressDialog pDialog = new ProgressDialog(PrayerActivity.this);
                pDialog.setMessage("Loading...");
                pDialog.show();
                final Timer timer=new Timer();
                timer.schedule(new TimerTask() {
                    public void run() {
                        pDialog.dismiss();
                        timer.cancel();
                    }
                }, 1500);
            }else if (activeNetwork.getType()==ConnectivityManager.TYPE_MOBILE){
                final ProgressDialog pDialog = new ProgressDialog(PrayerActivity.this);
                pDialog.setMessage("Loading...");
                pDialog.show();
                final Timer timer=new Timer();
                timer.schedule(new TimerTask() {
                    public void run() {
                        pDialog.dismiss();
                        timer.cancel();
                    }
                }, 1500);
            }
        }else {
            Toast.makeText(PrayerActivity.this,"NO INTERNET CONNECTION",Toast.LENGTH_LONG).show();
        }
    }

    private void SearchAPI() {
        SharedPreferences sharedPreferences=getSharedPreferences("METHOD", Context.MODE_PRIVATE);
        SharedPreferences sharedPreferences1=getSharedPreferences("SCHOOL", Context.MODE_PRIVATE);
        String mLocation=search.getText().toString().trim();
        int method = sharedPreferences.getInt("key",4);
        int school = sharedPreferences1.getInt("keyS",0);
        keyboard();
        if (mLocation.isEmpty()){
            Toast.makeText(PrayerActivity.this,"Please enter city name",Toast.LENGTH_SHORT).show();
        }else {
            checkConnection();

            Retrofit retrofit=new Retrofit.Builder()
                    .baseUrl(APIInterface.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            APIInterface apiInterface=retrofit.create(APIInterface.class);
            Call<Prayer> call=apiInterface.getPrayer(mLocation,method,school);
            call.enqueue(new Callback<Prayer>() {
                @RequiresApi(api = Build.VERSION_CODES.O)
                @Override
                public void onResponse(@NonNull Call<Prayer> call, @NonNull Response<Prayer> response) {
                    Prayer prayer=response.body();

                    String fajrMain=prayer.getData().getTimings().getFajr();
                    String dhuhrMain=prayer.getData().getTimings().getDhuhr();
                    String asrMain=prayer.getData().getTimings().getAsr();
                    String maghribMain=prayer.getData().getTimings().getMaghrib();
                    String ishaMain=prayer.getData().getTimings().getIsha();
                    String dateMain=prayer.getData().getDate().getReadable();
                    double locationMainLat=prayer.getData().getMeta().getLatitude();
                    double locationMainLong=prayer.getData().getMeta().getLongitude();

                    SimpleDateFormat sdfFajr = new SimpleDateFormat("HH:mm");
                    try{
                        Date date = sdfFajr.parse(fajrMain);
                        SimpleDateFormat sdf2Fajr = new SimpleDateFormat("hh:mm aa");
                        String fajrResult=sdf2Fajr.format(date);
                        fajrTv.setText(fajrResult);
                    }catch(ParseException e){
                        e.printStackTrace();
                    }

                    SimpleDateFormat sdfDhuhr = new SimpleDateFormat("HH:mm");
                    try{
                        Date date = sdfDhuhr.parse(dhuhrMain);
                        SimpleDateFormat sdf2Dhuhr = new SimpleDateFormat("hh:mm aa");
                        String dhuhrResult=sdf2Dhuhr.format(date);
                        dhuhrTv.setText(dhuhrResult);
                    }catch(ParseException e){
                        e.printStackTrace();
                    }

                    SimpleDateFormat sdfAsr = new SimpleDateFormat("HH:mm");
                    try{
                        Date date = sdfAsr.parse(asrMain);
                        SimpleDateFormat sdf2Asr = new SimpleDateFormat("hh:mm aa");
                        String AsrResult=sdf2Asr.format(date);
                        asrTv.setText(AsrResult);
                    }catch(ParseException e){
                        e.printStackTrace();
                    }

                    SimpleDateFormat sdfMaghrib = new SimpleDateFormat("HH:mm");
                    try{
                        Date date = sdfMaghrib.parse(maghribMain);
                        SimpleDateFormat sdf2Maghrib = new SimpleDateFormat("hh:mm aa");
                        String maghribResult=sdf2Maghrib.format(date);
                        maghribTv.setText(maghribResult);
                    }catch(ParseException e){
                        e.printStackTrace();
                    }

                    SimpleDateFormat sdfIsha = new SimpleDateFormat("HH:mm");
                    try{
                        Date date = sdfIsha.parse(ishaMain);
                        SimpleDateFormat sdf2Isha = new SimpleDateFormat("hh:mm aa");
                        String ishaResult=sdf2Isha.format(date);
                        ishaTv.setText(ishaResult);
                    }catch(ParseException e){
                        e.printStackTrace();
                    }

                    Geocoder geocoder = new Geocoder(PrayerActivity.this, Locale.getDefault());
                    String result = null;
                    try {
                        List < Address > addressList = geocoder.getFromLocation(locationMainLat, locationMainLong, 1);
                        if (addressList != null && addressList.size() != 0) {
                            Log.d("TAG", addressList.toString());
                            Address address = addressList.get(0);
                            StringBuilder sb = new StringBuilder();
                            for (int i = 0; i < address.getMaxAddressLineIndex(); i++) {
                                sb.append(address.getAddressLine(i));
                            }
                            sb.append(address.getLocality()).append(", ");
                            sb.append(address.getCountryName());
                            result = sb.toString();
                        }
                    } catch (IOException e) {
                        Log.e("Location Address Loader", "Unable connect to Geocode", e);
                    }

                    dateTv.setText(dateMain);
                    locationTv.setText(result);

                }

                @Override
                public void onFailure(Call<Prayer> call, Throwable t) {
                    //Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_LONG).show();
                }
            });
        }
    }

    private void openMethod() {
        dialog.setContentView(R.layout.method_dialog);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);

        RadioButton radioZero,radioOne,radioTwo,radioThree,radioFour,radioFive,radioSeven,radioEight,radioNine,radioTen,
                radioEleven,radioTwelve,radioThirteen,radioFourteen,radio0,radio1;
        RadioGroup radioGroup,radioGroup1;
        Button btnOK;
        final SharedPreferences sharedPreferences;
        final SharedPreferences sharedPreferences1;

        radioZero=dialog.findViewById(R.id.radioZero);
        radioOne=dialog.findViewById(R.id.radioOne);
        radioTwo=dialog.findViewById(R.id.radioTwo);
        radioThree=dialog.findViewById(R.id.radioThree);
        radioFour=dialog.findViewById(R.id.radioFour);
        radioFive=dialog.findViewById(R.id.radioFive);
        radioSeven=dialog.findViewById(R.id.radioSeven);
        radioEight=dialog.findViewById(R.id.radioEight);
        radioNine=dialog.findViewById(R.id.radioNine);
        radioTen=dialog.findViewById(R.id.radioTen);
        radioEleven=dialog.findViewById(R.id.radioEleven);
        radioTwelve=dialog.findViewById(R.id.radioTwelve);
        radioThirteen=dialog.findViewById(R.id.radioThirteen);
        radioFourteen=dialog.findViewById(R.id.radioFourteen);
        radioGroup=dialog.findViewById(R.id.radioGroup);
        radioGroup1=dialog.findViewById(R.id.radioGroup1);
        radio0=dialog.findViewById(R.id.radio0);
        radio1=dialog.findViewById(R.id.radio1);
        btnOK=dialog.findViewById(R.id.btnOK);

        sharedPreferences=getSharedPreferences("METHOD", Context.MODE_PRIVATE);
        int method = sharedPreferences.getInt("key",4);

        if (method==0){
            radioZero.setChecked(true);
        } else if (method == 1) {
            radioOne.setChecked(true);
        }else if (method == 2) {
            radioTwo.setChecked(true);
        }else if (method == 3) {
            radioThree.setChecked(true);
        }else if (method == 4) {
            radioFour.setChecked(true);
        }else if (method == 5) {
            radioFive.setChecked(true);
        }else if (method == 7) {
            radioSeven.setChecked(true);
        }else if (method == 8) {
            radioEight.setChecked(true);
        }else if (method == 9) {
            radioNine.setChecked(true);
        }else if (method == 10) {
            radioTen.setChecked(true);
        }else if (method == 11) {
            radioEleven.setChecked(true);
        }else if (method == 12) {
            radioTwelve.setChecked(true);
        }else if (method == 13) {
            radioThirteen.setChecked(true);
        }else if (method == 14) {
            radioFourteen.setChecked(true);
        }

        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            if (checkedId == R.id.radioZero) {
                editor.putInt("key", 0);
            } else if (checkedId == R.id.radioOne) {
                editor.putInt("key", 1);
            }else if (checkedId == R.id.radioTwo) {
                editor.putInt("key", 2);
            }else if (checkedId == R.id.radioThree) {
                editor.putInt("key", 3);
            }else if (checkedId == R.id.radioFour) {
                editor.putInt("key", 4);
            }else if (checkedId == R.id.radioFive) {
                editor.putInt("key", 5);
            }else if (checkedId == R.id.radioSeven) {
                editor.putInt("key", 7);
            }else if (checkedId == R.id.radioEight) {
                editor.putInt("key", 8);
            }else if (checkedId == R.id.radioNine) {
                editor.putInt("key", 9);
            }else if (checkedId == R.id.radioTen) {
                editor.putInt("key", 10);
            }else if (checkedId == R.id.radioEleven) {
                editor.putInt("key", 11);
            }else if (checkedId == R.id.radioTwelve) {
                editor.putInt("key", 12);
            }else if (checkedId == R.id.radioThirteen) {
                editor.putInt("key", 13);
            }else if (checkedId == R.id.radioFourteen) {
                editor.putInt("key", 14);
            }
            editor.apply();
        });

        sharedPreferences1=getSharedPreferences("SCHOOL", Context.MODE_PRIVATE);
        int school = sharedPreferences1.getInt("keyS",0);

        if (school==0){
            radio0.setChecked(true);
        } else if (school == 1) {
            radio1.setChecked(true);
        }

        radioGroup1.setOnCheckedChangeListener((group, checkedId) -> {
            SharedPreferences.Editor editor1 = sharedPreferences1.edit();
            if (checkedId == R.id.radio0) {
                editor1.putInt("keyS", 0);
            } else if (checkedId == R.id.radio1) {
                editor1.putInt("keyS", 1);
            }
            editor1.apply();
        });

        btnOK.setOnClickListener(v -> dialog.dismiss());

        dialog.show();
    }

    private void keyboard() {
        View v=PrayerActivity.this.getCurrentFocus();
        if (v!=null){
            InputMethodManager imm= (InputMethodManager) PrayerActivity.this.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(v.getWindowToken(),0);
        }
    }
}