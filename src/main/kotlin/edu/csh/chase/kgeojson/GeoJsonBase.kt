package edu.csh.chase.kgeojson

import edu.csh.chase.kgeojson.boundingbox.BoundingBox
import edu.csh.chase.kgeojson.coordinatereferencesystem.CoordinateReferenceSystem
import edu.csh.chase.kjson.JsonObject
import edu.csh.chase.kjson.JsonSerializable

abstract class GeoJsonBase(val type: GeoJsonType, //What type of GeoJson this is
                           val coordinateReferenceSystem: CoordinateReferenceSystem?,
                           val boundingBox: BoundingBox?,
                           val properties: JsonObject) : JsonSerializable {//Properties is the actual object this is

    override fun jsonSerialize(): String {
        return properties.toString()
    }

    //TODO is this needed?
    operator fun get(key: String): Any? {
        return properties[key]
    }

}