package com.niit.jdp.repository;

import com.niit.jdp.model.Playlist;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface Repository {
    List<Playlist> getAll() throws SQLException;
    Playlist getBySongId(int songId) throws SQLException;
    boolean deleteBySongId(int songId) throws SQLException;
}
