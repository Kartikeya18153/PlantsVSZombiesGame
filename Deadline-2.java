package javafxtrial;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.animation.*;
import javafx.application.*;
import javafx.scene.*;
import javafx.event.*;
import javafx.stage.*;
import javafx.util.Duration;
import javafx.scene.control.*;
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


class Sun_Drop {
	private ImageView Token;
	private Timeline timeline;
	
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
	private double X;
	private double Y;
	
	Shooting(double x, double y) {
		this.X = x;
		this.Y = y;
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
	
	private void Setup_Timeline() {
		KeyValue kv = new KeyValue(this.pea.centerXProperty(), 1100);
		KeyFrame kf = new KeyFrame(Duration.seconds(4), kv);
		Timeline timeline = new Timeline(kf);
		timeline.setCycleCount(Animation.INDEFINITE);
		timeline.play();
	}
	
	
}


class Timer {
	private Integer Time_Seconds = 120;	
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



class Menu_Box {
	public static void display(Stage primary, Scene Main_Scene) {
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
		
		VBox box = new VBox();
		box.getChildren().addAll(Resume_Game, Save_Game, Quit_Game);
		box.setAlignment(Pos.CENTER);
		Scene Game_Menu_Scene = new Scene(box);
		window.setScene(Game_Menu_Scene);
		window.showAndWait();
	}
	
}


class LoadingScreen extends Application {

	Button button;	
	List<Image> images = new ArrayList<>();
	
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub


		String string_title_sound = "C:\\Users\\karti\\Desktop\\pvz_sound.mp3";
		Media title_sound = new Media(new File(string_title_sound).toURI().toString());
		MediaPlayer mediaplayer = new MediaPlayer(title_sound);
		mediaplayer.setAutoPlay(true);
		MediaView mediaview = new MediaView(mediaplayer);
		
		
		//Zombie animation
		List<Integer> list = new ArrayList<>();
		list.add(-55);
		list.add(65);
		list.add(200);
		list.add(345);
		list.add(485);
		ImageView iv = new ImageView();
		FileInputStream in3 = new FileInputStream("C:\\Users\\karti\\Desktop\\Zombieidle.gif");
		Image image3 = new Image(in3);
		iv.setImage(image3);
		iv.setFitHeight(150);
		iv.setFitWidth(120);
				
		TranslateTransition transition = new TranslateTransition();
		transition.setDuration(Duration.seconds(10));
		Random rand = new Random();
		transition.setFromY(list.get(rand.nextInt(list.size())));
		transition.setFromX(0);
		transition.setToX(-1000);
		transition.setAutoReverse(false);
		transition.setCycleCount(Animation.INDEFINITE);
		transition.setNode(iv);
		
		
		//Timeline
		Timeline timeline = new Timeline();
		timeline.setCycleCount(Timeline.INDEFINITE);
		timeline.setAutoReverse(true);
		KeyValue kv = new KeyValue(iv.xProperty(), -500);
		KeyFrame kf = new KeyFrame(Duration.seconds(10), kv);
		timeline.getKeyFrames().add(kf);
		
		
		
		// Login Page Scene
		HBox h1 = new HBox();
		HBox h2 = new HBox();
		VBox v1 = new VBox();
		Button Create_User = new Button("Create User");
		Label Enter_Username = new Label("Enter Username");
		Label Enter_Password = new Label("Enter Password");
		TextField Username = new TextField();
		TextField Password = new TextField();
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
		v1.getChildren().addAll(h1, h2, Create_User);
		v1.setAlignment(Pos.CENTER);
		v1.setSpacing(30);
		Scene scene3 = new Scene(v1, 1920, 1080);

		

		// Scene 2 (lawn)
		
		VBox zombie = new VBox();
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
		Label Suns_Token = new Label("Sun Tokens: ");
		Suns_Token.setScaleX(2);
		Suns_Token.setScaleY(2);
		Suns_Collected.setDisable(true);
		Suns_Collected.setAlignment(Pos.TOP_RIGHT);
		Suns_Token.setAlignment(Pos.TOP_RIGHT);
		
