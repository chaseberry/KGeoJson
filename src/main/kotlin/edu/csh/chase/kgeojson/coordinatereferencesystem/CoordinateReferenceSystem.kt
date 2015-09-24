package edu.csh.chase.kgeojson.coordinatereferencesystem

import edu.csh.chase.kjson.JsonSerializable

//TODO consider keeping the rest of properties here?

abstract class CoordinateReferenceSystem(val type: CoordinateReferenceSystemType) : JsonSerializable