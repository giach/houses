package main.java

class House {
    var location: String = ""
    var price: String = ""
    var currency: String = ""
    var rooms: String = ""
    var area: String = ""
    var level: String = ""
    var blockType: String = ""
    var roomType: String = ""
    var link: String = ""
    var description: String = ""
    var address: String = ""
    var id: String? = null


//    Apartment at ${price} price, with ${rooms} rooms,
//    with ${size} size and at {$location}. Level {$level}.
    override fun toString(): String {
        return """
            Link: $link
            ID : $id
            Price : $price $currency
            Rooms: $rooms $roomType
            Area: $area
            Level: $level
            Block Type: $blockType
            Location: $address
            Description $description
        """
    }
}
