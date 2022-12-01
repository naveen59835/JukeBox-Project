package com.niit.jdp.repository;

import com.niit.jdp.model.Playlist;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface Repository {
    List<Playlist> getAll(Connection connection) throws SQLException;
    Playlist getBySongId(Connection connection, int songId) throws SQLException;
    boolean deleteBySongId(Connection connection, int songId) throws SQLException;
}
