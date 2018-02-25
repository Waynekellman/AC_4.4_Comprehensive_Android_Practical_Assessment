package com.nyc.ac_44_comprehensive_android_practical_assessment.network;

import com.nyc.ac_44_comprehensive_android_practical_assessment.model.Breed;
import com.nyc.ac_44_comprehensive_android_practical_assessment.model.BreedImgList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Wayne Kellman on 2/25/18.
 */

public interface BreedsApi {

    @GET("{breed}/images/random")
    Call<Breed> getBreed(@Path("breed") String breed);

    @GET("https://dog.ceo/api/breed/{breed}/images")
    Call<BreedImgList> getList(@Path("breed") String breed);
}
