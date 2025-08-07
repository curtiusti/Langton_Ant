package langtonAnt;

import javafx.scene.paint.Color;

public class CellData {
	public int CELLNUMBER;
	public int positionX;
	public int positionY;
	public Color color;
	public Color oldColor;

	public CellData(Color color, Color oldColor) {
		this.color = color;
		this.oldColor = oldColor;
	}
}