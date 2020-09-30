import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.util.ArrayList;


public class Line {
	
	ArrayList<DrawingPoint> dps = new ArrayList<DrawingPoint>();
	Stroke stroke;
	Color color;
	boolean erase, reflect;
	
	public Line(Stroke s, boolean e, boolean r, Color c) {
		stroke = s;
		erase = e;
		reflect = r;
		color = c;
	}
	
	public ArrayList<DrawingPoint> getDps(){
		return dps;
	}
	
	public void addDp(DrawingPoint dp) {
		dps.add(dp);
	}
	
	public void setStroke(Stroke s) {
		stroke = s;
	}
	
	public Stroke getStroke() {
		return stroke;
	}
	
	public boolean getErase() {
		return erase;
	}
	
	public boolean getReflect() {
		return reflect;
	}
	
	public Color getColor() {
		return color;
	}
}