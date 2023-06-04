/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Admin;

import Model.Account;
import View.ViewManager;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Yahya
 */
public class AccountsManagmentController implements Initializable {
public static Account selectedAccountToUpdate;
    public static Stage updateStage;
    @FXML
    private Button usersManagmentPageBtn;
    @FXML
    private Button accountsPageBtn;
    @FXML
    private Button operationsPageBtn;
    @FXML
    private Button createNewAccountrBtn;
    @FXML
    private Button showAllAccountsBtn;
    @FXML
    private Button updateSelectedAccountBtn;
    @FXML
    private Button deleteSelectedAccountBtn;
    @FXML
    private Button searchAccountBtn;
    @FXML
    private TextField accontSearchTF;
    @FXML
    private TableColumn<Account, Integer> idCol;
    @FXML
    private TableColumn<Account, Integer> accountnumberCol;
    @FXML
    private TableColumn<Account, String> usernameCol;
    @FXML
    private TableColumn<Account, String> currencyCol;
    @FXML
    private TableColumn<Account, Double> balanceCol;
    @FXML
    private TableColumn<Account, Date> creationDateCol;
    @FXML
    private TableView<Account> acountsTableView;

    /**
     * Initializes the controller class.
     
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        idCol.setCellValueFactory(new PropertyValueFactory("id"));
        accountnumberCol.setCellValueFactory(new PropertyValueFactory("Account number"));
        usernameCol.setCellValueFactory(new PropertyValueFactory("User Name"));
        currencyCol.setCellValueFactory(new PropertyValueFactory("Currency"));
        balanceCol.setCellValueFactory(new PropertyValueFactory("Balance"));
        creationDateCol.setCellValueFactory(new PropertyValueFactory("Creation Date"));
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
    private void showAccountCreationPage(ActionEvent event) {
        ViewManager.adminPage.changeSceneToCreateAccount();
    }

    @FXML
    private void showAllAccounts(ActionEvent event) throws SQLException, ClassNotFoundException {
        ObservableList<Account> accountsList =
      FXCollections.observableArrayList(Account.getAllAccounts());
    }

    @FXML
    private void updateSelectedAccount(ActionEvent event) throws IOException {
        if(acountsTableView.getSelectionModel().getSelectedItem() != null){
        selectedAccountToUpdate = acountsTableView.getSelectionModel().getSelectedItem();
        FXMLLoader loaderUpdate = new FXMLLoader(getClass().getResource("/View/AdminFXML/UpdateAccountPage.fxml"));
        Parent rootUpdate = loaderUpdate.load();     
        Scene updateAccountScene = new Scene(rootUpdate);
        updateStage = new Stage();
        updateStage.setScene(updateAccountScene);
        updateStage.setTitle("Update account " + selectedAccountToUpdate.getUsername() );
        updateStage.show();
        }
    }

    @FXML
    private void deleteSelectedAccount(ActionEvent event) {
        if(acountsTableView.getSelectionModel().getSelectedItem() != null){
             Account selectedAccount = acountsTableView.getSelectionModel().getSelectedItem();
             Alert deleteConfirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
            deleteConfirmAlert.setTitle("Account delete");
            deleteConfirmAlert.setContentText("Are you sure to delete this Account ?");
            deleteConfirmAlert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                try {        selectedAccount.delete();
                } catch (SQLException ex) {
                    Logger.getLogger(AccountsManagmentController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(AccountsManagmentController.class.getName()).log(Level.SEVERE, null, ex);
                }
            Alert deletedSuccessAlert = new Alert(Alert.AlertType.INFORMATION);
            deletedSuccessAlert.setTitle("User deleted");
            deletedSuccessAlert.setContentText("User deleted");
            deletedSuccessAlert.show();  
            }
            });
            }else{
        Alert warnAlert = new Alert(Alert.AlertType.WARNING);
        warnAlert.setTitle("Select an account");
        warnAlert.setContentText("Please select an account from the table view");
        warnAlert.show();  
        }
    }
        
    

    @FXML
    private void searchForAnAccount(ActionEvent event) {
        
    }
    
}
