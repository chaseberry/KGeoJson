package edu.csh.chase.kgeojson

import edu.csh.chase.kgeojson.boundingbox.BoundingBox
import edu.csh.chase.kgeojson.coordinatereferencesystem.CoordinateReferenceSystem
import edu.csh.chase.kjson.JsonObject

abstract class GeoJsonBase(val type: GeoJsonType,
                           val coordinateReferenceSystem: CoordinateReferenceSystem?,
                           val boundingBox: BoundingBox?,
                           val properties: JsonObject) {

    fun get(key: String): Any? {
        return properties[key]
    }

}