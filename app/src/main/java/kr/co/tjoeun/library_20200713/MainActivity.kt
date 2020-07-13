package kr.co.tjoeun.library_20200713

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_profile_photo.*

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupEvents()
        setValues()
    }

    override fun setupEvents() {

        goToPhotoViewBtn.setOnClickListener {

            val myIntent = Intent(mContext, ProfilePhotoActivity::class.java)
            startActivity(myIntent)

        }
    }

    override fun setValues() {
        val imgUrl = "https://i.pinimg.com/originals/c8/14/99/c814995b86a60232c93492f5c90c0570.jpg"
        Glide.with(mContext).load(imgUrl).into(profileImg)
    }

}
