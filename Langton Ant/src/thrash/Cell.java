package thrash;

import javafx.scene.paint.Color;

public class Cell {
	static int cellCount = 0;
	
	final int CELLNUMBER;
	private int positionX;
	private int positionY;
	
	
	
	private Color color = Color.WHITE;
	private Color oldColor = Color.WHITE;
	
	public Cell (int x, int y) {
		this.positionX = x;
		this.positionY = y;
		this.CELLNUMBER = cellCount;
		cellCount++;
		
		System.out.println(this.toString());
	}
	
	public int getCellNumber() {
		return CELLNUMBER;
	}
	
	public Color getCellColor(){
		return color;
	}
	
	public Color getCellOldColor(){
		return oldColor;
	}
	
	public int[] getCellPosition() {
		int[] positionXY = {positionX, positionY};
		return positionXY;
	}

	public void changeCellColor(Color color){
		this.color = color;
	}
	
	public void changeOldCellColor(Color oldColor){
		this.oldColor = oldColor;
	}
	
	@Override
	public String toString(){
		return "Num: " + CELLNUMBER + "  Position: " + getCellPosition()[0]+ "," + getCellPosition()[1]
		+ "  Color: " + getCellColor() + "  Old Color: " + getCellOldColor();
	}
	
	
}
