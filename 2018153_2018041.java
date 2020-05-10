package javafxtrial;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.animation.*;
import javafx.application.*;
import javafx.beans.value.ChangeListener;
import javafx.scene.*;
import javafx.scene.canvas.GraphicsContext;
import javafx.event.*;
import javafx.stage.*;
import javafx.util.Duration;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;

class InvalidUserException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2L;

	public InvalidUserException() {
		System.out.println("You won");
	}
}


class Level {
	private int NumberOfZombies;
	private int PlantsTypesAvailable;
	private int Suns;
	private int Current;
	
	public void SetLevel(int n) {
		this.Current = n;
	}
	
	public int ReturnLevel() {
		return this.Current;
	}
	
}


class LawnMower{
	private int Row;
	private ImageView Token;
	private ArrayList<Zombies> zom;
	
	LawnMower(ImageView i, int r, ArrayList<Zombies> z) {
		this.Token = i;
		this.Row = r;
		this.zom = z;
	}
	
	public void Start() {
		this.Setup_Timeline();
	}
	
	private void Setup_Timeline() {
		Token.xProperty().set(0);
		TranslateTransition transition = new TranslateTransition();
		transition.setDuration(Duration.seconds(4));
		transition.setFromX(200);
		transition.setFromY(0);
		transition.setToX(2000);
		transition.setAutoReverse(false);
		transition.setCycleCount(1);
		transition.setNode(Token);
		transition.play();
		this.Kil_Row();
	}
	
	private void Kil_Row() {
		for (int i=0; i<this.zom.size(); i++) {
			if (this.zom.get(i).Column == this.Row) {
				this.zom.get(i).return_GUI().return_token().setVisible(false);
			}
		}
	}
		
	
}




class Lawn extends Level{
	private int Suns = 0;
	LawnMower m;
	public int return_suns() {
		return this.Suns;
	}
	
	public void Add_Suns(int n) {
		this.Suns += n;
	}
	
	public LawnMower returnM() {
		return this.m;
	}
}



class Player implements Serializable {
	private static final long serialVersionUID = 1L;
	private String Name;
	private String Password;
	
	Player(String n, String m) {
		this.Name = n;
		this.Password = m;
	}
	
	public String GetName() {
		return this.Name;
	}
	
	public String GetPass() {
		return this.Password;
	}
	
	public void SetCredentials(String name, String pass) {
		this.Name = name;
		this.Password = pass;
	}

	
}






class Data implements Serializable{
	private static final long serialVersionUID = 1L;
	private ArrayList<Player> Players = new ArrayList<Player>();
	
	public boolean ReturnPlayer(String Player, String Password) {
		for (int i=0; i<this.Players.size(); i++) {
			if (this.Players.get(i).GetName().compareTo(Player) == 0 && this.Players.get(i).GetPass().compareTo(Password) == 0) {
				return true;
			}
		}
		return false;
	}
	
	public void AddPlayer(String Player_new, String Pass_new) {
		this.Players.add(new Player(Player_new, Pass_new));
	}
	
	
}








abstract class Character {
	protected int Health;
	protected int Row;
	protected int Column;
	
	public void SetHealth(int hp) {
		this.Health = hp;
	}
	
	public int GetHealth() {
		return this.Health;
	}
	
	public void SetPosition(int r, int c) {
		this.Row = r;
		this.Column = c;
	}
	
	public int GetPosition() {
		return this.Row;
	}
	
	public int GetPositionC() {
		return this.Column;
	}
}


class Barrier extends Character{
	
}

class WalNut extends Barrier {
	
}

class Zombies extends Character{
	private int Attack;
	private Zombie_Run GUI_Component;
	
	public void EatPlant() {
		
	}
	
	public Zombie_Run return_GUI() {
		return this.GUI_Component;
	}
	
	Zombies(int a, Zombie_Run z, int hp) {
		this.SetHealth(3);
		this.SetPosition(a);
		this.GUI_Component = z;
	}
	
	public int return_Attack() {
		return this.Attack;
	}
	
	public void SetPosition(int a) {
		if (a == -55) {
			this.Column = 1;
			this.Row = 0;
		}
		else if (a == -65) {
			this.Column = 2;
			this.Row = 0;
		}
		else if (a == -75) {
			this.Column = 3;
			this.Row = 0;
		}
		else if (a == -85) {
			this.Column = 4;
			this.Row = 0;
		}
		else if (a == -95) {
			this.Column = 5;
			this.Row = 0;
		}
	}
}


class Plants extends Character {
	protected ImageView GUI_Component;

	public ImageView Get_GUI() {
		return this.GUI_Component;
	}
	
	public void PlacePlant() {
		
	}
	
	public void PlacePlant(double r, double c) {
		int row = -1, col = -1;
		if (r > 400 && r < 470) {
			row = 1;
		}
		else if (r > 520 && r < 590) {
			row = 2;
		}
		else if (r > 650 && r < 715) {
			row = 3;
		}
		else if (r > 760 && r < 830) {
			row = 4;
		}
		else if (r > 890 && r < 960) {
			row = 5;
		}
		else if (r > 1020 && r < 1090) {
			row = 6;
		}
		else if (r > 1145 && r < 1215) {
			row = 7;
		}
		else if (r > 1260 && r < 1330) {
			row = 8;
		}
		else if (r > 1385 && r < 1455) {
			row = 9;
		}
		
		
		
		if (c > 120 && c < 190) {
			col = 1;
		}
		else if (c > 240 && c < 310) {
			col = 2;
		}
		else if (c > 377 && c < 440) {
			col = 3;
		}
		else if (c > 515 && c < 580) {
			col = 4;
		}
		else if (c > 640 && c < 710) {
			col = 5;
		}
		this.SetPosition(row, col);
	}
}


class Shooter extends Plants{
	protected int Attack;
	
	public void Shoot() {
		
	}
}


