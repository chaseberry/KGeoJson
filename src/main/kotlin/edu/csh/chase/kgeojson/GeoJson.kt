package edu.csh.chase.kgeojson

import edu.csh.chase.kgeojson.boundingbox.BoundingBox
import edu.csh.chase.kgeojson.coordinatereferencesystem.CoordinateReferenceSystem
import edu.csh.chase.kgeojson.coordinatereferencesystem.LinkedCoordinateReferenceSystem
import edu.csh.chase.kgeojson.coordinatereferencesystem.NamedCoordinateReferenceSystem
import edu.csh.chase.kgeojson.coordinatereferencesystem.UnknownCoordinateReferenceSystem
import edu.csh.chase.kjson.JsonArray
import edu.csh.chase.kjson.JsonObject
import java.net.URI
import java.net.URISyntaxException

public object GeoJson {

    fun parse(geoObject: JsonObject): GeoJsonBase? {
        val type = geoObject.getString("type") ?: return null

        val crs = if ("crs" in geoObject) {
            if (geoObject.isNull("crs")) {
                UnknownCoordinateReferenceSystem()
            } else {
                //TODO Type check this?
                parseCoordinateReferenceSystem(geoObject.getJsonObject("crs")!!)
            }
        } else {
            null
        }

        val bbox = if (geoObject.getJsonArray("bbox") != null) {
            parseBoundingBox(geoObject.getJsonArray("bbox")!!)
        } else {
            null
        }

        return when (type) {
            "Point" -> parsePoint(geoObject, crs, bbox)
            "MultiPoint" -> parseMutliPoint(geoObject, crs, bbox)
            else -> null
        }
    }

    private fun parsePoint(geoObject: JsonObject, crs: CoordinateReferenceSystem?, bbox: BoundingBox?): GeoJsonPoint? {
        val coordinates = geoObject.getJsonArray("coordinates") ?: return null
        val position = positionFromJson(coordinates) ?: return null
        return GeoJsonPoint(position, geoObject, crs, bbox)
    }

    private fun parseMutliPoint(geoObject: JsonObject, crs: CoordinateReferenceSystem?, bbox: BoundingBox?): GeoJsonMutliPoint? {
        val coordinates = geoObject.getJsonArray("coordinates") ?: return null
        val points: List<Position> = coordinates.map {
            if (it is JsonArray) {
                positionFromJson(it)
            } else {
                null
            }
        }.filterNotNull()
        return GeoJsonMutliPoint(Array(points.size()) { points[it] }, geoObject, crs, bbox)
    }

    fun parseCoordinateReferenceSystem(crsObject: JsonObject): CoordinateReferenceSystem? {
        val type = crsObject.getString("type") ?: return null
        return when (type) {
            "name" -> parseCrsName(crsObject)
            "link" -> parseCrsLink(crsObject)
            else -> null
        }
    }

    private fun parseCrsName(crsObject: JsonObject): NamedCoordinateReferenceSystem? {
        val name = crsObject.traverse("properties:name") as? String ?: return null
        //TODO Check if name is a valid CRS
        return NamedCoordinateReferenceSystem(name)
    }

    private fun parseCrsLink(crsObject: JsonObject): LinkedCoordinateReferenceSystem? {
        val href = crsObject.traverse("properties:href") as? String ?: return null
        val uriHref = try {
            URI(href)
        } catch(e: URISyntaxException) {
            return null
        }
        val linkType = crsObject.traverse("properties:type") as? String
        return LinkedCoordinateReferenceSystem(uriHref, linkType)
    }

    fun parseBoundingBox(bboxArray: JsonArray): BoundingBox? {
        if (bboxArray.size < 4 || bboxArray.size % 2 == 1) {
            return null
        }

        val count = bboxArray.size / 2
        val lowerArr = JsonArray()
        val upperArr = JsonArray()
        for (z in 0..(count - 1)) {
            lowerArr.put(bboxArray.get(z))
        }
        for (z in count..(bboxArray.size - 1)) {
            upperArr.put(bboxArray.get(z))
        }
        val p1 = positionFromJson(lowerArr) ?: return null
        val p2 = positionFromJson(upperArr) ?: return null
        return BoundingBox(p1, p2)

    }

}