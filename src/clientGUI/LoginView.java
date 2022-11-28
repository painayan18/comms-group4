package clientGUI;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class LoginView {
    public AnchorPane loginContext;
    public JFXTextField txtUsername;
    public JFXButton btnLogin;
    public Label lblError;

    public void loginOnAction(ActionEvent event) throws SQLException, ClassNotFoundException, IOException {

        if (txtUsername.getText().equals("")){
            lblError.setText("All fields are required.!");
            lblError.setStyle("-fx-text-fill: red");
        }else {
            ChatRoomView.userName = txtUsername.getText();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/Client.fxml"));
            Parent load = loader.load();
            clientGUI.ChatRoomView controller=loader.<clientGUI.ChatRoomView>getController();
            controller.setData(txtUsername.getText());
            Stage window = (Stage) loginContext.getScene().getWindow();
            window.setTitle("Chat Room");
            window.setScene(new Scene(load));
        }
    }
}