class PeaShooter extends Shooter {
		
	PeaShooter(double a, double b, ImageView i) {
		this.PlacePlant(a, b);
		this.GUI_Component = i;
	}
}


class SunProducing extends Plants {
	
	public boolean SunProduction(double r, double c) {
		int row = -1, col = -1;
		if (r > 400 && r < 470) {
			row = 1;
		}
		else if (r > 520 && r < 590) {
			row = 2;
		}
		else if (r > 650 && r < 715) {
			row = 3;
		}
		else if (r > 760 && r < 830) {
			row = 4;
		}
		else if (r > 890 && r < 960) {
			row = 5;
		}
		else if (r > 1020 && r < 1090) {
			row = 6;
		}
		else if (r > 1145 && r < 1215) {
			row = 7;
		}
		else if (r > 1260 && r < 1330) {
			row = 8;
		}
		else if (r > 1385 && r < 1455) {
			row = 9;
		}
		
		
		if (c > 120 && c < 190) {
			col = 1;
		}
		else if (c > 240 && c < 300) {
			col = 2;
		}
		else if (c > 377 && c < 440) {
			col = 3;
		}
		else if (c > 515 && c < 580) {
			col = 4;
		}
		else if (c > 640 && c < 710) {
			col = 5;
		}
		if (row == this.Row && col == this.Column) {
			return true;
		}
		return false;
	}
}

class Sunflower	extends SunProducing {
	
	private Sun_From_Sunflower Sun;
	
	Sunflower(double a, double b, Sun_From_Sunflower s, ImageView i) {
		this.PlacePlant(a, b);
		this.Sun = s;
		this.GUI_Component = i;
	}
	
	public Sun_From_Sunflower Get_Sun() {
		return this.Sun;
	}
	
	public ImageView return_GUI() {
		return this.GUI_Component;
	}
}






class Zombie_Run {
	private ImageView Token;
	private double Y;
	private Timeline time;
	LawnMower mow;
	ArrayList<Zombie_Run> list;
	private Game l;
	
	
	Zombie_Run(ImageView tok, double y) {
		this.Token = tok;
		this.Y = y;
		this.Token.setVisible(false);

	}
	
	public void SetStage(Game lil) {
		this.l = lil;
	}
	
	public void Set_Mow(LawnMower l) { 
		this.mow = l;
	}
	
	public void Set_Enemies(ArrayList<Zombie_Run> l) {
		this.list = l;
	}
	
	public ImageView return_token() {
		return this.Token;
	}
	
	public Timeline Get_Timeline() {
		return this.time;
	}
	
	public void Start() {
		this.Setup_Timeline();
	}

	private void Setup_Timeline() {
		this.Token.setVisible(true);
		Token.xProperty().set(-500);
		Token.yProperty().set(Y);
		TranslateTransition transition = new TranslateTransition();
		transition.setDuration(Duration.seconds(150));
		transition.setFromY(Y);
		transition.setFromX(0);
		transition.setToX(-1200);
		transition.setAutoReverse(false);
		transition.setCycleCount(1);
		transition.setNode(Token);
		transition.play();
		this.Token.translateXProperty().addListener((observable, oldValue, newValue) -> CheckHit(this.mow));
		for (int i=0; i<5; i++) {
			this.list.get(i).return_token().visibleProperty().addListener((observable, oldValue, newValue) -> {
				try {
					CheckVis(this.list);
				} catch (InvalidUserException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}); 
		}
	}
	
	private void CheckHit(LawnMower mow2) {
		if (this.Token.translateXProperty().get() < -1160 && this.Token.isVisible()) {
			this.mow.Start();
		}
	}
	
	private void CheckVis(ArrayList<Zombie_Run> z) throws InvalidUserException {
		int flag = 1;
		for (int i=0; i<5; i++) {
			if (z.get(i).return_token().isVisible()) {
				flag = 0;
			}
		}
		if (flag == 1) {
			Alert alert = new Alert(AlertType.CONFIRMATION, "Level Completed! Continue to Next Level or Return to Main Menu", ButtonType.NEXT, ButtonType.FINISH);
			alert.show();
			if (alert.getResult() == ButtonType.NEXT) {
				alert.close();
				throw new InvalidUserException();
			}
			else if (alert.getResult() == ButtonType.FINISH) {
				alert.close();
				this.l.SetStage();
			}
		}
	}

}





class Sprite {

    private Image image;
    private double positionX;
    private double positionY;
    private double width;
    private double height;

    public Sprite(Image image) {
        this.image = image;
        width = image.getWidth();
        height = image.getHeight();
        positionX = 500;
        positionY = 500;
        
        
    }

    public void setPosition(double x, double y) {
        positionX = x;
        positionY = y;
    }

    public void render(GraphicsContext gc) {
        gc.drawImage(image, positionX, positionY);
    }

    public Rectangle getBoundary() {
        return new Rectangle(positionX, positionY, width, height);
    }
    
    

}


class Sun_From_Sunflower {
	private ImageView Token;
	private Timeline timeline;
	private double X;
	private double Y;
	public boolean flag = true;
	
	Sun_From_Sunflower(double X, double Y, ImageView Sun) {
		this.X = X;
		this.Y = Y;
		this.Token = Sun;
	}
	
	public void Start() {
		this.Setup_Timeline();
	}
	
	public ImageView return_token() {
		return this.Token;
	}
	
	public Timeline return_time() {
		return this.timeline;
	}
	
	private void Setup_Timeline() {
		Token.setOpacity(0);
		KeyFrame kf = new KeyFrame(Duration.seconds(15), new TimeHandler(this.X, this.Y));
		timeline = new Timeline(kf);
		timeline.setCycleCount(Animation.INDEFINITE);
		timeline.play();
	}
	
	class TimeHandler implements EventHandler<ActionEvent> {
		
