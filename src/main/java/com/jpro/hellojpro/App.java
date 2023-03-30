package com.jpro.hellojpro;

import com.jpro.webapi.WebAPI;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import one.jpro.routing.LinkUtil;
import one.jpro.routing.Route;
import one.jpro.routing.RouteApp;
import one.jpro.routing.View;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.LinkedHashMap;
import java.util.Map;

import static one.jpro.routing.RouteUtils.get;
import static one.jpro.routing.RouteUtils.redirect;

public class App extends RouteApp {
    private Stage pStage;

//    public static void main(String[] args) {
//        launch(args);
//    }

    @Override
    public void start(Stage pStage) {
        this.pStage = pStage;
        super.start(pStage);


//        SessionManager sessionManager = SessionManager.getDefault(getRouteNode(), getStage());
//        sessionManager.start();


//        LinkUtil.getSessionManager(getRouteNode()).gotoURL(PageUtil.getLink(mo));
//        Stage stage = (Stage) app.getScene().getWindow();
//        stage.show();
//        stage.toFront();
    }

    @Override
    public Route createRoute() {
        return Route.empty()
                .and(redirect("/", "/info"))
                .and(get("/info", (r) -> new InfoPage(pStage)));
    }
}

class InfoPage extends View {
    private Stage pStage;

    public InfoPage(Stage pStage) {
        this.pStage = pStage;
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
        VBox root = new VBox(10);
        root.setAlignment(Pos.CENTER);
        root.setPrefSize(500, 1000);

        Label lblText = new Label("+92 (302) 050-6910");
//        LinkUtil.setLink(lblText, "tel:+923020506910");
        lblText.setOnMouseClicked(event -> {
//            LinkUtil.gotoPage(lblText, "tel:+923020506910");
//            WebAPI.getWebAPI(pStage).openURL("+923020506910");
        });

        Label lblText1 = new Label("abid.maqbool55@gmail.com");
        LinkUtil.setLink(lblText1, "mailto:abid.maqbool55@gmail.com");

//        Label lblText2 = new Label("Home");
//        LinkUtil.gotoPage(lblText, "/");

        Label lblText2 = new Label("Info with Parm");
        LinkUtil.setLink(lblText2, "/info?q=About&email=abid.maqbool55@gmail.com");

        Button btnGetParm = new Button("Get Parm");
        btnGetParm.setOnAction(event -> {
            System.out.println("q: " + WebAPI.getWebAPI(pStage).getURLQueryParams().get("q"));

            WebAPI.getWebAPI(pStage).executeScript(""" 
                    const params = new Proxy(new URLSearchParams(window.location.search), {
                      get: (searchParams, prop) => searchParams.get(prop),
                    });
                    // Get the value of "some_key" in eg "https://example.com/?some_key=some_value"
                    let value = params.q; // "some_value"
                                                            
                    console.log("q=" + value);  """
            );

//            try {
//               String temp= WebAPI.getWebAPI(pStage).executeScriptWithReturn("""
//                    const params = new Proxy(new URLSearchParams(window.location.search), {
//                      get: (searchParams, prop) => searchParams.get(prop),
//                    });
//                    // Get the value of "some_key" in eg "https://example.com/?some_key=some_value"
//                    let value = params.q; // "some_value"
//
//                    console.log("q=" + value);
//                    return value;""");
//                System.out.println("temp" + temp);
//            } catch (Exception e) {
//                throw new RuntimeException(e);
//            }

//            System.out.println(LinkUtil.getSessionManager(btnGetParm).url());
//            WebAPI.getWebAPI(pStage).url
//            System.out.println(LinkUtil.getCurrentPage(btnGetParm));
            System.out.println(WebAPI.getWebAPI(pStage).getServerName());

            String url = WebAPI.getWebAPI(pStage).getServerName();

            String[] split = url.split("/?");

            if (split.length > 1) {
                String[] split1 = split[1].split("=");
            }

            System.out.println("parm: " + splitQuery(url));

            System.out.println("the value for q for: " + splitQuery(url).get("q"));
            System.out.println(WebAPI.getWebAPI(pStage).getServer());
        });


        if (WebAPI.getWebAPI(pStage).getURLQueryParams().get("q") != null) {
            btnGetParm.fire();
        }

        root.getChildren().addAll(lblText, lblText1, lblText2, btnGetParm);


        return root;
    }

    public static Map<String, String> splitQuery(String urlString) {
        final Map<String, String> query_pairs = new LinkedHashMap<>();

        try {
            URL url = new URL(urlString);

            final String[] pairs = url.getQuery().split("&");
            for (String pair : pairs) {
                final int idx = pair.indexOf("=");
                final String key = idx > 0 ? URLDecoder.decode(pair.substring(0, idx), "UTF-8") : pair;
//                if (!query_pairs.containsKey(key)) {
//                    query_pairs.put(key, "");
//                }
                final String value = idx > 0 && pair.length() > idx + 1 ? URLDecoder.decode(pair.substring(idx + 1), "UTF-8") : null;
                System.out.println(value + " val");
//                query_pairs.get(key).add(value);
                query_pairs.get(key);
                if (!query_pairs.containsKey(key)) {
                    query_pairs.put(key, value);
                }
            }
        } catch (MalformedURLException | UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }

        return query_pairs;
    }
}