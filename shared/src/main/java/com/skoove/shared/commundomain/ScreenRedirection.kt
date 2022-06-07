package com.skoove.shared.commundomain

enum class ScreenBX(val source: String , val destination : String) {
    SCREENB1("screenB1","screenC1"),
    SCREENB2("screenB2","screenC2"),
    SCREENB3("screenB3","screenC2"),
    NOSCREENB("noScreenB ","screenC2")



}
fun getScreenBxDestination( screenName : String) : String =
    when(screenName) {
        ScreenBX.SCREENB1.source  ->  ScreenBX.SCREENB1.destination
        else -> ScreenBX.SCREENB1.destination

    }