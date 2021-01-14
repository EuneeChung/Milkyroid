package com.milkyway.milkyway.ui.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.milkyway.milkyway.R
import com.milkyway.milkyway.databinding.FragmentHomeSearchResultBinding
import com.milkyway.milkyway.ui.home.homesearch.CafeSearchActivity
import com.milkyway.milkyway.ui.home.result.HomeSearchResultViewModel
import com.milkyway.milkyway.ui.main.MainActivity

class HomeSearchResultFragment : Fragment() {

    private val resultViewModel: HomeSearchResultViewModel by activityViewModels()
    private lateinit var binding: FragmentHomeSearchResultBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeSearchResultBinding.inflate(inflater,container,false)
        binding.resultViewModel=resultViewModel
        binding.lifecycleOwner=this

        setClickBtnBack(binding)
        setClickBtnCancel(binding)

        return binding.root

//        val placeSearchIntent = Intent(context as MainActivity, CafeSearchActivity::class.java)
//        startActivity(placeSearchIntent)

//        startActivityForResult(placeSearchIntent, CafeSearchActivity.REQUEST_CODE)

//        findNavController().navigate(R.id.action_homeSearchResult_to_homeSearch)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val placeSearchIntent = Intent(context as MainActivity, CafeSearchActivity::class.java)
        startActivityForResult(placeSearchIntent, CafeSearchActivity.REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == SEARCH_RESULT_HOME) {

            val cafeName = data?.getStringExtra("cafeName")
            val cafeAddress = data?.getStringExtra("cafeAddress")
            val longitude = data?.getDoubleExtra("longitude",-1.1)
            val latitude = data?.getDoubleExtra("latitude",-1.1)
            val businessHours = data?.getStringExtra("businessHours")

            binding.tvSearchResult.text=cafeName
            Log.d("cafe info",cafeName.toString()+cafeAddress.toString()+longitude.toString()+latitude.toString()+businessHours)
        }
    }

    private fun setClickBtnBack(binding: FragmentHomeSearchResultBinding){
        binding.btnBackSearchResult.setOnClickListener {
            val placeSearchIntent = Intent(context as MainActivity, CafeSearchActivity::class.java)
            startActivityForResult(placeSearchIntent, CafeSearchActivity.REQUEST_CODE)
            //findNavController().navigate(R.id.action_homeSearchResult_to_homeFragment)
        }
    }
    private fun setClickBtnCancel(binding: FragmentHomeSearchResultBinding){
        binding.btnSearchCancel.setOnClickListener {
            findNavController().navigate(R.id.action_homeSearchResult_to_homeFragment)
        }
    }

    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1000
        const val SEARCH_RESULT_HOME = 3
    }
}