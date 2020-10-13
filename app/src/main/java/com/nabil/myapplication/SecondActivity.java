package com.nabil.myapplication;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.MenuItem;
import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        recyclerView = findViewById(R.id.recyclerview);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ArrayList<Demo> demoList = new ArrayList<>();
        demoList.add(new Demo("سُبْحَانَ الله", "Glory to Allah"));
        demoList.add(new Demo("الحَمْدُ لله", "All Praise is for Allah"));
        demoList.add(new Demo("اللهُ أكْبَرُ", "Allah is the Greatest"));
        demoList.add(new Demo("لا إلهَ إلاّ اللّهُْ", "There is no God but Allah"));
        demoList.add(new Demo("أَسْتَغْفِرُ اللَّهَ","I seek Allah’s forgiveness"));
        demoList.add(new Demo("سُبْحَـانَ اللهِ وَبِحَمْـدِهَِ","How perfect Allah is and I praise Him"));
        demoList.add(new Demo("أَسْتَغْفِرُ اللَّهَ وَأَتُوبُ إِلَيْهِْ", "I ask for forgiveness from Allah"));
        demoList.add(new Demo("حَسْبُنَا اللَّهُ وَنِعْمَ الْوَكِيلُْ", "Allah suffices me, for He is the best disposer of affairs"));
        demoList.add(new Demo("اللَّهُمَّ أَجِرْنِي مِنَ النَّارِ", "O Allah, protect me from Hell"));
        demoList.add(new Demo("اللَّهُمَّ أَنْتَ السَّلاَمُ،وَمِنْكَ السَّلاَمُ،تَبَارَكْتَ يَـاذَاالْجَلاَلِ وَاْلإِكْرَامِ", "O Allah, You are peace, peace comes from You. Blessed are You O Possessor of Glory and Honour "));
        demoList.add(new Demo("أَسْتَغْفِرُ اللَّهَ الَّذِي لاَ إِلَهَ إِلاَّ هُوَ الْحَىُّ الْقَيُّومُ وَأَتُوبُ إِلَيْهِْ", "I ask pardon of Allah than Whom there is no deity, the Living, the eternal, and I turn to Him in repentance"));
        demoList.add(new Demo("سُبْحـانَ اللهِ وَبِحَمْـدِهِ عَدَدَ خَلْـقِه،وَرِضـا نَفْسِـه،وَزِنَـةَ عَـرْشِـه،وَمِـدادَ كَلِمـاتِـه", "Glory is to Allah and praise is to Him, by the multitude of his creation, by His Pleasure, by the weight of His Throne, and by the extent of His Words"));
        demoList.add(new Demo("أعوذ بكلمات الله التامات من شر ما خلق", "I seek refuge in the Perfect Words of Allah from the evil of that which He has created"));
        demoList.add(new Demo("لاَأِلَاهَ اِلاَّ اللّهُ وَحْدَهُ لاَ شَرِيكَ لَهُ لَهُ الْمُلْكُ وَلَهُ الْحَمْدُ وَهُوَ عَلىَ كُلِّ شَيْءِِقَدِيرُ", "There is no god but Allah alone, He has no partners, to Him belongs dominion and to Him belongs praises, and He has power over all things"));
        demoList.add(new Demo("اَللّٰهُمَّ أَعِنّ۪ي عَلٰى ذِكْرِكَ وَشُكْرِكَ وَحُسْنِ عِبَادَتِكَ", "O Allah! Help me to remember You and recite Your name, to thank for Your bounties, and to worship You as befits You!"));
        demoList.add(new Demo("لَا حَوْلَ وَلَا قُوَّةَ إِلَّا بِاللَّهِ", "There is no capability nor is there any power except with Allah"));
        demoList.add(new Demo("رَضِيْتُ بَاللّٰهِ رَبًّاوَّبِالإسْلَامِ دِيْنًاوَّبِمُحَمَّدٍ نَّبِيًّاْ", "I am pleased with Allah as Lord, with Islam as the religion and with Muhammad (saw) as the Prophet"));
        demoList.add(new Demo("بِسْمِ اللَّهِ الَّذِیْ لَا یَضُرُّ مَعَ اسْمِهِ شَیْ ءٌ فِیْ الْاَرْضِ وَلَا فِی السَّمَآءِ وَھُوَ السَّمِیْعُ الْعَلِیْمُْ", "In the name of Allah, with Whose name nothing can cause harm on earth or in the heavens and He is The All-Hearer, The All-Knowing"));
        adapter = new MyAdapter(this, demoList);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        finish();
        startActivity(new Intent(SecondActivity.this, MainActivity.class));
        Animatoo.animateSlideRight(SecondActivity.this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
            startActivity(new Intent(SecondActivity.this, MainActivity.class));
            Animatoo.animateSlideRight(SecondActivity.this);
        }
        return super.onOptionsItemSelected(item);
    }
}
