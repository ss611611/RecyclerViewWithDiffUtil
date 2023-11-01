package com.example.recyclerviewwithdiffutil

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recyclerviewwithdiffutil.databinding.ActivityMainBinding
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var adapter: ItemAdapter
    private var items = ArrayList<Item>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        items.add(
            Item(
                UUID.randomUUID(),
                "https://images4.alphacoders.com/107/thumb-1920-1070725.jpg",
                "BMW",
                "X6 M (2019)"
            )
        )
        items.add(
            Item(
                UUID.randomUUID(),
                "https://images4.alphacoders.com/924/924850.jpg",
                "Mercedes",
                "GLE 400d (2021)"
            )
        )
        items.add(
            Item(
                UUID.randomUUID(),
                "https://carsguide-res.cloudinary.com/image/upload/f_auto,fl_lossy,q_auto,t_cg_hero_large/v1/editorial/2018-lamborghini-urus-suv-yellow-1200x800-%281%29.jpg",
                "Lamborghini",
                "Urus (2018)"
            )
        )
        items.add(
            Item(
                UUID.randomUUID(),
                "https://images.alphacoders.com/942/942538.jpg",
                "Tesla",
                "Model X (2020)"
            )
        )
        items.add(
            Item(
                UUID.randomUUID(),
                "https://images4.alphacoders.com/133/1337535.jpeg",
                "Ferrari",
                "LaFerrari (2014)"
            )
        )

        adapter = ItemAdapter(this, object : ItemAdapter.OnItemClickListener {
            override fun onItemLongClick(position: Int) {
                val dialog = AlertDialog.Builder(this@MainActivity)
                dialog.setMessage("Delete Item ${position + 1} ?")

                dialog.setNegativeButton("No") { p0, p1 ->
                    p0.cancel()
                }

                dialog.setPositiveButton("Yes") { p0, p1 ->
                    p0.cancel()

                    val item = items[position]

                    val list = ArrayList(items)
                    list.remove(item)
                    items = list
                    adapter.submitList(list)
                }
                dialog.show()
            }
        })

        adapter.submitList(items)

        binding.recyclerView.apply {
            val linearLayoutManager = LinearLayoutManager(this@MainActivity)
            linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
            layoutManager = linearLayoutManager
            hasFixedSize()
            adapter = this@MainActivity.adapter
        }
    }
}