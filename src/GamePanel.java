import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import javax.swing.Timer;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements ActionListener {
	
	static final int width = 600;
	static final int height = 600;
	static final int size = 25;
	static final int units = (width * height) / size;
	static final int delay = 75;
	final int x[] = new int[units];
	final int y[] = new int[units];
	int body = 6;
	int frutisEaten;
	int fruitX;
	int fruitY;
	char direction = 'D';
	boolean running = false;
	Timer timer;
	Random random;
	
	GamePanel(){
		random = new Random();
		this.setPreferredSize(new Dimension(width,height));
		this.setBackground(Color.BLACK);
		this.setFocusable(true);
		this.addKeyListener(new MyKeyAdapter());
		startGame();
	}
	
	public void startGame() {
		newFruit();
		running = true;
		timer = new Timer(delay, this);
		timer.start();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		draw(g);
	}
	
	public void draw(Graphics g) {
		if(running) {
			for(int i =0; i< height/size;i++) {
				g.drawLine(i * size, 0, i * size, height);
				g.drawLine(0, i * size, width, i * size);
			}
			g.setColor(Color.PINK);
			g.fillOval(fruitX, fruitY, size, size);
			
			for(int i = 0; i < body; i++) {
				if(i == 0) {
					g.setColor(Color.GREEN);
					g.fillRect(x[i], y[i],size,size);
				}
				else {
					g.setColor(new Color(45,180,0));
					//g.setColor(new Color(random.nextInt(255),random.nextInt(255),random.nextInt(255)));
					g.fillRect(x[i], y[i],size,size);
				}
			}
			
			
		}else {
			gameOver(g);
		}
		
	}
	
	public void newFruit() {
		fruitX = random.nextInt((int)(width / size))*size ;
		fruitY = random.nextInt((int)(height / size))*size;
	}
	
	public void move() {
		 for(int i = body;i > 0;i --) {
			 x[i] = x[i - 1];
			 y[i] = y[i - 1];
		 }
		 
		 switch(direction) {
		 case 'U':
			 y[0] = y[0] - size;
			 break;
		 case 'D':
			 y[0] = y[0] + size;
			 break;
		 case 'L':
			 x[0] = x[0] - size;
			 break;
		 case 'R':
			 x[0] = x[0] + size;
			 break;
		 } 

	}	 
	
	public void fruitCheck() {
		if((x[0] == fruitX) &&(y[0] == fruitY)) {
			body++;
			frutisEaten++;
			newFruit();
		}
	}
	public void checkCollisions() {
		//corpo
		for(int i = body; i> 0; i--) {
			if((x[0] == x[i])&& (y[0] == y[i])) {
			running = false;
			}
		}
		//parede esquerda
		if(x[0] < 0) {
			running = false;
		}
		//parede direita
		if(x[0] > width) {
			running = false;
		}//parede cima
		if(y[0] < 0) {
			running = false;
		}//parede baixo
		if(y[0] > height) {
			running = false;
		}
		if(!running) {
			timer.stop();
		}
		
		
		
		
	}
	
	public void gameOver(Graphics g) {
		g.setColor(Color.RED);
		g.setFont(new Font("Arial",Font.BOLD,75));
		FontMetrics metrics = getFontMetrics(g.getFont());
		g.drawString("GAME OVER",(width - metrics.stringWidth("GAME OVER"))/ 2,height /2 );
		g.setColor(Color.RED);
		g.setFont(new Font("Arial",Font.BOLD,40));
		FontMetrics metrics2 = getFontMetrics(g.getFont());
		g.drawString("Score: " + frutisEaten,(width - metrics2.stringWidth(("Score: " + frutisEaten)))/ 2
				,g.getFont().getSize());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(running) {
			move();
			fruitCheck();
			checkCollisions();
		}
		repaint();
		
	}
	
	public class MyKeyAdapter extends KeyAdapter{
		@Override
		public void keyPressed(KeyEvent e) {
			switch(e.getKeyCode()) {
			case KeyEvent.VK_A:
			case KeyEvent.VK_LEFT:
				if(direction != 'R') {
					direction = 'L';
				}
				break;
			case KeyEvent.VK_D:
			case KeyEvent.VK_RIGHT:
				if(direction != 'L') {
					direction = 'R';
				}
				break;
			case KeyEvent.VK_W:
			case KeyEvent.VK_UP:
				if(direction != 'D') {
					direction = 'U';
				}
				break;
			case KeyEvent.VK_S:
			case KeyEvent.VK_DOWN:
				if(direction != 'U') {
					direction = 'D';
				}
				break;
			
			}
		}
	}
	

}
