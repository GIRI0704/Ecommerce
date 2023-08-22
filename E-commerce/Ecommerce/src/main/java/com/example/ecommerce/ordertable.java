package com.example.ecommerce;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

public class ordertable {
    private TableView<ordersproduct> orderproduct;

    public VBox ordertable1(ObservableList<ordersproduct> data){
        //columns

        //select orders.id,product.name,orders.order_date,orders.order_status from orders join product on orders.product_id=product.id;
        TableColumn product_id=new TableColumn("Product ID");
        product_id.setCellValueFactory(new PropertyValueFactory<>("product_id"));

        TableColumn order_date=new TableColumn("Ordered Date");
        order_date.setCellValueFactory(new PropertyValueFactory<>("order_date"));

        TableColumn order_status=new TableColumn("Order Status");
        order_status.setCellValueFactory(new PropertyValueFactory<>("order_status"));



        orderproduct=new TableView<>();
        orderproduct.setItems(data);

        orderproduct.getColumns().addAll(product_id,order_date,order_status);
        orderproduct.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        VBox vBox=new VBox();
        vBox.setPadding(new Insets(10));
        vBox.getChildren().addAll(orderproduct);
        return vBox;

    }
    ///
    public VBox getoder(){
        ObservableList<ordersproduct> data=ordersproduct.getAllorders();
        return ordertable1(data);
    }
    public ordersproduct getselectedorders(){
        return orderproduct.getSelectionModel().getSelectedItem();
    }
    public VBox getproductsinorder(ObservableList<ordersproduct> data){
        return ordertable1(data);
    }
}
