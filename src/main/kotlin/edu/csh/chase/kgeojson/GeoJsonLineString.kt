package edu.csh.chase.kgeojson

import edu.csh.chase.kgeojson.boundingbox.BoundingBox
import edu.csh.chase.kgeojson.coordinatereferencesystem.CoordinateReferenceSystem
import edu.csh.chase.kjson.JsonObject

class GeoJsonLineString(val line: Line,
                        properties: JsonObject,
                        crs: CoordinateReferenceSystem? = null,
                        boundingBox: BoundingBox? = null) :
        GeoJsonBase(GeoJsonType.LineString, crs, boundingBox, properties),
        Iterable<Position> {

    override fun iterator(): Iterator<Position> = line.iterator()

    fun get(index: Int): Position = line[index]

}