package crs

import edu.csh.chase.kgeojson.GeoJson
import edu.csh.chase.kgeojson.coordinatereferencesystem.CoordinateReferenceSystemType
import edu.csh.chase.kgeojson.coordinatereferencesystem.LinkedCoordinateReferenceSystem
import edu.csh.chase.kgeojson.coordinatereferencesystem.NamedCoordinateReferenceSystem
import edu.csh.chase.kjson.json
import org.junit.Test
import java.net.URI
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull
import kotlin.test.assertTrue

class CrsTest {

    @Test fun testInvalidCrs() {
        val crsObject = json(
                "type" to "invalid",
                "properties" to json(
                        "name" to "CRS"
                )
        )
        assertNull(GeoJson.parseCoordinateReferenceSystem(crsObject))
    }

    @Test fun testNamedCrs() {
        val crsObject = json(
                "type" to "name",
                "properties" to json(
                        "name" to "CRS"
                )
        )

        val namedCrs = GeoJson.parseCoordinateReferenceSystem(crsObject)
        assertNotNull(namedCrs)
        assertTrue(namedCrs is NamedCoordinateReferenceSystem)
        assertEquals(CoordinateReferenceSystemType.Name, namedCrs!!.type)
        assertEquals("CRS", (namedCrs as NamedCoordinateReferenceSystem).name)

    }

    @Test fun testBasicLinkedCrs() {
        val crsObject = json(
                "type" to "link",
                "properties" to json(
                        "href" to "http://example.com"
                )
        )
        val linkedCrs = GeoJson.parseCoordinateReferenceSystem(crsObject)
        assertNotNull(linkedCrs)
        assertTrue(linkedCrs is LinkedCoordinateReferenceSystem)
        assertEquals(CoordinateReferenceSystemType.Link, linkedCrs!!.type)
        assertEquals(URI("http://example.com"), (linkedCrs as LinkedCoordinateReferenceSystem).href)
        assertNull(linkedCrs.linkType)
    }

    @Test fun testFullLinkedCrs() {
        val crsObject = json(
                "type" to "link",
                "properties" to json(
                        "href" to "http://example.com",
                        "type" to "proj4"
                )
        )
        val linkedCrs = GeoJson.parseCoordinateReferenceSystem(crsObject)
        assertNotNull(linkedCrs)
        assertTrue(linkedCrs is LinkedCoordinateReferenceSystem)
        assertEquals(CoordinateReferenceSystemType.Link, linkedCrs!!.type)
        assertEquals(URI("http://example.com"), (linkedCrs as LinkedCoordinateReferenceSystem).href)
        assertEquals("proj4", linkedCrs.linkType)
    }

}