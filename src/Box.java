import java.util.*;
public class Box {
	
	private int value;
	private ArrayList possibleValues;
	private int row;
	private int col;
	public int square;
	private boolean changeable;
	private Box[] boxRow;
	private Box[] boxCol;
	private Box[] boxSquare;
	
	public Box(int r, int c, int s) {
		row = r;
		col = c;
		square = s;
		value = 0;
		changeable = true;
		possibleValues = new ArrayList<Integer>();
		possibleValues.add(1);
		possibleValues.add(2);
		possibleValues.add(3);
		possibleValues.add(4);
		possibleValues.add(5);
		possibleValues.add(6);
		possibleValues.add(7);
		possibleValues.add(8);
		possibleValues.add(9);
	}
	
	public Box(int r, int c, int v, int s) {
		row = r;
		col = c;
		value = v;
		square = s;
		changeable = false;	
	}
	
	public boolean isChangeable() {
		return changeable;
	}
	
	public void setRowColSquare(Board b) {
		boxRow = b.getRow(row);
		boxCol = b.getCol(col);
		boxSquare = b.getSquare(square);
	}
	
	public int getValue() {
		return value;
	}
	
	public ArrayList<Integer> getPossibleValues() {
		return possibleValues;
	}
	
	public ArrayList<Integer> setPossibleValues() {
		for (Box box : boxRow) {
			for (int i = 0; i < possibleValues.size(); i++) {
				if (box.getValue() == (int)possibleValues.get(i)) {
					//System.out.println("REMOVED ROW: " + possibleValues.get(i));
					possibleValues.remove(possibleValues.indexOf((Integer)box.getValue()));
				}
			}
		}
		for (Box box : boxCol) {
			for (int i = 0; i < possibleValues.size(); i++) {
				if (box.getValue() == (int)possibleValues.get(i)) {
					possibleValues.remove(possibleValues.indexOf((Integer)box.getValue()));
				}
			}
		}
		for (Box box : boxSquare) {
			for (int i = 0; i < possibleValues.size(); i++) {
				if (box.getValue() == (int)possibleValues.get(i)) {
					possibleValues.remove(possibleValues.indexOf((Integer)box.getValue()));
				}
			}
		}
		
		if (possibleValues.size() > 0)
			return possibleValues;
		return new ArrayList<Integer>();
	}
	
	public int getRow() {
		return row;
	}
	
	public int getCol() {
		return col;
	}
	
	public void setChangeable(boolean change) {
		changeable = change;
	}
	
	public void setValue(int val) {
		value = val;
	}
}
