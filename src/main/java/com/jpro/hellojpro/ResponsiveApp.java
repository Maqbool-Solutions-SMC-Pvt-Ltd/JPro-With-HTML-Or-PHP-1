package com.jpro.hellojpro;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import one.jpro.routing.Filters;
import one.jpro.routing.Route;
import one.jpro.routing.RouteApp;
import one.jpro.routing.RouteUtils;

public class ResponsiveApp extends RouteApp {

    Label infoWidth;
    Label infoHeight;

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

        infoWidth = new Label("Width");
        infoHeight = new Label("Height");
        Label infoOrientation = new Label("Orientation");
        Label infoDisplay = new Label("Display");

        root.getChildren().addAll(infoWidth, infoHeight, infoOrientation, infoDisplay);

        Scene scene = getScene();

        runOnOrientationChanged(scene, () -> {
            infoOrientation.setText("Orientaion: Landscape");
        }, () -> {
            infoOrientation.setText("Orientaion: Portrait");
        });

        runOnWidthChanged(scene, () -> {
            infoDisplay.setText("Display: Laptop");
        }, () -> {
            infoDisplay.setText("Display: Tablet");
        }, () -> {
            infoDisplay.setText("Display: Android");
        });

        return root;
    }

    public void runOnOrientationChanged(Scene scene, Runnable runOnLandscape, Runnable runOnPortrait) {
        BooleanProperty orientationProperty = new SimpleBooleanProperty();

        scene.widthProperty().addListener((observable, oldValue, newValue) -> {
            infoWidth.setText("Width: " + newValue);

            orientationProperty.set(newValue.doubleValue() > scene.getHeight());
        });

        scene.heightProperty().addListener((observable, oldValue, newValue) -> {
            infoHeight.setText("Height: " + newValue);

            orientationProperty.set(newValue.doubleValue() < scene.getWidth());
        });

        orientationProperty.addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                runOnLandscape.run();
            } else {
                runOnPortrait.run();
            }
        });

        // First run even if Stage width not changed
        if (scene.getWidth() > scene.getHeight()) {
            // System.out.println("Landscape Display");
            runOnLandscape.run();
        } else {
            // System.out.println("Portrait Display");
            runOnPortrait.run();
        }
    }

    public void runOnWidthChanged(Scene scene, Runnable runOnG800, Runnable runOnL800, Runnable runOnL380) {
        BooleanProperty widthG800 = new SimpleBooleanProperty();
        BooleanProperty widthL380 = new SimpleBooleanProperty();

        scene.widthProperty().addListener((observable, oldValue, newValue) -> {
            widthG800.set((newValue.intValue() > 800));
            widthL380.set((newValue.intValue() < 380));
        });

        widthG800.addListener((observable, oldValue, newValue) -> {
            if (newValue) { // > 800
                // System.out.println("Laptop Display");
                runOnG800.run();
            } else { // <= 800
                // System.out.println("Tablet Display");
                runOnL800.run();
            }
        });
        widthL380.addListener((observable, oldValue, newValue) -> {
            if (newValue) { // < 380
                // System.out.println("Android Display");
                runOnL380.run();
            } else { // >= 380
                // System.out.println("Tablet Display");
                runOnL800.run();
            }
        });

        // First run even if Stage width not changed
        if (scene.getWidth() > 800) {
            // System.out.println("Laptop Display");
            runOnG800.run();
        } else if (scene.getWidth() >= 380) {
            // System.out.println("Tablet Display");
            runOnL800.run();
        } else {
            // System.out.println("Android Display");
            runOnL380.run();
        }
    }

}
