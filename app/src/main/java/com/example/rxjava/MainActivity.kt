package com.example.rxjava

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.rxjava.databinding.ActivityMainBinding
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.Single
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /*
        var t = getObservable().subscribeOn(Schedulers.io())
             .observeOn(AndroidSchedulers.mainThread())
             .subscribeWith(getObserver())
        */

         var x = MyApi().getPosts().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(getObserver())
    }
    /*
    fun getObservable(): Observable<PostResponse> {



        var api = MyApi()
        api.getPosts().enqueue(object : Callback<PostResponse> {
            override fun onResponse(call: Call<PostResponse>, response: Response<PostResponse>) {
                var str = ""
                for (item in response.body() as ArrayList<PostResponseItem>) {
                    str += "${item.id} : ${item.title}\n"
                }
                binding.textViewBody.text = str
            }

            override fun onFailure(call: Call<PostResponse>, t: Throwable) {
                Toast.makeText(applicationContext, t.message, Toast.LENGTH_SHORT).show()
            }
        })


        return Observable.just("1", "2", "3", "4", "5")
    }
   */

    fun getObserver(): SingleObserver<PostResponse> {
        return object : SingleObserver<PostResponse> {
            override fun onSubscribe(d: Disposable) {

            }

            override fun onSuccess(t: PostResponse) {
                var str = ""
                for(item in t){
                    str += "${item.id} : ${item.title}\n"
                }

                binding.textViewBody.text = str
            }

            override fun onError(e: Throwable) {}
        }
    }

}