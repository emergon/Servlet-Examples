package dao;

import entities.Customer;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class CustomerDao {

    private final String URL = "jdbc:mysql://localhost:3306/sales?serverTimezone=UTC";
    private final String USERNAME = "root";
    private final String PASS = "1234";
    private Connection conn;
    private final String getCustomers = "SELECT * FROM customers";
    private final String getCustomerById = "SELECT * FROM customers WHERE ccode = ?";
    private final String insertCustomer = "INSERT INTO customers VALUES (?,?)";
    private final String updateCustomer = "UPDATE customers SET cname = ? WHERE ccode = ?";

    private Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(URL, USERNAME, PASS);
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CustomerDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conn;
    }

    private void closeConnections(ResultSet rs, Statement st) {
        try {
            rs.close();
            st.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Customer> getCustomers() {
        List<Customer> list = new ArrayList();
        try {
            Connection con = getConnection();
            Statement st = con.createStatement();
            //Statement st = getConnection().createStatement();
            ResultSet rs = st.executeQuery(getCustomers);
            while (rs.next()) {
                Customer c = new Customer(rs.getInt("ccode"), rs.getString("cname"));
                list.add(c);
            }
            closeConnections(rs, st);
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public Customer getCustomerById(int ccode) {
        Customer c = null;
        try {
            PreparedStatement pst = getConnection().prepareStatement(getCustomerById);
            pst.setInt(1, ccode);
            ResultSet rs = pst.executeQuery();
            rs.next();
            c = new Customer(rs.getInt("ccode"), rs.getString("cname"));
            closeConnections(rs, pst);
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return c;
    }

    public boolean insertCustomer(Customer c) {
        boolean inserted =false;
        try {
            PreparedStatement pst = getConnection().prepareStatement(insertCustomer);
            pst.setInt(1, c.getCcode());
            pst.setString(2, c.getCname());
            int result = pst.executeUpdate();
            if (result > 0) {
                inserted = true;
                System.out.println("Customer inserted successfully");
            } 
            pst.close();
            conn.close();
        } catch (SQLException ex) {
            inserted = false;
            System.out.println(ex.getLocalizedMessage());
            //Logger.getLogger(JDBCApp.class.getName()).log(Level.SEVERE, null, ex);
        }
        return inserted;
    }

    public boolean updateCustomer(Customer c) {
        boolean updated=false;
        try {
            PreparedStatement pst = getConnection().prepareStatement(updateCustomer);
            pst.setString(1, c.getCname());
            pst.setInt(2, c.getCcode());
            int result = pst.executeUpdate();
            if (result > 0) {
                updated = true;
                System.out.println("Customer updated successfully");
            } else {
                updated = false;
                System.out.println("Customer not updated");
            }
            pst.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return updated;
    }

    public void deleteCustomer(Customer c) {
        String sql = "DELETE FROM customers WHERE ccode = ?";
        try {
            PreparedStatement pst = getConnection().prepareStatement(sql);
            pst.setInt(1, c.getCcode());
            int result = pst.executeUpdate();
            if (result > 0) {
                System.out.println("Customer deleted successfully");
            } else {
                System.out.println("Customer not deleted");
            }
            pst.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean deleteCustomerById(int id) {
        boolean deleted = false;
        String sql = "DELETE FROM customers WHERE ccode = ?";
        try {
            PreparedStatement pst = getConnection().prepareStatement(sql);
            pst.setInt(1, id);
            int result = pst.executeUpdate();
            if (result > 0) {
                deleted = true;
                System.out.println("Customer deleted successfully");
            } else {
                System.out.println("Customer not deleted");
            }
            pst.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return deleted;
    }
    
    
    public List<Customer> getCustomersProc() {
        List<Customer> list = new ArrayList();
        try {
            Connection con = getConnection();
            CallableStatement st = con.prepareCall("{call getCustomers()}");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Customer c = new Customer(rs.getInt("ccode"), rs.getString("cname"));
                list.add(c);
            }
            closeConnections(rs, st);
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
        
    }
    
    

}
