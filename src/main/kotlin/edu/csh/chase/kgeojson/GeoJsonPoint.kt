package edu.csh.chase.kgeojson

import edu.csh.chase.kgeojson.boundingbox.BoundingBox
import edu.csh.chase.kgeojson.coordinatereferencesystem.CoordinateReferenceSystem
import edu.csh.chase.kgeojson.models.Position
import edu.csh.chase.kjson.JsonObject

class GeoJsonPoint(val position: Position,
                   properties: JsonObject,
                   crs: CoordinateReferenceSystem? = null,
                   boundingBox: BoundingBox? = null) : GeoJsonBase(GeoJsonType.Point, crs, boundingBox, properties) {

    val x = position.x

    val y = position.y

    val z = position.z

}