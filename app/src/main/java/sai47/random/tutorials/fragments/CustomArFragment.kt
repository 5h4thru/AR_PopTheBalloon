package sai47.random.tutorials.fragments

import android.os.Bundle
import com.google.ar.sceneform.ux.ArFragment

class CustomArFragment : ArFragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        planeDiscoveryController.hide()
        planeDiscoveryController.setInstructionView(null)
    }
}