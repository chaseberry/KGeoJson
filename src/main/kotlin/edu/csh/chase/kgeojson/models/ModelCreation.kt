package edu.csh.chase.kgeojson.models

import edu.csh.chase.kjson.JsonArray

fun positionFromJson(positionArray: JsonArray): Position? {
    val x = positionArray.getDouble(0) ?: return null
    val y = positionArray.getDouble(1) ?: return null
    val z = positionArray.getDouble(2)
    return Position(x, y, z)
}

fun positionsFromJson(positionArray: JsonArray): Array<Position>? {
    val points = positionArray.map {
        if (it is JsonArray) {
            positionFromJson(it) ?: return null
        } else {
            return null
        }
    }.filterNotNull()
    return Array(points.size()) { points[it] }
}

fun lineFromJson(positionArray: JsonArray): Line? {
    val arr = positionsFromJson(positionArray) ?: return null
    return Line(arr)
}

fun linesFromJson(positionArray: JsonArray): Array<Line>? {
    val lines = positionArray.map {
        if (it is JsonArray) {
            lineFromJson(it) ?: return null
        } else {
            return null
        }
    }.filterNotNull()
    return Array(lines.size()) { lines[it] }
}

fun polygonFromJson(positionArray: JsonArray): Polygon? {
    if (positionArray.size < 1) {
        return null
    }
    val lineArray = positionArray.getJsonArray(0) ?: return null
    val poly = lineFromJson(lineArray) ?: return null
    if (!poly.isLinearRing()) {
        return null
    }
    if (positionArray.size == 1) {
        return Polygon(poly, emptyArray())
    }
    val json = JsonArray()
    for (z in 1..positionArray.size - 1) {
        json.put(positionArray[z])
    }
    val holes = linesFromJson(json) ?: emptyArray()
    return Polygon(poly, holes)
}
