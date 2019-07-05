package app.woovictory.forearthforus.util

import app.woovictory.forearthforus.R

/**
 * Created by VictoryWoo
 */
const val baseURL = "http://looksgoood.pythonanywhere.com"
const val headerInterceptor = "headerInterceptor"
const val loggingInterceptor = "loggingInterceptor"
const val TAG = "VictoryWoo 94"
const val LEVEL = "LV."
const val TIME_INTERVAL: Long = 2000
const val MISSION_STATUS_COMPLETE: String = "complete"
const val MISSION_STATUS_PROGRESS: String = "progress"
const val MISSION_STATUS_NEW: String = "new"
const val NAME = "ForEarth"
const val PREF_USER_TOKEN = "ForEarthForUs"
const val PREF_EARTH_LEVEL = "EarthLevel"
const val PREF_USER_NAME = "UserName"
const val PREF_USER_ID = "UserId"
const val PREF_USER_EMAIL = "UserEmail"
const val PREF_USER_CONTENT = "UserContent"
const val PREF_USER_MISSION_COMPLETE_STATUS = "UserStatus"
const val PREF_USER_MISSION_COMPLETE_COUNT = "UserCount"
const val PREF_USER_FIRST_STATE = "UserFirstState"
val earthLevelList = arrayListOf(
    R.drawable.main_bar_graph1,
    R.drawable.main_bar_graph2,
    R.drawable.main_bar_graph3,
    R.drawable.main_bar_graph4,
    R.drawable.main_bar_graph5,
    R.drawable.main_bar_graph6,
    R.drawable.main_bar_graph7,
    R.drawable.main_bar_graph8
)

val earthStatusList = arrayListOf(
    R.drawable.earth_1,
    R.drawable.earth_2,
    R.drawable.earth_3,
    R.drawable.earth_4,
    R.drawable.earth_5,
    R.drawable.earth_6,
    R.drawable.earth_7,
    R.drawable.earth_8
)

val missionCategoryList = arrayListOf(
    R.drawable.mission_clear_greenlife,
    R.drawable.mission_clear_bear,
    R.drawable.mission_clear_fish,
    R.drawable.mission_clear_reduce,
    R.drawable.mission_clear_earth,
    R.drawable.mission_clear_desert,
    R.drawable.mission_clear_dust
)

val missionCategoryTitleList = arrayListOf(
    "실천하는 그린 라이프",
    "갈 곳 잃은 북극곰을 구해주세요",
    "사라져가는 물고기를 구해주세요",
    "일회용품을 줄여봐요",
    "뜨거운 지구를 위한 에너지 절약",
    "사라져가는 산림을 지켜주세요",
    "콜록콜록, 숨쉴 수가 없어요"
)