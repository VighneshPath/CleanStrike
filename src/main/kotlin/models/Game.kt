package models

import java.io.ByteArrayInputStream
import java.io.DataInputStream
import java.io.InputStream
import java.io.PrintWriter

class Game(val board: Board) {
    fun startGame(player1: Player, player2: Player, inputStream: DataInputStream, outputStream: PrintWriter){
        var isPlayer1Turn = true
        while(true){
            val player = if(isPlayer1Turn) player1 else player2
            isPlayer1Turn = !isPlayer1Turn
            inputStream.readInt()
        }
    }
}