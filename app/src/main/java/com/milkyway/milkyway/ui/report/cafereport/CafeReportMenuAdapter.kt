package com.milkyway.milkyway.ui.report.cafereport

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.Toast
import androidx.appcompat.view.ContextThemeWrapper
import androidx.recyclerview.widget.RecyclerView
import com.milkyway.milkyway.R
import com.milkyway.milkyway.ui.main.MainActivity


class CafeReportMenuAdapter(private var context: Context): RecyclerView.Adapter<CafeReportMenuViewHolder>() {

    var data = mutableListOf<CafeReportMenuData>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CafeReportMenuViewHolder {
        val view = LayoutInflater.from(context).inflate(
            R.layout.item_cafe_report_menu,
            parent,
            false
        )
        return CafeReportMenuViewHolder(view)
    }

    override fun onBindViewHolder(holder: CafeReportMenuViewHolder, position: Int) {
        holder.onBind(data[position])
        holder.btnViewOption.setOnClickListener {
            val popupMenu: PopupMenu = PopupMenu(
                ContextThemeWrapper(
                    context,
                    R.style.PopupMenuStyle
                ), holder.btnViewOption
            )
            popupMenu.inflate(R.menu.option_menu)
            popupMenu.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener() {
                onMenuItemClick(context, it, position)
            })
            popupMenu.show()
        }
    }

    override fun getItemCount(): Int = data.size

    private fun onMenuItemClick(context: Context, item: MenuItem, position: Int): Boolean {
        when (item.itemId) {
            R.id.edit -> {
                Toast.makeText(context, "수정버튼눌림", Toast.LENGTH_SHORT).show()
                editItem(context, position)
            }
            R.id.delete -> {
                Toast.makeText(context, "삭제버튼눌림", Toast.LENGTH_SHORT).show()
                removeItem(position)
            }
        }
        return false
    }

    private fun removeItem(position: Int) {
        data.removeAt(position)
        notifyDataSetChanged()
    }

    private fun editItem(context: Context, position: Int) {
        val intent = Intent(context as MainActivity, CafeReportMenuActivity::class.java)
        //val intent2 = Intent(context as MainActivity, MainActivity::class.java)
        //val manager: android.app.FragmentManager? = (context as MainActivity).fragmentManager
        //val fagment = manager?.findFragmentByTag(R.layout.fragment_cafe_report.toString())
        intent.putExtra("menu", data[position].cafeMenuName.toString())
        intent.putExtra("price", data[position].cafeMenuPrice.toString())
        intent.putExtra("category",data[position].cafeMenuPrice.toString())
        intent.putExtra("posi", position)
        Log.d("메롱", CafeReportMenuActivity.EDIT_CODE.toString())
        context.startActivityForResult(intent, CafeReportMenuActivity.EDIT_CODE)
        //(context as MainActivity).startActivity(intent2)
    }
}
