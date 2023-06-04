/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author SCc
 */
public class Account {

    private int id;
    private int user_id;
    private int account_number;
    private String username;
    private double balance;
    private String currency;
    private Date creation_date;

    public Account(String username, String currency) {
        
        this.username = username;
        this.currency = currency;
        
    }

    public Account(String username, String currency, double balance, int accountNumber, Date date) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getAccount_number() {
        return account_number;
    }

    public void setAccount_number(int account_number) {
        this.account_number = account_number;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Date getCreation_date() {
        return creation_date;
    }

    public void setCreation_date(Date creation_date) {
        this.creation_date = creation_date;
    }
    public int save() throws SQLException, ClassNotFoundException{
        Connection c = DB.getInstance().getConnection();
        PreparedStatement ps = null;
        int recordCounter =0;
        String sql = "INSERT INTO `accounts`(`id`, `user_id`, `account_number`, `username`, `currency`, `balance`, `creation_date`) VALUES ('?','?','?','?','?','?','?')";
        ps = c.prepareStatement(sql);
        ps.setInt(1, this.getId());
        ps.setInt(2,this.getUser_id());
        ps.setInt(3, this.getAccount_number());
        ps.setString(4, this.getUsername());
        ps.setString(5, this.getCurrency());
        ps.setDouble(6, this.getBalance());
        ps.setDate(7, this.getCreation_date());
        recordCounter = ps.executeUpdate();
        if (recordCounter > 0) {
            System.out.println(this.getUsername()
                    +" User was added successfully!");
        }
        if (ps != null){
            ps.close();
        }
        c.close();
        return recordCounter;  
    }
    public static ArrayList<Account> getAllAccounts() throws SQLException, ClassNotFoundException{
        Connection c = DB.getInstance().getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Account> accounts = new ArrayList<>();
        String sql = "SELECT * FROM ACCOUNTS";
        ps = c.prepareStatement(sql);
        rs = ps.executeQuery();
        while (rs.next()){
            Account account = new Account(rs.getString(4),rs.getString(5));
            account.setId(rs.getInt(1));
            accounts.add(account);
            
        }
        if (ps != null){
            ps.close();
        }
        c.close();
        return accounts;
    }
    public int delete() throws SQLException, ClassNotFoundException{
        Connection c = DB.getInstance().getConnection();
        PreparedStatement ps = null;
        int recordCounter =0;
        String sql = "DELETE FROM ACCOUNTS WHERE ID=? ";
        ps = c.prepareStatement(sql);
        ps.setInt(1, this.getId());
        recordCounter = ps.executeUpdate();
        if (recordCounter > 0) {
            System.out.println("The user with id : "+this.getId()+" was deleted successfully!");
        }
        if (ps != null){
            ps.close();
        }
        c.close();
        return recordCounter;  
    }
    public int update() throws SQLException, ClassNotFoundException{
        Connection c = DB.getInstance().getConnection();
        PreparedStatement ps = null;
        int recordCounter =0;
        String sql = "UPDATE ACCOUNTS SET USERNAME=?, BALANCE=?, CURRENCY=? WHERE ID=?";
        ps = c.prepareStatement(sql);
         ps.setString(1, this.getUsername());
    ps.setDouble(2, this.getBalance());
    ps.setString(3, this.getCurrency());
    ps.setInt(4, this.getId());
        recordCounter = ps.executeUpdate();
        if (recordCounter > 0) {
            System.out.println("User with id : "+this.getId()+" was updated successfully!");
        }
        if (ps != null){
            ps.close();
        }
        c.close();
        return recordCounter;  
    }
    
}
