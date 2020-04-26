package main.java

import org.jsoup.nodes.Element

fun getLink(elem: Element): String {
    return elem.attr("href")
}

fun getTitle(elem: Element): String {
    return elem.attr("title")
}

fun getId(elem: Element): String? {
    val pattern = "[-][a-zA-Z0-9]+[?]".toRegex()
    val id = pattern.find(getLink(elem))?.value

    return id?.removePrefix("-")?.removeSuffix("?")
}

fun getText(elem: Element, className: String): String {
    return elem.getElementsByClass(className).text()
}

fun isRoomNr(elem: String): Boolean {
    val pattern = "camer".toRegex()
    return pattern.containsMatchIn(elem)
}

fun isArea(elem: String): Boolean {
    val pattern = "[0-9]+\\s+mp".toRegex()
    return pattern.containsMatchIn(elem)
}

fun isLevel(elem: String): Boolean {
    val pattern = "Etaj|Parter".toRegex()
    return pattern.containsMatchIn(elem)
}

fun isRoomsType(elem: String): Boolean {
    val pattern = "decomandat".toRegex()
    return pattern.containsMatchIn(elem)
}

fun isBlockType(elem: String): Boolean {
    val pattern = "Bloc nou".toRegex()
    return pattern.containsMatchIn(elem)
}
