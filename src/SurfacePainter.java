import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
//import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
//import java.awt.AlphaComposite;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.imageio.ImageIO;
import javax.swing.JButton;
//import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SurfacePainter extends JPanel {
	
	private void doDrawing(Graphics g) throws IOException {	
		Graphics2D g2d = (Graphics2D) g;
				
		drawBoard(g2d);
		
		//TODO
		//look through board and add numbers to where they should go
		
	}
	
	private void drawBoard(Graphics2D g2d) {
		g2d.setFont(new Font("TimesRoman", Font.PLAIN, 36));
		for (int i = 1; i < 9; i++) {
				g2d.drawLine(i*(Main.WIDTH/9), 0, i*(Main.WIDTH/9), (Main.FULL_HEIGHT));	
		}
		for (int i = 1; i < 10; i++) {
			g2d.drawLine(0, i*(Main.FULL_HEIGHT/9), (Main.WIDTH), i*(Main.FULL_HEIGHT/9));
		}
		for (int r = 0; r < 9; r++) {
			for (int c = 0; c < 9; c++) {
				String val = Integer.toString(Main.mainBoard.getBoard()[r][c].getValue());
				g2d.drawString(val, r*(Main.WIDTH/9)+30, (c+1)*(Main.FULL_HEIGHT/9)-30);
				repaint();
			}
		}
	}
	
	@Override 
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		try { //try-catch for doDrawing(g)
			doDrawing(g);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}