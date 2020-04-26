package main.java

import org.jsoup.Jsoup
import java.sql.Connection
import java.sql.DriverManager
import java.sql.Statement


fun getHouses(link: String): MutableList<House> {
    var houses = mutableListOf<House>()

    val doc = Jsoup.connect(link).get()
    val boxes = doc.select(".box-anunt")

    boxes.forEach { box ->
        var house = House()
        val hLinks =  box.select("a.img-block")
        hLinks.forEach {
            house.link = getLink(it)
            house.description = getTitle(it)
            house.id = getId(it)
        }
        val hPrices = box.select(".pret")
        hPrices.forEach {
            house.price = getText(it, "pret-mare")
            house.currency = getText(it, "tva-luna")
        }

        val hCharacteristics = box.select("ul.caracteristici")
        hCharacteristics.forEach {
            val apCharcs = it.select("li")
            apCharcs.forEach {
                val charcs: String = it.text()
                when {
                    isRoomNr(charcs) -> house.rooms = charcs
                    isArea(charcs) -> house.area = charcs
                    isLevel(charcs) -> house.level = charcs
                    isRoomsType(charcs) -> house.roomType = charcs
                    isBlockType(charcs) -> house.blockType = charcs
                }
            }
        }
        if (house.id != null)
            houses.add(house)
    }
    return houses
}

fun main() {

    val imob = "https://www.imobiliare.ro/vanzare-apartamente/bucuresti?pagina="
    val last = 1
    var houses = mutableListOf<House>()

    for (page in 1..last) {
        houses = getHouses("$imob$page")
    }
    for (h in houses) {
        println(h.toString())
    }

    val conn = get_connection()
    if (conn != null) {
        for (h in houses) {
            add_house(h, conn)
        }
    }

}
