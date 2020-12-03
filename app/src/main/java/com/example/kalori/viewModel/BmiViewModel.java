package com.example.kalori.viewModel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.kalori.BmiRepository;
import com.example.kalori.db.entity.BmiEntity;

import java.util.List;

public class BmiViewModel extends AndroidViewModel {
    private BmiRepository repository;
    private LiveData<List<BmiEntity>> allBmi;
    //Todo 12
    public BmiViewModel(Application application) {
        super(application);
        repository = new BmiRepository(application);
        allBmi = repository.getAllBmi();
    }
    public LiveData<List<BmiEntity>> getAllBmi() {
        return allBmi;
    };
    public void insertBmi(BmiEntity bmiEntity){
        repository.insert(bmiEntity);
    }
}
