package com.lambdaschool.sprint2_challenge.Activities

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.PendingIntent.getActivity
import android.content.Context
import android.content.Intent
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.app.NotificationCompat
import android.support.v4.view.ViewCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.transition.Fade
import android.util.Log
import android.view.Window
import android.widget.ImageView
import com.lambdaschool.sprint2_challenge.model.GroceryItems

import com.lambdaschool.sprint2_challenge.model.ShoppingItemRepository.Companion.createShoppingList
import com.lambdaschool.sprint2_challenge.model.ShoppingItemRepository.Companion.shoppingList
import com.lambdaschool.sprint2_challenge.recycleView.ShoppingListAdapter
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    companion object {
        const val NOTIFICATION_ID = 1
        const val IMAGE_TRANSITION_NAME = "2"
        const val PASSED_ITEM ="3"
        var finalstr = ""
        var drawableRes:Int = 0
        const val transitionName = "yus"
        val acivity =
        fun clickIntentHandler (context: Context, pos:Int, groceryItems: GroceryItems, im: ImageView) {
            var intent = Intent(context, FullscreenActivity::class.java)
            intent.putExtra(PASSED_ITEM, groceryItems)
            intent.putExtra(IMAGE_TRANSITION_NAME, ViewCompat.getTransitionName(im))

            val options = ActivityOptionsCompat.makeSceneTransitionAnimation(this, im, ViewCompat.getTransitionName(im).toString())

            context.startActivity(intent, options.toBundle())
    }
        }
    //going to try some high level notes here like i saw vivek do last night, seemed good
    //step 1 object class (GroceryItems) to define what an individual items qualities will be
    //2: create a class to build a mutuable list of GroceryItems out of the two arrays provided in shoppingitemconstants
    // ShoppingItemRepository (my understanding of the word respository also grew last night, Vivek is a good teacher.)
    //2.X: call createShoppingList() onCreate in mainAcitivty to populate the mutable list
    //3: ok data seems sane, xml for the card view next
    //4: listadapter after some reverting and ironing out dependancies
    //5: add frame restrcited submit order button to xml layout
    //6: added onclick to items to indicate an order (green is selected -- white is unselected )
    //7: set launcher icon before i forget
    //8: set notification capability on click
    //9: retrieve from the shoppingList any items that have the ordered booleon set to true
    //10: send retrieved list of objects to a log to see if it work, it works
    //11: look up old project to figure out how to do implicit intents
    //11.5: intent actually does work, just wildly slow on my emulator
    //epilogue: nope, I had placed closing curly brackets incorrectly and was triggering a solid few dozen notifications and shares -- works fine now if not terribly pretty

    fun makeNotification(text:String) {
        val contentIntent = Intent(this, MainActivity::class.java)
        val pendingContentIntent = PendingIntent.getActivity(this, 0, contentIntent, PendingIntent.FLAG_ONE_SHOT)
        val channelId = "$packageName.channel"
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Notification Channel"
            val importance = NotificationManager.IMPORTANCE_MIN
            val description = "NOTIFICATION CHANNEL DESCRIPTION"
            val channel = NotificationChannel(channelId, name, importance)
            channel.description = description
            notificationManager.createNotificationChannel(channel)
        }
        val notificationBuilder = NotificationCompat.Builder(this, channelId)
                .setPriority(NotificationManager.IMPORTANCE_MIN)
                .setSmallIcon(android.R.drawable.ic_media_ff)
                .setContentTitle("Groceries list!")
                .setContentText("Your groceries list is being sent to the app of your choice $text")
                .setContentIntent(pendingContentIntent)
                .setAutoCancel(true)
        notificationManager.notify(NOTIFICATION_ID, notificationBuilder.build())

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            with(window) {
                requestFeature(Window.FEATURE_CONTENT_TRANSITIONS)
                enterTransition = Fade()
                exitTransition = Fade()

            }
        } else {
            // Swap without transition
        }
        super.onCreate(savedInstanceState)
        setContentView(com.lambdaschool.sprint2_challenge.R.layout.activity_main)
        createShoppingList()



        recycle_view.setHasFixedSize(true)
        val manager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        var adapter = ShoppingListAdapter(shoppingList)
        adapter.setHasStableIds(true)

        recycle_view.layoutManager = manager
        recycle_view.adapter = adapter

        btn_button.setOnClickListener{
            finalstr = ""
            for (item in shoppingList){
                if (item.ordered) {
                    finalstr += "${item.kind}; "
                }
            }
                makeNotification(finalstr)
                    Log.i("currentasignment", "$drawableRes")
            /* var action = Intent(this,FullscreenActivity::class.java)
                     action.putExtra(FullscreenActivity.key, shoppingList[12])
                     startActivity(action, ActivityOptions.makeSceneTransitionAnimation(this).toBundle())*/
            val intent = Intent(this, FullscreenActivity::class.java)
           // val androidRobotView = iv_item_drawable.findViewById<View>(drawableRes)
           // val activityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(this, androidRobotView, "robot")
            intent.putExtra(FullscreenActivity.key, drawableRes)
            //activityOptions.toBundle()
            startActivity(intent)
                  /// val sendIntent: Intent = Intent().apply {
                       //action = Intent.ACTION_SEND
                      /// action = Intent(this,FullscreenActivity::class.java)
                       //                       putExtra(Intent.EXTRA_TEXT, "$finalstr")
                       //                       type = "text/plain"
                 //  }
                //   startActivity(Intent.createChooser(sendIntent, resources.getText(R.string.send_to)))


            }
        }



    }
    }


