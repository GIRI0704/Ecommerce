package com.example.ecommerce;

        import javafx.beans.property.SimpleDoubleProperty;
        import javafx.beans.property.SimpleIntegerProperty;
        import javafx.beans.property.SimpleStringProperty;
        import javafx.collections.FXCollections;
        import javafx.collections.ObservableList;

        import java.sql.ResultSet;
        import java.text.SimpleDateFormat;
        import java.util.Date;

public class ordersproduct {
    private SimpleIntegerProperty product_id;
    private SimpleStringProperty order_date;

    private SimpleStringProperty order_status;

    public ordersproduct(int product_id,String ordered_date, String order_status) {
        this.product_id = new SimpleIntegerProperty(product_id);
        this.order_status= new SimpleStringProperty(order_status);
        this.order_date=new SimpleStringProperty(ordered_date);

    }
    public static ObservableList<ordersproduct> getAllorders(){
        String selectAllProduct="select product_id,order_status,order_date from orders";
        return fectchorders(selectAllProduct);
    }
    public static ObservableList<ordersproduct> fectchorders(String Query){
        ObservableList<ordersproduct> data= FXCollections.observableArrayList();
        DbConnection dBconnection = new DbConnection();
        try{
            ResultSet rs=dBconnection.getQueryTable(Query);
            while(rs.next()){
                ordersproduct orders = new ordersproduct(rs.getInt("product_id"),rs.getString("order_date"),rs.getString("order_status"));
                data.add(orders);
            }
            return data;
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public int getProduct_id() {
        return product_id.get();
    }



    public String getOrder_status() {
        return order_status.get();
    }

    public String getOrder_date() {
        return order_date.get();
    }


}
