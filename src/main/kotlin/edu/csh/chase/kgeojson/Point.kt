package edu.csh.chase.kgeojson

import edu.csh.chase.kgeojson.boundingbox.BoundingBox
import edu.csh.chase.kgeojson.coordinatereferencesystem.CoordinateReferenceSystem

class GeoJsonPoint(val position: Position,
                   properties: Map<String, Any?>,
                   crs: CoordinateReferenceSystem? = null,
                   boundingBox: BoundingBox? = null) : GeoJsonBase(GeoJsonType.Point, crs, boundingBox, properties) {

    val x = position.x

    val y = position.y

    val z = position.z

}