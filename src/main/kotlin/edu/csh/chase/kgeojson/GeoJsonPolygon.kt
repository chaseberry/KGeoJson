package edu.csh.chase.kgeojson

import edu.csh.chase.kgeojson.boundingbox.BoundingBox
import edu.csh.chase.kgeojson.coordinatereferencesystem.CoordinateReferenceSystem
import edu.csh.chase.kgeojson.models.Polygon
import edu.csh.chase.kjson.JsonObject

class GeoJsonPolygon(val polygon: Polygon,
                     properties: JsonObject,
                     crs: CoordinateReferenceSystem? = null,
                     boundingBox: BoundingBox? = null) :
        GeoJsonBase(GeoJsonType.Polygon, crs, boundingBox, properties) {
}
