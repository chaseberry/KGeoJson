package edu.csh.chase.kgeojson

import edu.csh.chase.kgeojson.boundingbox.BoundingBox
import edu.csh.chase.kgeojson.coordinatereferencesystem.CoordinateReferenceSystem

class GeoJsonPoint(val position: Position,
                   crs: CoordinateReferenceSystem? = null,
                   boundingBox: BoundingBox? = null) : GeoJsonBase(GeoJsonType.Point, crs, boundingBox) {


}