package edu.csh.chase.kgeojson.coordinatereferencesystem

import edu.csh.chase.kjson.json
import java.net.URI

data public class LinkedCoordinateReferenceSystem(val href: URI, val linkType: String?) : CoordinateReferenceSystem(CoordinateReferenceSystemType.Link) {
    override fun jsonSerialize(): String {
        return json(
                "type" to "link",
                "properties" to json(
                        "href" to href.toString()
                ).putNotNull("type", linkType)
        ).toString()
    }
}