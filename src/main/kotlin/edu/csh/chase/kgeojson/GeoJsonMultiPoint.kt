package edu.csh.chase.kgeojson

import edu.csh.chase.kgeojson.boundingbox.BoundingBox
import edu.csh.chase.kgeojson.coordinatereferencesystem.CoordinateReferenceSystem
import edu.csh.chase.kjson.JsonObject

class GeoJsonMultiPoint(val points: Array<Position>,
                        properties: JsonObject,
                        crs: CoordinateReferenceSystem? = null,
                        boundingBox: BoundingBox? = null) : GeoJsonBase(GeoJsonType.MultiPoint, crs, boundingBox, properties) {

}