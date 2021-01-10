package com.milkyway.milkyway.ui.modify.dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.milkyway.milkyway.R
import com.milkyway.milkyway.databinding.DialogDeleteReasonsBinding
import com.milkyway.milkyway.ui.modify.ModifyDialogViewModel


class DeleteFragmentDialog :  DialogFragment() {

    private lateinit var binding: DialogDeleteReasonsBinding
    private val modifyDialogViewModel : ModifyDialogViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isCancelable=true
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding= DialogDeleteReasonsBinding.inflate(inflater, container, false)
        binding.vm=modifyDialogViewModel
        binding.lifecycleOwner=this

        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.window?.requestFeature(Window.FEATURE_NO_TITLE)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        modifyDialogViewModel.isSelectedList.value= MutableList(5) {false}
        binding.btnDialogConfirm.setOnClickListener {
            modifyDialogViewModel.apply {
                isDeleteClick.value = isActive.value!!
            }
            if(modifyDialogViewModel.isDeleteClick.value!!) dismiss()
            Log.e("isDeleteClick",modifyDialogViewModel.isDeleteClick.value.toString())

        }
    }

    override fun onResume() {
        super.onResume()
        val width = resources.getDimensionPixelSize(R.dimen.dialog_width)
        val height = resources.getDimensionPixelSize(R.dimen.dialog_delete_height)
        dialog?.window?.setLayout(width,height)
    }

}

