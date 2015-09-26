package edu.csh.chase.kgeojson.models

data class Line(val points: Array<Position>) : Iterable<Position> {

    override fun iterator(): Iterator<Position> = points.iterator()

    fun get(index: Int): Position = points[index]

}