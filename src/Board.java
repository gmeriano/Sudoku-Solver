import java.util.ArrayList;

public class Board {
	
	private Box[][] boxes;
	private ArrayList<Box> possibleValues;
	
	public Board(int[][] sudokuBoard) {
		boxes = new Box[9][9];
		possibleValues = new ArrayList<Box>();
		for (int r = 0; r < sudokuBoard.length; r++)
			for (int c = 0; c < sudokuBoard[r].length; c++) {
				boxes[r][c] = new Box(r,c,getSquare(r,c));
				boxes[r][c].setValue(sudokuBoard[r][c]);
			}
		for (int r = 0; r < sudokuBoard.length; r++)
			for (int c = 0; c < sudokuBoard[r].length; c++) 
				boxes[r][c].setRowColSquare(this);
	}
	
	public static int getSquare(int r, int c) {
		int square = 0;
		if (r < 3 && c < 3)
			square = 0;
		else if (r < 3 && c > 2 && c < 6)
			square = 1;
		else if (r < 3 && c >= 6)
			square = 2;
		else if (r >= 3 && r < 6 && c < 3)
			square = 3;
		else if (r >= 3 && r < 6 && c > 2 && c < 6)
			square = 4;
		else if (r >= 3 && r < 6 && c > 5)
			square = 5;
		else if (r >= 6 && c < 3)
			square = 6;
		else if (r >= 6 && c > 2 && c < 6)
			square = 7;
		else if (r >= 6 && c > 5)
			square = 8;
		
		return square;
			
	}
	
	public Box getBox(int r, int c) {
		return boxes[r][c];
	}
	
	public Box[][] getBoard() {
		return boxes;
	}
	
	public Box[] getRow(int row) {
		return boxes[row];
	}
	
	public Box[] getCol(int col) {
		Box[] boxColumn = new Box[9];
		for (int r = 0; r < boxes.length; r++)
			boxColumn[r] = boxes[r][col];
		return boxColumn;
	}
	
	public ArrayList<Box> getPossibleBoxes() {
		return possibleValues;
	}
	
	//top left square = 0; top middle = 1; top right = 2
	//mid left = 3; mid mid = 4; mid right = 5;
	//bot left = 6; bot mid = 7; bot right = 8;
	public Box[] getSquare(int square) {
		switch (square) {
		case 0: return new Box[] {boxes[0][0],boxes[0][1],boxes[0][2],boxes[1][0],boxes[1][1],boxes[1][2],boxes[2][0],boxes[2][1],boxes[2][2]};
		case 1: return new Box[] {boxes[0][3],boxes[0][4],boxes[0][5],boxes[1][3],boxes[1][4],boxes[1][5],boxes[2][3],boxes[2][4],boxes[2][5]};
		case 2: return new Box[] {boxes[0][6],boxes[0][7],boxes[0][8],boxes[1][6],boxes[1][7],boxes[1][8],boxes[2][6],boxes[2][7],boxes[2][8]};
		case 3: return new Box[] {boxes[3][0],boxes[3][1],boxes[3][2],boxes[4][0],boxes[4][1],boxes[4][2],boxes[5][0],boxes[5][1],boxes[5][2]};
		case 4: return new Box[] {boxes[3][3],boxes[3][4],boxes[3][5],boxes[4][3],boxes[4][4],boxes[4][5],boxes[5][3],boxes[5][4],boxes[5][5]};
		case 5: return new Box[] {boxes[3][6],boxes[3][7],boxes[3][8],boxes[4][6],boxes[4][7],boxes[4][8],boxes[5][6],boxes[5][7],boxes[5][8]};
		case 6: return new Box[] {boxes[6][0],boxes[6][1],boxes[6][2],boxes[7][0],boxes[7][1],boxes[7][2],boxes[8][0],boxes[8][1],boxes[8][2]};
		case 7: return new Box[] {boxes[6][3],boxes[6][4],boxes[6][5],boxes[7][3],boxes[7][4],boxes[7][5],boxes[8][3],boxes[8][4],boxes[8][5]};
		case 8: return new Box[] {boxes[6][6],boxes[6][7],boxes[6][8],boxes[8][6],boxes[8][7],boxes[8][8],boxes[8][6],boxes[8][7],boxes[8][8]};
		default: return new Box[]{};
		}
	}
	
}
