import java.awt.*;
import javax.swing.*;

public class DigitalDoily extends JFrame {
	
	public DigitalDoily(String name) {
		super(name);
	}
	
	public void init() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Container container = getContentPane();
		getContentPane().setLayout(new BorderLayout());
		DrawingPanel dPanel = new DrawingPanel();
		container.add(dPanel, BorderLayout.CENTER);
		GalleryPanel gPnl = new GalleryPanel();
		container.add(gPnl, BorderLayout.SOUTH);
		SettingsPanel sPanel = new SettingsPanel(dPanel, gPnl);
		container.add(sPanel, BorderLayout.WEST);
		
		
		setResizable(false);
		pack();
		setVisible(true);
	}
}