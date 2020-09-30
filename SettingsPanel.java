import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.*;

public class SettingsPanel extends JPanel{

	JButton clearBtn, redoBtn, undoBtn;
	DrawingPanel dPanel;
	
	public SettingsPanel(DrawingPanel dPanel, GalleryPanel gPanel) {
		
		setLayout(new GridLayout(7,1));
		this.dPanel = dPanel;
		
		//Creating and setting the clear button
		clearBtn = new JButton("Clear");							
		clearBtn.addActionListener(e -> dPanel.clear());
		
		//Creating and setting the redo and undo buttons
		JPanel redoUndoPnl = new JPanel();					
		redoBtn = new JButton("Redo");
		undoBtn = new JButton("Undo");
		redoUndoPnl.add(clearBtn);
		redoUndoPnl.add(undoBtn);
		redoUndoPnl.add(redoBtn);
		add(redoUndoPnl);
		redoBtn.addActionListener(e -> dPanel.redo());
		undoBtn.addActionListener(e -> dPanel.undo());
		
		//Color of strokes
				ColorBtn colorBtn = new ColorBtn(dPanel.getColor());
				add(colorBtn);
		
		//Stroke size
		JPanel sectorsPnl = new JPanel();							
		JLabel sectorsLbl = new JLabel("Number of sectors:");
		JSpinner sectorsSpn = new JSpinner();
		sectorsSpn.setModel(new SpinnerNumberModel(dPanel.getSectors(), 2, 100, 1));
		sectorsPnl.add(sectorsLbl);
		sectorsPnl.add(sectorsSpn);
		sectorsSpn.addChangeListener(e -> dPanel.setSectors((int)sectorsSpn.getValue()));
		add(sectorsPnl);
		
		//Creating input for stroke size
		JPanel strokePnl = new JPanel();
		JLabel strokeLbl = new JLabel("Stroke size:");
		JSpinner strokeSpn = new JSpinner();
		strokeSpn.setModel(new SpinnerNumberModel(1, 1, 20, 1));
		strokePnl.add(strokeLbl);
		strokePnl.add(strokeSpn);
		strokeSpn.addChangeListener(e -> dPanel.setStroke((int)strokeSpn.getValue()));
		add(strokePnl);
		
		//Toggle Reflection
		JToggleButton reflectBtn = new JToggleButton("Toggle reflection", false);
		reflectBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(reflectBtn.isSelected()) dPanel.setReflect(true);
				else dPanel.setReflect(false);
			}
		});
		
		//Show sectors
		JToggleButton showSectorsBtn = new JToggleButton("Toggle sector lines", true);
		showSectorsBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(showSectorsBtn.isSelected()) dPanel.showSectors(true);
				else dPanel.showSectors(false);
			}
		});
		
		JPanel toggles = new JPanel();
		toggles.add(reflectBtn);
		toggles.add(showSectorsBtn);
		add(toggles);
		
		
		//Erase/draw
		JPanel eraseDraw = new JPanel();
		JButton eraseBtn = new JButton("Erase");
		JButton drawBtn = new JButton("Draw");
		eraseDraw.add(drawBtn);
		drawBtn.setEnabled(false);
		eraseDraw.add(eraseBtn);
		eraseBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dPanel.setDrawErase(true);	
				drawBtn.setEnabled(true);
				eraseBtn.setEnabled(false);
			}
		});
		drawBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dPanel.setDrawErase(false);
				eraseBtn.setEnabled(true);
				drawBtn.setEnabled(false);
			}
		});
		add(eraseDraw);
		
		//save
		JButton saveBtn = new JButton("Save");
		saveBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(gPanel.getImagesSize() < 12) {
					ImageIcon img = new ImageIcon(resizeImage(dPanel.getScreenGrab(), 100, 100));
					gPanel.addImage(new JLabel(img));
					gPanel.revalidate();
				} else JOptionPane.showMessageDialog(null, "Can't save more than 12 images!");
				
			}
		});
		
		//delete
		JButton delBtn = new JButton("Delete");
		delBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int num = Integer.parseInt(JOptionPane.showInputDialog("Enter the number of the image you want to delete"));
					if(num <= gPanel.getImagesSize() && num > 0) {
						gPanel.removeImage(num-1);
						gPanel.revalidate();
					} else JOptionPane.showMessageDialog(null, "Input is invalid");
				} catch (NumberFormatException exception) {
					JOptionPane.showMessageDialog(null, "Please enter an integer value");
				}
			}
		});
		
		JPanel saveDeletePnl = new JPanel();
		saveDeletePnl.add(saveBtn);
		saveDeletePnl.add(delBtn);
		add(saveDeletePnl);
	}
	
	public BufferedImage resizeImage(Image img, int width, int height) {
		//resize the copied image so that it fits into the gallery
		BufferedImage resizedImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = resizedImg.createGraphics();
		
		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g2.drawImage(img, 0, 0, width, height, null);
	    g2.dispose();

	    return resizedImg;
	}
	
	class ColorBtn extends JButton {
		
		//A color button which creates a JColorChooser dialog when pressed on
		//sets the color of the stroke when the color is selected by the user
		
		Color color;
		
		public ColorBtn(Color c) {
			super("Color");
			color = c;
			addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Color newColor = JColorChooser.showDialog(null, "Select color", getColor());
					dPanel.setColor(newColor);
				}
			});
		}
		
		public void setColor(Color c) {
			//sets the color
			color = c;
		}
		
		public Color getColor() {
			//returns the color
			return color;
		}
		
	}
}

