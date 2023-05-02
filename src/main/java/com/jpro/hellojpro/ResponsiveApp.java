package com.jpro.hellojpro;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import one.jpro.routing.Filters;
import one.jpro.routing.Route;
import one.jpro.routing.RouteApp;
import one.jpro.routing.RouteUtils;
import one.jpro.routing.dev.DevFilter;

import static one.jpro.routing.RouteUtils.getNode;
import static one.jpro.routing.RouteUtils.redirect;

import com.jpro.webapi.WebAPI;

public class ResponsiveApp extends RouteApp {

    public static void main(String[] args) {
        launch(args);
    }

    public Route createRoute() {
        return Route.empty()
                .and(redirect("/", "/info"))
                .and(getNode("/info", (r) -> getView()));
        // .filter(Filters.FullscreenFilter(true))
        // .filter(RouteUtils.sideTransitionFilter(1))
        // .filter(DevFilter.create());
    }

    private Node getView() {
        StackPane root = new StackPane();

        TitledPane titledPane2 = new TitledPane();
        titledPane2.setMinWidth(100);
        titledPane2.setPrefWidth(100);
        titledPane2.setMaxWidth(100);
        titledPane2.setWrapText(true);
        titledPane2.setAlignment(Pos.TOP_LEFT);

        Label lbl1 = new Label(
                "5 sample paragraphs for reading test on 1. Traveling in a D. T. C. Bus  2. Look Before You Leap 3. Rising Prices 4. ‘Simple Living, High Thinking’ 5. A Football Match");
        lbl1.setWrapText(true);
        lbl1.setMinHeight(Region.USE_PREF_SIZE);

        titledPane2.setGraphic(lbl1);

        Label lbl12 = new Label(
                "Delhi is a crowded city. There are very few rich people who travel by their own vehicles. The majority of the people cannot afford to hire a taxi or a three-wheeler. They have to depend on D.T.C. buses, which are the cheapest mode of conveyance.");
        lbl12.setWrapText(true);

        titledPane2.setContent(new StackPane(lbl12));

        root.getChildren().addAll(titledPane2);

        Platform.runLater(() -> {
            System.out.println(titledPane2.lookup(".title"));

            Pane title = (Pane) titledPane2.lookup(".title");
            // title.setMinSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
            // title.setPrefHeight(Region.USE_COMPUTED_SIZE);
            // title.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
            // title.setMaxHeight(Double.MAX_VALUE);
            title.setPadding(new Insets(20));

            // HBox test = new HBox();
            // test.setPrefWidth(100);
            // test.getChildren().add(lbl1);
            //
            //
            // title.getChildren().add(test);

            // TitledPaneSkin test;

            // title.setPrefHeight(50);

            // Text text = (Text) title.lookup(".text");
            // text.setMinHeight(Region.USE_PREF_SIZE);
            // text.resize(Region.USE_COMPUTED_SIZE, Region.USE_PREF_SIZE);
            // LabeledText text = (LabeledText) title.lookup((".text"));

            // Label text = (Label) title.lookup(".label");

            // System.out.println("test:" + text.prefHeight(-1));
            // or
            // title.prefHeightProperty().bind(text.getBoundsInLocal().getHeight());

            // title.prefHeightProperty().bind(lbl1.heightProperty());

            Button btnTest = new Button();
            btnTest.setOnAction(event -> {
                System.out.println("-----------------------------");
                Scene scene = getScene();
                System.out.println("scene width: " + scene.getWidth());
                System.out.println("scene height: " + scene.getHeight());

                Stage stage = getStage();
                System.out.println("stage width: " + stage.getWidth());
                System.out.println("stage height: " + stage.getHeight());

                System.out.println("root width: " + ((Region) scene.getRoot()).getWidth());
                System.out.println("root height: " + ((Region) scene.getRoot()).getHeight());
            });

            root.getChildren().add(btnTest);

            // Scene scene = getScene();
            // scene.heightProperty().addListener((observable, oldValue, newValue) -> {
            // System.out.println("width1: " + newValue);
            // });

            // StackPane myRoot = (StackPane) getScene().getRoot();
            // myRoot.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
            // myRoot.setMaxSize(300, 300);
            // myRoot.setMinSize(300, 300);
            // myRoot.setPrefSize(300, 300);

            // myRoot.widthProperty().addListener((observable, oldValue, newValue) -> {
            // System.out.println("width: " + newValue);
            // });
            // myRoot.heightProperty().addListener((observable, oldValue, newValue) -> {
            // System.out.println("height: " + newValue);
            // });

            // WebAPI.getWebAPI(getStage()).layoutRoot(getScene()).;
            // WebAPI.layoutRoot(getStage());

            // Label lblTemp = new Label(lbl1.getText());
            // lblTemp.setWrapText(true);
            // lblTemp.prefWidthProperty().bind(lbl1.widthProperty());
            // lblTemp.setVisible(false);
            //
            //
            // root.getChildren().add(lblTemp);
            //
            // title.prefHeightProperty().bind(lblTemp.heightProperty()
            // .add(title.getInsets().getTop() + title.getInsets().getBottom()));

            title.prefHeightProperty().bind(lbl1.heightProperty()
                    .add(title.getInsets().getTop() + title.getInsets().getBottom()));
        });

        return root;
    }

}
