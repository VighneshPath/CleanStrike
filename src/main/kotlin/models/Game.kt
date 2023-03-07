package models

import java.io.ByteArrayInputStream
import java.io.DataInputStream
import java.io.InputStream
import java.io.PrintWriter

class Game(val board: Board, private val player1: Player, private val player2: Player) {
    fun startGame(inputStream: DataInputStream, outputStream: PrintWriter){
        var isPlayer1Turn = true
        while(true){
            val player = if(isPlayer1Turn) player1 else player2
            isPlayer1Turn = !isPlayer1Turn
            inputStream.readInt()
        }
    }
}