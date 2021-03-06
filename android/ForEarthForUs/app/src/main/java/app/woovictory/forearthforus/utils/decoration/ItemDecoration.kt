package app.woovictory.forearthforus.utils.decoration

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by VictoryWoo
 * RecyclerView Item Decoration
 * side : 양 끝 넓이.
 * space : 아이템 사이 간격.
 */
class ItemDecoration(private val space: Int, private val side: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)
        when (parent.getChildAdapterPosition(view)) {
            0 -> {
                outRect.right = space //don't forget about recycling...
                outRect.left = side + 20

            }
            state.itemCount - 1 -> {
                outRect.right = side //don't forget about recycling...
                outRect.left = space
            }
            else -> {
                outRect.left = space
                outRect.right = space
            }
        }

    }
}