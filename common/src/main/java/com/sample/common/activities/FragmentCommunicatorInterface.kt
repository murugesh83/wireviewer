package com.smartherd.multiplescreensupport

interface FragmentCommunicatorInterface { // Meant for inter-fragment communication

    fun updateDisplayDetails(title: String, description: String, url: String)
}
