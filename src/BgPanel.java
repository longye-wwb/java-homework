
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class BgPanel extends JPanel {
			Image image1 = null ;
			Image image2=null;
			
			public BgPanel()
			{
				URL imageUrl = MyFrame.class.getResource("images/0.png");
				URL imageUrl2 = MyFrame.class.getResource("images/logo.gif");
				try{
					image1 = ImageIO.read(imageUrl);
					image2=ImageIO.read(imageUrl2);
				}catch(Exception e)
				{
					e.printStackTrace();
				}
			}
			@Override
			protected void paintComponent(Graphics g)
			{
				int width = this.getWidth();
				int height = this.getHeight();
				g.clearRect(0, 0, width, height);
				
		
				g.drawImage(image1, 0, 0, width, height, null);
				g.drawImage(image2, 497, 0, 123, 80, null);
				g.drawImage(image2, 187, 0, 123, 80, null);
		
				g.setColor(new Color(255,255,255,100));
				g.fillRect(0, 0, width, height);
			}
		}

