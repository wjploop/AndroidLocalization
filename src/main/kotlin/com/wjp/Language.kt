package com.wjp

enum class Language(
    val englishName:String,
    val codeForApi:String,
    val suffixForFile:String
){
    English("English","en","en"),
    ChineseSimplified("ChineseSimplified","zh","zh"),
    ChineseTraditional("ChineseTraditional","cht","zh-rTW"),
    Japanese("Japanese","jp","ja"),
    Korean("Korean","kor","ko")

}