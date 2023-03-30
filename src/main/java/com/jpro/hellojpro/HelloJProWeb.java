package com.jpro.hellojpro;

import one.jpro.routing.Route;
import one.jpro.routing.RouteApp;
import fr.brouillard.oss.cssfx.CSSFX;
import javafx.stage.Stage;

public class HelloJProWeb extends RouteApp {
    private Stage pStage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        this.pStage = pStage;
        super.start(pStage);

        CSSFX.start();

//        HelloJProApp app = new HelloJProApp();
//        Scene scene = new Scene(app.get);
//        stage.setScene(scene);
//        stage.show();
//        app.start((Stage) SessionManager.getDefault(app.getRouteNode(), stage));

//        new RouteNode(stage, app.getRoute());

    }

    @Override
    public Route createRoute() {
        HelloJProApp app = new HelloJProApp();

        return new HelloJProApp().getRoute(pStage);
    }

}
