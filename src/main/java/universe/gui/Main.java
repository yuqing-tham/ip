package universe.gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import universe.Universe;

/**
 * The Main class for the GUI.
 * The code is modified from the JavaFx tutorial.
 */
public class Main extends Application {
    private Universe universe = new Universe("data/chores.txt");

    /**
     * Overrides start() method in parent class Application.
     * @param stage Window in desktop application.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setUniverse(universe);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
