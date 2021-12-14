package com.Hackathon.bialgenieapp.Fragments;

import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.Hackathon.bialgenieapp.Models.ArDepModel;
import com.Hackathon.bialgenieapp.Queries.ArrDepQueryUtils;
import com.Hackathon.bialgenieapp.R;
import com.Hackathon.bialgenieapp.databinding.FragmentFlighsArrivalBinding;

import java.net.URL;
import java.util.ArrayList;


public class FlightsArrival extends Fragment {

    public FlightsArrival() {
        // Required empty public constructor
    }

    FragmentFlighsArrivalBinding binding;
    private String Sample_Json_query = "https://api.flightstats.com/flex/flightstatus/rest/v2/json/airport/status/BLR/arr/2021/12/14/15?appId=3d44123a&appKey=ce3c12a840540d7528f086a02ccd3f2a&utc=true&numHours=5&maxFlights=5";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentFlighsArrivalBinding.inflate(getLayoutInflater());

        CourseAsyncTask task = new CourseAsyncTask();
        task.execute();

        return binding.getRoot();
    }

    protected void updateUi(ArrayList<ArDepModel> booksInfos) {

       // bookList = booksInfos;

        /*CoursesItemAdapter sliderAdapter = new CoursesItemAdapter(booksInfos, binding.recyclerView, getApplicationContext(), R.layout.courses_item_specific, 2);
        binding.recyclerView.setAdapter(sliderAdapter);
        binding.recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));*/

        binding.textView3.setText(booksInfos.get(0).getArrivalAirport()+"");

    }

    private class CourseAsyncTask extends AsyncTask<URL, Void, ArrayList<ArDepModel>> {
        @RequiresApi(api = Build.VERSION_CODES.O)
        @Override
        protected ArrayList<ArDepModel> doInBackground(URL... urls) {
            ArrayList<ArDepModel> event = ArrDepQueryUtils.fetchFlightsData(Sample_Json_query);            //also we can use  urls[0]
            Log.i("CategoryCoursesActivity", Sample_Json_query);
            return event;
        }

        @Override
        protected void onPostExecute(ArrayList<ArDepModel> event) {

          //  binding.progressSpineer.setVisibility(View.GONE);

            if (event == null) {
                //  binding.emptyNoBook.setText("No Books Found");
                return;
            }

            updateUi(event);

        }

    }

}