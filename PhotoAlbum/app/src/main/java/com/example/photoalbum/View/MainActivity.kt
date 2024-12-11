package com.example.photoalbum.View

import android.content.ClipData.Item
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.photoalbum.Adapter.MyImagesAdapter
import com.example.photoalbum.R
import com.example.photoalbum.ViewModel.MyImagesViewModel
import com.example.photoalbum.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var myImagesViewModel : MyImagesViewModel
    lateinit var mainBinding : ActivityMainBinding
    lateinit var myImagesAdapter: MyImagesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        myImagesViewModel = ViewModelProvider(this)[MyImagesViewModel::class.java]

        mainBinding.recyclerView.layoutManager = LinearLayoutManager(this)
        myImagesAdapter = MyImagesAdapter(this)
        mainBinding.recyclerView.adapter = myImagesAdapter

        // observer -> comes from livedata and observes changes
        myImagesViewModel.getAllImages().observe(this, Observer { images->
            //update UI
            myImagesAdapter.setImage(images)
        })

        mainBinding.floatAB.setOnClickListener {
            val intent = Intent(this, AddImageActivity::class.java)
            startActivity(intent)
        }

        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                TODO("Not yet implemented")
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                // good if a dialog box shows up for the user before the deletion
                myImagesViewModel.delete(myImagesAdapter.returnItemAtGivenPosition(viewHolder.adapterPosition))
            }
        }).attachToRecyclerView(mainBinding.recyclerView)

    }
}