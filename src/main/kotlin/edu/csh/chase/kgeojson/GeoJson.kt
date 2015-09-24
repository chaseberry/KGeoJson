package edu.csh.chase.kgeojson

import edu.csh.chase.kgeojson.coordinatereferencesystem.CoordinateReferenceSystem
import edu.csh.chase.kgeojson.coordinatereferencesystem.LinkedCoordinateReferenceSystem
import edu.csh.chase.kgeojson.coordinatereferencesystem.NamedCoordinateReferenceSystem
import edu.csh.chase.kjson.JsonObject
import java.net.URI
import java.net.URISyntaxException

public object GeoJson {

    fun parse(geoObject: JsonObject): GeoJsonBase? {
        val type = geoObject.getString("type") ?: return null
        return when (type) {
            "Point" -> parsePoint(geoObject)
            else -> null
        }
    }

    fun parsePoint(geoObject: JsonObject): GeoJsonPoint? {
        val coordinates = geoObject.getJsonArray("coordinates") ?: return null
        val position = positionFromJson(coordinates) ?: return null
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

}