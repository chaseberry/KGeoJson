package edu.csh.chase.kgeojson.coordinatereferencesystem

import edu.csh.chase.kjson.json

data class NamedCoordinateReferenceSystem(val name: String) : CoordinateReferenceSystem(CoordinateReferenceSystemType.Name) {
    override fun jsonSerialize(): String {
        return json(
                "type" to "name",
                "properties" to json(
                        "name" to name
                )
        ).toString()
    }
}