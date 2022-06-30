package Ik.ijse.hybernate.controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class DashboardFormController {
    public Pane paneDashboarad;
    public AnchorPane aPane;
    public Label lblDate;
    public Label lblTime;
    static LocalDate date;

    public void initialize() throws SQLException, ClassNotFoundException {
        loadDateAndTime();

    }

    private void loadDateAndTime() {

        lblDate.setText(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        date= LocalDate.parse(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));

        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            DateFormat dateFormat = new SimpleDateFormat("hh : mm : ss aa");
            String dateString = dateFormat.format(new Date()).toString();
            lblTime.setText(dateString);
        }),
                new KeyFrame(Duration.seconds(1))

        );
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }

    public void btnBack(ActionEvent actionEvent) throws IOException {
        setUI("LoginForm");
    }

    public void btnLoginDetails(ActionEvent actionEvent) throws IOException {
        paneDashboarad.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("../view/LoginDetailsForm.fxml"));
        paneDashboarad.getChildren().add(parent);
    }

    private void setUI(String location) throws IOException {
        Stage stage=(Stage) aPane.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/"+location+".fxml"))));
        stage.centerOnScreen();
    }

    private void setUI2(String location) throws IOException {
        paneDashboarad.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("../view/"+location+".fxml"));
        paneDashboarad.getChildren().add(parent);

    }

    public void btnManageStudent(ActionEvent actionEvent) throws IOException {
        setUI2("ManageStudentsForm");

    }

    public void btnManageRooms(ActionEvent actionEvent) throws IOException {
        setUI2("ManageRoomsForm");

    }

    public void btnResevation(ActionEvent actionEvent) throws IOException {

       setUI("ReservationForm");
    }

    public void btnRemainKeyMoney(ActionEvent actionEvent) throws IOException {
        setUI2("RemainKeyMoneyForm");

    }
}
