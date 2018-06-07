package com.example.kamil.birthdaywish;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = MainActivity.class.getCanonicalName();
    private Realm realm;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    AdapterWishes adapterWishes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        realm = Realm.getDefaultInstance();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        adapterWishes = new AdapterWishes(new ArrayList<Wish>(), realm);
        recyclerView.setAdapter(adapterWishes);
    }

    @OnClick(R.id.fabAdd)
    public void onClickFab()
    {
        Intent intent = new Intent(MainActivity.this, WishAddActivity.class);
        startActivity(intent);
    }

    @Override protected void onResume()
    {
        super.onResume();
        RealmResults<Wish> wishes = realm.where(Wish.class).findAll();
        if (wishes.size() > 0) {
            for (Wish wish : wishes) {
                if (!adapterWishes.wishes.contains(wish)) {
                    adapterWishes.wishes.add(wish);
                }
            }
            adapterWishes.notifyDataSetChanged();
        }
        Log.d(TAG, "onResume: ");
    }

}
