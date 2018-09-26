package io.github.anderscheow.library.util

import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View

class VerticalSpaceItemDecoration(private val verticalSpaceHeight: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView,
                                state: RecyclerView.State) {
        parent.adapter?.let { adapter ->
            if (parent.getChildAdapterPosition(view) != adapter.itemCount - 1) {
                outRect.bottom = verticalSpaceHeight
            }
        }
    }
}