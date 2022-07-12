import javax.swing.JFrame;

public class GameFrame extends JFrame {
	
	
	public GameFrame(){
		
		GamePanel painel = new GamePanel();
		this.add(painel);
		this.setTitle("Snake");
		this.setResizable(false);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
	}

}
