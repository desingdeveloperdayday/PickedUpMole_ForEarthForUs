package app.woovictory.forearthforus.model.article.detail

/**
 * Created by VictoryWoo
 * articleDetail
 * url: String
 * like: Boolean
 * 위의 두 프로퍼티가 추가되어야 할 것! 서버 통신 시!
 */
data class ArticleDetailResponse(
    val articleDetailImage: Int,
    val articleDetailTitle: String,
    val articleDetailSubTitle: String
)