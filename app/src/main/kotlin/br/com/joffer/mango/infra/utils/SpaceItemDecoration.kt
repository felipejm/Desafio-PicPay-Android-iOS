package br.com.joffer.mango.infra.utils

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class SpaceItemDecoration @JvmOverloads constructor(private val space: Int, private val addSpaceAboveFirstItem: Boolean = DEFAULT_ADD_SPACE_ABOVE_FIRST_ITEM,
                                                    private val addSpaceBelowLastItem: Boolean = DEFAULT_ADD_SPACE_BELOW_LAST_ITEM
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView,
                                state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)
        if (space <= 0) {
            return
        }

        if (addSpaceAboveFirstItem && parent.getChildLayoutPosition(view) < 1 || parent.getChildLayoutPosition(view) >= 1) {
            if (getOrientation(parent) == LinearLayoutManager.VERTICAL) {
                outRect.top = space
            } else {
                outRect.left = space
            }
        }

        if (addSpaceBelowLastItem && parent.getChildAdapterPosition(view) == getTotalItemCount(parent) - 1) {
            if (getOrientation(parent) == LinearLayoutManager.VERTICAL) {
                outRect.bottom = space
            } else {
                outRect.right = space
            }
        }

        if (parent.layoutManager is GridLayoutManager) {
            if(parent.getChildLayoutPosition(view) % 2 == 0) {
                outRect.set(space, space, 0, space)
            }else{
                outRect.set(0, space, space, space)
            }
        }
    }

    private fun getTotalItemCount(parent: RecyclerView): Int {
        return parent.adapter!!.itemCount
    }

    private fun getOrientation(parent: RecyclerView): Int {
        return if (parent.layoutManager is LinearLayoutManager) {
            (parent.layoutManager as LinearLayoutManager).orientation
        } else {
            throw IllegalStateException(
                    "SpaceItemDecoration can only be used with a LinearLayoutManager.")
        }
    }

    companion object {
        private val DEFAULT_ADD_SPACE_ABOVE_FIRST_ITEM = false
        private val DEFAULT_ADD_SPACE_BELOW_LAST_ITEM = false
    }
}