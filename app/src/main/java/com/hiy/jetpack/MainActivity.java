package com.hiy.jetpack;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.lifecycle.ViewModelStore;

import java.util.Random;

public class MainActivity extends AppCompatActivity {


    private MutableLiveData<String> liveData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getLifecycle().addObserver(new LifecycleEventObserver() {
            @Override
            public void onStateChanged(@NonNull LifecycleOwner source, @NonNull Lifecycle.Event event) {

            }
        });

        TextView tvContent = findViewById(R.id.content_tv);

        BaseModel model = ViewModelProviders.of(this).get(BaseModel.class);
        liveData = new MutableLiveData<>(model.userTag);
        liveData.setValue("11");
        tvContent.setText(model.userTag);
        tvContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                model.userTag = "Hello BaseModel" + new Random().nextInt();
//                tvContent.setText(model.userTag);
                liveData.setValue();
                liveData.postValue();
            }
        });

        liveData.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                tvContent.setText(s);
            }
        });
    }

    @NonNull
    @Override
    public Lifecycle getLifecycle() {
        return super.getLifecycle();
    }


    @NonNull
    @Override
    public ViewModelStore getViewModelStore() {
        return super.getViewModelStore();
    }
}