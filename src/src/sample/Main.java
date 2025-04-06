package sample;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Create an instance of our GUI class
        GUI gui = new GUI(primaryStage);

        // Set up the main window
        primaryStage.setTitle("JavaFX GUI Example");
        primaryStage.setScene(gui.getScene());
        primaryStage.show();
    }
}

class GUI {
    private Scene scene;
    private Label label;
    private Button button;
    private int clickCount = 0;

    public GUI(Stage stage) {
        // Create UI components
        label = new Label("Box Office GUI");
        button = new Button("Click Me");

        // Set button action
        button.setOnAction(e -> {
            clickCount++;
            label.setText("Button clicked " + clickCount + " times");
        });

        // Create layout and add components
        VBox layout = new VBox(20);
        layout.getChildren().addAll(label, button);

        // Create scene with the layout
        scene = new Scene(layout, 300, 200);
    }

    public Scene getScene() {
        return scene;
    }
}