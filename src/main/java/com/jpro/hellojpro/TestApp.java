package com.jpro.hellojpro;


import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import one.jpro.routing.Filters;
import one.jpro.routing.Route;
import one.jpro.routing.RouteApp;
import one.jpro.routing.RouteUtils;
import one.jpro.routing.dev.DevFilter;

import static one.jpro.routing.RouteUtils.getNode;
import static one.jpro.routing.RouteUtils.redirect;

public class TestApp extends RouteApp {


    public static void main(String[] args) {
        launch(args);
    }

    public Route createRoute() {
        return Route.empty()
                .and(redirect("/", "/info"))
                .and(getNode("/info", (r) -> getView()))
//                .and(getNode("/red", (r) -> getNode("Red", "/blue", Color.RED)))
//                .and(getNode("/blue", (r) -> getNode("Blue", "/yellow", Color.BLUE)))
//                .and(getNode("/yellow", (r) -> getNode("Yellow", r.resolve("/color/00ff00"), Color.YELLOW)))
//                .and(r -> {
//                    var matcher = colorPattern.matcher(r.path());
//                    if (matcher.matches()) {
//                        var colorStr = matcher.group(1);
//                        var color = Color.web(colorStr);
//                        return FXFuture.unit(viewFromNode(getNode("#" + colorStr, r.resolve("/red"), color)));
//                    } else {
//                        return FXFuture.unit(null);
//                    }
//                })
//                .path("/colors",
//                        Route.empty()
//                                .and(getNode("/green", (r) -> getNode("Green", r.resolve("/red"), Color.GREEN)))
//                                .and(getNode("/red", (r) -> getNode("Red", r.resolve("/green"), Color.RED)))
//                )
                .filter(Filters.FullscreenFilter(true))
                .filter(RouteUtils.sideTransitionFilter(1))
                .filter(DevFilter.create())
//                .filter(ContainerFilter.create(() -> new SimpleContainer()))
//                .filter(ContainerFilter.create(() -> new SimpleHamburgerMenu(List.of(
//                        new SimpleHamburgerMenu.Link("Green", "/green"),
//                        new SimpleHamburgerMenu.Link("Red", "/red"),
//                        new SimpleHamburgerMenu.Link("Blue", "/blue"),
//                        new SimpleHamburgerMenu.Link("Yellow", "/yellow")
//                ))))
                ;
    }

    private Node getView() {
        StackPane root = new StackPane();


//        TitledPane titledPane1 = new TitledPane();
//        titledPane1.setMinWidth(100);
//        titledPane1.setPrefWidth(100);
//        titledPane1.setMaxWidth(100);
//
//        Label lbl1 = new Label("5 sample paragraphs for reading test on 1. Traveling in a D. T. C. Bus  2. Look Before You Leap 3. Rising Prices 4. ‘Simple Living, High Thinking’ 5. A Football Match");
//        lbl1.setWrapText(true);
//
//        titledPane1.setGraphic(lbl1);
//
//        Label lbl11 = new Label("Delhi is a crowded city. There are very few rich people who travel by their own vehicles. The majority of the people cannot afford to hire a taxi or a three-wheeler. They have to depend on D.T.C. buses, which are the cheapest mode of conveyance.");
//        lbl11.setWrapText(true);
//
//        titledPane1.setContent(new VBox(lbl11));

        // OR

        TitledPane titledPane2 = new TitledPane();
//        titledPane2.setText("5 sample paragraphs for reading test on 1. Traveling in a D. T. C. Bus  2. Look Before You Leap 3. Rising Prices 4. ‘Simple Living, High Thinking’ 5. A Football Match");
        titledPane2.setMinWidth(100);
        titledPane2.setPrefWidth(100);
        titledPane2.setMaxWidth(100);
        titledPane2.setWrapText(true);
        titledPane2.setAlignment(Pos.TOP_LEFT);

        Label lbl1 = new Label("5 sample paragraphs for reading test on 1. Traveling in a D. T. C. Bus  2. Look Before You Leap 3. Rising Prices 4. ‘Simple Living, High Thinking’ 5. A Football Match");
        lbl1.setWrapText(true);
        lbl1.setMinHeight(Region.USE_PREF_SIZE);

        titledPane2.setGraphic(lbl1);

        Label lbl12 = new Label("Delhi is a crowded city. There are very few rich people who travel by their own vehicles. The majority of the people cannot afford to hire a taxi or a three-wheeler. They have to depend on D.T.C. buses, which are the cheapest mode of conveyance.");
        lbl12.setWrapText(true);

        titledPane2.setContent(new StackPane(lbl12));

        root.getChildren().addAll(titledPane2);

        Platform.runLater(() -> {
            System.out.println(titledPane2.lookup(".title"));

            Pane title = (Pane) titledPane2.lookup(".title");
//            title.setMinSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
//            title.setPrefHeight(Region.USE_COMPUTED_SIZE);
//            title.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
//            title.setMaxHeight(Double.MAX_VALUE);
            title.setPadding(new Insets(20));

//            HBox test = new HBox();
//            test.setPrefWidth(100);
//            test.getChildren().add(lbl1);
//
//
//            title.getChildren().add(test);


//            TitledPaneSkin test;


//            title.setPrefHeight(50);

//            Text text = (Text) title.lookup(".text");
//            text.setMinHeight(Region.USE_PREF_SIZE);
//            text.resize(Region.USE_COMPUTED_SIZE, Region.USE_PREF_SIZE);
//            LabeledText text = (LabeledText) title.lookup((".text"));


//            Label text = (Label) title.lookup(".label");

//            System.out.println("test:" + text.prefHeight(-1));
            //or
//            title.prefHeightProperty().bind(text.getBoundsInLocal().getHeight());

//            title.prefHeightProperty().bind(lbl1.heightProperty());

            Button btnTest = new Button();
            btnTest.setOnAction(event -> {
                System.out.println("min-width: " + lbl1.getMinWidth());
                System.out.println("pref-width: " + lbl1.getPrefWidth());
                System.out.println("max-width: " + lbl1.getMaxWidth());

                System.out.println("min-height: " + lbl1.getMinHeight());
                System.out.println("pref-height: " + lbl1.getPrefHeight());
                System.out.println("max-height: " + lbl1.getMaxHeight());

                System.out.println("min-width: " + lbl1.getMinWidth());
                System.out.println("pref-width: " + lbl1.getPrefWidth());
                System.out.println("max-width: " + lbl1.getMaxWidth());

                System.out.println("width: " + lbl1.getWidth());
                System.out.println("height: " + lbl1.getHeight());

                System.out.println("layout-bounds-width: " + lbl1.getLayoutBounds().getHeight());
                System.out.println("bounds-in-local: " + lbl1.getBoundsInLocal().getHeight());
                System.out.println("bounds-in-parent: " + lbl1.getBoundsInParent().getHeight());
            });

            root.getChildren().add(btnTest);

//            Label lblTemp = new Label(lbl1.getText());
//            lblTemp.setWrapText(true);
//            lblTemp.prefWidthProperty().bind(lbl1.widthProperty());
//            lblTemp.setVisible(false);
//
//
//            root.getChildren().add(lblTemp);
//
//            title.prefHeightProperty().bind(lblTemp.heightProperty()
//                    .add(title.getInsets().getTop() + title.getInsets().getBottom()));


            title.prefHeightProperty().bind(lbl1.heightProperty()
                    .add(title.getInsets().getTop() + title.getInsets().getBottom()));
        });

        return root;
    }


}
