package com.lambdaschool.sprint2_challenge

import android.content.Intent
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.transition.Explode
import android.util.Log
import android.view.View
import android.view.Window
import com.lambdaschool.sprint2_challenge.model.GroceryItems
import com.lambdaschool.sprint2_challenge.model.ShoppingItemRepository
import com.lambdaschool.sprint2_challenge.recycleView.ShoppingListAdapter
import kotlinx.android.synthetic.main.activity_fullscreen.*
import kotlinx.android.synthetic.main.activity_main.*
import android.support.v7.widget.LinearLayoutManager as LinearLayoutManager1
import kotlinx.android.synthetic.main.activity_main.cl as cl1

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
class FullscreenActivity : AppCompatActivity() {

    companion object {
        const val key = "key"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fullscreen)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)






        val item = intent.getSerializableExtra(key) as GroceryItems
        tv_item_name.text = item.kind
        iv_item_drawable.setImageDrawable(ContextCompat.getDrawable(this,item.resourceId))


       // val intGet = intent.getIntExtra("action", R.drawable.cake)
        cl.setBackgroundResource(item.resourceId)


        // code from this weekend to use same adapter to display something like a checkout bin

        /*var checkoutList = mutableListOf<GroceryItems>()
        for (i in 0 until ShoppingItemRepository.shoppingList.size) {
            if (ShoppingItemRepository.shoppingList[i].ordered) {
                checkoutList.add(ShoppingItemRepository.shoppingList[i])
            }
        }
        // Set up the user interaction to manually show or hide the system UI.
        recycle_view_fullscreen.setHasFixedSize(true)
        val manager2 = LinearLayoutManager1(this, 1, false)
        val adapter2 = ShoppingListAdapter(checkoutList)
        recycle_view_fullscreen.layoutManager = manager2
        recycle_view_fullscreen.adapter = adapter2*/


    }

}