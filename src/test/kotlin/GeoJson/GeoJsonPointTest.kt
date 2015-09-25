package GeoJson

import edu.csh.chase.kgeojson.GeoJson
import edu.csh.chase.kgeojson.GeoJsonPoint
import edu.csh.chase.kgeojson.GeoJsonType
import edu.csh.chase.kjson.json
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull

class GeoJsonPointTest() {

    @Test fun testBasicGeoJsonPoint() {
        val jsonPoint = json(
                "type" to "Point",
                "coordinates" to json[
                        100.0,
                        10.0
                        ]
        )

        val point = GeoJson.parse(jsonPoint)
        assertNotNull(point)
        assertEquals(GeoJsonType.Point, point!!.type)

        assertNull(point.coordinateReferenceSystem)
        assertNull(point.boundingBox)

        assertEquals(10.0, (point as GeoJsonPoint).y)
        assertEquals(100.0, point.x)
        assertNull(point.z)

    }

    @Test fun testComplexGeoJsonPoint() {
        val jsonPoint = json(
                "type" to "Point",
                "key" to "value",
                "coordinates" to json[
                        100.0,
                        10.0,
                        18.0
                        ]
        )

        val point = GeoJson.parse(jsonPoint)
        assertNotNull(point)
        assertEquals(GeoJsonType.Point, point!!.type)

        assertEquals("value", point["key"])
        assertNull(point.coordinateReferenceSystem)
        assertNull(point.boundingBox)

        assertEquals(10.0, (point as GeoJsonPoint).y)
        assertEquals(100.0, point.x)
        assertEquals(18.0, point.z)
    }

}