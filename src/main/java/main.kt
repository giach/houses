package main.java

import org.jsoup.Jsoup

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
        val hAddresses = box.select(".localizare")
        hAddresses.forEach {
            house.address = getText(it)
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
    val last = 100


//    var house = House()
//     house.id = " 2323"
//    house.price = "3434"
//     house.rooms = "3"
//    house.area = "45"
//    house.level = "4"
//    house.blockType = "bloc no"
//     house.link = "link"
//    house.description = "descriere"

    for (page in 1..last) {
        println("$imob$page")
        var houses = mutableListOf<House>()
        houses = getHouses("$imob$page")

        val conn = get_connection()
        if (conn != null) {
            for (h in houses) {
                add_house(h, conn)
            }
        }
    }
//    for (h in houses) {
//        println(h.toString())
//    }




}
