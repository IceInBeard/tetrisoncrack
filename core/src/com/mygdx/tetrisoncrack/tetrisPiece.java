package com.mygdx.tetrisoncrack;


public class tetrisPiece {
    int x, y, type;
    int [][] pieceGrid;

    public tetrisPiece(int pieceType){

        // Initial position for piece measured in the grid system from bottom left (starting at 0, not 1)
        // refers to bottom left in pieceGrid
        x = 4;
        y = 18 ;

        type = pieceType;
        // Shape of the piece
        pieceGrid = Ass.tetrisPieces[pieceType];



    }

    void movePieceDown(){
        y -= 1;
    }
}
