package com.example.epicassignment;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CityService {
    @GET("location/search/?query=ba")
    Call<List<CityModel>> getCities();
}
