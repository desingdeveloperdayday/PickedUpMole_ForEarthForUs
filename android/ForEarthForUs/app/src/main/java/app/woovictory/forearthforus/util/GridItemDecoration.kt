package app.woovictory.forearthforus.util

import android.graphics.Rect
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by VictoryWoo
 */
class GridItemDecoration(private var space: Int, private var side: Int) : RecyclerView.ItemDecoration() {

    // getItemOffsets() 함수를 통해서 RecyclerView 안에 있는 아이템에게 여백을 설정할 수 있다.
    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)

        // 각 아이템의 포지션을 받아온다.
        var position = parent.getChildAdapterPosition(view)
        // 전체 아이템 갯수를 받아온다.
        var itemCount = state.itemCount

        // 상, 하 설정.
        /*if (position == 0 || position == 1) {
            //outRect.top = space
            //outRect.bottom = space
        } else {
            outRect.bottom = space
        }*/

        if(position == itemCount-1){
            outRect.bottom = space*2
        }
        Log.v("99889900009 position ", position.toString())
        Log.v("99889900009 itemCount", itemCount.toString())




        // GridLayoutManager, StaggeredGridLayout 에서만 받아올 수 있는 함수이다.
        // spanCount = 2일 때, 0은 왼쪽, 1은 오른쪽 아이템을 나타낸다.
        val lp: GridLayoutManager.LayoutParams = view.layoutParams as GridLayoutManager.LayoutParams
        val spanIndex = lp.spanIndex

        // 왼쪽 아이템.

        when (spanIndex) {
            0 -> { // 왼쪽 아이템.
                outRect.left = space
            }
            1 -> { // 오른쪽 아이템.
                outRect.right = space
                //outRect.right = space

            }
        }


    }

}