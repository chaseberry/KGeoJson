package edu.csh.chase.kgeojson.models

data class Line(val points: Array<Position>) : Iterable<Position> {

    override fun iterator(): Iterator<Position> = points.iterator()

    operator public fun get(index: Int): Position = points[index]

    public fun isLinearRing(): Boolean = points.size() > 4 && points.first() == points.last()

}