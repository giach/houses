package main.java

import java.sql.Connection
import java.sql.DriverManager
import java.sql.Statement

fun get_connection(): Connection? {
//    val JDBC_DRIVER = "com.mysql.cj.jdbc.Driver"
    val DB_URL = "jdbc:mysql://localhost:3306/imb"

    val USER = "root"
    val PASS = ""

    var conn: Connection? = null
    var stmt: Statement? = null

//    Class.forName(JDBC_DRIVER)

    println("Connecting to database...")
    conn = DriverManager.getConnection(DB_URL, USER, PASS)

    return conn
}

fun add_house(house: House, conn: Connection) {
//    house_id VARCHAR(255) NOT NULL,
//    price VARCHAR(255) NOT NULL,
//    rooms VARCHAR(255),
//    ap_area varchar(255),
//    ap_level VARCHAR(255),
//    block_type varchar(255),
//    link varchar(255),
//    ap_description varchar(255),

    conn.setAutoCommit(false);
    val statement = conn.prepareStatement("insert into HOUSES (house_id, price, rooms, ap_area, ap_level, block_type, link, ap_description) values (?,?,?,?,?,?,?,?)")
    statement.setString(1, house.id)
    statement.setString(2, house.price)
    statement.setString(3, house.rooms)
    statement.setString(4, house.area)
    statement.setString(5, house.level)
    statement.setString(6, house.blockType)
    statement.setString(7, house.link)
    statement.setString(8, house.description)

    statement.executeUpdate()
    conn.commit()
}
