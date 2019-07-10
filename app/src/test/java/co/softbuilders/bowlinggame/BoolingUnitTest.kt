package co.softbuilders.bowlinggame

import org.junit.Assert.*
import org.junit.Test
import BowlingEngine

/**
 * Unit tests for the Bowling Engine logic.
 */
class BoolingUnitTest {

    @Test
    fun engineCorrectComputationData1() {
        assertEquals(BowlingEngine.computeGame(exempleData1), "| 5| 14| 29| 49| 60| 61| 77| 97| 117| 133")
    }

    @Test
    fun engineCorrectComputationData2() {
        assertEquals(BowlingEngine.computeGame(exempleData2), "| 20| 39| 48| 66| 74| 84| 90| 120| 148| 167")
    }

    @Test
    fun engineCorrectComputationData3() {
        assertEquals(BowlingEngine.computeGame(exempleData3), "| 30| 60| 90| 120| 150| 180| 210| 240| 270| 300")
    }

    @Test //this test should not pass as the provided answer does not match the correct response
    fun engineFalseComputationData3() {
        assertEquals(BowlingEngine.computeGame(exempleData3), "| 30| 60| 90| 120| 150| 180| 210| 240| 270| 301")
    }



    companion object{
        val exempleData1 : MutableList<BowlingEngine.FrameResult> =
            mutableListOf(
                BowlingEngine.FrameResult(mutableListOf("1", "4")),
                BowlingEngine.FrameResult(mutableListOf("4","5")),
                BowlingEngine.FrameResult(mutableListOf("6", "SPARE")),
                BowlingEngine.FrameResult(mutableListOf("5", "SPARE")),
                BowlingEngine.FrameResult(mutableListOf("STRIKE")),
                BowlingEngine.FrameResult(mutableListOf("0","1")),
                BowlingEngine.FrameResult(mutableListOf("7","SPARE")),
                BowlingEngine.FrameResult(mutableListOf("6","SPARE")),
                BowlingEngine.FrameResult(mutableListOf("STRIKE")),
                BowlingEngine.FrameResult(mutableListOf("2","SPARE","6"))
            )

        val exempleData2 : MutableList<BowlingEngine.FrameResult> =
            mutableListOf(
                BowlingEngine.FrameResult(mutableListOf("STRIKE")),
                BowlingEngine.FrameResult(mutableListOf("7","SPARE")),
                BowlingEngine.FrameResult(mutableListOf("9", "0")),
                BowlingEngine.FrameResult(mutableListOf("STRIKE")),
                BowlingEngine.FrameResult(mutableListOf("0","8")),
                BowlingEngine.FrameResult(mutableListOf("8","SPARE")),
                BowlingEngine.FrameResult(mutableListOf("0","6")),
                BowlingEngine.FrameResult(mutableListOf("STRIKE")),
                BowlingEngine.FrameResult(mutableListOf("STRIKE")),
                BowlingEngine.FrameResult(mutableListOf("STRIKE","8","1"))
            )

        val exempleData3 : MutableList<BowlingEngine.FrameResult> =
            mutableListOf(
                BowlingEngine.FrameResult(mutableListOf("STRIKE")),
                BowlingEngine.FrameResult(mutableListOf("STRIKE")),
                BowlingEngine.FrameResult(mutableListOf("STRIKE")),
                BowlingEngine.FrameResult(mutableListOf("STRIKE")),
                BowlingEngine.FrameResult(mutableListOf("STRIKE")),
                BowlingEngine.FrameResult(mutableListOf("STRIKE")),
                BowlingEngine.FrameResult(mutableListOf("STRIKE")),
                BowlingEngine.FrameResult(mutableListOf("STRIKE")),
                BowlingEngine.FrameResult(mutableListOf("STRIKE")),
                BowlingEngine.FrameResult(mutableListOf("STRIKE","STRIKE","STRIKE"))
            )

    }
}