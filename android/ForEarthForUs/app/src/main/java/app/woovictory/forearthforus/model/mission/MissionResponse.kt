package app.woovictory.forearthforus.model.mission

/**
 * Created by VictoryWoo
 * Mock 데이터.
 * Model 패키지는 사용될 데이터가 위치하거나
 * data 패키지는 repository, source 같은 것들이 위치.
 */
data class MissionResponse(
    val mainImage : Int,
    val mainTitle : String,
    val mainContents : String
)