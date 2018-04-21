package com.travel.hackroads.net;

import com.travel.hackroads.PlaceResult;
import com.travel.hackroads.Result;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;


public interface RoadsServiceApi {

    @POST("tourism/interface")
    Observable<Result> getRoads(@Body RequestBody body);

    @GET("tourism/search")
    Observable<PlaceResult> getPlaces();
}
