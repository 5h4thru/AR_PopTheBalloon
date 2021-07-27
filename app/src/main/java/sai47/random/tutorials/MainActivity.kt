package sai47.random.tutorials

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.ar.sceneform.Camera
import com.google.ar.sceneform.Scene
import sai47.random.tutorials.fragments.CustomArFragment

class MainActivity : AppCompatActivity() {
    private lateinit var scene: Scene
    private lateinit var camera: Camera

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        prepareARScene()
    }

    /**
     * Prepares AR Scene and sets up the Camera from AR Scene
     */
    private fun prepareARScene() {
        val customArFragment: CustomArFragment = supportFragmentManager.findFragmentById(R.id.customArFragment) as CustomArFragment
        scene = customArFragment.arSceneView.scene
        camera = scene.camera
    }

    /**
     * Adds hard-coded # of Balloons randomly on the scene
     */
    private fun addBalloons() {
        // TODO
    }
}