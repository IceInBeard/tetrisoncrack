package com.mygdx.tetrisoncrack;


public class tetrisPiece {
    int x, y, type, widht, height;
    int [][] pieceGrid;

    public tetrisPiece(int pieceType){

        // Initial position for piece measured in the grid system from bottom left (starting at 0, not 1)
        // refers to bottom left in pieceGrid
        x = 4;
        y = 18 ;

        type = pieceType;
        // Shape of the piece
        pieceGrid = Ass.tetrisPieces[pieceType];

        widht = pieceGrid.length;



    }

    void movePieceDown(){
        y -= 1;
    }
    void movePieceRight(){
        x += 1;
    }
    void movePieceLeft(){
        x -= 1;
    }
}
