package com.example.myshop

import android.app.Activity
import android.net.DnsResolver
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.InputQueue.Callback
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.example.myshop.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()
        fetchProducts()
    }

    fun fetchProducts(){
        val apiClient = ApiClient.buildClient(ApiInterface::class.java)
        val request = apiClient.getProducts()
        request.enqueue(object : retrofit2.Callback<ProductResponse> {


            override fun onFailure(call: Call<ProductResponse>,t:Throwable){
                Toast.makeText(baseContext,t.message,Toast.LENGTH_LONG).show()
            }

             override  fun onResponse(
                 call: Call<ProductResponse>, response: Response<ProductResponse>){



                  if (response.isSuccessful){
                     val product =response.body()?.products
                     var productAdapter= ProductAdapter (product?: emptyList())
                     binding.rvProducts.layoutManager =GridLayoutManager(this@MainActivity,2)
                      binding.rvProducts.adapter = productAdapter
                      Toast.makeText(baseContext,
                      "Fetched ${product?.size} products",Toast.LENGTH_SHORT).show()

                 }
                 else{
                     Toast.makeText(baseContext,response.errorBody()?.string(),Toast.LENGTH_LONG).show()
                 }
             }


        })
    }
}

