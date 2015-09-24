package edu.csh.chase.kgeojson.coordinatereferencesystem

import java.net.URI

data public class LinkedCoordinateReferenceSystem(val href: URI, val linkType: String?) : CoordinateReferenceSystem(CoordinateReferenceSystemType.Link)