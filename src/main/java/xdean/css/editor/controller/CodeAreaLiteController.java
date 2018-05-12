package xdean.css.editor.controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollBar;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

import org.controlsfx.control.StatusBar;
import org.fxmisc.richtext.CodeArea;

import xdean.css.editor.config.Options;
import xdean.css.editor.controller.comp.SearchBar;
import xdean.css.editor.controller.manager.CodeAreaManager;
import xdean.css.editor.controller.manager.StatusBarManager;
import xdean.jfx.ex.support.RecentFileMenuSupport;


import lombok.extern.slf4j.Slf4j;
import static xdean.jex.util.lang.ExceptionUtil.uncatch;

/**
 * FXML Controller class
 *
 * @author andje22
 */
@Slf4j
public class CodeAreaLiteController extends CodeArea {

    @FXML
    Button newButton, openButton, saveButton, undoButton, redoButton;
    @FXML
    ScrollBar verticalScrollBar, horizontalScrollBar;

    @FXML
    VBox bottomExtraPane;

    @FXML
    CodeArea codeArea;
    CodeAreaManager manager;
    @FXML
    StatusBar statusBar;

    StatusBarManager statusBarManager;
    RecentFileMenuSupport recentSupport;
    SearchBar searchBar;
     ObjectProperty<File> file;
   
     
     public CodeAreaLiteController() {
        initialize();
    }

    /**
     * Initializes the controller class.
     */
    private void initialize() {

        try {
            final FXMLLoader loader = new FXMLLoader();
            loader.setLocation(CodeAreaLiteController.class.getResource("/fxml/FXMLCodeAreaLite.fxml")); //NOI18N
            loader.setController(this);
            loader.setRoot(this);
            loader.load();
        } catch (IOException ex) {
            Logger.getLogger(CodeAreaLiteController.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.manager = new CodeAreaManager(codeArea);
        this.codeArea = manager.getCodeArea();
//        setParagraphGraphicFactory(LineNumberFactory.get(this));
//         richChanges()
//                .filter(ch -> !ch.getInserted().equals(ch.getRemoved())) // XXX
//                .subscribe(change -> this.setStyleSpans(0, StyleFormatter.computeHighlighting(this.getText())));

    }
     
  public void newFile() {
    //openFile(null);
  }

    
      void loadFile() {
      loadFile(file.get());
    }

    void loadFile(File file) {
      uncatch(() -> {
        codeArea.replaceText(new String(Files.readAllBytes(file.toPath()), Options.charset.get()));
        codeArea.moveTo(0);
        codeArea.getUndoManager().forgetHistory();
        manager.saved();
      });
    }

    protected boolean saveToFile(File file) {
        try {
            Files.write(file.toPath(), codeArea.getText().getBytes(Options.charset.get()));
       //     saved();
        } catch (IOException ex) {
            Logger.getLogger(CodeAreaLiteController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }


  public void open() {
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Open");
    fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Style Sheet", "*.css"));
    fileChooser.setInitialDirectory(recentSupport.getLastFile().getParentFile());
   // File selectedFile = fileChooser.showOpenDialog(stage);
//    if (selectedFile != null) {
//      openFile(selectedFile);
//    }
  }

  @FXML
  public void clearRecent() {
    recentSupport.clear();
  }

 

}
