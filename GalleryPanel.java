import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Stack;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GalleryPanel extends JPanel {
	
	ArrayList<JLabel> images = new ArrayList<JLabel>();
	
	public GalleryPanel() {
		setLayout(new GridLayout(2,6));
		setPreferredSize(new Dimension(500,200));
		setVisible(true);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
	}
	
	public void addImage(JLabel image) {
		images.add(image);
		add(image);
	}
	
	
	public void removeImage(int i) {
		images.remove(i);
		remove(i);
		repaint();
	}
	
	public int getImagesSize() {
		return images.size();
	}
	
}
