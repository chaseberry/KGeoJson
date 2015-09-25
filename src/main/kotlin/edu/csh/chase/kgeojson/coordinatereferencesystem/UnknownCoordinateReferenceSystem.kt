package edu.csh.chase.kgeojson.coordinatereferencesystem

class UnknownCoordinateReferenceSystem : CoordinateReferenceSystem(CoordinateReferenceSystemType.Unknown) {
    override fun jsonSerialize(): String {
        return "null"
    }
}