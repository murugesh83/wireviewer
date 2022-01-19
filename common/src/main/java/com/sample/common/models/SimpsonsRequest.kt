package com.sample.common.models

import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class SimpsonsRequest(url: String) {

    var urlValue : String =""
    init {
        //urlValue = url
        urlValue = "http://api.duckduckgo.com/?q=simpsons+characters&format=json"
    }

    fun run(): ArrayList<SimpsonsMyListFeed> {
        var personName: ArrayList<SimpsonsMyListFeed> = ArrayList()
        val repoListJsonStr = java.net.URL(urlValue).readText()

        try {

            val obj = JSONObject(repoListJsonStr)
           // println("> From JSON String:******" + obj)
           val userArray = obj.getJSONArray("RelatedTopics")
           // println("> User Array From JSON String:******" + userArray.length())
            for (i in 0 until userArray.length()) {
               val userDetail = userArray.getJSONObject(i)
                val jsonObjecticon = (userDetail).getJSONObject("Icon")
               var IconUrl : String? = null
                for (i in 0 until jsonObjecticon.length()){
                      IconUrl = jsonObjecticon.getString("URL")
                }
                var firstUrl=userDetail.getString("FirstURL")
                var url = firstUrl
                if(!url.equals(""))
                    IconUrl = url.substringBefore(".com")+".com"+IconUrl
                var text = userDetail.getString("Text")
                var title =""
                if(!text.equals(""))
                    title = text.substringBefore("-")
               personName.add(SimpsonsMyListFeed(
                   FirstURL = firstUrl,
                   Icon = IconUrl,
                   Result = userDetail.getString("Result"),
                   Text = userDetail.getString("Text"),
                   Title =  title))

           }
        }

        catch (e: JSONException) {
            e.printStackTrace()
        }
/*
        for ((i, _) in personName.withIndex())
        {
            println("> From JSON String:******" + personName[i].FirstURL)
            println("> From JSON String:******" + personName[i].Icon)
            println("> From JSON String:******" + personName[i].Result)
            println("> From JSON String:******" + personName[i].Text)
        }*/

       // println("> From JSON String:******" + personName)
        return personName
    }
}