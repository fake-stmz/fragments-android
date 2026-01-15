package moe.stmz.fragments

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity() {
    private lateinit var photoButton: Button
    private lateinit var videoButton: Button
    private var currentFragment: Fragment = PhotoFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        photoButton = findViewById(R.id.photo_button)
        videoButton = findViewById(R.id.video_button)

        photoButton.setOnClickListener {
            if (currentFragment !is PhotoFragment) {
                loadFragment(PhotoFragment(), "photos")
                photoButton.backgroundTintList = ContextCompat.getColorStateList(this, R.color.accent)
                videoButton.backgroundTintList = ContextCompat.getColorStateList(this, R.color.mute)
            }
        }

        videoButton.setOnClickListener {
            if (currentFragment is PhotoFragment) {
                loadFragment(VideoFragment(), "videos")
                videoButton.backgroundTintList = ContextCompat.getColorStateList(this, R.color.accent)
                photoButton.backgroundTintList = ContextCompat.getColorStateList(this, R.color.mute)
            }
        }
    }

    private fun loadFragment(fragment: Fragment, tag: String) {
        currentFragment = fragment

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment, tag)
            .addToBackStack(tag)
            .commit()
    }
}