		private double X;
		private double Y;
		
		TimeHandler(double X, double Y) {
			this.X = X;
			this.Y = Y;
		}
		public void handle(ActionEvent event) {
			Token.setVisible(true);
			flag = true;
			RotateTransition r = new RotateTransition();
			TranslateTransition t = new TranslateTransition();
			PauseTransition pt = new PauseTransition(Duration.seconds(2));
			PauseTransition pt2 = new PauseTransition(Duration.seconds(4));
			FadeTransition ft = new FadeTransition(Duration.seconds(2), Token);
			SequentialTransition s = new SequentialTransition(t, pt, ft, pt2);
			ft.setFromValue(1);
			ft.setToValue(0);
			ft.setAutoReverse(false);
			r.setDuration(Duration.seconds(10));
			r.setNode(Token);
			r.setByAngle(360);
			t.setDuration(Duration.seconds(6));
			t.setNode(Token);
			t.setFromX(this.X-920);
			t.setFromY(this.Y+100);
			t.setByX(100);
			t.setAutoReverse(false);
			s.play();
			r.play();
		}
	}
}

class Sun_Drop {
	private ImageView Token;
	private Timeline timeline;
	public int clicks = 0;
	public boolean flag = true;
	
	Sun_Drop(ImageView Sun_im) {
		this.Token = Sun_im;
	}
	
	public void Start() {
		this.Setup_Timeline();
	}
	
	public ImageView return_token() {
		return this.Token;
	}
	
	public Timeline return_time() {
		return this.timeline;
	}
	
	private void Setup_Timeline() {
		Token.setOpacity(0);
		KeyFrame kf = new KeyFrame(Duration.seconds(11), new TimeHandler());
		timeline = new Timeline(kf);
		timeline.setCycleCount(Animation.INDEFINITE);
		timeline.play();
	}
	
	class TimeHandler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent event) {
			Token.setVisible(true);
			flag = true;
			RotateTransition r = new RotateTransition();
			TranslateTransition t = new TranslateTransition();
			PauseTransition pt = new PauseTransition(Duration.seconds(2));
			FadeTransition ft = new FadeTransition(Duration.seconds(2), Token);
			SequentialTransition s = new SequentialTransition(t, pt, ft);
			ft.setFromValue(1);
			ft.setToValue(0);
			ft.setAutoReverse(false);
			r.setDuration(Duration.seconds(10));
			r.setNode(Token);
			r.setByAngle(360);
			t.setDuration(Duration.seconds(6));
			t.setNode(Token);
			t.setFromX((Math.random()*500));
			t.setFromY(0);
			t.setToY(500);
			t.setAutoReverse(false);
			s.play();
			r.play();
		}
	}
	
	
	
}

class Shooting {
	private Circle pea = new Circle();
	private Sprite zombie;
	VBox V;
	ImageView I;
	private Timeline T;
	Zombies Z;
	int row;
	
	private double X;
	private double Y;
	
	Shooting(double x, double y, int r) {
		this.X = x;
		this.Y = y;
		this.row = r;
		this.pea.setRadius(12);
		this.pea.setFill(Color.DARKGREEN);
		this.pea.relocate(X, Y);
	}
	
	public void Start() {
		this.Setup_Timeline();
	}
	
	public Circle Get_Pea() {
		return this.pea;
	}
	
	public void Set_Sprite(Sprite get, VBox v, ImageView i, Zombies z) {
		this.zombie = get;
		this.V = v;
		this.I = i;
		this.Z = z;
	}
	
	private void Setup_Timeline() {
		KeyValue kv = new KeyValue(this.pea.centerXProperty(), 1100);
		KeyFrame kf = new KeyFrame(Duration.seconds(4), kv);
		Timeline timeline = new Timeline(kf);
		this.T = timeline;
		timeline.setCycleCount(Animation.INDEFINITE);
		this.pea.boundsInParentProperty().addListener((observable, oldValue, newValue) -> CheckHit(this.zombie.getBoundary()));
		timeline.play();
	}
	
	private void CheckHit(Rectangle block) {
		if (this.V.getChildren().contains(I)) {
	        if ((this.pea.getCenterX() + (this.row-1)*120) - this.I.getTranslateX() > 1000 && (this.pea.getCenterX() + (this.row-1)*120) - this.I.getTranslateX() < 1060) {
	          for (int i=0; i<V.getChildren().size(); i++) {
	        	  if (V.getChildren().get(i) == I && V.getChildren().get(i).isVisible() && pea.isVisible()) {
	        		  if (Z.GetHealth() == 0) {
		        		  V.getChildren().get(i).setVisible(false);
		    	          T.pause();
		    	          T.playFrom(Duration.ZERO);
	        		  }
	        		  else {
	        			  if (Z.GetHealth() < 0) {
	        				  Z.SetHealth(4);
	        			  }
	        			  Z.SetHealth(Z.GetHealth()-1);
	        			  pea.setVisible(false);
	        		  }
	        	  }
	          }
	        }
	        else if (this.pea.getCenterX() + (this.row-1)*120 - this.I.getTranslateX() <1000){
	        	pea.setVisible(true);
	        }
	      }
	}

	
	
}


class Timer {
	private Integer Time_Seconds = 150;	
	private Label Timer = new Label();
		
	public Label Get_Time() {
		return this.Timer;
	}
	
	public void Start() {
		this.Setup_Timeline();
		this.Timer.setTextFill(Color.BLUEVIOLET);
		this.Timer.setScaleX(2);
		this.Timer.setScaleY(2);
	}
	
	private void Setup_Timeline() {
		KeyFrame kf = new KeyFrame(Duration.seconds(1), new TimeHandler());
		Timeline timeline = new Timeline(kf);
		timeline.setCycleCount(Animation.INDEFINITE);
		timeline.play();
	}
	
	class TimeHandler implements EventHandler<ActionEvent>{

