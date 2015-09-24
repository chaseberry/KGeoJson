package edu.csh.chase.kgeojson

import edu.csh.chase.kjson.JsonArray

//TODO Add support for N arguments past the x, y, z

data class Position(val x: Double, val y: Double, val z: Double?) {

    //TODO is the number of dimensions == size of the position array?
    val dimensions: Int = if ( z == null) 2 else 3

}

fun positionFromJson(positionArray: JsonArray): Position? {
    val x = positionArray.getDouble(0) ?: return null
    val y = positionArray.getDouble(1) ?: return null
    val z = positionArray.getDouble(2)
    return Position(x, y, z)
}