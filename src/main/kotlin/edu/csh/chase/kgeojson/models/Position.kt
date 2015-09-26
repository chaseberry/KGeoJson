package edu.csh.chase.kgeojson.models

//TODO Add support for N arguments past the x, y, z
public data class Position(val x: Double, val y: Double, val z: Double?) {

    //TODO is the number of dimensions == size of the position array? Do args past z count?
    public val dimensions: Int = if ( z == null) 2 else 3

}