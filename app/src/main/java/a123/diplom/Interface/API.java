package a123.diplom.Interface;


import a123.diplom.Model.Commission.JSONcomm;
import a123.diplom.Model.Count.CountMember;
import a123.diplom.Model.Count.CountRequest;
import a123.diplom.Model.Group.JSON;
import a123.diplom.Model.Login.Registration;
import a123.diplom.Model.Login.Request;
import a123.diplom.Model.Login.SessionReg;
import a123.diplom.Model.Login.SessionRequest;
import a123.diplom.Model.Student.JSONstud;
import a123.diplom.Model.Sum.Sum;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface API {
    @GET("/api/{savedKey}/groups.json")
    Call<JSON> getAllGroups(@Path("savedKey") String savedKey);
    @GET("/api/{savedKey}/students")
    Call<JSONstud> getAllStudents(@Path("savedKey") String savedKey,@Query("id") String id_group);
    @POST("/api/session.json")
    Call<Registration> getReg(@Body Request request);
    @POST("/api/session.json")
    Call<SessionReg> getSession(@Body SessionRequest sessionRequest);
    @POST("/api/{savedKey}/valuation.json")
    Call<CountMember> getCount(@Path("savedKey") String savedKey, @Body CountRequest countRequest);
    @GET("/api/{savedKey}/valuations.json")
    Call<JSONcomm> getComm(@Path("savedKey") String savedKey,@Query("student") String id_std);
    @PUT("/api/{savedKey}/students/{id}")
    Call<Sum> getPutCoinsStd(@Path("savedKey") String savedKey, @Path("id") String id_group);
    @DELETE("/api/{savedKey}/students/{id}")
    Call<JSONstud> getDeleteCoinsStd(@Path("savedKey") String savedKey,@Path("id") String id_group);
    @DELETE("/api/session/{savedKey}")
    Call<SessionReg> getDeleteKey(@Path("savedKey") String savedKey);

    class Converter{
        public static API getConvert() {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://verso.students.d.rnds.pro")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            API service = retrofit.create(API.class);
            return service;
        }
    }
}
