package com.example.doctor


import com.google.gson.GsonBuilder
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface ServerInterface {

    @POST("signup")
    fun signUp(@Body user:Newuser):Observable<Newuser>

    @POST("newspecs")
    fun addNewSpecs(@Body spec: NewSpec):Observable<NewSpec>

    @POST("con")
    fun conTest(@Body test:Tester):Observable<Tester>

    @POST("food")
    fun addFood(@Body fat:NewFood):Observable<NewFood>

    @POST("exercise")
    fun addExercise(@Body exercise:NewExercise):Observable<NewExercise>

    @POST("medicine")
    fun addMedicine(@Body medicine:NewMedicine):Observable<NewMedicine>

    @GET("specs/solo@gmail.com")
    fun getSpecs():Observable<Response.Result>

    @GET("exercise/{id}")
    fun getExercise(@Path("id") id:String):Observable<Response.ExerciseResult>

    @GET("food/{id}")
    fun getFood(@Path("id")id:String):Observable<Response.FoodResult>

    @GET("medicine/{id")
    fun getMedicine(@Path("id")id:String):Observable<Response.MedicineResult>

    companion object {
        fun create():ServerInterface{
            val gson = GsonBuilder()
                .setLenient()
                .create()

            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(
                    RxJava2CallAdapterFactory.create()
                )
                .addConverterFactory(
                    GsonConverterFactory.create(gson)
                )
                .baseUrl("http://192.168.43.215:4001/")
                .build()
            return retrofit.create(ServerInterface::class.java)
        }
    }
}
