/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Admin;
import Model.Account;
import View.ViewManager;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.sql.Date;
import java.sql.SQLException;

/**
 *
 * @author SCc
 */
public class CreateAccountController implements Initializable {
    
    @FXML
    private Button usersManagmentPageBtn;
    @FXML
    private Button accountsPageBtn;
    @FXML
    private Button operationsPageBtn;
    @FXML
    private Button cancelBtn;
    @FXML
    private Button saveNewAccountBtn;
    @FXML
    private TextField currencyTF;
    @FXML
    private TextField balanceTF;
    @FXML
    private TextField dateTF;
    @FXML
    private TextField accountnumberTF;
    @FXML
    private TextField usernameTF;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void showUsersManagmentPage(ActionEvent event) {
        ViewManager.adminPage.changeSceneToUsersManagment();
    }

    @FXML
    private void showAccountsPage(ActionEvent event) {
        ViewManager.adminPage.changeSceneToAccountsManagment();
    }

    @FXML
    private void showOperationsPage(ActionEvent event) {
    }
    @FXML
    private void saveNewAccount(ActionEvent event) throws SQLException, ClassNotFoundException {
        String username = usernameTF.getText();
        String currency = currencyTF.getText();
        String balanceText = balanceTF.getText();
        double balance = Double.parseDouble(balanceText);
        String accountNumberText = accountnumberTF.getText();
        int accountNumber = Integer.parseInt(accountNumberText);
        String dateText = dateTF.getText();
        Date date = Date.valueOf(dateText);
        Account account = new Account(username,currency,balance,accountNumber,date);
        account.save();
        ViewManager.adminPage.changeSceneToAccountsManagment();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Account inserted");
        alert.setContentText("Account inserted");
        alert.showAndWait();
    }

    @FXML
    private void cancelAccountCreation(ActionEvent event) {
        ViewManager.adminPage.changeSceneToAccountsManagment();
    }
}
