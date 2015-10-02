package edu.csh.chase.kgeojson

import edu.csh.chase.kgeojson.boundingbox.BoundingBox
import edu.csh.chase.kgeojson.coordinatereferencesystem.CoordinateReferenceSystem
import edu.csh.chase.kgeojson.models.Polygon
import edu.csh.chase.kjson.JsonObject

class GeoJsonMultiPolygon(val polygons: Array<Polygon>,
                          properties: JsonObject,
                          crs: CoordinateReferenceSystem? = null,
                          boundingBox: BoundingBox? = null) :
        GeoJsonBase(GeoJsonType.MultiPoint, crs, boundingBox, properties), Iterable<Polygon> {

    override fun iterator(): Iterator<Polygon> = polygons.iterator()

    operator fun get(index: Int): Polygon = polygons[index]

}