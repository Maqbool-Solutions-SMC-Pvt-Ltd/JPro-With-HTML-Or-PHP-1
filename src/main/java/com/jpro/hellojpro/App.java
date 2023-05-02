package com.jpro.hellojpro;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import one.jpro.routing.LinkUtil;
import one.jpro.routing.Route;
import one.jpro.routing.RouteApp;
import one.jpro.routing.View;
import simplefx.experimental.parts.FXFuture;

import static one.jpro.routing.RouteUtils.redirect;

public class App extends RouteApp {
    private Stage pStage;

    @Override
    public void start(Stage pStage) {
        this.pStage = pStage;
        super.start(pStage);
    }

    @Override
    public Route createRoute() {
        return Route.empty()
                .and(redirect("/", "/info"))
                .and(r -> {
                    System.out.println("path: " + r.path());
                    System.out.println("prams empty: " + r.queryParameters().isEmpty());

                    // if (r.path().equals("/")) {
                    // System.out.println("trig /");
                    // redirect("/", "/info");
                    // } else
                    if (r.path().equals("/info") && r.queryParameters().contains("q")) {
                        return FXFuture.unit(new InfoPage((pStage), r.queryParameters().get("q").get()));
                    } else if (r.path().equals("/info")) {
                        return FXFuture.unit(new InfoPage((pStage)));
                    }
                    // else if (r.path().equals("/")) {
                    // return FXFuture.unit(new InfoPage((pStage)));
                    // }

                    // Map<String, String> stringStringMap = (Map<String, String>)
                    // r.queryParameters();

                    // System.out.println("pram: q= " + r.queryParameters().get("q").get());
                    // System.out.println("pram: email= " + r.queryParameters().get("email"));

                    // new InfoPage(pStage, r.queryParameters().get("q").get());

                    return FXFuture.unit(null);
                });
        //
        // .and(get("/info", (r) -> new InfoPage(pStage)));
    }
}

class InfoPage extends View {
    private Stage pStage;

    public InfoPage(Stage pStage) {
        this.pStage = pStage;
    }

    public InfoPage(Stage pStage, String q) {
        this.pStage = pStage;
        System.out.println("route contans, q: " + q);
    }

    @Override
    public String title() {
        return "Sample JPro Page";
    }

    @Override
    public String description() {
        return "This is a simple page where JPro testing done.";
    }

    @Override
    public Node content() {
        // Map<String, String> test = null;
        // test.put("test", "Test");
        //
        // System.out.println(test.get("test"));

        VBox root = new VBox(10);
        root.setAlignment(Pos.CENTER);
        root.setPrefSize(500, 1000);

        Label lblText = new Label("Home");
        LinkUtil.setLink(lblText, "/");

        Label lblText2 = new Label("Info with Parm");
        LinkUtil.setLink(lblText2, "/info?name=Abid&Aqib".replaceAll(" ", "%20").replaceAll("&", "%26"));

        root.getChildren().addAll(lblText, lblText2, getNode());

        return root;
    }

    private VBox getNode() {
        VBox root = new VBox();

        TitledPane titledPane1 = new TitledPane();
        titledPane1.setMinWidth(100);
        titledPane1.setPrefWidth(100);
        titledPane1.setMaxWidth(100);

        Label lbl1 = new Label(
                "5 sample paragraphs for reading test on 1. Traveling in a D. T. C. Bus  2. Look Before You Leap 3. Rising Prices 4. ‘Simple Living, High Thinking’ 5. A Football Match");
        lbl1.setWrapText(true);

        titledPane1.setGraphic(lbl1);

        Label lbl11 = new Label(
                "Delhi is a crowded city. There are very few rich people who travel by their own vehicles. The majority of the people cannot afford to hire a taxi or a three-wheeler. They have to depend on D.T.C. buses, which are the cheapest mode of conveyance.");
        lbl11.setWrapText(true);

        titledPane1.setContent(new VBox(lbl11));

        // OR

        TitledPane titledPane2 = new TitledPane();
        titledPane2.setText(
                "5 sample paragraphs for reading test on 1. Traveling in a D. T. C. Bus  2. Look Before You Leap 3. Rising Prices 4. ‘Simple Living, High Thinking’ 5. A Football Match");
        titledPane2.setMinWidth(100);
        titledPane2.setPrefWidth(100);
        titledPane2.setMaxWidth(100);

        Label lbl12 = new Label(
                "Delhi is a crowded city. There are very few rich people who travel by their own vehicles. The majority of the people cannot afford to hire a taxi or a three-wheeler. They have to depend on D.T.C. buses, which are the cheapest mode of conveyance.");
        lbl12.setWrapText(true);

        titledPane2.setContent(new VBox(lbl12));

        root.getChildren().addAll(titledPane1, titledPane2);

        return root;
    }
}