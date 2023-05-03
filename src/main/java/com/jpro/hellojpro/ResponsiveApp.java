package com.jpro.hellojpro;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import one.jpro.routing.Filters;
import one.jpro.routing.Route;
import one.jpro.routing.RouteApp;
import one.jpro.routing.RouteUtils;

public class ResponsiveApp extends RouteApp {

    public static void main(String[] args) {
        launch(args);
    }

    public Route createRoute() {
        return Route.empty()
                .and(RouteUtils.getNode("/", (r) -> getView()))
                .filter(Filters.FullscreenFilter(true));
        // .filter(DevFilter.create());
    }

    private Node getView() {
        VBox root = new VBox(10);
        root.setAlignment(Pos.CENTER);

        Label infoWidth = new Label("Width");
        Label infoHeight = new Label("Height");
        Label infoOrientation = new Label("Orientation");

        root.getChildren().addAll(infoWidth, infoHeight, infoOrientation);

        Scene scene = getScene();

        BooleanProperty OrientationProperty = new SimpleBooleanProperty();

        scene.widthProperty().addListener((observable, oldValue, newValue) -> {
            infoWidth.setText("Width: " + newValue);

            OrientationProperty.set(newValue.doubleValue() > scene.getHeight());
        });

        scene.heightProperty().addListener((observable, oldValue, newValue) -> {
            infoHeight.setText("Height: " + newValue);
        });

        OrientationProperty.addListener((observable, oldValue, newValue) -> {
            System.out.println("val: " + newValue);

            if (newValue) {
                infoOrientation.setText("Orientaion: Landscape");
            } else {
                infoOrientation.setText("Orientaion: Portrait");
            }
        });

        return root;
    }

}
