package space.beka.valyuta_kursi

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.widget.addTextChangedListener
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.schedulers.Schedulers.io
import space.beka.valyuta_kursi.databinding.ActivityMainBinding
import java.net.HttpURLConnection
import java.net.URL
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    //    private var info = ""
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(binding.root)
////        println(urlToString(URL("http://cbu.uz/uzc/arkhiv-kursov-valut/json/")))
//
//        val myTask = MyTask()
//        myTask.execute()
//    }
//
//    fun urlToString(url: URL): String {
//        val stringBuilder = StringBuilder()
//
//        val httpURLConnection = url.openConnection() as HttpURLConnection
//        httpURLConnection.connect()
//        val inputStream = httpURLConnection.inputStream
//        val bufferReader = inputStream.bufferedReader()
//        val list = bufferReader.readLines()
//        for (s in list) {
//            stringBuilder.append(s)
//
//        }
//        return stringBuilder.toString()
//
//    }
//
//    inner class MyTask : AsyncTask<Void, Void, Void>() {
//        override fun onPostExecute(result: Void?) {
//            super.onPostExecute(result)
//            binding.myProgress.visibility = View.GONE
//            binding.tvInfo.text = info
//        }
//
//        override fun doInBackground(vararg p0: Void?): Void? {
//            info = urlToString(URL("http://cbu.uz/uzc/arkhiv-kursov-valut/json/"))
//            return null
//        }
//
//        override fun onPreExecute() {
//            super.onPreExecute()
//            binding.myProgress.visibility = View.VISIBLE
//        }
//    }
    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

ozgartirishniEshitish().subscribeOn(Schedulers.io())
    .observeOn(AndroidSchedulers.mainThread())
//    .map {                        //map kelayotgan malumotni ozgartirib beradi
//        it+" Ism"            // Text qoshadi
////        it+it            //ikki marta chiqaradi
//    }

//    .filter {
//        it.length== 4             // malumotni filtledi va shart berish uchun
//        it.length>= 4
//        it.length<= 4
//    }

    .debounce(4L , TimeUnit.SECONDS)
    .subscribe{
        Log.d(TAG, "onCreate: Bizning Rx Kotlin ")            //4 sekunddan keyon ishla degan shart operatori . Faqat background uchun viewlar uchun emas xato beradi view lar uchun handler yoki boshqasini ishlatish kerak
    }
    }

    fun ozgartirishniEshitish(): Observable<String> {
        return Observable.create<String> { emitter ->
            binding.editQuery.addTextChangedListener {
                emitter.onNext(binding.editQuery.text.toString())
            }
        }
    }
}

