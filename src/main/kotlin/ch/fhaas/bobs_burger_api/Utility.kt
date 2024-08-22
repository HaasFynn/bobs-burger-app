package ch.fhaas.bobs_burger_api

import java.awt.Desktop
import java.net.URI

fun printErrln(msg: Any) {
    System.err.println(msg)
}

fun openUrl(url: String): Boolean {
    if (!isValidUrl(url)) {
        printErrln("Invalid URL")
        return false
    }
    if (!Desktop.isDesktopSupported()) {
        println("Desktop is not supported on this platform.")
        return false
    }
    val desktop = Desktop.getDesktop()
    if (!desktop.isSupported(Desktop.Action.BROWSE)) {
        println("Browse action is not supported on this platform.")
        return false
    }
    desktop.browse(URI.create(url))
    return true
}

fun isValidUrl(url: String): Boolean {
    val regex = Regex("""https?://([A-z0-9]|-|/|\.|:)+/?(([A-z0-9]|-|/|\.)+)?""")
    return url.matches(regex)
}

fun printWarning(msg: Any) {
    println("[Warning]: $msg")
}