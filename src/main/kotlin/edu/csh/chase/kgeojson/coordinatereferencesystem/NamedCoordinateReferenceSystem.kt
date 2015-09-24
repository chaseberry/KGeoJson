package edu.csh.chase.kgeojson.coordinatereferencesystem

data class NamedCoordinateReferenceSystem(val name: String) : CoordinateReferenceSystem(CoordinateReferenceSystemType.Name)