		public void handle(ActionEvent event){
		Time_Seconds --;
		Timer.setText(Time_Seconds.toString());

		}
	}	
}


class Eat implements Runnable {
	private ArrayList<Zombie_Run> Enemies;
	private ArrayList<Plants> Allies;
	private Pane Plant_Pane;
	private boolean flag = true;
	
	Eat(ArrayList<Zombie_Run> r, ArrayList<Plants> a, Pane p) {
		this.Enemies = r;
		this.Allies = a;
		this.Plant_Pane = p;
	}
	
	public void run() {

		
		while (this.flag) {
			for(int i=0; i<this.Enemies.size(); i++) {
				double check = this.Enemies.get(i).return_token().translateXProperty().get();
				if (check < -15 && check > -25) {
					for (int j=0; j<this.Allies.size(); j++) {
						if (this.Allies.get(j).GetPosition() == 9 && this.Allies.get(j).GetPositionC() == (int)(i+1)) {
							this.Allies.get(j).Get_GUI().setVisible(false);
							System.out.println("Zombie " + i + " Eating");
						}
					}
				}
				
				else if (check < -200 && check > -210) {
					for (int j=0; j<this.Allies.size(); j++) {
						if (this.Allies.get(j).GetPosition() == 8 && this.Allies.get(j).GetPositionC() == i+1) {
							System.out.println("avsasva");
							this.Allies.get(j).Get_GUI().setVisible(false);
							System.out.println("Zombie " + i + " Eating");
						}
					}
				}
				
				else if (check < -317 && check > -327) {
					for (int j=0; j<this.Allies.size(); j++) {
						if (this.Allies.get(j).GetPosition() == 7 && this.Allies.get(j).GetPositionC() == i+1) {
							System.out.println("avsasva");
							this.Allies.get(j).Get_GUI().setVisible(false);
							System.out.println("Zombie " + i + " Eating");
						}
					}
				}
				
				else if (check < -450 && check > -460) {
					for (int j=0; j<this.Allies.size(); j++) {
						System.out.println(this.Allies.get(j).GetPosition() + " as" + this.Allies.get(j).GetPositionC() + " " + i+1);
						if (this.Allies.get(j).GetPosition() == 6 && this.Allies.get(j).GetPositionC() == i+1) {
							this.Allies.get(j).Get_GUI().setVisible(false);
							System.out.println("Zombie " + i + " Eating");
						}
					}
				}
				
				else if (check < -573 && check > -583) {
					for (int j=0; j<this.Allies.size(); j++) {
						System.out.println(this.Allies.get(j).GetPosition() + " as" + this.Allies.get(j).GetPositionC() + " " + i+1);
						if (this.Allies.get(j).GetPosition() == 5 && this.Allies.get(j).GetPositionC() == i+1) {
							this.Allies.get(j).Get_GUI().setVisible(false);
							System.out.println("Zombie " + i + " Eating");
						}
					}
				}
				
				else if (check < -711 && check > -721) {
					for (int j=0; j<this.Allies.size(); j++) {
						System.out.println(this.Allies.get(j).GetPosition() + " as" + this.Allies.get(j).GetPositionC() + " " + i+1);
						if (this.Allies.get(j).GetPosition() == 4 && this.Allies.get(j).GetPositionC() == i+1) {
							this.Allies.get(j).Get_GUI().setVisible(false);
							System.out.println("Zombie " + i + " Eating");
						}
					}
				}
				
				else if (check < -823 && check > -833) {
					for (int j=0; j<this.Allies.size(); j++) {
						System.out.println(this.Allies.get(j).GetPosition() + " as" + this.Allies.get(j).GetPositionC() + " " + i+1);
						if (this.Allies.get(j).GetPosition() == 3 && this.Allies.get(j).GetPositionC() == i+1) {
							this.Allies.get(j).Get_GUI().setVisible(false);
							System.out.println("Zombie " + i + " Eating");
						}
					}
				}
				
				else if (check < -939 && check > -949) {
					for (int j=0; j<this.Allies.size(); j++) {
						System.out.println(this.Allies.get(j).GetPosition() + " as" + this.Allies.get(j).GetPositionC() + " " + i+1);
						if (this.Allies.get(j).GetPosition() == 2 && this.Allies.get(j).GetPositionC() == i+1) {
							this.Allies.get(j).Get_GUI().setVisible(false);
							System.out.println("Zombie " + i + " Eating");
						}
					}
				}
				
			}
		}
	}
}



class Menu_Box {
	public static void display(Stage primary, Scene Main_Scene, Data Database) {
		Stage window = new Stage();
		
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("Game Menu");
		window.setMinWidth(250);
		window.setMinHeight(300);
		
		Button Resume_Game = new Button("Resume Game");
		Button Save_Game = new Button("Save Game");
		Button Quit_Game = new Button("Quit Game");
		
		Resume_Game.setOnAction(e -> window.close());
		Quit_Game.setOnAction(e -> {primary.setScene(Main_Scene);
									primary.setFullScreen(true);
									window.close();}
		);
		
		Save_Game.setOnAction(e -> {
			try {
				Main.serialize(Database);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		});
		
		VBox box = new VBox();
		box.getChildren().addAll(Resume_Game, Save_Game, Quit_Game);
		box.setAlignment(Pos.CENTER);
		Scene Game_Menu_Scene = new Scene(box);
		window.setScene(Game_Menu_Scene);
		window.showAndWait();
	}
	
}


class Game extends Application implements Serializable{

	private static final long serialVersionUID = 1L;
	private Stage Master;
	Button button;	
	List<Image> images = new ArrayList<>();
	private  Scene scen;
	private int CurrentLevel = 1;
	private int Strength;
	Data Database;
	
	public void SetStage() {
		this.Master.setScene(scen);
		
	}
	
