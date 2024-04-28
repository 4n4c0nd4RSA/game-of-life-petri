import java.awt.*;
public class Renderer{
	private static Game game;
	
	public Renderer(Game input){
		game = input;
	}	
		
	public void renderBackGround(Graphics g){	
	}
	
	private double maxValue = 0.0;
	public void renderForeground(Graphics g){
		Cell[][] map = game.getMap();
		
		for(int i = 0; i < map.length; i++){
			for(int j = 0; j < map[0].length; j++){
				if(map[i][j].getValue()==1){
					g.setColor(new Color(map[i][j].getR(),map[i][j].getG(),map[i][j].getB()));
					g.fillRect(i,j,1,1);
					g.setColor(Color.BLACK);
					//g.drawString(""+game.countNeigbors(i,j), i*15+5, j*15+10);
				}
			}
		}
		
		double value = 0.0;
		int[][][] amountOfSpec = game.getAmountOfSpec();
		g.setColor(Color.BLACK);
		g.fillRect(0,game.getHeight()-game.getGraphSize(),game.getWidth(),game.getGraphSize());
		for(int i = 0; i < 256; i++){
			for(int j = 0; j < 256; j++){
				for(int k = 0; k < 256; k++){
					if(amountOfSpec[i][j][k]>maxValue){
						maxValue = (double)amountOfSpec[i][j][k];
					}
				}
			}
		}
		for(int i = 0; i < 256; i++){
			for(int j = 0; j < 256; j++){
				for(int k = 0; k < 256; k++){
					value = (double)amountOfSpec[i][j][k]/maxValue;
					if(value == 1){
						//System.out.println(amountOfSpec[i][j][k]+"/"+maxValue+" "+i+":"+j+":"+k);
					}
					if(value>0){
						//System.out.println((int)((double)game.getGraphSize()*value));
						g.setColor(new Color(i,j,k));
						g.fillRect(i+j+k,game.getHeight()-game.getGraphSize()+(int)((double)game.getGraphSize()*(1-value)),1,game.getGraphSize());
					}
				}
			}
		}
	}
}
