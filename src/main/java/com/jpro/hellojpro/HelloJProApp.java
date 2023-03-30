package com.jpro.hellojpro;

import com.jpro.hellojpro.page.FXMLPage;
import com.jpro.hellojpro.page.InfoPage;
import com.jpro.hellojpro.page.LandingPage;
import one.jpro.routing.Route;
import one.jpro.routing.View;
import com.jpro.webapi.WebAPI;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import static one.jpro.routing.RouteUtils.get;
import static one.jpro.routing.RouteUtils.redirect;

public class HelloJProApp {
    public Route getRoute(Stage stage) {
        return Route.empty()
                .and(redirect("/", "/landing"))
                .and(get("/landing", (r) -> {
                    return new LandingPage(WebAPI.isBrowser() ? WebAPI.getWebAPI(stage) : null);
                }))
                .and(get("/info", (r) -> {
                    return new InfoPage(WebAPI.isBrowser() ? WebAPI.getWebAPI(stage) : null);
                }))
                .and(get("/fxml", (r) -> {
                    return new FXMLPage(WebAPI.isBrowser() ? WebAPI.getWebAPI(stage) : null);
                }));
//                .filter(Filters.FullscreenFilter(true))
//                .filter(RouteUtils.sideTransitionFilter(1));
    }

//    HelloJProApp(Stage stage) {
//        super(stage);
//        getStylesheets().add(getClass().getResource("/com/jpro/hellojpro/css/HelloJPro.css").toString());
//
//        addRoute((s) -> { switch(s) {
//            case "":
//            case "/":
//                return new Redirect("/landing");
//            case "/landing":
//                return new LandingPage(WebAPI.isBrowser() ? WebAPI.getWebAPI(stage) : null);
//            case "/info":
//                return new InfoPage(WebAPI.isBrowser() ? WebAPI.getWebAPI(stage) : null);
//            case "/fxml":
//                return new FXMLPage(WebAPI.isBrowser() ? WebAPI.getWebAPI(stage) : null);
//            default:
//                return new LandingPage(WebAPI.isBrowser() ? WebAPI.getWebAPI(stage) : null);
//        }});
//    }


}

class Temp extends View {

    @Override
    public String title() {
        return "Some titile";
    }

    @Override
    public String description() {
        return " some desc";
    }

    @Override
    public Node content() {
        VBox root = new VBox(10);
        Label lblText = new Label("Some simple Text");
        root.getChildren().add(lblText);
        return root;
    }
}