		FileInputStream Sun_in = new FileInputStream("C:\\Users\\karti\\Desktop\\Sun_resized.jpg");
		FileInputStream Pea_in = new FileInputStream("C:\\Users\\karti\\Desktop\\Pea_resized.jpg");
		FileInputStream Wal_in = new FileInputStream("C:\\Users\\karti\\Desktop\\Wal_resized.jpg");
		FileInputStream Che_in = new FileInputStream("C:\\Users\\karti\\Desktop\\Che_resized.jpg");
		FileInputStream Tok_in = new FileInputStream("C:\\Users\\karti\\Desktop\\Tok_resized.png");
		Image Sunflower_Image = new Image(Sun_in);
		Image Peashooter_Image = new Image(Pea_in);
		Image Wallnut_Image = new Image(Wal_in);
		Image Cherry_Image = new Image(Che_in);
		Image Token_image = new Image(Tok_in);
		ImageView Sunflower_view = new ImageView(Sunflower_Image);
		ImageView Peashooter_view = new ImageView(Peashooter_Image);
		ImageView Wallnut_view = new ImageView(Wallnut_Image);
		ImageView Cherry_view = new ImageView(Cherry_Image);
		ImageView Token_view = new ImageView(Token_image);
		
		Sun_Drop s = new Sun_Drop(Token_view);
	
		Sunflower_Button.setGraphic(Sunflower_view);
		Peashooter_Button.setGraphic(Peashooter_view);
		Wallnut_Button.setGraphic(Wallnut_view);
		Cherry_Button.setGraphic(Cherry_view);
		Plant_Panel.getChildren().addAll(Return_to_main_menu, Sunflower_Button, Peashooter_Button, Wallnut_Button, Cherry_Button, Suns_Token, Suns_Collected, s.return_token(), Timer_Box.Get_Time());
		Plant_Panel.setSpacing(35);
		
		zombie.getChildren().add(iv);
		
		FileInputStream in1 = new FileInputStream("C:\\Users\\karti\\Desktop\\lawn_resized.png");
		Image image1 = new Image(in1, 1550, 805, false, true);
		BackgroundImage bi1 = new BackgroundImage(image1, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
		Background b1 = new Background(bi1);
		layout2.setBackground(b1);
		layout2.setTop(Plant_Panel);
		layout2.setRight(zombie);
		layout2.setCenter(plants);
		Scene scene2 = new Scene(layout2, 1720, 900);
		
		Peashooter_Button.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
			scene2.setOnMouseReleased(new EventHandler<MouseEvent>() {
				boolean mouse = true;
				@Override
			    public void handle(MouseEvent event) {
					if (mouse == true) {
						
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
				        System.out.println(event.getSceneX());
				        System.out.println(event.getSceneY());
				        iv2.setLayoutX(Click_X - 58);
				        iv2.setLayoutY(Click_Y - 170);
				        Shooting shot = new Shooting(Click_X+10, Click_Y - 127);				        
				        
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
					if (mouse == true) {
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
				        System.out.println(event.getSceneX());
				        System.out.println(event.getSceneY());
				        iv2.setLayoutX(Click_X - 58);
				        iv2.setLayoutY(Click_Y - 170);
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
		
		scene2.setOnMouseClicked(e -> {System.out.println(s.return_token().getX());
									System.out.println(s.return_token().getY());});
		
		
		
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
		
		new_game_bt.setOnAction(e -> primaryStage.setScene(scene3));
		
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
		primaryStage.setScene(scene2);
		primaryStage.setMaximized(true);
		s.Start();
		Timer_Box.Start();
		transition.play();
		});
		Return_to_main_menu.setOnAction(e -> Menu_Box.display(primaryStage, scene));
		
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

	@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub
		LoadingScreen l = new LoadingScreen();
		l.start(arg0);

	}
	

}



