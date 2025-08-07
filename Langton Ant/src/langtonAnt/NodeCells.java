package langtonAnt;

import java.util.Random;
import javafx.animation.FillTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class NodeCells extends Application {

	private double sceneSide = 1000;

	private int n = 500;

	double gridSide = sceneSide / n;

	NodeCells.SquareCells[][] playfield = new NodeCells.SquareCells[n][n];

	Ant ant1;

	public void start(Stage primaryStage) {

		Group root = new Group();

		// initialize playfield
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {

				// create node
				NodeCells.SquareCells node = new NodeCells.SquareCells(i, j, i * gridSide, j * gridSide, gridSide);

				// add node to group
				root.getChildren().add(node);

				// add to playfield for further reference using an array
				playfield[i][j] = node;

			}

		}

		int middle = n / 4 - 1;
		ant1 = new Ant(middle, middle);
		// System.out.println(ant1.getCell().getCellColor());
		ant1.getCell().changeCellColor(Color.RED);
		// System.out.println(ant1.getCell().getCellColor()+ " "
		// + ant1.getCell().getCellPosition()[0] + " " +
		// ant1.getCell().getCellPosition()[1]);

		Scene scene = new Scene(root, sceneSide, sceneSide);

		primaryStage.setScene(scene);
		primaryStage.show();

		animate();
	}

	private void animate() {
		Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1), e -> {
			ant1.moveLangton();

		}));
		timeline.setCycleCount(Timeline.INDEFINITE);
		timeline.play();
	}

	public static void main(String[] args) {
		launch(args);
	}

	public class Ant {

		int step = 0;
		private static int antCount = 0;
		private int id;
		private NodeCells.SquareCells occupiedCell;
		private int direction = (new Random().nextInt(5)) % 4;

		public Ant(int x, int y) {
			antCount++;
			this.id = antCount;
			occupiedCell = playfield[x][y];
		}

		public NodeCells.SquareCells getCell() {
			return occupiedCell;
		}

		public void moveLangton() {
			step++;
			System.out.println("Step " + step);
			int antX = occupiedCell.getCellPosition()[0];
			int antY = occupiedCell.getCellPosition()[1];
			NodeCells.SquareCells rightCell = null;
			NodeCells.SquareCells leftCell = null;
			

			//get right and left cell according to direction
			switch (direction) {

			// direction is top
			case 0:

				rightCell = antY < n - 1 ? playfield[antX][antY + 1] : playfield[antX][0];

				leftCell = antY > 0 ? playfield[antX][antY - 1] : playfield[antX][n - 1];
				
				break;

			// direction is right
			case 1:

				rightCell = antX < n - 1 ? playfield[antX + 1][antY] : playfield[antX][0];

				leftCell = antX > 0 ? playfield[antX - 1][antY] : playfield[n-1][antY];
				
				
				break;

			// direction is bottom
			case 2:

				rightCell = antY > 0 ? playfield[antX][antY - 1] : playfield[antX][n - 1];

				leftCell = antY < n - 1 ? playfield[antX][antY + 1] : playfield[antX][0];
				
				break;

			// direction is left
			case 3:

				rightCell = antX > 0 ? playfield[antX - 1][antY] : playfield[n - 1][antY];

				leftCell = antX < n - 1 ? playfield[antX + 1][antY] : playfield[0][antY];
				
				break;
				
			default: 
				System.out.println("Something went wrong.");
			}
			

			if (occupiedCell.getCellOldColor().equals(Color.WHITE)) {

				Color randomColor = Color.rgb((new Random().nextInt() & 255),
						(new Random().nextInt() & 255), (new Random().nextInt() & 255));
				occupiedCell.animateCellColorChange(occupiedCell, Color.BLACK, 5);
				occupiedCell.changeOldCellColor(Color.BLACK);
				occupiedCell = rightCell;
				direction = (direction + 1) % 4;
				
			} else {
				occupiedCell.animateCellColorChange(occupiedCell, Color.WHITE, 5);
				occupiedCell.changeOldCellColor(Color.WHITE);		
				occupiedCell = leftCell;
				direction = (direction - 1) % 4 >= 0 ? (direction - 1) % 4 : 3;
			}
			
			occupiedCell.animateCellColorChange(occupiedCell, Color.RED, 5);

		}

		public void moveRandom() {
			Random r = new Random();
			int randomDirection = r.nextInt(4);

			int x = occupiedCell.getCellPosition()[0];
			int y = occupiedCell.getCellPosition()[1];
			// System.out.println(occupiedCell);

			// change color depending on the old cell color
			if (occupiedCell.getCellOldColor().equals(Color.WHITE)) {

				occupiedCell.animateCellColorChange(occupiedCell, Color.rgb((new Random().nextInt() & 255),
						(new Random().nextInt() & 255), (new Random().nextInt() & 255)), 50);
				occupiedCell.changeOldCellColor(Color.BLACK);
			} else {
				occupiedCell.animateCellColorChange(occupiedCell, Color.WHITE, 50);
				occupiedCell.changeOldCellColor(Color.WHITE);
			}

			if (randomDirection == 0) {
				// go top cell
				if (x > 0) {
					occupiedCell = playfield[--x][y];
				} else {
					occupiedCell = playfield[n - 1][y];
				}
			}

			if (randomDirection == 1) {
				// go bot cell
				if (x < (n - 1)) {
					occupiedCell = playfield[++x][y];
				} else {
					occupiedCell = playfield[0][y];
				}
			}

			if (randomDirection == 2) {
				// go left cell
				if (y > 0) {
					occupiedCell = playfield[x][--y];
				} else {
					occupiedCell = playfield[x][n - 1];

				}
			}

			if (randomDirection == 3) {
				// go right cell
				if (y < (n - 1)) {
					occupiedCell = playfield[x][++y];
				} else {
					occupiedCell = playfield[x][0];

				}
			}

			occupiedCell.animateCellColorChange(occupiedCell, Color.RED, 500);
		}
	}

	public static class SquareCells extends StackPane {

		static int cellCount = 0;

		private Rectangle rectangle;

		CellData cell = new CellData(Color.WHITE, Color.WHITE);

		public SquareCells(int xNum, int yNum, double x, double y, double side) {

			// get coordinates
			this.cell.positionX = xNum;
			this.cell.positionY = yNum;
			this.cell.CELLNUMBER = cellCount;
			cellCount++;

			// create rectangle
			this.rectangle = new Rectangle(side, side);
			rectangle.setStroke(Color.WHITE);
			rectangle.setStrokeWidth(0.0);
			rectangle.setFill(cell.color);

			// set position
			setTranslateX(x);
			setTranslateY(y);

			getChildren().addAll(rectangle);
		}

		public Rectangle getRectangle() {
			return rectangle;
		}

		public int getCellNumber() {
			return cell.CELLNUMBER;
		}

		public Color getCellColor() {
			return cell.color;
		}

		public void animateCellColorChange(NodeCells.SquareCells squareCells, Color toColor, int durationMillis) {
			FillTransition ft = new FillTransition(Duration.millis(durationMillis), squareCells.getRectangle());
			ft.setFromValue(squareCells.getCellColor());
			ft.setToValue(toColor);
			ft.setCycleCount(1);
			ft.setAutoReverse(false);
			ft.play();

			// Update the stored color after animation starts
			squareCells.changeCellColor(toColor);
		}

		public Color getCellOldColor() {
			return cell.oldColor;
		}

		public int[] getCellPosition() {
			int[] positionXY = { cell.positionX, cell.positionY };
			return positionXY;
		}

		public void changeCellColor(Color color) {
			this.cell.color = color;
			rectangle.setFill(color);
		}

		public void changeOldCellColor(Color oldColor) {
			this.cell.oldColor = oldColor;
		}

		@Override
		public String toString() {
			return "Num: " + cell.CELLNUMBER + "  Position: " + getCellPosition()[0] + "," + getCellPosition()[1]
					+ "  Color: " + getCellColor() + "  Old Color: " + getCellOldColor();
		}
	}

}
