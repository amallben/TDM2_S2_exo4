package com.example.retrofitliste

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {
    private var textViewResult: TextView? = null
    lateinit var jsonPlaceHolderApi : JsonPlaceHolderApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView_main.layoutManager = LinearLayoutManager(this)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
         jsonPlaceHolderApi = retrofit.create(
            JsonPlaceHolderApi::class.java
        )
       getTache()
    }

    fun getTache(){
        val call: Call<List<TodoItem?>?>? = jsonPlaceHolderApi.posts
        call!!.enqueue(object : Callback<List<TodoItem?>?> {
            override fun onResponse(
                call: Call<List<TodoItem?>?>?,
                response: Response<List<TodoItem?>?>
            ) {
                if (!response.isSuccessful()) {
                    println("CODE:" + response.code())
                    return
                }
                val todos: List<TodoItem> = response.body() as List<TodoItem>
                runOnUiThread {
                    recyclerView_main.adapter = MainAdapter(todos)
                }
//                for (todo in todos) {
//                    var content = ""
//                    content += "ID: " + todo.id + "\n"
//                    content += "User ID: " + todo.userId.toString() + "\n"
//                    content += "Title: " + todo.title + "\n"
//                    content += "Completed: " + todo.completed + "\n\n"
//                    text_view_result.append(content)
//                }
            }

            override fun onFailure(
                call: Call<List<TodoItem?>?>?,
                t: Throwable
            ) {
                println("Failed to execute request")
            }


        })
    }
}
