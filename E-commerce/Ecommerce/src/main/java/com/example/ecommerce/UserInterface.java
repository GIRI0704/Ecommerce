package com.example.ecommerce;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.scene.control.TextFormatter;
import java.util.function.UnaryOperator;
import javafx.scene.control.TextFormatter.Change;

public class UserInterface {
    ordertable ordertable = new ordertable();
    VBox orderpage;

    GridPane loginPage;
    GridPane signUppage;
    HBox headerBar;
    HBox footerBar;
    Button signInButton;

    Label welcomeLabel;
    VBox body;
    Customer loggedInCustomer;

    ProductList productList = new ProductList();
    VBox productPage;
    Button placeOrderButton = new Button("Place Order");

    Button logoutButton = new Button("Log Out");

    Button innerSignIn = new Button("Sign In");

    ObservableList<Product> itemsInCart = FXCollections.observableArrayList();
    TextField userName = new TextField();
    PasswordField password = new PasswordField();
    Label messageLable = new Label("Hi");

    Button signupButton = new Button("Sign Up");

    public BorderPane createContent()
    {
        BorderPane root = new BorderPane();
        root.setPrefSize(800,600); // setting the initial size of the window
//        root.getChildren().add(loginPage); // method to add children to pane
        root.setTop(headerBar);

//        root.setCenter(loginPage);
//        orderpage =ordertable.getoder();

        body = new VBox();
        body.setPadding(new Insets(10));
        body.setAlignment(Pos.CENTER);
        root.setCenter(body);
        productPage = productList.getAllProducts();
        body.getChildren().add(productPage);
        body.setStyle("-fx-background-color:grey");

        root.setBottom(footerBar);

        return root;
    }
    public UserInterface()
    {
        createLoginPage();
        createHeaderBar();
        createFooterBar();
        createsignupPage();
    }
    private void createLoginPage()
    {
        Text userNameText = new Text("User Name");
        Text passwordText = new Text("Password");

//        TextField userName = new TextField();
        userName.setPromptText("Type your user name here");
//        PasswordField password = new PasswordField();
        password.setPromptText("Type your password here");

        Label signupLable = new Label("If you don't have an account please Sign Up here");

        // creating a login button
        Button loginButton = new Button("Login");

        loginPage = new GridPane();
//        loginPage.setStyle("-fx-background-color:grey;");
        loginPage.setAlignment(Pos.CENTER); // setting into the centre
        // next 4 lines are adding into the gridpane
        loginPage.setHgap(10);
        loginPage.setVgap(10);
        loginPage.add(userNameText,0,0);  // first column value second row value
        loginPage.add(userName,1,0);
        loginPage.add(passwordText,0,1);
        loginPage.add(password,1,1);
        loginPage.add(messageLable,0,2);
        loginPage.add(loginButton,1,2);
        loginPage.add(signupLable,0,3);
        loginPage.add(signupButton,1,3);
        
        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String name = userName.getText();
                String pass = password.getText();
                Login login = new Login();
                loggedInCustomer = login.customerLogin(name,pass);
                if(loggedInCustomer != null)
                {
                    messageLable.setText("Welcome "+ loggedInCustomer.getName());
                    welcomeLabel.setText("Welcome " + loggedInCustomer.getName());
                    headerBar.getChildren().add(logoutButton);
                    headerBar.getChildren().add(welcomeLabel);
                    body.getChildren().clear();
                    body.getChildren().add(productPage);
                }
                else {
                    messageLable.setText("Login Failed !! Invalid user name or password.");
                }
            }
        });
    }
    private void createsignupPage()
    {
        Text signupUserNameText = new Text("User Name");
        Text signUpUseremailText = new Text("E-mail");
        Text signUpPasswordText = new Text("Enter your Password");
        Text signUpmobileText = new Text("Enter your Mobile Number");

        TextField signupUserName = new TextField();
        signupUserName.setPromptText("Enter your User Name");
        TextField signUpUseremail = new TextField();
        signUpUseremail.setPromptText("Enter your E-mail");
        PasswordField signUpPassword = new PasswordField();
        signUpPassword.setPromptText("Enter your Password");
        TextField signUpmobile = new TextField();
        signUpmobile.setPromptText("Enter your Mobile Number");


        UnaryOperator<Change> filter = change -> {
            String newText = change.getControlNewText();
            if (newText.matches("\\d{0,10}")) {
                return change;
            }
            return null;
        };

        TextFormatter<String> textFormatter = new TextFormatter<>(filter);
        signUpmobile.setTextFormatter(textFormatter);

        signUppage = new GridPane();
        signUppage.setAlignment(Pos.CENTER);

        signUppage.setHgap(10);
        signUppage.setVgap(10);

        signUppage.add(signupUserNameText,0,0);
        signUppage.add(signupUserName,1,0);
        signUppage.add(signUpUseremailText,0,1);
        signUppage.add(signUpUseremail,1,1);
        signUppage.add(signUpPasswordText,0,2);
        signUppage.add(signUpPassword,1,2);
        signUppage.add(signUpmobileText,0,3);
        signUppage.add(signUpmobile,1,3);
        signUppage.add(innerSignIn,1,4);

        signupButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                body.getChildren().clear();
                body.getChildren().add(signUppage);
                signUpmobile.clear();
                signUpPassword.clear();
                signupUserName.clear();
                signUpUseremail.clear();
            }
        });

        innerSignIn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Login login = new Login();
                Customer sin =  login.customerLogin(signUpUseremail.getText());
                if(sin == null)
                {
                    NewUser.getNewUser(signupUserName.getText(),signUpUseremail.getText(),signUpPassword.getText(),signUpmobile.getText());
                    body.getChildren().clear();
                    body.getChildren().add(loginPage);
                }
                else {
                    showDialog("User already exits");
                }
            }
        });
    }
    private void createHeaderBar()
    {
        Image logo = new Image("C:\\Users\\ADMIN\\Desktop\\E-commerce\\Ecommerce\\src\\main\\resources\\56afea50b83164e3e272d4ebeccd94fb.png");
        ImageView logoView = new ImageView();
        logoView.setImage(logo);
        logoView.setFitHeight(35);
        logoView.setFitWidth(100);

        Button homeButton = new Button();
        Image image = new Image("C:\\Users\\ADMIN\\Desktop\\E-commerce\\Ecommerce\\src\\img_5710.png.crdownload");
        ImageView imageview = new ImageView();
        imageview.setImage(image);
        imageview.setFitHeight(30);
        imageview.setFitWidth(30);
        homeButton.setGraphic(imageview);
        // creating a search bar
        TextField searchBar = new TextField();
        searchBar.setPromptText("Search Here");
        searchBar.setPrefWidth(180); // adding width of the search bar

        // creating a button to search
        Button searchButton = new Button("Search");
        signInButton = new Button("Sign in");
        welcomeLabel = new Label();

        Button cartButton = new Button("Cart");
        Button orderbutton = new Button("Orders");

        headerBar = new HBox();
        headerBar.setPadding(new Insets(10)); // add padding at top of the header
        headerBar.setSpacing(10); // here 10 the gap B/W the element in the Hbox
        headerBar.setAlignment(Pos.CENTER);  // setting header into centre;
        headerBar.getChildren().addAll(logoView,homeButton ,searchBar,searchButton, cartButton, orderbutton, signInButton);
        searchButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                body.getChildren().clear();
                String str=searchBar.getText();

                productPage=productList.getAllProducts(str);
                if(productPage == null)
                {
                    Image productImage = new Image("C:\\Users\\ADMIN\\Desktop\\E-commerce\\Ecommerce\\src\\main\\resources\\no-product-found.png");
                    ImageView productImageView = new ImageView();
                    productImageView.setImage(productImage);
                    productImageView.setFitWidth(500);
                    productImageView.setFitHeight(400);
                    body.getChildren().add(productImageView);
                    return;
                }
                body.getChildren().add(productPage);
            }
        });
        orderbutton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                orderpage =ordertable.getoder();
                body.getChildren().clear();
                body.getChildren().add(orderpage);

                footerBar.setVisible(true);
                if(loggedInCustomer==null){
                    if( headerBar.getChildren().indexOf(signInButton)==-1){
                        headerBar.getChildren().add(signInButton);
                    }
                }
            }
        });

        signInButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                userName.setPromptText("Type your user name here");
                password.setPromptText("Type your password here");
                body.getChildren().clear(); // clear every thing in the body
                body.getChildren().add(loginPage); // and add login page on the body
                headerBar.getChildren().remove(signInButton);
                userName.clear();
                password.clear();
                messageLable.setText("Hi");
            }
        });

        cartButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                body.getChildren().clear();
                VBox prodPage = productList.getProductsInCart(itemsInCart);
                prodPage.setAlignment(Pos.CENTER);
                prodPage.setSpacing(10);
                prodPage.getChildren().add(placeOrderButton);
                body.getChildren().add(prodPage);
                footerBar.setVisible(false);
            }
        });

        placeOrderButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(itemsInCart == null)
                {
                    showDialog("Please add some product in the cart to place order");
                    return;
                }
                if(loggedInCustomer == null)
                {
                    showDialog("Please login to place order");
                    return;
                }
                int count = Order.placeMultipleOrder(loggedInCustomer, itemsInCart);
                if(count != 0)
                {
                    showDialog("Order for "+count+" products placed Successfully!!");
                }
                else showDialog("Order Failed");
            }
        });

        homeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                productPage = productList.getAllProducts();
                body.getChildren().clear();
                body.getChildren().add(productPage);
                footerBar.setVisible(true);
                if(loggedInCustomer==null)
                {
                    if(headerBar.getChildren().indexOf(signInButton)==-1)
                    {
                        headerBar.getChildren().add(signInButton);
                    }
                }
            }
        });

        logoutButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                headerBar.getChildren().removeAll(logoutButton,welcomeLabel);
                headerBar.getChildren().add(signInButton);
                loggedInCustomer = null;

            }
        });
        headerBar.setStyle("-fx-background-color:black");
        headerBar.setPrefHeight(80);
    }

    private void createFooterBar()
    {
        Button buyNowButton = new Button("Buy Now");
        Button addToCartButton = new Button("Add to cart");
        footerBar = new HBox();
        footerBar.setPadding(new Insets(10));
        footerBar.setSpacing(10);
        footerBar.setAlignment(Pos.CENTER);
        footerBar.getChildren().addAll(buyNowButton, addToCartButton);
        footerBar.setStyle("-fx-background-color:black");
        footerBar.setPrefHeight(80);

        buyNowButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Product product = productList.getSelectedProduct();
                if(product == null)
                {
                    showDialog("Please select any one of our product to place order!");
                    return;
                }
                if(loggedInCustomer == null)
                {
                    showDialog("Please login to place order");
                    return;
                }
                boolean status = Order.placeOrder(loggedInCustomer, product);
                if(status)
                {
                    showDialog("Order placed Successfully!!");
                }
                else showDialog("Order Failed");
            }
        });

        addToCartButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Product product = productList.getSelectedProduct();
                if(product == null)
                {
                    showDialog("Please select any one of our product to add on your Cart!");
                    return;
                }
                itemsInCart.add(product);
                showDialog("Selected Product successfully added on to the Cart");
            }
        });
    }

    private void showDialog(String message)
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.setTitle("MESSAGE");
        alert.showAndWait();
    }
}

