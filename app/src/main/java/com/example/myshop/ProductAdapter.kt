package com.example.myshop


import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.inputmethod.InputBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.myshop.databinding.ProductdisplayBinding
import com.squareup.picasso.Picasso

class ProductAdapter(var productList: List<Product>):RecyclerView.Adapter<ProductAdapter.ProductViewHolder>()  {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding =
            ProductdisplayBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        var currentProduct = productList[position]
        holder.bind(currentProduct)
    }


    inner class ProductViewHolder(val binding: ProductdisplayBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(currentProduct: Product) {
            binding.apply {
                tvid.text = currentProduct.id.toString()
                tvTitle.text = currentProduct.title
                tvDescription.text = currentProduct.description



                Picasso.get()
                    .load(currentProduct.thumbnail)
                    .resize(250, 250)
                    .into(binding.ivproduct)


            }
        }
    }
}