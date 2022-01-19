package com.sample.common.activities

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.*
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.sample.common.Contstants
import com.sample.common.R
import com.sample.common.adapters.SimpsonMovieCharactorAdapter
import com.sample.common.models.SimpsonsMyListFeed
import com.sample.common.models.SimpsonsRequest
import kotlinx.android.synthetic.main.happies_activity.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.util.*
import kotlin.collections.ArrayList

/*
What is the RecyclerView?

Advanced and Efficient listview
taggered Grid Layout
Three types of layouts orientation
Linear Layout
Grid Layout
*/
class FragmentList: Fragment(){

    private var arraryList = ArrayList<SimpsonsMyListFeed>()
    private var displayList =ArrayList<SimpsonsMyListFeed>()
    lateinit var cxt : Context

    override fun onAttach(context: Context) {
        super.onAttach(context)
        cxt = context
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val rootView = inflater.inflate(R.layout.fragment_a, container, false)

        if (cxt != null) {
            setUpRecyclerView(cxt,rootView)
        }

        return rootView
    }

    //view code
    @RequiresApi(Build.VERSION_CODES.M)
    fun setUpRecyclerView(context: Context, view: View)
    {
        if (Contstants.isNetworkConnected(cxt)) {
            doAsync {
                val url = resources.getString(R.string.URL)
                //println("muru > From JSON String:******Hello" + url)
                arraryList =  SimpsonsRequest(url).run()
                uiThread {
                    val layoutManager = LinearLayoutManager(context)
                    layoutManager.orientation = LinearLayoutManager.VERTICAL

                    displayList.addAll(arraryList)
                    recylerViewList.layoutManager = layoutManager
                    var adapter = SimpsonMovieCharactorAdapter(context, displayList)

                    recylerViewList.adapter = adapter
                }
            }
        }
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu)
        val menuItem = menu!!.findItem(R.id.search)

        if (menuItem != null){
            val searchView = menuItem.actionView as androidx.appcompat.widget.SearchView

            searchView.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return  true
                }

                override fun onQueryTextChange(newText: String?): Boolean {

                    if (newText!!.isNotEmpty()) {
                        displayList.clear()
                        val search = newText.toLowerCase(Locale.getDefault())
                        arraryList.forEach {
                            if (it.Title?.toLowerCase(Locale.getDefault())?.contains(search) ==true) {
                                displayList.add(it)
                            }
                        }
                        doAsync { uiThread {
                            recylerViewList.adapter!!.notifyDataSetChanged()
                        } }


                    } else {
                        displayList.clear()
                        displayList.addAll(arraryList)

                        doAsync {
                            uiThread {  recylerViewList.adapter!!.notifyDataSetChanged()}

                        }

                    }
                    return true

                }
            })
        }
        return super.onCreateOptionsMenu(menu, inflater)
    }

}
