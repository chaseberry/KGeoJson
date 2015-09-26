package edu.csh.chase.kgeojson.boundingbox

import edu.csh.chase.kgeojson.models.Position

public data class BoundingBox(val bottomLeft: Position, val topRight: Position) {

    public val dimensions: Int = bottomLeft.dimensions

    public fun contains(position: Position): Boolean {
        return position.x >= bottomLeft.x &&
                position.y >= bottomLeft.y &&
                position.x <= topRight.x &&
                position.y <= topRight.y &&
                ((position.z == null ||
                        bottomLeft.z == null ||
                        topRight.z == null) ||
                        (position.z >= bottomLeft.z &&
                                position.z <= topRight.z))
    }

}