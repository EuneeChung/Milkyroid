package com.milkyway.milkyway.ui.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.whenResumed
import com.milkyway.milkyway.R
import com.milkyway.milkyway.data.remote.RetrofitBuilder
import com.milkyway.milkyway.data.remote.request.RequestCafeId
import com.milkyway.milkyway.data.remote.response.AroundCafe
import com.milkyway.milkyway.data.remote.response.ResponseAddUniverse
import com.milkyway.milkyway.data.remote.response.ResponseDeleteUniverse
import com.milkyway.milkyway.databinding.ActivityHomeResultBinding
import com.milkyway.milkyway.ui.detail.CafeDetailActivity
import com.milkyway.milkyway.ui.main.MainActivity
import com.milkyway.milkyway.ui.search.homesearch.CafeSearchActivity
import com.milkyway.milkyway.util.DataStore
import com.milkyway.milkyway.util.MarkerDrawer
import com.milkyway.milkyway.util.Toast
import com.milkyway.milkyway.util.startActivity
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.*
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.overlay.OverlayImage
import com.naver.maps.map.util.FusedLocationSource
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeResultActivity : AppCompatActivity(), OnMapReadyCallback {
    private val homeViewModel : HomeViewModel by viewModels()
    private lateinit var binding : ActivityHomeResultBinding
    private val marker = Marker()
    private lateinit var cafeData : AroundCafe
    private var cafeIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home_result)
        binding.homeViewModel = homeViewModel
        binding.lifecycleOwner = this

        setLoading()
        setMap()
        setCard()
        setBackButton()
    }

    private fun setLoading() {
        binding.imgLoading.playAnimation()
    }

    private fun setMap() {
        val fm = supportFragmentManager
        val mapFragment = fm.findFragmentById(R.id.map) as MapFragment?
            ?: MapFragment.newInstance().also {
                fm.beginTransaction().add(R.id.map, it).commit()
            }

        mapFragment.getMapAsync(this)
    }

    private fun setCard() {
        val position = LatLng(
            intent.getDoubleExtra("latitude", 0.0),
            intent.getDoubleExtra("longitude", 0.0))
        cafeData = MarkerDrawer.findData(position)!!
        cafeIndex = MarkerDrawer.findIndex(position)!!

        if (cafeData.isUniversed) {
            binding.btnAddUniverse.setBackgroundResource(R.drawable.btn_universe_added)
            binding.btnAddUniverse.setOnClickListener{
                deleteListener()
            }
            binding.tvLikeCount.setTextColor(getColor(R.color.blue_3320a6))
            binding.tvLikeCount.typeface = ResourcesCompat.getFont(binding.tvLikeCount.context, R.font.roboto_bold)
        } else {
            binding.btnAddUniverse.setBackgroundResource(R.drawable.btn_universe)
            binding.btnAddUniverse.setOnClickListener {
                addListener()
            }
            binding.tvLikeCount.setTextColor(getColor(R.color.gray_97))
            binding.tvLikeCount.typeface = ResourcesCompat.getFont(binding.tvLikeCount.context, R.font.roboto_regular)
        }

        binding.tvSearchResult.text = cafeData.cafeName
        binding.tvCafeName.text = cafeData.cafeName
        binding.tvAddress.text = cafeData.cafeAddress
        binding.tvCafeHour.text = String.format(binding.tvCafeHour.context.getString(R.string.home_cafe_hour), cafeData.businessHours)
        binding.tvLikeCount.text = cafeData.universeCount.toString()

        if(cafeData.businessHours!=null) binding.tvCafeHour.visibility = View.VISIBLE
        else binding.tvCafeHour.visibility = View.GONE

        binding.layoutSearchResultCard.setOnClickListener {
            val intent = Intent(this, CafeDetailActivity::class.java)
            intent.putExtra("cafeId", cafeData.id)
            startActivity(intent)
            finish()
        }
    }

    private fun setBackButton() {
        binding.btnBackSearch.setOnClickListener{
            startActivity<CafeSearchActivity>()
        }
        binding.btnBackHome.setOnClickListener{
            startActivity<MainActivity>()
        }
    }

    override fun onMapReady(p0: NaverMap) {
        val position = LatLng(
            intent.getDoubleExtra("latitude", 0.0),
            intent.getDoubleExtra("longitude", 0.0))

        setUiSetting(p0)
        setMarker(p0, position)
        setLocation(p0)
        setCurrentLocationIcon(p0)
        setCameraMoveListener(p0)
        moveCamera(p0, position)
        homeViewModel.isLoadingEnd()
    }

    private fun setUiSetting(p0 : NaverMap) {
        val uiSettings = p0.uiSettings
        uiSettings.isZoomControlEnabled = false // zoom
        uiSettings.isScaleBarEnabled = false // scale
        uiSettings.isCompassEnabled = false // compass
        uiSettings.isLocationButtonEnabled = false // nowLocation
    }

    private fun setMarker(p0: NaverMap, position: LatLng) {
        marker.position = position
        if(MarkerDrawer.findData(position)!!.isUniversed)
            marker.icon = OverlayImage.fromResource(R.drawable.ic_marker_universe_selected)
        else
            marker.icon = OverlayImage.fromResource(R.drawable.ic_marker_selected)
        marker.map = p0
    }

    private fun setLocation(p0: NaverMap) {
        val locationSource = FusedLocationSource(this, LOCATION_PERMISSION_REQUEST_CODE)
        p0.locationSource = locationSource
    }

    private fun setCurrentLocationIcon(p0: NaverMap) {
        val locationOverlay = p0.locationOverlay
        locationOverlay.isVisible = true
        locationOverlay.icon = OverlayImage.fromResource(R.drawable.ic_current_location)
        locationOverlay.circleRadius = 0

        homeViewModel.compass.observe(this, Observer { compass->
            compass?.let {
                if(compass) {
                    p0.locationTrackingMode = LocationTrackingMode.Face
                    locationOverlay.subIcon = OverlayImage.fromResource(R.drawable.ic_location_face)
                }
                else {
                    p0.locationTrackingMode = LocationTrackingMode.NoFollow
                    locationOverlay.subIcon = OverlayImage.fromResource(R.drawable.ic_location_no_follow)
                }
            }
        })
    }

    private fun setCameraMoveListener(p0: NaverMap) {
        p0.addOnCameraChangeListener { _, _ ->
            if(p0.locationTrackingMode == LocationTrackingMode.NoFollow) homeViewModel.notCompassIcon()
        }
    }

    private fun moveCamera(p0: NaverMap, position: LatLng) {
        val cameraUpdate = CameraUpdate.scrollTo(position)
            .animate(CameraAnimation.Linear)
        p0.defaultCameraAnimationDuration = 1000
        p0.moveCamera(cameraUpdate)
    }

    private fun addListener() {
        homeViewModel.isLoading()
        lifecycleScope.launch{
            whenResumed{
                DataStore(this@HomeResultActivity).getToken.collect{
                    addUniverse(it!!, cafeData.id)
                }
            }
        }
    }

    private fun deleteListener() {
        homeViewModel.isLoading()
        lifecycleScope.launch{
            whenResumed{
                DataStore(this@HomeResultActivity).getToken.collect{
                    deleteUniverse(it!!, cafeData.id)
                }
            }
        }
    }

    private fun addUniverse(token: String, cafeId: Int) {
        val body = RequestCafeId(cafeId = cafeId)
        val call: Call<ResponseAddUniverse> = RetrofitBuilder.service.addMyUniverseHome(token, body)
        call.enqueue(object : Callback<ResponseAddUniverse> {
            override fun onFailure(call: Call<ResponseAddUniverse>, t: Throwable) {
                Log.d("response", t.localizedMessage!!)
            }
            override fun onResponse(
                call: Call<ResponseAddUniverse>,
                response: Response<ResponseAddUniverse>
            ) {
                response.takeIf { it.isSuccessful }
                    ?.body()
                    ?.let {
                        homeViewModel.isLoadingEnd()
                        binding.btnAddUniverse.setBackgroundResource(R.drawable.btn_universe_added)
                        binding.btnAddUniverse.setOnClickListener{
                            deleteListener()
                        }
                        binding.tvLikeCount.setTextColor(getColor(R.color.blue_3320a6))
                        binding.tvLikeCount.typeface = ResourcesCompat.getFont(binding.tvLikeCount.context, R.font.roboto_bold)
                        binding.tvLikeCount.text = it.data.universeCount.toString()
                        marker.icon = OverlayImage.fromResource(R.drawable.ic_marker_universe_selected)
                        MarkerDrawer.updateData(cafeIndex)
                        Toast.customToast("나의 유니버스에서 추가되었습니다", this@HomeResultActivity)
                    } ?: Log.d("response", response.body().toString())
            }
        })
    }

    private fun deleteUniverse(token: String, cafeId : Int) {
        val call: Call<ResponseDeleteUniverse> = RetrofitBuilder.service.deleteUniverseMarker(token, cafeId)
        call.enqueue(object : Callback<ResponseDeleteUniverse> {
            override fun onFailure(call: Call<ResponseDeleteUniverse>, t: Throwable) {
                Log.d("response", t.localizedMessage!!)
            }
            override fun onResponse(
                call: Call<ResponseDeleteUniverse>,
                response: Response<ResponseDeleteUniverse>
            ) {
                response.takeIf { it.isSuccessful }
                    ?.body()
                    ?.let {
                        homeViewModel.isLoadingEnd()
                        binding.btnAddUniverse.setBackgroundResource(R.drawable.btn_universe)
                        binding.btnAddUniverse.setOnClickListener{
                            addListener()
                        }
                        binding.tvLikeCount.setTextColor(getColor(R.color.gray_97))
                        binding.tvLikeCount.typeface = ResourcesCompat.getFont(binding.tvLikeCount.context, R.font.roboto_bold)
                        binding.tvLikeCount.text = it.data.universeCount.toString()
                        marker.icon = OverlayImage.fromResource(R.drawable.ic_marker_selected)
                        MarkerDrawer.updateData(cafeIndex)
                        Toast.customToast("나의 유니버스에서 삭제되었습니다", this@HomeResultActivity)
                    } ?: Log.d("response", response.body().toString())
            }
        })
    }

    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1000
    }
}