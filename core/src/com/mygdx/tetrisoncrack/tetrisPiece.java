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

    // Rotates the piece
    void rotate(){

        // Create a new "rotated" piece
        int[][] new_grid = new int[pieceGrid.length][pieceGrid[0].length];

        for(int i = 0; i < pieceGrid.length; i++){
            for(int j = 0; j < pieceGrid.length; j++){
                new_grid[j][pieceGrid.length - 1 - i] = pieceGrid[i][j];
            }
        }
        // Replace the grid with the new grid
        pieceGrid = new_grid;
    }
}
