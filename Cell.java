
import java.awt.Color;
public class Cell{
	private int value;
	private int r;
	private int g;
	private int b;
	private int x;
	private int y;
	
	public Cell(){
		this(0,255,255,255,0,0);
	}
	
	public Cell(int x, int y){
		this(0,255,255,255,x,y);
	}
	
	public Cell(int x, int y, int value){
		this(value,255,255,255,x,y);
	}
	
	public Cell(int value, int r, int b, int g, int x, int y){
		this.value = value;
		this.r = r;
		this.b = b;
		this.g = g;
		this.x = x;
		this.y = y;
	}
	
	public int getValue(){
		return value;
	}
	
	public int getR(){
		return r;
	}
	
	public int getG(){
		return g;
	}
	
	public int getB(){
		return b;
	}
	
	public void setValue(int value){
		this.value = value;
	}
	
	public void setColor(Color color){
		r = color.getRed();
		g = color.getGreen();
		b = color.getBlue();
	}
}