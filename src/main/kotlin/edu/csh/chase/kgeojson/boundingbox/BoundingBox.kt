package edu.csh.chase.kgeojson.boundingbox

import edu.csh.chase.kgeojson.Position

public data class BoundingBox(val bottomLeft: Position, val topRight: Position) {

    public val dimensions: Int = bottomLeft.dimensions

}