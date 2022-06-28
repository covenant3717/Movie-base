package com.example.feature_bottom_dialog

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.evgenykuksov.core.extensions.buildBackgroundDrawable
import com.evgenykuksov.core.items.CustomEmptyItem
import com.evgenykuksov.core.items.CustomTextItem
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.xwray.groupie.GroupieAdapter
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.bottom_dialog_fragment.*

class BottomDialogFragment : BottomSheetDialogFragment() {

    private val listItems: List<Item> by lazy { arguments?.getSerializable(ARG_LIST_ITEMS) as List<Item> }
    private val adapter = GroupieAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.BottomDialogTransparentBackground)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.bottom_dialog_fragment, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bottomContainer.background = requireContext().buildBackgroundDrawable(
            colorRes = R.color.core_black_80,
            topCornersRadiusRes = R.dimen.dimen_30
        )
        rvItems.adapter = adapter.apply { update(listItems) }
    }

    companion object {

        private const val ARG_LIST_ITEMS = "arg_list_items"

        fun createBundle(listItems: List<*>) = Bundle().apply {
            val serializableListItems: ArrayList<Item>? = listItems.toMutableList() as? ArrayList<Item>

            if (serializableListItems.isNullOrEmpty()) putSerializable(ARG_LIST_ITEMS, buildStubItems())
            else putSerializable(ARG_LIST_ITEMS, serializableListItems)
        }

        private fun buildStubItems() = arrayListOf(
            CustomEmptyItem(R.dimen.dimen_30),
            CustomTextItem(
                textContentRes = R.string.text_stub,
                styleRes = R.style.TextAppearance_MaterialComponents_Headline6,
                colorRes = R.color.core_white_40,
                sideMarginsRes = R.dimen.dimen_20,
                gravityState = Gravity.CENTER_HORIZONTAL
            ),
            CustomEmptyItem(R.dimen.dimen_60)
        )
    }
}