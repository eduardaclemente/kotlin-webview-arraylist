package com.aula.httpappb

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.widget.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    var nova: ArrayList<String> = arrayListOf<String>("")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val webView: WebView = findViewById(R.id.webView)
        webView.settings.javaScriptEnabled = true



        val btExecute = findViewById<Button>(R.id.btExecute)
        btExecute.setOnClickListener{
            Toast.makeText(this,"Botao acionado", Toast.LENGTH_SHORT)
            //webView.loadUrl("http://themekit.frontendmatter.com/dist/themes/social-1/profile.html")
            webView.loadUrl("http://themekit.frontendmatter.com/dist/themes/social-1/profile.html")
        }

        getData()

    }

    fun lista() {
        val arrayAdapter: ArrayAdapter<*>

        var mListView = findViewById<ListView>(R.id.userList);

        arrayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, nova)

        mListView.adapter = arrayAdapter

        mListView.onItemClickListener = object : AdapterView.OnItemClickListener {
            override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                print("teste")

                Toast.makeText(applicationContext, "selecionado" + nova[p2], Toast.LENGTH_SHORT).show()

                openSecondActivity(nova[p2])
            }
        }
    }

    fun openSecondActivity(item: String) {
        val intent = Intent(this, DetailActivity::class.java).apply {
            putExtra("item-selecionado", item)
        }
        startActivity(intent)
    }

    fun getData() {
        val retrofitClient = NetworkUtils
            .getRetrofitInstance("https://jsonplaceholder.typicode.com")
        val endpoint = retrofitClient.create(Endpoint::class.java)
        val callback = endpoint.getPosts()
        callback.enqueue(object : Callback<List<Posts>> {
            override fun onFailure(call: Call<List<Posts>>, t: Throwable) {
                Toast.makeText(baseContext, t.message, Toast.LENGTH_SHORT).show()
            }
            override fun onResponse(call: Call<List<Posts>>, response: Response<List<Posts>>) {

                response.body()?.forEach {
                    nova.add(it.body)
                }
                lista()
            }
        })
    }
}