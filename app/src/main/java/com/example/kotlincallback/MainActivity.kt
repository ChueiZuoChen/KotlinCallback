package com.example.kotlincallback

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlincallback.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btn.setOnClickListener {
            SomeFunction(object : SomeCallback {
                override fun onSuccess(data: String) {
                    Toast.makeText(baseContext, data, Toast.LENGTH_SHORT).show()
                }

                override fun onFailure(error: String) {
                    Toast.makeText(baseContext, error, Toast.LENGTH_SHORT).show()
                }
            })
        }
    }
}
/*
* 程式A傳入介面可是還是沒有實做介面
* */
fun SomeFunction(someCallback: SomeCallback) {
    val w = "123"
    val n = "2"
    if (w.contains(n)) {
        someCallback.onSuccess("$w has $n")
    } else {
        someCallback.onFailure("Not contain")
    }
}

/*
* 介面 -> 程式A(介面) -> 程式B呼叫程式A然後終於實做介面
* 程式A的結果 可以在程式B得到
* */
interface SomeCallback {
    fun onSuccess(data: String)
    fun onFailure(error: String)
}