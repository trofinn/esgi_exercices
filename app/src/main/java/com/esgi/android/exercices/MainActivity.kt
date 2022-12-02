package com.esgi.android.exercices

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContentView(R.layout.activity_main)
        val productCell = ProductCell(
            "Petits pois et carrotes",
            "Cassegrain",
            "234",
            "https://static.openfoodfacts.org/images/products/308/368/008/5304/front_fr.7.400.jpg"
        );
        val products = listOf(productCell)
        val adapter= ListAdapter(products, object : OnProductListener {
                override fun onClicked(product: ProductCell, position: Int) {
                    Toast.makeText(
                        this@MainActivity,
                        "Product $position clicked",
                        Toast.LENGTH_SHORT).show()
                }
            })
        }
    }


    class ListAdapter(private val products : List<ProductCell>,
                      private val listener : OnProductListener) : RecyclerView.Adapter<ProductViewHolder>() {


        override fun getItemCount(): Int {
            return products.size
        }

        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): ProductViewHolder {
            return ProductViewHolder(
                LayoutInflater.from(parent.context)
                .inflate(R.layout.list_item_cell, parent, false))
        }

        override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
            val product = products[position];
            holder.updateProduct(product)

            holder.itemView.setOnClickListener {
                listener.onClicked(product, position)
            }
        }
    }
}

interface OnProductListener {
    fun onClicked(product: ProductCell, position: Int)
}

class ProductViewHolder(v : View) : RecyclerView.ViewHolder(v) {
    private val productName = v.findViewById<TextView>(R.id.name);
    private val productImage = v.findViewById<ImageView>(R.id.image);

    fun updateProduct(product : ProductCell) {
        productName.text = product.nom;
        Glide.with(itemView).load(product.image).into(productImage);
    }
}
    }
}

data class ProductCell(val nom : String, val marque : String, val kcal : String, val image : String) {}