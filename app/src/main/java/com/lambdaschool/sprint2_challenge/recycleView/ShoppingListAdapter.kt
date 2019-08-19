package com.lambdaschool.sprint2_challenge.recycleView

import android.app.Activity
import android.app.ActivityOptions
import android.content.Context
import android.content.Context.CONTEXT_IGNORE_SECURITY
import android.content.Intent
import android.support.annotation.MainThread
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.content.ContextCompat
import android.support.v4.content.ContextCompat.startActivity
import android.support.v4.view.ViewCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.lambdaschool.sprint2_challenge.Activities.FullscreenActivity
import com.lambdaschool.sprint2_challenge.Activities.FullscreenActivity.Companion.key
import com.lambdaschool.sprint2_challenge.Activities.MainActivity


import com.lambdaschool.sprint2_challenge.Activities.MainActivity.Companion.drawableRes

import com.lambdaschool.sprint2_challenge.R
import com.lambdaschool.sprint2_challenge.model.GroceryItems
import kotlinx.android.synthetic.main.grocery_items_layout.view.*
import java.io.Serializable

class ShoppingListAdapter(val shoppingList: MutableList<GroceryItems>) : RecyclerView.Adapter<ShoppingListAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewGroup = LayoutInflater.from(parent.context).inflate(R.layout.grocery_items_layout, parent, false)
        return ViewHolder(viewGroup)
    }

    override fun getItemCount(): Int {
        return shoppingList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.nameTv.text = shoppingList[position].kind
        holder.imageIv.setImageResource(shoppingList[position].resourceId)

        val currentSelection = shoppingList[position]
        holder.bindModel(currentSelection)

        holder.parentView.setOnClickListener {view ->
            //ViewCompat.setTransitionName(holder.imageIv, currentSelection.kind)
            MainActivity.clickIntentHandler()


            if (currentSelection.ordered) {
                currentSelection.ordered = false
                notifyItemChanged(position)

            } else {
                currentSelection.ordered = true
                notifyItemChanged(position)
                drawableRes=currentSelection.resourceId
            }

        }

        }
    /*fun clickIntentHandler (context: Context, pos:Int, groceryItems: GroceryItems, im:ImageView){
        var intent = Intent(context, FullscreenActivity::class.java)
        intent.putExtra(MainActivity.PASSED_ITEM, groceryItems as Serializable)
        intent.putExtra(MainActivity.IMAGE_TRANSITION_NAME, ViewCompat.getTransitionName(im))

        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(context, im, ViewCompat.getTransitionName(im).toString())

        context.startActivity(intent, options.toBundle())

    }*/

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageIv: ImageView = view.iv_item_drawable
        val nameTv: TextView = view.tv_item_name
        val parentView: LinearLayout = view.llcardview

        fun bindModel(currentSelection: GroceryItems) {
            if (currentSelection.ordered) {
                parentView.setBackgroundColor(ContextCompat.getColor(parentView.context, R.color.bought))
            } else {
                parentView.setBackgroundColor(ContextCompat.getColor(parentView.context, R.color.unbought))
            }
        }
    }


}


