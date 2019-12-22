package com.ouc;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SQLiteHandler {

    /**
     * 写一些初始化的东西
     */
    public SQLiteHandler(){

    }

    /**
     * 创建SQLite 数据库连接
     * @return
     */
    private Connection getConn(){
        Connection con = null;
        try {
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("jdbc:sqlite:music.db");
            con.setAutoCommit(false);
            System.out.println("Opened database successfully");
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        return  con;
    }

    /**
     * 添加一个歌单
     * @param name
     * @param creatorId
     * @param picture
     */
    public void insertSheet(String name, String creatorId,  String picture){
        //  name, dateCreated, creatorId, picture
        Connection c = getConn();
        PreparedStatement pst = null;  // 可以防止SQL注入
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String dateCreated = formatter.format(new Date());
//            String name = "Cherry的几首歌";
//            String creatorId = "17090022030";
//            String picture = "player.png";
            pst = c.prepareStatement("INSERT INTO sheet (name, dateCreated, creatorId, picture) VALUES(?, ?, ?, ?)");
            pst.setString(1, name);
            pst.setString(2, dateCreated);
            pst.setString(3, creatorId);
            pst.setString(4, picture);
            pst.executeUpdate();

        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        } finally {
            try {
                if(pst != null)
                    pst.close();
                if(c != null){
                    c.commit();
                    c.close();
                }
            }catch (SQLException ex){
                System.err.println( ex.getClass().getName() + ": " + ex.getMessage() );
            }
        }
        System.out.println("Insert To Sheet Successfully");
    }

    /**
     * 通过id删除一个歌单
     * @param id
     */
    public void deleteSheetById(int id){
        Connection c = getConn();
        PreparedStatement pst = null;  // 可以防止SQL注入
        try {
            pst = c.prepareStatement("DELETE from sheet where id=?;");
            pst.setInt(1, id);
            pst.executeUpdate();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        } finally {
            try {
                if(pst != null)
                    pst.close();
                if(c != null){
                    c.commit();
                    c.close();
                }
            }catch (SQLException ex){
                System.err.println( ex.getClass().getName() + ": " + ex.getMessage() );
            }

        }
        System.out.println("Delete data From Sheet successfully");
    }

    /**
     * 获取所有歌单
     */
    public void getSheet(){
        Connection c = getConn();
        PreparedStatement pst = null;  // 可以防止SQL注入
        try {
            pst = c.prepareStatement("SELECT * FROM sheet;");
            ResultSet rs = pst.executeQuery();
            while ( rs.next() ) {
                int id = rs.getInt("id");
                String  name = rs.getString("name");
                String  dateCreated = rs.getString("dateCreated");
                String  creatorId = rs.getString("creatorId");
                String  picture = rs.getString("picture");
                System.out.println( "id = " + id );
                System.out.println( "name = " + name );
                System.out.println( "dateCreated = " + dateCreated );
                System.out.println( "creatorId = " + creatorId );
                System.out.println( "picture = " + picture );
                System.out.println("MusicItems: ");
                this.getMusicBySheetId(id);
                System.out.println();
            }
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        } finally {
            try {
                if(pst != null)
                    pst.close();
                if(c != null){
                    c.commit();
                    c.close();
                }
            }catch (SQLException ex){
                System.err.println( ex.getClass().getName() + ": " + ex.getMessage() );
            }
        }
        System.out.println("Get data From Sheet successfully");
    }

    /**
     * 添加一首音乐
     * @param name
     * @param sheetId
     * @param md5value
     * @param namePath
     */
    public void insertMusic(String name, int sheetId, String md5value, String namePath){
        Connection conn = getConn();
        PreparedStatement pst = null;
        try{
            pst = conn.prepareStatement("INSERT INTO music (name, sheetId, md5value, namePath) VALUES(?, ?, ?, ?)");
            pst.setString(1, name);
            pst.setInt(2, sheetId);
            pst.setString(3, md5value);
            pst.setString(4, namePath);
            pst.executeUpdate();

        }catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }finally {
            try {
                if(pst != null)
                    pst.close();
                if(conn != null){
                    conn.commit();
                    conn.close();
                }
            }catch (SQLException ex){
                System.err.println( ex.getClass().getName() + ": " + ex.getMessage() );
            }
        }
    }

    /**
     * 通过歌单id获取音乐
     * @param id
     */
    public void getMusicBySheetId(int id){
        Connection c = getConn();
        PreparedStatement pst = null;  // 可以防止SQL注入
        try {
            pst = c.prepareStatement("select * from music where sheetId=?;");
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()){
                int musicId = rs.getInt("id");
                String  name = rs.getString("name");
                int  sheetId = rs.getInt("sheetId");
                String  md5value = rs.getString("md5value");
                String  namePath = rs.getString("namePath");
                System.out.println( "id = " + musicId );
                System.out.println( "name = " + name );
                System.out.println( "sheetId = " + sheetId );
                System.out.println( "md5value = " + md5value );
                System.out.println( "namePath = " + namePath );
                System.out.println();
            }
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        } finally {
            try {
                if(pst != null)
                    pst.close();
                if(c != null){
                    c.commit();
                    c.close();
                }
            }catch (SQLException ex){
                System.err.println( ex.getClass().getName() + ": " + ex.getMessage() );
            }

        }
        System.out.println("getMusicBySheetId From Sheet successfully");
    }

    public static void main( String args[] )
    {
        SQLiteHandler sqLiteHandler = new SQLiteHandler();
//        sqLiteHandler.insertSheet("Bob", "19030022035","bob.png");
//        sqLiteHandler.deleteSheetById(4);
        sqLiteHandler.getSheet();
//        for(int i=0;i<5;i++) {
//            sqLiteHandler.insertMusic("CherryTest".concat(String.valueOf(i)), 1, "4564d65a4d65a4d6a4d6wa46", "/home/music");
//        }
//        sqLiteHandler.getMusicBySheetId(3);
    }
}