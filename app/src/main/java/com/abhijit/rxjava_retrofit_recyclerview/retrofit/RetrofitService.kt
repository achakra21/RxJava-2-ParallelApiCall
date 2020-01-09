package com.abhijit.rxjava_retrofit_recyclerview.retrofit

import com.abhijit.rxjava_retrofit_recyclerview.data.Post
import com.abhijit.rxjava_retrofit_recyclerview.data.PostComments
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET

interface RetrofitService {

    @get:GET("posts")
    val posts:Observable<List<Post>>
    @get:GET("posts/1/comments")
    val postComments:Observable<List<PostComments>>


}