package com.sample.common.adapters

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sample.common.R
import com.sample.common.models.SimpsonsMyListFeed
import com.smartherd.multiplescreensupport.FragmentCommunicatorInterface
import kotlinx.android.synthetic.main.list_item.view.*
import org.jetbrains.anko.doAsync

//Recyclerview adapter for listview
class SimpsonMovieCharactorAdapter(val context : Context, val hoppies : ArrayList<SimpsonsMyListFeed>) : RecyclerView.Adapter<SimpsonMovieCharactorAdapter.MyViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
     val view = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false)

      return  MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val hoppy = hoppies[position]
        holder.setData(hoppy, position)
    }

    override fun getItemCount(): Int {
          return  hoppies.size
    }


    inner class MyViewHolder(itemview : View) :RecyclerView.ViewHolder(itemview) {

        var currentHoppy: SimpsonsMyListFeed? = null
        var currentPostion: Int = 0

        init {
            itemview.setOnClickListener {
                val myCommunicator = context as FragmentCommunicatorInterface
                val text = currentHoppy?.Title
                val icon =  currentHoppy?.Icon
                val description = currentHoppy?.Result
                if (text != null) {
                    if (icon != null) {
                        if (description != null) {
                            myCommunicator.updateDisplayDetails(text, description, icon)
                        }
                    }
                }

            }

        }



            fun setData(hoppy: SimpsonsMyListFeed, pos: Int) {
                hoppy?.let {
                    val title = hoppy!!.Title
                    itemView.txvTitle.text = title
                    doAsync {
                        val imageURL = hoppy!!.Icon
                        var image: Bitmap? = null
                        try {
                            val `in` = java.net.URL(imageURL).openStream()
                            image = BitmapFactory.decodeStream(`in`)
                        }
                        catch (e: Exception) {
                            Log.e("Error Message", e.message.toString())
                            e.printStackTrace()
                        }
                        itemView.imgShare.setImageBitmap(image)                    }


                }
                this.currentHoppy = hoppy
                this.currentPostion = pos

            }
    }



}