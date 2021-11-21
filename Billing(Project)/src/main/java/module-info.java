module com.billing.billing {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.billing.billing to javafx.fxml;
    exports com.billing.billing;
}