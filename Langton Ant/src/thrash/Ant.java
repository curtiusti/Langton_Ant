package thrash;

import java.util.Random;
import javafx.scene.paint.Color;

public class Ant {

	private static int antCount = 0;
	private int id;
	private Cell occupiedCell;
	private Terrain onTerrain;

	public Ant(int x, int y, Terrain t) {
		antCount++;
		this.id = antCount;
		occupiedCell = t.getTerrain()[x][y];
		this.onTerrain = t;
	}
	
	public Cell getCell() {
		return occupiedCell;
	}

	public void move() {		
		Random r = new Random();
		int randomDirection = r.nextInt(3);
		
		int x = occupiedCell.getCellPosition()[0];
		int y = occupiedCell.getCellPosition()[1];
		System.out.println(occupiedCell);
		
		//change color depending on the old cell color
		if(occupiedCell.getCellOldColor().equals(Color.WHITE)) {
			occupiedCell.changeCellColor(Color.BLACK);
			occupiedCell.changeOldCellColor(Color.BLACK);
		} else {
			occupiedCell.changeCellColor(Color.WHITE);
			occupiedCell.changeOldCellColor(Color.WHITE);
		}
				
		
		if(randomDirection == 0) {
			//go top cell
			if(x > 0) {
				occupiedCell = onTerrain.getTerrain()[--x][y];
			} else {
				occupiedCell = onTerrain.getTerrain()[onTerrain.getSize()- 1][y];
			}
		}
		
		if(randomDirection == 1) {
			//go bot cell
			if(x < (onTerrain.getSize()- 1)) {
				occupiedCell = onTerrain.getTerrain()[++x][y];
			} else {
				occupiedCell = onTerrain.getTerrain()[0][y];
			}
		}
		
		if(randomDirection == 2) {
			//go left cell
			if(y > 0) {
				occupiedCell = onTerrain.getTerrain()[x][--y];
			} else {
				occupiedCell = onTerrain.getTerrain()[x][onTerrain.getSize()- 1];

			}
		}
		
		if(randomDirection == 3) {
			//go right cell
			if(y < (onTerrain.getSize()- 1)) {
				occupiedCell = onTerrain.getTerrain()[x][++y];
			}  else {
				occupiedCell = onTerrain.getTerrain()[x][0];

			}
		}

		
		occupiedCell.changeCellColor(Color.RED);
		System.out.println(occupiedCell);
		
	}
}
