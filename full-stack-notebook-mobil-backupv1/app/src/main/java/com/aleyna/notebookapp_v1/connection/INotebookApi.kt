package com.aleyna.notebookapp_v1.connection

import android.provider.ContactsContract.CommonDataKinds.Note
import com.aleyna.notebookapp_v1.models.NotesModel
import com.aleyna.notebookapp_v1.models.NotesResponseModel
import com.aleyna.notebookapp_v1.models.UserInfos
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path

interface INotebookApi {
    @POST("signUp")
    suspend fun signUp(@Body userInfos: UserInfos): Response<Boolean> //suspend for async

    @GET("user")
    suspend fun getUserDetails(): Response<Long>

    @Headers("Content-Type: application/x-www-form-urlencoded")
    @FormUrlEncoded
    @POST("login")
    suspend fun login(
        @Field("username") username: String,
        @Field("password") password: String
    ): Response<String> //suspend for async

    @GET("user/notepage")
    suspend fun getNotes() : Response<List<NotesResponseModel>>

    @POST("user/notepage")
    suspend fun addNote(@Body notesModel: NotesModel) : Response<Boolean>

    @GET("user/notepage/{note_id}")
    suspend fun deleteNote(@Path("note_id") note_id:Long ): Response<String>
}