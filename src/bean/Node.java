package bean;

import java.awt.geom.Area;

public interface Node{
	public boolean deleteNode();

	public String print();
	
	public Area draw();

	public void drawPixel(int x, int y);
}
