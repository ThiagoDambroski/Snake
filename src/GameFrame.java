import javax.swing.JFrame;

public class GameFrame extends JFrame {
	private static final long serialVersionUID = 1L;

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
