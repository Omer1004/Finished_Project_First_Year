package application;
	
import controller.Controller;
import javafx.application.Application;
import javafx.stage.Stage;
import model.AutoGen;
import model.Company;
import view.View;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
//Omer Iny - 206571739

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Company model = new Company("");
			View view = new View(primaryStage);
			Controller controller = new Controller(view,model);
			primaryStage.setTitle(controller.getNameOfCompany());
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}
}
