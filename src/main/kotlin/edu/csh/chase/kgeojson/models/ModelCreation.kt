package edu.csh.chase.kgeojson.models

import edu.csh.chase.kjson.JsonArray

fun positionFromJson(positionArray: JsonArray): Position? {
    val x = positionArray.getDouble(0) ?: return null
    val y = positionArray.getDouble(1) ?: return null
    val z = positionArray.getDouble(2)
    return Position(x, y, z)
}