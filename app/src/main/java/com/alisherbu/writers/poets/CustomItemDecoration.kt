package com.alisherbu.writers.poets

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class CustomItemDecoration(context: Context) : RecyclerView.ItemDecoration() {

    private val paddingTopBottom: Int
    private val paddingSeparator: Int

    init {
        // Convert dp to pixels
        paddingTopBottom = dpToPx(context, 16)
        paddingSeparator = dpToPx(context, 6)
    }

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        // First item top padding
        if (parent.getChildAdapterPosition(view) == 0) {
            outRect.top = paddingTopBottom
        } else {
            outRect.top = paddingSeparator
        }

        // Last item bottom padding
        if (parent.getChildAdapterPosition(view) == state.itemCount - 1) {
            outRect.bottom = paddingTopBottom
        } else {
            outRect.bottom = paddingSeparator
        }

        // Left and right padding can be adjusted as needed
        outRect.left = 0
        outRect.right = 0
    }

    // Helper function to convert dp to pixels
    private fun dpToPx(context: Context, dp: Int): Int {
        val density = context.resources.displayMetrics.density
        return (dp * density).toInt()
    }
}