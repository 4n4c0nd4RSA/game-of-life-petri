import java.awt.Canvas;
import java.awt.Color;
import javax.swing.JFrame;
import java.awt.Graphics;
import java.util.Scanner;
import java.awt.image.BufferStrategy;
public class Game extends Canvas implements Runnable{
	private static int WIDTH;
	private static int HEIGHT;
	private static int GRAPHSIZE;
	private static String TITLE = "Game Of Life";
	private static Game game;
	private boolean running = false;
	private Thread thread;
	private Renderer gfx;
	private static Cell map[][];
	private static double popDense;
	private static int foodDesnsity;
	private static int mateDistance;
	private static int feedDistance;
	private static int[][][] amountOfSpec = new int[256][256][256];
	
	public Game(int width, int graphsize, double popDense, int foodDesnsity, int mateDistance, int feedDistance){
		WIDTH = width;
		HEIGHT = (int)(((double)WIDTH / 4.0) * 3.0);
		GRAPHSIZE = graphsize;
		map = new Cell[WIDTH][HEIGHT-GRAPHSIZE];
		this.popDense = popDense;
		this.foodDesnsity = foodDesnsity;
		this.mateDistance = mateDistance;
		this.feedDistance = feedDistance;
	}
	
	public Game(){
		this(600,100,0.025,100,1,5);
	}
	
	public static Game getInstance(){
		return game;
	}
	
	public Cell[][] getMap(){
		return map;
	}
	
	public int getWidth(){
		return WIDTH;
	}
	public int getHeight(){
		return HEIGHT;
	}
	public int getGraphSize(){
		return GRAPHSIZE;
	}
	public int[][][] getAmountOfSpec(){
		return amountOfSpec;
	}
	
	private synchronized void start(){
		if(running){
			return;
		}
		else
			running=true;
		thread = new Thread(this);
		thread.start();
	}
	
	private synchronized void stop(){
		if(!running){
			return;
		}
		else
			running = false;
		try{
			thread.join();
		}
		catch(Exception ex){
			
		}
		System.exit(1);
		
	}	
	
	public void init(){
		gfx=new Renderer(this.getInstance());
		for(int i = 0 ; i < map.length ; i++){
			for(int j = 0 ; j < map[0].length ; j++){
				if(Math.random()>popDense){
					map[i][j] = new Cell(0,(int)(Math.random()*256),(int)(Math.random()*256),(int)(Math.random()*256),i,j);
				}
				else{
					map[i][j] = new Cell(1,(int)(Math.random()*256),(int)(Math.random()*256),(int)(Math.random()*256),i,j);
				}
			}
		}
		for(int i = 0; i < 256; i++){
			for(int j = 0; j < 256; j++){
				for(int k = 0; k < 256; k++){
					amountOfSpec[i][j][k] = 0;
				}
			}
		}
	}
	
	public void tick(){
		Cell[][] oldMap = map;
		for(int i = 0; i < map.length; i++){
			for(int j = 0; j < map[0].length; j++){
				if(checkCell(i,j)){
					map[i][j].setValue(1);
				}
				else{
					map[i][j].setValue(0);
				}
			}
		}
		for(int i = 0; i < 256; i++){
			for(int j = 0; j < 256; j++){
				for(int k = 0; k < 256; k++){
					amountOfSpec[i][j][k] = 0;
				}
			}
		}
		for(int i = 0; i < map.length; i++){
			for(int j = 0; j < map[0].length; j++){
				if(map[i][j].getValue()==1){
					amountOfSpec[map[i][j].getR()][map[i][j].getG()][map[i][j].getB()]++;
				}
			}
		}
	}

	private boolean checkCell(int i, int j){		
		if(((double)map[i][j].getR()/255)>0.95){
			return false;
		}
		if(countNeigborsLarge(i,j,feedDistance)>foodDesnsity+(4*foodDesnsity*((double)map[i][j].getG()/255))){
			//System.out.println("Death from over population on large scale");
			return false;
		}
		if(countNeigbors(i,j)<2){
			return false;
		}
		if(countNeigbors(i,j)>=2 && countNeigbors(i,j)<=3){
			if(map[i][j].getValue()==1){
				return true;	
			}
		}
		if(countNeigbors(i,j)>3){
			return false;
		}
		if(countNeigbors(i,j)==3){
			if(map[i][j].getValue()==0){
				map[i][j].setColor(getNeighborColor(i,j,mateDistance+(int)(4*mateDistance*((double)map[i][j].getB()/255))));
				return true;	
			}
		}
		return false;
	}
	
