import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Main implements MouseListener {
	
	public static final int FULL_HEIGHT = 648;
	public static final int WIDTH = 648;
	public static final int HEIGHT = FULL_HEIGHT - 100;
	
	public static int[][] board = new int[9][9];
	public static int[] boardCols = new int[9];
	public static int[] boardRows = new int[9];
	public static int[] boardSquare0 = new int[9];
	
	public static Board mainBoard;
	
	
	public static void main(String args[]) {

		for (int i = 0; i < board.length; i++)
			for (int j = 0; j < board[i].length; j++) 
				board[i][j] = 0;
		
		
		mainBoard = new Board(board);
		
		JFrame window = new JFrame();
		
		window.setSize(WIDTH+20,FULL_HEIGHT+100);
		window.setTitle("Sudoku!");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
		window.setLocationRelativeTo(null);
		
		SurfacePainter dc = new SurfacePainter();
		window.add(dc);	

		JButton solver = new JButton("Solve");
		dc.add(solver);
		solver.setBounds(Main.WIDTH/2-80,Main.HEIGHT+100,150,50);
		
		
		window.addMouseListener(new MouseAdapter() {
		     @Override
		     public void mousePressed(MouseEvent e) {
		        int x = (e.getX()-10)/(WIDTH/9);
		        int y = (e.getY()-45)/(FULL_HEIGHT/9);
	        	System.out.println(x + " " + y);

		        if (x<9 && y <9) {
		        	mainBoard.getBox(x, y).setValue(Integer.parseInt((String)JOptionPane.showInputDialog(
		                    window,"Enter a number:","Box " + x + "," + y,JOptionPane.PLAIN_MESSAGE,null,null,"")));
		        	mainBoard.getBox(x, y).setChangeable(false);
		        }		     
		     }
		  });
		
		
		solver.addActionListener(new ActionListener() {
		       public void actionPerformed(ActionEvent e) {
		             // this makes sure the button you are pressing is the button variable
		             if(e.getSource() == solver) {
		                solve();
		              }
		       }
		 });
		
		
		//TODO 
		//Add button to say start solving
		
	}
	
	public static void solve() {
		for (Box[] boxArray : mainBoard.getBoard()) {
			for (Box box : boxArray) {
				System.out.print(box.getValue() + " ");
			}
			System.out.println();
		}
		
		System.out.println("SOLVING...SOLVING...SOLVING...\nSOLVING...SOLVING...SOLVING...\nSOLVING...SOLVING...SOLVING...");
		
		boolean solved = false;
		
		firstSolve();
		
		for (Box[] boxArray : mainBoard.getBoard()) {
			for (Box box : boxArray) {
				if (box.getValue() == 0) 
					solved = false;
				else 
					solved = true;
			}
		}
		
		if (!solved) {
			secondSolve();
			secondSolve();
			secondSolve();
		}
		
		for (Box[] boxArray : mainBoard.getBoard()) {
			for (Box box : boxArray) {
				System.out.print(box.getValue() + " ");
			}
			System.out.println();
		}
	}

	public static void firstSolve() {
		int counter = 0;
		do {
			counter = 0;
			for (Box[] boxArray : mainBoard.getBoard()) {
				for (Box box : boxArray) {
					box.setPossibleValues();
					if (box.getPossibleValues().size() == 1 && box.isChangeable()) {
						box.setValue(box.getPossibleValues().get(0));
						box.setChangeable(false);
						counter = 1;
					}
				}
			}
		} while (counter != 0);
	}
	
	public static void secondSolve() {
		boolean solved = false;
		Box currentBox = null;
		//while (!solved) {
			if (currentBox == null) {
				for (Box[] boxArray : mainBoard.getBoard()) {
					for (Box box : boxArray) {
						if (box.getValue() == 0 && box.isChangeable() && box.getPossibleValues().size() > 0) {
							box.setValue(box.getPossibleValues().get(0));
							currentBox = box;
							firstSolve();
					}
						if (box.getPossibleValues().size() == 0 && box.getValue() == 0) {
							//get the previous box in possible boxes array
							continue;
						}
						/*if (box.getPossibleValues().size() == 2) {
							box.setValue(box.getPossibleValues().get(0));
							mainBoard.getPossibleBoxes().add(box);
							firstSolve(true);
						}*/
						
					}
				}
			}
			else {
				//set currentBox to next possible value
				if (currentBox.getPossibleValues().size() > 1) {
					currentBox.setValue(currentBox.getPossibleValues().get(currentBox.getPossibleValues().indexOf(currentBox.getValue()+1)));
				}
				firstSolve();
			}
			/*boolean notSolved = false;
			for (Box[] boxArray : mainBoard.getBoard()) {
				for (Box box : boxArray) {
					if (box.getValue() == 0)
						notSolved = true;
				}
			}
			if (!notSolved)
				solved = true;*/
		//}
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
