package com.pkm.smarttoilet

import android.content.ContentValues
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.*
import android.provider.MediaStore
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.pkm.smarttoilet.adapter.FecesSectionPagerAdapter
import com.pkm.smarttoilet.adapter.UrineSectionPagerAdapter
import com.pkm.smarttoilet.viewmodel.FecesResultViewModel
import com.pkm.smarttoilet.viewmodel.MainViewModel
import com.pkm.smarttoilet.viewmodel.UrineResultViewModel
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.*
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.Executors


class MainActivity : AppCompatActivity() {

    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var hasilDeteksi: TextView
    private lateinit var fecesResultViewModel: FecesResultViewModel
    private lateinit var urineResultViewModel: UrineResultViewModel
    private lateinit var mainViewModel: MainViewModel
    private var ivFecesForm: ImageView? = null
    private var getFile: File? = null
    private var myImage: Bitmap? = null
    var num: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_main)

        val fecesSectionPagerAdapter = FecesSectionPagerAdapter(this)
        val fecesViewPager: ViewPager2 = findViewById(R.id.view_pager_feces)
        fecesViewPager.adapter = fecesSectionPagerAdapter
        val tabsFeces: TabLayout = findViewById(R.id.tab_layout_feces)
        TabLayoutMediator(tabsFeces, fecesViewPager){ tab, position->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()

        val urineSectionPagerAdapter = UrineSectionPagerAdapter(this)
        val urineViewPager: ViewPager2 = findViewById(R.id.view_pager_urine)
        urineViewPager.adapter = urineSectionPagerAdapter
        val tabsUrine: TabLayout = findViewById(R.id.tab_layout_urine)
        TabLayoutMediator(tabsUrine, urineViewPager){ tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()

        fecesResultViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[FecesResultViewModel::class.java]
        urineResultViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[UrineResultViewModel::class.java]
        mainViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[MainViewModel::class.java]
        hasilDeteksi = findViewById(R.id.tv_result)
        swipeRefresh = findViewById(R.id.refresh_layout)
        ivFecesForm = findViewById(R.id.iv_feces_form)

        val ivHistory = findViewById<ImageView>(R.id.ic_history)
        ivHistory.setOnClickListener {
            startActivity(Intent(this@MainActivity, HistoryActivity::class.java))
        }
        val updateTimeFeces = findViewById<TextView>(R.id.tv_date_updated)
        updateTimeFeces.text = "Last Updated: -"

        swipeRefresh.setOnRefreshListener {

            mainViewModel.getLatestImageUrl()
            var myUrl: String = "https://storage.googleapis.com/staging.pkm2022.appspot.com/upload/1662262660498_IMG_1927.jpg"
            mainViewModel.latestImageUrl.observe(this){
                myUrl = it.imgPath
            }
            Log.d("TAG", "onCreate: $myUrl")

            val myImageUrl = if (myUrl == "null"){
                "https://storage.googleapis.com/staging.pkm2022.appspot.com/upload/1662262660498_IMG_1927.jpg"
            } else {
                publicUrl(myUrl)
            }
            Log.d("TAG", "onCreate: $myImageUrl")

            // Declaring and initializing an Executor and a Handler
            val myExecutor = Executors.newSingleThreadExecutor()
            val myHandler = Handler(Looper.getMainLooper())
            myExecutor.execute {
                myImage = mLoad("https://storage.googleapis.com/staging.pkm2022.appspot.com/upload/1662262660498_IMG_1927.jpg")
                myHandler.post {
                    if(myImage!=null){
//                        mSaveMediaToStorage(myImage) taken from geeks for geeks
                        val filename = "${System.currentTimeMillis()}.jpg"
                        val f = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), filename)
                        val os: OutputStream = BufferedOutputStream(FileOutputStream(f))
                        myImage?.compress(Bitmap.CompressFormat.JPEG, 100, os)
                        os.close()
                        val f2 = f as File
                        val requestImageFile = f2.asRequestBody("image/jpg".toMediaTypeOrNull())
                        val imageMultipartBody: MultipartBody.Part = MultipartBody.Part.createFormData(
                            "image",
                            f2.name,
                            requestImageFile
                        )
//                        urineResultViewModel.upUrineColor(imageMultipartBody)
                        fecesResultViewModel.upFecesForm(imageMultipartBody)
                        fecesResultViewModel.upFecesColor(imageMultipartBody)
                    }
                }
            }

            fecesResultViewModel.isLoading.observe(this){
                if (!it){
                    val df2 = SimpleDateFormat("yyyy-MM-dd hh:mm:ss a", Locale.US)
                    val time2: String = df2.format(Date())
                    updateTimeFeces.text = "Last Updated: $time2"
                    swipeRefresh.isRefreshing = false
                }
            }
        }


    }

    // Function to establish connection and load image
    private fun mLoad(string: String): Bitmap? {
        val url: URL = mStringToURL(string)!!
        Log.d("TAG", "mLoad: $string")
        val connection: HttpURLConnection?
        try {
            connection = url.openConnection() as HttpURLConnection
            connection.connect()
            val inputStream: InputStream = connection.inputStream
            val bufferedInputStream = BufferedInputStream(inputStream)
            return BitmapFactory.decodeStream(bufferedInputStream)
        } catch (e: IOException) {
            e.printStackTrace()
            Toast.makeText(applicationContext, "Error", Toast.LENGTH_LONG).show()
        }
        return null
    }

    private fun mStringToURL(string: String): URL? {
        try {
            return URL(string)
        } catch (e: MalformedURLException) {
            e.printStackTrace()
        }
        return null
    }

    private fun publicUrl(latestImageUrl: String): String {
        val dataPath = latestImageUrl.drop(58)
        return "https://storage.googleapis.com/staging.pkm2022.appspot.com$dataPath"
    }


    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.tab_text_feces_result,
            R.string.tab_text_feces_tips
        )
    }

}