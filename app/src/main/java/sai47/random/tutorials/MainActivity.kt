package sai47.random.tutorials

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.ar.sceneform.Camera
import com.google.ar.sceneform.Node
import com.google.ar.sceneform.Scene
import com.google.ar.sceneform.math.Vector3
import com.google.ar.sceneform.rendering.Material
import com.google.ar.sceneform.rendering.MaterialFactory
import com.google.ar.sceneform.rendering.ModelRenderable
import com.google.ar.sceneform.rendering.ShapeFactory
import com.google.ar.sceneform.rendering.Texture
import sai47.random.tutorials.fragments.CustomArFragment
import java.util.*
import java.util.logging.Level
import java.util.logging.Logger


class MainActivity : AppCompatActivity() {
    private lateinit var scene: Scene
    private lateinit var camera: Camera
    private lateinit var bulletRenderable: ModelRenderable

    /**
     * Sample Assets to test loading remote models
     */
    // private val GLTF_ASSET =
    // "https://github.com/KhronosGroup/glTF-Sample-Models/raw/master/2.0/Duck/glTF/Duck.gltf"
    // "https://github.com/KhronosGroup/glTF-Sample-Models/blob/master/2.0/Box/glTF/Box.gltf"
    // "https://github.com/KhronosGroup/glTF-Sample-Models/blob/master/2.0/AnimatedCube/glTF/AnimatedCube.gltf"

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

        hideHandAnimation(customArFragment)
        addBalloons()
        buildBulletModel()
    }

    /**
     * Hides the default hand animation that comes with Sceneform
     */
    private fun hideHandAnimation(customArFragment: CustomArFragment) {
        customArFragment.planeDiscoveryController.hide()
        customArFragment.planeDiscoveryController.setInstructionView(null)
    }

    /**
     * Creates model renderable for the ball
     */
    private fun buildBulletModel() {
        Texture
            .builder()
            .setSource(this, R.drawable.texture)
            .build()
            .thenAccept { texture: Texture? ->
                MaterialFactory
                    .makeOpaqueWithTexture(this, texture)
                    .thenAccept { material: Material? ->
                        bulletRenderable = ShapeFactory
                            .makeSphere(
                                0.1f,
                                Vector3(0f, 0f, 0f),
                                material
                            )
                    }
            }
    }

    /**
     * Adds hard-coded # of Balloons randomly on the scene
     */
    private fun addBalloons() {
        logger.log(Level.INFO, "Add Balloons method!")
        ModelRenderable
            .builder()
            .setSource(this, Uri.parse("balloon.sfb"))
            .build()
            .thenAccept { renderable: ModelRenderable? ->
                for (i in 0..19) {
                    val node = Node()
                    node.renderable = renderable
                    scene.addChild(node)
                    val random = Random()
                    val x = random.nextInt(10)
                    var z = random.nextInt(10)
                    val y = random.nextInt(20)
                    z = -z
                    node.worldPosition = Vector3(
                        x.toFloat(),
                        y / 10f,
                        z.toFloat()
                    )
                }
            }
    }

    companion object {
        private val logger: Logger = Logger.getLogger("MARK#42")
    }
}