package thrash;

import java.util.ArrayList;
import java.util.function.Predicate;

import javafx.scene.paint.Color;

public class Terrain {

	private int size;

	private Cell[][] terrain;
	private Ant ant1;

	public Terrain(int size) {
		this.size = size;

		terrain = new Cell[this.size][this.size];

		int a = 0;
		int b = 0;

		while (a < this.size) {
			while (b < this.size) {
				terrain[a][b] = new Cell(a, b);
				b++;
			}
			a++;
			b = 0;
		}
		int middle = size / 2;
		this.ant1 = new Ant(++middle, ++middle, this);
		changeCellColor(Color.RED, ant1.getCell());

	}
	
	public void moveAnt() {
		ant1.move();
	}
	
	public Cell[][] getTerrain() {
		return terrain;
	}

	public int getSize() {
		return size;
	}
	
	public ArrayList<Cell> filterCellTest(Predicate<Cell> tester){
		ArrayList<Cell> list = new ArrayList<>();
		
		for(Cell[] a: terrain) {
			for (Cell b: a) {
				if(tester.test(b)) {
					list.add(b);
				}
			}
		}
		return list;
	}
	
	public ArrayList<Cell> getCellsWithColor(Color color){
		return filterCellTest(b -> b.getCellColor().equals(color));
	}

	public Color getCellColor(int x, int y) {
		return terrain[--x][--y].getCellColor();
	}

	public void changeCellColor(Color color, Cell c) {
		c.changeCellColor(color);
	}

	@Override
	public String toString() {
		StringBuffer result = new StringBuffer("");

		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {

				if (terrain[i][j].getCellColor().equals(Color.WHITE)) {
					result.append("o ");
				}
				if (terrain[i][j].getCellColor().equals(Color.RED)) {
					result.append("@ ");
				}
				if (terrain[i][j].getCellColor().equals(Color.BLACK)) {
					result.append("+ ");
				}
			}
			result.append("\n");

		}
		return result.toString();
	}

}
