import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Stack;
import javax.swing.JPanel;

public class DrawingPanel extends JPanel{
	int sectors = 12;
	boolean showSectors = true;
	Stroke stroke = new BasicStroke(1);
	Color color = Color.WHITE;
	boolean reflect = false;
	boolean erase = false;
	
	ArrayList<DrawingPoint> dps = new ArrayList<DrawingPoint>();
	ArrayList<Line> lines = new ArrayList<Line>();
	Stack<Line> undoStk = new Stack<Line>();
	
	public DrawingPanel() {
		DrawingListener l = new DrawingListener();
		setPreferredSize(new Dimension(500,500));
		setBackground(Color.BLACK);
		addMouseListener(l);
		addMouseMotionListener(l);
	}
		
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		double angle = (double) (2*Math.PI/sectors);
		Dimension centre = new Dimension(getWidth()/2,getHeight()/2);
		
		//draw the lines stored in lines
		for(Line l : lines) {
			g2.setStroke(l.getStroke());
			for(DrawingPoint dp : l.getDps()) {
				for(int i=0; i < sectors; i++) {
					//check whether or not the line is an eraser, if yes set the color to black, 
					//or else change it to the color of the line stored
					if(l.getErase()) g2.setColor(Color.black);
					else g2.setColor(l.getColor());
					g2.drawLine(dp.getX1(), dp.getY1(), dp.getX2(), dp.getY2());
					if(l.getReflect()) {
						g2.drawLine(2*centre.width - dp.getX1(), dp.getY1(), 2*centre.width - dp.getX2(), dp.getY2());
					}
					g2.rotate(angle, centre.width, centre.height);
				}
			}
		}	
		
		
		//draw sector lines
		if(showSectors) {
			g2.setColor(Color.white);
			g2.setStroke(new BasicStroke(1));
			for(int i=0; i < sectors; i++) {
				g2.drawLine(centre.width, centre.height, centre.width, 0);
				g2.rotate(angle, centre.width, centre.height);
			}
		}
	}
	
	class DrawingListener implements MouseListener, MouseMotionListener{
		Line line;
		int x1,y1,x2,y2;
		public void mousePressed(MouseEvent e) {
			//when mouse is pressed create a new line object and add it into the list of lines
			//the starting points of the lines would be when the user first clicked
			line = new Line(stroke, erase, reflect, color);
			lines.add(line);
			x1 = e.getX();
			y1 = e.getY();
		}
		
		public void mouseDragged(MouseEvent e) {
			//when the mouse is dragged store the coordinates of the end points
			//add a drawingpoint into the line
			x2 = e.getX();
			y2 = e.getY();
			line.addDp(new DrawingPoint(x1,y1,x2,y2));
			repaint();
			//make the start of the next point be the end of the last one
			x1 = x2;
			y1 = y2;
		}

		public void mouseReleased(MouseEvent e) {
			x2 = e.getX();
			y2 = e.getY();
			line.addDp(new DrawingPoint(x1,y1,x2,y2));
			repaint();
		}
		
		public void mouseMoved(MouseEvent e) {}
		public void mouseClicked(MouseEvent e) {}
		public void mouseEntered(MouseEvent e) {}
		public void mouseExited(MouseEvent e) {}
		
	}

	public void clear() {
		//clears all the lines in the lines list
		lines.clear();
		repaint();
	}
	
	public void undo() {
		//checks whether or not there are lines in the lines list
		//if there is then remove the last line in the lines list and store it in a stack
		if(!lines.isEmpty()) {
			Line l = lines.remove(lines.size()-1);
			undoStk.push(l);
			repaint();
		}
	}
	
	public void redo() {
		//check whether the stack is empty,
		//if it isn't then pop the line out of the stack and add it back onto the lines list
		if(!undoStk.isEmpty()) {
			Line l = undoStk.pop();
			lines.add(l);
			repaint();
		}
	}
	
	public int getSectors() {
		//returns current number of sectors
		return sectors;
	}
	
	public void setSectors(int s) {
		//sets the number of sectors
		sectors = s;
		repaint();
	}
	
	public Stroke getStroke() {
		//returns the current stroke size
		return stroke;
	}
	
	public void setStroke(int s) {
		//set's the stroke size
		stroke = new BasicStroke(s);
	}
	
	public void setDrawErase(boolean x) {
		//sets erase to true or false
		erase = x;
	}
	
	public void setReflect(boolean x) {
		//set reflect to true or false
		reflect = x;
	}
	
	public BufferedImage getScreenGrab() {
		//returns an image of the doily
		BufferedImage image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
		paint(image.getGraphics());
		return image;
	}
	
	public void setColor(Color c) {
		//sets the color of the lines
		color = c;
	}
	
	public Color getColor() {
		//returns the current color settings
		return color;
	}
	
	public void showSectors(boolean x) {
		//sets the value of showSectors
		showSectors = x;
		repaint();
	}
	
}