	private static Game c = null;
	public static Game GetInstance() {
		if (c == null) {
			c = new Game();
		}
		return c;
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
				
		//List of Lawnmowers
		ArrayList<LawnMower> Mower_List = new ArrayList<LawnMower>();
		
		//List of Peashooters
		ArrayList<PeaShooter> Pea_List = new ArrayList<PeaShooter>();
		
		//List of Sunflower
		ArrayList<Sunflower> Sunflower_List = new ArrayList<Sunflower>();
		
		//List of Zombies
		ArrayList<Zombies> Zombie_List = new ArrayList<Zombies>();
		
		//List of Plants
		ArrayList<Plants> Plant_List = new ArrayList<Plants>();
		
		
		//List of Players in database
		Database = new Data();
		Database.AddPlayer("kar", "123");

		//Object for Lawn
		Lawn lawn = new Lawn();

		String string_title_sound = "C:\\Users\\karti\\Desktop\\pvz_sound.mp3";
		Media title_sound = new Media(new File(string_title_sound).toURI().toString());
		MediaPlayer mediaplayer = new MediaPlayer(title_sound);
		mediaplayer.setAutoPlay(true);
		MediaView mediaview = new MediaView(mediaplayer);
		
		
		//Zombie animation
		ArrayList<Integer> list = new ArrayList<Integer>();
		ArrayList<Zombie_Run> Enemies = new ArrayList<Zombie_Run>();
		List<Sprite> Zombie_Sprites = new ArrayList<Sprite>();
		list.add(-55);
		list.add(-65);
		list.add(-75);
		list.add(-85);
		list.add(-95);
		FileInputStream in3 = new FileInputStream("C:\\Users\\karti\\Desktop\\Zombieidle.gif");
		Image image3 = new Image(in3);
		Random rand = new Random();
		for (int i=0; i<5; i++) {
			ImageView iv = new ImageView();
			iv.setImage(image3);
			iv.setFitHeight(150);
			iv.setFitWidth(120);
			Enemies.add(new Zombie_Run(iv, list.get(i)));
			Zombie_Sprites.add(new Sprite(image3));
			Zombie_List.add(new Zombies(list.get(i), Enemies.get(i), Strength));
			Enemies.get(i).SetStage(this);
		}
		for (int i=0; i<5; i++) {
			Enemies.get(i).Set_Enemies(Enemies);
		}
		
		
		
		// Login Page Scene
		HBox h1 = new HBox();
		HBox h2 = new HBox();
		VBox v1 = new VBox();
		Button Create_User = new Button("Create New User");
		Button Existing_User = new Button("Login to Existing User");
		Label Enter_Username = new Label("Enter Username");
		Label Enter_Password = new Label("Enter Password");
		TextField Username = new TextField();
		PasswordField Password = new PasswordField();
		h1.getChildren().addAll(Enter_Username, Username);
		h2.getChildren().addAll(Enter_Password, Password);
		h1.setAlignment(Pos.CENTER);
		h2.setAlignment(Pos.CENTER);
		h1.setSpacing(10);
		h2.setSpacing(10);
		FileInputStream in2 = new FileInputStream("C:\\Users\\karti\\Desktop\\login.jpg");
		Image image2 = new Image(in2, 1920, 1080, true, true);
		BackgroundImage bi2 = new BackgroundImage(image2, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
		Background b2 = new Background(bi2);
		v1.setBackground(b2);
		v1.getChildren().addAll(h1, h2, Create_User, Existing_User);
		v1.setAlignment(Pos.CENTER);
		v1.setSpacing(30);
		Scene scene3 = new Scene(v1, 1920, 1080);

		

		// Scene 2 (lawn)
		
		VBox zombie = new VBox();
		VBox Lawn_mower = new VBox();
		Pane plants = new Pane();
		HBox Plant_Panel = new HBox();
		BorderPane layout2 = new BorderPane();
		Timer Timer_Box = new Timer();
		
		Button Return_to_main_menu = new Button("Menu");
		Button Sunflower_Button = new Button();
		Button Peashooter_Button = new Button();
		Button Wallnut_Button = new Button();
		Button Cherry_Button = new Button();
		TextField Suns_Collected = new TextField();
		Suns_Collected.setText("0");
		Suns_Collected.setFont(Font.font("Verdana", FontWeight.BLACK, 15));
		Label Suns_Token = new Label("Sun Tokens: ");
		Suns_Token.setScaleX(2);
		Suns_Token.setScaleY(2);
		Suns_Collected.setDisable(true);
		Suns_Collected.setAlignment(Pos.TOP_RIGHT);
		Suns_Token.setAlignment(Pos.TOP_RIGHT);
		
		Sunflower_Button.setDisable(true);
		Peashooter_Button.setDisable(true);
		Wallnut_Button.setDisable(true);
		Cherry_Button.setDisable(true);
		
		FileInputStream Sun_in = new FileInputStream("C:\\Users\\karti\\Desktop\\Sun_resized.jpg");
		FileInputStream Pea_in = new FileInputStream("C:\\Users\\karti\\Desktop\\Pea_resized.jpg");
		FileInputStream Wal_in = new FileInputStream("C:\\Users\\karti\\Desktop\\Wal_resized.jpg");
		FileInputStream Che_in = new FileInputStream("C:\\Users\\karti\\Desktop\\Che_resized.jpg");
		FileInputStream Tok_in = new FileInputStream("C:\\Users\\karti\\Desktop\\Tok_resized.png");
		FileInputStream Law_in = new FileInputStream("C:\\Users\\karti\\Desktop\\lawn_mower.gif");
		Image Sunflower_Image = new Image(Sun_in);
		Image Peashooter_Image = new Image(Pea_in);
		Image Wallnut_Image = new Image(Wal_in);
		Image Cherry_Image = new Image(Che_in);
		Image Token_image = new Image(Tok_in);
		Image Lawn_Image = new Image(Law_in);
		ImageView Sunflower_view = new ImageView(Sunflower_Image);
		ImageView Peashooter_view = new ImageView(Peashooter_Image);
		ImageView Wallnut_view = new ImageView(Wallnut_Image);
		ImageView Cherry_view = new ImageView(Cherry_Image);
		ImageView Token_view = new ImageView(Token_image);
		ArrayList<ImageView> Lawnmower_view = new ArrayList<ImageView>();
		for (int i=0; i<5; i++) {
			Lawnmower_view.add(new ImageView(Lawn_Image));
			Mower_List.add(new LawnMower(Lawnmower_view.get(i), i+1, Zombie_List));
			Enemies.get(i).Set_Mow(Mower_List.get(Mower_List.size()-1));
		}
		ArrayList<ImageView> Suns = new ArrayList<ImageView>();
		Suns.add(Token_view);
		Sun_Drop s = new Sun_Drop(Token_view);
		
		
	
		Sunflower_Button.setGraphic(Sunflower_view);
		Peashooter_Button.setGraphic(Peashooter_view);
		Wallnut_Button.setGraphic(Wallnut_view);
		Cherry_Button.setGraphic(Cherry_view);
		Plant_Panel.getChildren().addAll(Return_to_main_menu, Sunflower_Button, Peashooter_Button, Wallnut_Button, Cherry_Button, Suns_Token, Suns_Collected, s.return_token(), Timer_Box.Get_Time());
		Plant_Panel.setSpacing(35);
		for (int i=0; i<5; i++) {
			Lawn_mower.getChildren().add(Lawnmower_view.get(i));
		}
		
		for (int i=0; i<Enemies.size(); i++) {
			zombie.getChildren().add(Enemies.get(i).return_token());
		}
		
		Lawn_mower.setSpacing(70);
		
		FileInputStream in1 = new FileInputStream("C:\\Users\\karti\\Desktop\\lawn_resized.png");
		Image image1 = new Image(in1, 1550, 805, false, true);
		BackgroundImage bi1 = new BackgroundImage(image1, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
		Background b1 = new Background(bi1);
		layout2.setBackground(b1);
		layout2.setTop(Plant_Panel);
		layout2.setRight(zombie);
		layout2.setLeft(Lawn_mower);
		layout2.setCenter(plants);
		Scene scene2 = new Scene(layout2, 1720, 900);
		
		//Eat
		Eat Eating = new Eat(Enemies, Plant_List, plants);
		Thread Second_Thread = new Thread(Eating);

		
		Peashooter_Button.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
			scene2.setOnMouseReleased(new EventHandler<MouseEvent>() {
				boolean mouse = true;
				@Override
			    public void handle(MouseEvent event) {
					if (mouse == true && lawn.return_suns() >= 50) {
						Peashooter_Button.setDisable(false);
						lawn.Add_Suns(-50);
						Suns_Collected.setText(Integer.toString(Integer.parseInt(Suns_Collected.getText())-50));
						if (lawn.return_suns() < 50) {
							Peashooter_Button.setDisable(true);
						}
						if (lawn.return_suns() < 50) {
							Sunflower_Button.setDisable(true);
						}
						if (lawn.return_suns() < 100) {
							Wallnut_Button.setDisable(true);
						}
						ImageView iv2 = new ImageView();
						FileInputStream in4 = null;
						try {
							in4 = new FileInputStream("C:\\Users\\karti\\Desktop\\Peashoot.gif");
						} catch (FileNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						Image image4 = new Image(in4);
						iv2.setImage(image4);
				        double Click_X = event.getSceneX();
				        double Click_Y = event.getSceneY();
				        Pea_List.add(new PeaShooter(Click_X, Click_Y, iv2));
				        Plant_List.add(Pea_List.get(Pea_List.size()-1));
				        iv2.setLayoutX(Click_X - 140);
				        iv2.setLayoutY(Click_Y - 170);
				        Shooting shot = new Shooting(Click_X-72, Click_Y - 127, (Pea_List.get(Pea_List.size()-1).GetPosition()));
				        int set = 0;
				        for (int i=0 ; i<Zombie_List.size(); i++) {
				        	if (Zombie_List.get(i).GetPositionC() == Pea_List.get(Pea_List.size()-1).GetPositionC()) {
				        		set = i;
				        	}
				        }
				        shot.Set_Sprite(Zombie_Sprites.get(set), zombie, Enemies.get(set).return_token(), Zombie_List.get(set));
				        
				        plants.getChildren().addAll(iv2, shot.Get_Pea());
				        shot.Start();
				        try {
							in4.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				        mouse = false;
					}
			        
			    }
			});
			}
			});		
		
		Sunflower_Button.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
			scene2.setOnMouseReleased(new EventHandler<MouseEvent>() {
				boolean mouse = true;
				@Override
			    public void handle(MouseEvent event) {
					if (mouse == true && lawn.return_suns() >= 25) {
						Sunflower_Button.setDisable(false);
						lawn.Add_Suns(-50);
						Suns_Collected.setText(Integer.toString(lawn.return_suns()));
						if (lawn.return_suns() < 50) {
							Peashooter_Button.setDisable(true);
						}
						if (lawn.return_suns() < 25) {
							Sunflower_Button.setDisable(true);
						}
						if (lawn.return_suns() < 100) {
							Wallnut_Button.setDisable(true);
						}
						ImageView iv2 = new ImageView();
						FileInputStream in4 = null;
						try {
							in4 = new FileInputStream("C:\\Users\\karti\\Desktop\\Sun.gif");
						} catch (FileNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						Image image4 = new Image(in4);
						iv2.setImage(image4);
				        double Click_X = event.getSceneX();
				        double Click_Y = event.getSceneY();
				        iv2.setLayoutX(Click_X - 140);
				        iv2.setLayoutY(Click_Y - 170);
				        plants.getChildren().add(iv2);
				        ImageView Sun = new ImageView(Token_image);
				        Sun_From_Sunflower new_Sun = new Sun_From_Sunflower(Click_X - (230 * (Suns.size()-1)), Click_Y - 170, Sun);
				        Plant_Panel.getChildren().add(new_Sun.return_token());
				        new_Sun.Start();
				        Suns.add(Sun);
				        Sunflower_List.add(new Sunflower(Click_X, Click_Y, new_Sun, iv2));
				        Plant_List.add(Sunflower_List.get(Sunflower_List.size()-1));
				        try {
							in4.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				        mouse = false;
					}
			        
			    }
			});
			}	
			});
		
		
		Wallnut_Button.setOnMousePressed(new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent event) {
				scene2.setOnMouseReleased(new EventHandler<MouseEvent>() {
					boolean mouse = true;
					@Override
					public void handle(MouseEvent event) {
						if (mouse == true && lawn.return_suns() >= 100 && CurrentLevel >= 2) {
							Wallnut_Button.setDisable(false);
							lawn.Add_Suns(-100);
							Suns_Collected.setText(Integer.toString(lawn.return_suns()));
							if (lawn.return_suns() < 50) {
								Peashooter_Button.setDisable(true);
							}
							if (lawn.return_suns() < 25) {
								Sunflower_Button.setDisable(true);
							}
							if (lawn.return_suns() < 100) {
								Wallnut_Button.setDisable(true);
							}
							ImageView iv2 = new ImageView();
							FileInputStream in4 = null;
							try {
								in4 = new FileInputStream("C:\\Users\\karti\\Desktop\\walnut_full_life.gif");
							} catch (FileNotFoundException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							Image image4 = new Image(in4);
							iv2.setImage(image4);
					        double Click_X = event.getSceneX();
					        double Click_Y = event.getSceneY();
					        iv2.setLayoutX(Click_X - 120);
					        iv2.setLayoutY(Click_Y - 150);
					        plants.getChildren().add(iv2);
					        try {
								in4.close();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
					        mouse = false;

						}
					}
				});
			}
		});
		
		
		
		scene2.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				for (int i=0; i<5; i++) {
				}
				for (int i=0; i<5; i++) { 
				}
				if (s.flag == true && event.getSceneY() > Suns.get(0).translateYProperty().get() + 11 && event.getSceneY() < Suns.get(0).translateYProperty().get() + 86 && event.getSceneX() > Suns.get(0).translateXProperty().get() + 841 && event.getSceneX() < Suns.get(0).translateXProperty().get() + 914) {
					Suns_Collected.setText(Integer.toString(Integer.parseInt(Suns_Collected.getText()) + 25));
					s.flag = false;
					s.return_token().setVisible(false);
					lawn.Add_Suns(25);
					if (lawn.return_suns() >= 50) {
						Peashooter_Button.setDisable(false);
					}
					if (lawn.return_suns() >= 25) {
						Sunflower_Button.setDisable(false);
					}
					if (lawn.return_suns() >= 100) {
						Wallnut_Button.setDisable(false);
					}
				}
				for (int i=1; i<Suns.size(); i++) {
					if (Sunflower_List.get(i-1).Get_Sun().flag == true && Sunflower_List.get(i-1).SunProduction(event.getSceneX(), event.getSceneY())) {
						Suns_Collected.setText(Integer.toString(Integer.parseInt(Suns_Collected.getText()) + 25));
						Sunflower_List.get(i-1).Get_Sun().flag = false;
						Sunflower_List.get(i-1).Get_Sun().return_token().setVisible(false);
						lawn.Add_Suns(25);
						if (lawn.return_suns() >= 50) {
							Peashooter_Button.setDisable(false);
						}
						if (lawn.return_suns() >= 25) {
							Sunflower_Button.setDisable(false);
						}
						if (lawn.return_suns() >= 100) {
							Wallnut_Button.setDisable(false);
						}
					}
				}
			}
			
		});

		//Scene 4 (Choose Level)
		HBox Choose_levels_buttons = new HBox();
		Button Level_1 = new Button("Level 1");
		Button Level_2 = new Button("Level 2");
		Button Level_3 = new Button("Level 3");
		Button Level_4 = new Button("Level 4");
		Button Level_5 = new Button("Level 5");
		FileInputStream in11 = new FileInputStream("C:\\Users\\karti\\Desktop\\Choose_back.jpg");
		Image Choose_Image = new Image(in11, 1920, 1080, true, true);
		BackgroundImage Bi_choose = new BackgroundImage(Choose_Image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT); 
		Choose_levels_buttons.getChildren().addAll(Level_1, Level_2, Level_3, Level_4, Level_5);
		Choose_levels_buttons.setAlignment(Pos.CENTER);
		Choose_levels_buttons.setSpacing(50);
		Background bb = new Background(Bi_choose);
		Choose_levels_buttons.setBackground(bb);
		Scene Choose_Scene = new Scene(Choose_levels_buttons, 1920, 1080);
		
		
		// Scene 1 (Title screen)
		primaryStage.setTitle("Plants VS Zombies");	
		Button new_game_bt = new Button("New Game");
		Button load_game_bt = new Button("Load Game");
		Button quit_game_bt = new Button("Quit Game");
		Button choose_level_bt = new Button("Choose Level");
		new_game_bt.setLayoutX(100);
		new_game_bt.setLayoutY(100);
		load_game_bt.setLayoutX(200);
		load_game_bt.setLayoutY(100);
		choose_level_bt.setLayoutX(300);
		choose_level_bt.setLayoutY(100);
		quit_game_bt.setLayoutX(400);
		quit_game_bt.setLayoutY(100);
		
		new_game_bt.setOnAction(e -> {
			if (!v1.getChildren().contains(Create_User)) {
				v1.getChildren().add(Create_User);
			}
			v1.getChildren().remove(Existing_User);
			primaryStage.setScene(scene3);
		});
		load_game_bt.setOnAction(e -> {
			if (!v1.getChildren().contains(Existing_User)) {
				v1.getChildren().add(Existing_User);
			}
			v1.getChildren().remove(Create_User);
			primaryStage.setScene(scene3);
		});
		choose_level_bt.setOnAction(e -> {
			primaryStage.setScene(Choose_Scene);
			primaryStage.setMaximized(true);
		});
		
		Level_1.setOnAction(e -> {
			primaryStage.setScene(scene3);
			primaryStage.setMaximized(true);
		});
		Level_2.setOnAction(e -> {
			this.Strength = 5;
			CurrentLevel = 2;
			for (int i=0; i<5; i++) {
				Zombie_List.get(i).SetHealth(this.Strength);
			}
			primaryStage.setScene(scene3);
			primaryStage.setMaximized(true);
		});
		Level_3.setOnAction(e -> {
			this.Strength = 6;
			CurrentLevel = 3;
			for (int i=0; i<5; i++) {
				Zombie_List.get(i).SetHealth(this.Strength);
			}
			primaryStage.setScene(scene3);
			primaryStage.setMaximized(true);
		});
		Level_4.setOnAction(e -> {
			this.Strength = 7;
			CurrentLevel = 4;
			for (int i=0; i<5; i++) {
				Zombie_List.get(i).SetHealth(this.Strength);
			}
			primaryStage.setScene(scene3);
			primaryStage.setMaximized(true);
		});
		Level_5.setOnAction(e -> {
			this.Strength = 8;
			CurrentLevel = 5;
			for (int i=0; i<5; i++) {
				Zombie_List.get(i).SetHealth(this.Strength);
			}
			primaryStage.setScene(scene3);
			primaryStage.setMaximized(true);
		});
		
		Group menu_group = new Group();
		menu_group.getChildren().add(new_game_bt);
		menu_group.getChildren().add(load_game_bt);
		menu_group.getChildren().add(choose_level_bt);
		menu_group.getChildren().add(quit_game_bt);
		StackPane layout = new StackPane();
		FileInputStream in = new FileInputStream("C:\\Users\\karti\\Desktop\\pvz_background.jpg");
		Image image = new Image(in, 1920, 900, true, true);
		BackgroundImage bi = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
		Background b = new Background(bi);
		layout.setBackground(b);
		layout.getChildren().add(menu_group);
		layout.getChildren().add(mediaview);
		
		Scene scene = new Scene(layout, 1920, 1080);
		quit_game_bt.setOnAction(e -> primaryStage.close());
		Create_User.setOnAction(e -> {
			if (Username.getText().compareTo("") == 0 || Password.getText().compareTo("") == 0) {
				Alert alert = new Alert(AlertType.ERROR, "Please enter a valid password or username and try again!", ButtonType.OK);
				alert.showAndWait();
				if (alert.getResult() == ButtonType.OK) {
					alert.close();
				}
			}
			else {
				Database.AddPlayer(Username.getText(), Password.getText());
				Username.clear();
				Password.clear();
				primaryStage.setScene(scene2);
				primaryStage.setMaximized(true);
				s.Start();
				Timer_Box.Start();
				for (int i=0; i<Enemies.size(); i++) {
					Enemies.get(i).Start();
				}
				Second_Thread.start();
			}
		});
		
		Existing_User.setOnAction(e -> {
			if (Username.getText().compareTo("") == 0 || Password.getText().compareTo("") == 0) {
				Alert alert = new Alert(AlertType.ERROR, "Please enter a valid password or username and try again!", ButtonType.OK);
				alert.showAndWait();
				if (alert.getResult() == ButtonType.OK) {
					alert.close();
				}
			}
			else {
				if (Database.ReturnPlayer(Username.getText(), Password.getText())) {
					Alert alert = new Alert(AlertType.INFORMATION, "User found! Press OK to start from saved spot!", ButtonType.OK);
					alert.showAndWait();
					if (alert.getResult() == ButtonType.OK) {
						alert.close();
					}
					Username.clear();
					Password.clear();
					for (int i=0; i<Enemies.size(); i++) {
						Enemies.get(i).Start();
					}
					s.Start();
					Timer_Box.Start();
					primaryStage.setScene(scene2);
					primaryStage.setMaximized(true);
				}
				else {
					Alert alert = new Alert(AlertType.WARNING, "Please enter valid credentials to load your game or create a new user by pressing the Create New User button!", ButtonType.CLOSE);
					alert.showAndWait();
					if (alert.getResult() == ButtonType.CLOSE) {
						alert.close();
					}
				}
			}
		});
		
		Return_to_main_menu.setOnAction(e -> Menu_Box.display(primaryStage, scene, Database));
		
//		Scene to Login after pressing the load game button on the main menu (this checks for the name in database)
		
		
		primaryStage.setScene(scene);
		primaryStage.setFullScreen(true);
		primaryStage.show();
		
			
	}
	
}



public class Main extends Application {
	

	public static void main(String[] args){
		// TODO Auto-generated method stub
		launch(args);
		
	}

	public static void serialize(Data database) throws IOException {
		Data d1 = database;
		ObjectOutputStream out = null;
		try {
			out = new ObjectOutputStream (new FileOutputStream("Player_database.txt"));
			out.writeObject(d1);
		}
		finally {
			out.close();
		}
	}
		
	public static Object deserialize() throws IOException, ClassNotFoundException{
		ObjectInputStream in = null;
		Data database = null;
		try {
			in = new ObjectInputStream(new FileInputStream("out.txt"));
			database = (Data) in.readObject();
		}
		finally {
			in.close();
		}
		
		return database;
	}
	
	@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub
		Game l = new Game();
		l.start(arg0);

	}
	

}



