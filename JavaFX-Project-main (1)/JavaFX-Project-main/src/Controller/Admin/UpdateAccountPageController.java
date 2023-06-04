/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Admin;

import Model.Account;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author SCc
 */
public class UpdateAccountPageController implements Initializable {

    private Account oldAccount;
    @FXML
    private Button cancelBtn;
    @FXML
    private TextField accountNmuberTF;
    @FXML
    private Button updateAccountBtn;
    @FXML
    private TextField usernameTF;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.oldAccount = Controller.Admin.AccountsManagmentController.selectedAccountToUpdate;
        usernameTF.setText(oldAccount.getUsername());
        accountNmuberTF.setText(String.valueOf(oldAccount.getAccount_number()));
    }

    private void updateAccount(ActionEvent event) throws SQLException, ClassNotFoundException {

        String username = usernameTF.getText();
        String accountNmuber = accountNmuberTF.getText();

        Account newAccount = new Account(username, accountNmuber);

        newAccount.setId(oldAccount.getId());

        newAccount.update();

        Controller.Admin.UsersManagmentController.updateStage.close();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Account updated");
        alert.setContentText("Account updated");
        alert.showAndWait();
    }

    @FXML
    private void cancelCreation(ActionEvent event) {
        Controller.Admin.AccountsManagmentController.updateStage.close();
    }

}
