package com.pkm.smarttoilet

import android.R.attr.bitmap
import android.content.ContentValues
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.*
import android.provider.MediaStore
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.pkm.smarttoilet.adapter.FecesSectionPagerAdapter
import com.pkm.smarttoilet.adapter.UrineSectionPagerAdapter
import com.pkm.smarttoilet.viewmodel.FecesResultViewModel
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
        hasilDeteksi = findViewById(R.id.tv_result)
        swipeRefresh = findViewById(R.id.refresh_layout)
        ivFecesForm = findViewById(R.id.iv_feces_form)



        val updateTimeFeces = findViewById<TextView>(R.id.tv_date_updated)

        swipeRefresh.setOnRefreshListener {
            val df = SimpleDateFormat("yyyy-MM-dd hh:mm:ss a", Locale.US)
            val time: String = df.format(Date())
            updateTimeFeces.text = "Last Updated: $time"

//          //test tfjs heroku
            val myImageUrl = "https://storage.googleapis.com/staging.pkm2022.appspot.com/upload/1662262660498_IMG_1927.jpg"

            // Declaring and initializing an Executor and a Handler
            val myExecutor = Executors.newSingleThreadExecutor()
            val myHandler = Handler(Looper.getMainLooper())
            myExecutor.execute {
                myImage = mLoad(myImageUrl)
//                Log.d(TAG, "onCreate: ${myImage.is}")
//                if (myImage!=null){
////                    val myFile = bitmapToFile(this, myImage, "mypkm2022.png")
////                    ivFecesForm?.setImageBitmap(myImage)
////                    val f: File = File("app/app/src/main/res/drawable")
////                    val os: OutputStream = BufferedOutputStream(FileOutputStream(f))
////                    myImage?.compress(Bitmap.CompressFormat.JPEG, 100, os)
////                    os.close()
//
//                }
                myHandler.post {
                    if(myImage!=null){
//                        mSaveMediaToStorage(myImage)
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
                        fecesResultViewModel.upFecesColor(imageMultipartBody)
                    }
                }
            }

            fecesResultViewModel.isLoading.observe(this){
                if (!it){
                    swipeRefresh.isRefreshing = false
                }
            }
        }


    }
    // Function to establish connection and load image
    private fun mLoad(string: String): Bitmap? {
        val url: URL = mStringToURL(string)!!
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

    // Refer: https://www.geeksforgeeks.org/circular-crop-an-image-and-save-it-to-the-file-in-android/
    private fun mSaveMediaToStorage(bitmap: Bitmap?) {
        val filename = "${System.currentTimeMillis()}.jpg"
        var fos: OutputStream? = null
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            this.contentResolver?.also { resolver ->
                val contentValues = ContentValues().apply {
                    put(MediaStore.MediaColumns.DISPLAY_NAME, filename)
                    put(MediaStore.MediaColumns.MIME_TYPE, "image/jpg")
                    put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES)
                }
                val imageUri: Uri? = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
                fos = imageUri?.let { resolver.openOutputStream(it) }
            }
        } else {
            val imagesDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
            val image = File(imagesDir, filename)
            fos = FileOutputStream(image)
        }
        fos?.use {
            bitmap?.compress(Bitmap.CompressFormat.JPEG, 100, it)
            Toast.makeText(this , "Saved to Gallery" , Toast.LENGTH_SHORT).show()
        }
    }


    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.tab_text_feces_result,
            R.string.tab_text_feces_tips
        )
    }

}