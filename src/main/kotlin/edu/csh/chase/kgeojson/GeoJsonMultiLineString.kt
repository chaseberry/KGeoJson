package edu.csh.chase.kgeojson

import edu.csh.chase.kgeojson.boundingbox.BoundingBox
import edu.csh.chase.kgeojson.coordinatereferencesystem.CoordinateReferenceSystem
import edu.csh.chase.kgeojson.models.Line
import edu.csh.chase.kjson.JsonObject

class GeoJsonMultiLineString(val lines: Array<Line>,
                             properties: JsonObject,
                             crs: CoordinateReferenceSystem? = null,
                             boundingBox: BoundingBox? = null) :
        GeoJsonBase(GeoJsonType.MultiLineString, crs, boundingBox, properties),
        Iterable<Line> {

    override fun iterator(): Iterator<Line> = lines.iterator()

    operator fun get(index: Int): Line = lines[index]

}