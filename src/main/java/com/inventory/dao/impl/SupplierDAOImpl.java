package com.inventory.dao.impl;

import com.inventory.config.DBConnection;
import com.inventory.dao.SupplierDAO;
import com.inventory.model.Supplier;
import java.sql.*;
import java.util.*;

public class SupplierDAOImpl implements SupplierDAO {

    @Override
    public boolean addSupplier(Supplier supplier) {
        String sql = "INSERT INTO supplier (name,contactInfo,address) VALUES (?,?,?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, supplier.getName());
            ps.setString(2, supplier.getContactInfo());
            ps.setString(3, supplier.getAddress());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); return false; }
    }

    @Override
    public Supplier getSupplierById(int id) {
        String sql = "SELECT * FROM supplier WHERE id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return mapRow(rs);
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    @Override
    public List<Supplier> getAllSuppliers() {
        List<Supplier> list = new ArrayList<>();
        String sql = "SELECT * FROM supplier";
        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) list.add(mapRow(rs));
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    @Override
    public boolean updateSupplier(Supplier supplier) {
        String sql = "UPDATE supplier SET name=?,contactInfo=?,address=? WHERE id=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, supplier.getName());
            ps.setString(2, supplier.getContactInfo());
            ps.setString(3, supplier.getAddress());
            ps.setInt(4, supplier.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); return false; }
    }

    @Override
    public boolean deleteSupplier(int id) {
        String sql = "DELETE FROM supplier WHERE id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); return false; }
    }

    private Supplier mapRow(ResultSet rs) throws SQLException {
        Supplier s = new Supplier();
        s.setId(rs.getInt("id"));
        s.setName(rs.getString("name"));
        s.setContactInfo(rs.getString("contactInfo"));
        s.setAddress(rs.getString("address"));
        return s;
    }
}