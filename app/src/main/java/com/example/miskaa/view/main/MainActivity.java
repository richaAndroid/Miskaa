package com.example.miskaa.view.main;

import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.miskaa.R;
import com.example.miskaa.databinding.ActivityMainBinding;
import com.example.miskaa.model.CountryPojo;
import com.example.miskaa.view.main.adapter.MainAdapter;
import com.example.miskaa.viewmodel.MainViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int TIME_INTERVAL = 2000;
    private long mBackPressed;
    private MainViewModel viewModel;
    AlertDialog.Builder builder;
    AlertDialog dialog = null;

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        setClickListener();
        setViewModelObserver();
        fetchAndPopulateData();

    }

    private void setClickListener() {
        binding.ivBack.setOnClickListener(this);
        binding.ivDelete.setOnClickListener(this);
    }

    private void checkForStoredCountryInDatabase() {
        viewModel.checkForStoredCountryInDatabase();
    }

    private void fetchAndPopulateData() {
        startProgress();
        if (isNetworkConnected()) {
            viewModel.getAllCountriesFromNetwork();
        } else {
            viewModel.getAllCountriesFromDatabase();
        }
    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return cm.getActiveNetwork() != null && cm.getActiveNetworkInfo().isConnected();
        }
        return true;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == binding.ivBack.getId()) {
            onBackPressed();
        } else if (v.getId() == binding.ivDelete.getId()) {
            checkForStoredCountryInDatabase();
        }
    }

    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() - mBackPressed > TIME_INTERVAL) {
            Toast.makeText(getBaseContext(), getString(R.string.press_again), Toast.LENGTH_SHORT).show();
            mBackPressed = System.currentTimeMillis();
        } else {
            super.onBackPressed();
        }
    }

    private void deleteDataFromDatabase() {
        viewModel.deleteAllCountriesFromDatabase();
    }

    private void handleDeleteClick(List<CountryPojo> list) {
        if (list == null || list.isEmpty()) {
            showNoDataFoundMsg(getString(R.string.no_data_to_delete));
        } else {
            AlertDialog dialog = new AlertDialog.Builder(MainActivity.this)
                    .setTitle(getString(R.string.rest_country))
                    .setMessage(getString(R.string.delete_confirm_msg))
                    .setCancelable(true)
                    .setPositiveButton(getString(R.string.yes), new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            deleteDataFromDatabase();
                            dialog.cancel();
                        }
                    })
                    .setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    })
                    .create();
            dialog.show();
        }
    }

    private void setViewModelObserver() {
        viewModel.countryList.observe(this, new Observer<List<CountryPojo>>() {
            @Override
            public void onChanged(List<CountryPojo> countryPojos) {
               stopProgress();
                if (countryPojos == null || countryPojos.isEmpty()) {
                    showNoDataFoundMsg(getString(R.string.no_data));
                } else {
                    viewModel.insertCountriesInDB(countryPojos);
                    binding.clData.setVisibility(View.VISIBLE);
                    binding.rvCountries.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                    binding.rvCountries.setAdapter(new MainAdapter(countryPojos, MainActivity.this));
                }
            }
        });

        viewModel.errorMsg.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String msg) {
                Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });

        viewModel.countryListStoredInDb.observe(this, new Observer<List<CountryPojo>>() {
            @Override
            public void onChanged(List<CountryPojo> countryPojos) {
                handleDeleteClick(countryPojos);
            }
        });
    }

    private void showNoDataFoundMsg(String msg) {
        AlertDialog dialog = new AlertDialog.Builder(MainActivity.this)
                .setTitle(getString(R.string.rest_country))
                .setMessage(msg)
                .setCancelable(true)
                .setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                })
                .create();
        dialog.show();
    }

    private void  prepareProgressDialog() {
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE) ;
        View  view = inflater.inflate(R.layout.progress, null);
        builder = new AlertDialog.Builder(this);
        builder.setView(view);
        builder.setCancelable(false);
    }

    private void startProgress() {
        if (null == dialog) {
            prepareProgressDialog();
            dialog = builder.create();
        }
        dialog.show();
    }

    private void stopProgress() {
        dialog.dismiss();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        viewModel.clearDB();
    }
}