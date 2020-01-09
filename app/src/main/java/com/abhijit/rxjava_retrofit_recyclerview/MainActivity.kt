package com.abhijit.rxjava_retrofit_recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.abhijit.rxjava_retrofit_recyclerview.adapter.PostAdapter
import com.abhijit.rxjava_retrofit_recyclerview.data.Post
import com.abhijit.rxjava_retrofit_recyclerview.data.PostComments
import com.abhijit.rxjava_retrofit_recyclerview.retrofit.RetrofitObject
import com.abhijit.rxjava_retrofit_recyclerview.retrofit.RetrofitService
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.functions.BiFunction
import io.reactivex.internal.operators.observable.ObservableReplay.observeOn
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var retrofitService: RetrofitService
    private val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofit = RetrofitObject.instance
        retrofitService = retrofit.create(RetrofitService::class.java)

        recyclerview.setHasFixedSize(true)
        recyclerview.layoutManager = LinearLayoutManager(this)


        //fetchData()
        fetchDataParallel()
    }

    private fun fetchData() {

        compositeDisposable.add(
            retrofitService.posts
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe({ posts ->
                    displayData(posts)
                })
        )

    }

    /**
     * Here i have called two services in parrallel
     * retrofitService.posts
     * retrofitService.postComments
     */
    private fun fetchDataParallel() {

        Observable.zip(
            retrofitService.posts,
            retrofitService.postComments,
            BiFunction<List<Post>, List<PostComments>, List<Post>> { post, postComments ->
                // here we get both the results at a time
                displayData(post)
                return@BiFunction filterUserWhoLovesBoth(post, postComments)
            })
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(getObserver())


    }

    private fun getObserver(): Observer<List<Post>> {

        return object : Observer<List<Post>> {
            override fun onComplete() {
                println("Complete ")//To change body of created functions use File | Settings | File Templates.
            }

            override fun onSubscribe(d: Disposable) {
                println("onSubscribe : ${d.isDisposed}")//To change body of created functions use File | Settings | File Templates.
            }

            override fun onNext(post: List<Post>) {
                println("onNext : $post")
            }

            override fun onError(e: Throwable) {
                println("onError : ${e.message}") //To change body of created functions use File | Settings | File Templates.
            }

        }


    }

    private fun filterUserWhoLovesBoth(
        post: List<Post>,
        postComments: List<PostComments>
    ): List<Post> {
        return emptyList()
    }

    private fun doSomethingWithIndividualResponse(it: Unit?) {

    }

    private fun combineResult(postResponse: Post, postCommentResponse: PostComments) {

        print(postResponse.body)

    }


    private fun displayData(posts: List<Post>?) {

        val adapter = PostAdapter(this, posts!!)
        recyclerview.adapter = adapter

    }
}
