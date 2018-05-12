package xdean.css.editor;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import xdean.css.editor.controller.CodeAreaLiteController;

public class CSSMain extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        // Load custom fonts used in css stylesheet
        Font.loadFont(CSSMain.class.getResource("/fonts/OpenSans-Regular.ttf").toExternalForm(), 10);
        Font.loadFont(CSSMain.class.getResource("/fonts/FiraCode-Regular.ttf").toExternalForm(), 10);
        Font.loadFont(CSSMain.class.getResource("/fonts/FiraCode-Bold.ttf").toExternalForm(), 10);

        CodeAreaLiteController codeAreaLite = new CodeAreaLiteController();

        Scene scene = new Scene(codeAreaLite);

        scene.getStylesheets().add("/css/skin/gtk-dark.css");

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
