package kr.co.tjoeun.library_20200713

import android.Manifest
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bumptech.glide.Glide
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.TedPermission
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

        callBtn.setOnClickListener {
//            TedPermission 을 이용해서 통화권한을 허락할건지 질문.
//            권한 승인/거절에 따라 어떤 행동을 할지 할일을 적어둔 변수
            val permissionListener = object :PermissionListener{
                override fun onPermissionGranted() {
//                    승인을 받은 상황 -> 실제 전화걸기 시작
                    val phoneNum = phoneNumTxt.text.toString()
                    val myUri = Uri.parse("tel:${phoneNum}")
                    val myIntent = Intent(Intent.ACTION_CALL,myUri)
                    startActivity(myIntent)
                }

                override fun onPermissionDenied(deniedPermissions: MutableList<String>?) {
//                  최종 거부된 상황. 통화할수 없다.
                    Toast.makeText(mContext,"통화권한이 거부되어 연결 불가",Toast.LENGTH_SHORT).show()
                }
            }

//            실제 권한 확인요청
            TedPermission.with(mContext)
                .setPermissions(Manifest.permission.CALL_PHONE)
                .setDeniedMessage("설정에서 통화 권한을 허용해줘야한다.")
                .setPermissionListener(permissionListener)
                .check()

        }
    }

    override fun setValues() {
        val imgUrl = "https://i.pinimg.com/originals/c8/14/99/c814995b86a60232c93492f5c90c0570.jpg"
        Glide.with(mContext).load(imgUrl).into(profileImg)
    }

}