	public int countNeigborsLarge(int i, int j, int size){
		int amount = 0;
		for(int a = -size; a < (size+1); a++){
			for(int b = -size; b < (size+1); b++){
				if(((a+i)>-1) && ((a+i)<map.length)){
					if(((b+j)>-1) && ((b+j)<map[0].length)){
						if(!(a==0 && b==0)){
							if(map[i+a][j+b].getValue()==1){
								amount++;
							}
						}
					}
				}
			}	
		}
		return amount;
	}
	
	public Color getNeighborColor(int i, int j, int size){
		int amount = 0;
		int r = 0;
		int g = 0;
		int b = 0;
		
		for(int a = -size; a < (size+1); a++){
			for(int B = -size; B < (size+1); B++){
				if(((a+i)>-1) && ((a+i)<map.length)){
					if(((B+j)>-1) && ((B+j)<map[0].length)){
						if(!(a==0 && B==0)){
							if(map[i+a][j+B].getValue()==1){
								amount++;
								r += map[i+a][j+B].getR();
								g += map[i+a][j+B].getG();
								b += map[i+a][j+B].getB();
							}
						}
					}
				}
			}	
		}
		return new Color((int)((double)r/amount),(int)((double)g/amount),(int)((double)b/amount));
	}
	
	public int countNeigbors(int i, int j){
		int amount = 0;
		for(int a = -1; a < 2; a++){
			for(int b = -1; b < 2; b++){
				if(((a+i)>-1) && ((a+i)<map.length)){
					if(((b+j)>-1) && ((b+j)<map[0].length)){
						if(!(a==0 && b==0)){
							if(map[i+a][j+b].getValue()==1){
								amount++;
							}
						}
					}
				}
			}	
		}
		return amount;
	}
	
	public void render(){
		BufferStrategy bs = this.getInstance().getBufferStrategy();
		if(bs == null){
			createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		g.setColor(new Color(10,2,25));
		g.fillRect(0,0,WIDTH,HEIGHT);
		g.setColor(Color.BLACK);
		g.fillRect(0,HEIGHT,WIDTH,GRAPHSIZE);
		
		/////////////////////////////////////
		
		gfx.renderBackGround(g);
		gfx.renderForeground(g);
		
		g.dispose();
		bs.show();
		tick();
		
	}
	
	@Override 
	public void run(){
		init();
		long lastTime = System.nanoTime();
		final double numTicks = 60.0;
		double n = 1000000000/numTicks;
		double delta = 0;
		int frames = 0;
		int ticks = 0;
		long timer = System.currentTimeMillis();
		
		
		while(running){
			long currentTime = System.nanoTime();
			delta += (currentTime-lastTime)/n;
			lastTime = currentTime;
			
			if(delta >= 1){
				ticks++;
				delta--;
			}
			render();
			frames++;
			
			if(System.currentTimeMillis() - timer > 150){
				timer+=150;
				System.out.println(ticks+" Ticks FPS: "+frames);
				frames=0;
				ticks=0;
				//tick();
			}
		}
	}
	
	public static void main(String[] args){
		Scanner input = new Scanner(System.in);
		JFrame frame = new JFrame(TITLE);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setFocusable(true);
		frame.setLocationRelativeTo(null);	
		frame.setResizable(false);
		System.out.print("Enter experiment width (pixels): ");
		WIDTH = input.nextInt();
		HEIGHT = (int)(((double)WIDTH / 4.0) * 3.0);
		map = new Cell[WIDTH][HEIGHT];
		System.out.print("Enter seed density (0.00 - 0.99): ");
		popDense = input.nextDouble();
		System.out.print("Enter food density (0 - 100): ");
		foodDesnsity = input.nextInt();
		System.out.print("Enter mix distance(1 - 100): ");
		mateDistance = input.nextInt();
		System.out.print("Enter feed distance (5 - 100): ");
		feedDistance = input.nextInt();
		frame.setTitle(TITLE+"          Seed Density: "+popDense+"  Food Density: "+foodDesnsity+"  Feed Distance: "+feedDistance+"  Mix Distance: "+mateDistance);
		game = new Game(WIDTH, 100, popDense, foodDesnsity, mateDistance, feedDistance);
		frame.setSize(game.getWidth(),game.getHeight()+20);
		frame.add(game);
		frame.setVisible(true);
		frame.pack();
		game.start();
	}
}
