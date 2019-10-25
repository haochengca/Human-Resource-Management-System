/**
 * @program: Human Resource Management System
 * @description:
 * @author: Luke
 * @create: 2019-10-23 19:46
 **/


package com.luke.hrms.dao.impl;

import com.luke.hrms.dao.EmpDao;
import com.luke.hrms.pojo.Emp;
import com.luke.hrms.util.JdbcUtil;
import com.sun.org.apache.bcel.internal.generic.ANEWARRAY;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class EmpDaoImpl implements EmpDao {
    //Query all employees
    public ArrayList<Emp> selAllEmpInfo(){
        //declare Arraylist object
        ArrayList<Emp> list=null;
        //declare JDBC objects
        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        //execute sql
        try {
            //get connection
            conn= JdbcUtil.getConnection();
            //create sql statement
            String sql="select * from emp";
            //create sql preparedstatement object
            ps=JdbcUtil.getPreparedStatement(sql,conn);
            rs= ps.executeQuery();
            list=new ArrayList<>();
            //traverse the query results
            while (rs.next()){
                Emp p=new Emp();
                p.setEmpno(rs.getInt("empno"));
                p.setEname(rs.getString("ename"));
                p.setJob(rs.getNString("job"));
                p.setMgr(rs.getNString("mgr"));
                p.setDate(rs.getDate("hiredate"));
                p.setSal(rs.getDouble("sal"));
                p.setComm(rs.getDouble("comm"));
                p.setDeptno(rs.getInt("deptno"));
                list.add(p);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtil.closeAll(rs,ps,conn);
        }

        return list;
    }

    //Query employee by id
    public Emp selEmpInfoByEmpno(int empno){
        //declare JDBC objects
        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        Emp p=null;

        try {
            //get connection
            conn= JdbcUtil.getConnection();
            //create sql statement
            String sql="select * from emp where empno=?";
            ps=JdbcUtil.getPreparedStatement(sql,conn);
            ps.setInt(1,empno);
            //assign value to placeholder
            rs=ps.executeQuery();
            if (rs.next()){
                p=new Emp();
                p.setEmpno(rs.getInt("empno"));
                p.setEname(rs.getString("ename"));
                p.setJob(rs.getString("job"));
                p.setMgr(rs.getString("mgr"));
                p.setDate(rs.getDate("hiredate"));
                p.setSal(rs.getDouble("sal"));
                p.setComm(rs.getDouble("comm"));
                p.setDeptno(rs.getInt("deptno"));
            }


        } catch (Exception ex) {
            ex.printStackTrace();
        }finally {
            JdbcUtil.closeAll(rs,ps,conn);
        }

        return p;
    }

    //Add employee
    public int insEmpInfo(int empno, String ename, String job, int mgr, Date hiredate, double sal, double comm, int deptno){

        //create sql statement
        String sql="insert into emp values(?,?,?,?,?,?,?,?)";
        //transform the date type to java.sql.date

        java.sql.Date d=new java.sql.Date(hiredate.getTime());

        int i=JdbcUtil.executeDML(sql,empno,ename,job,mgr,d,sal,comm,deptno);

        return i;
    }


    //update employee's name
    public int upEmp(String newName,int empno){

        return JdbcUtil.executeDML("update emp set ename=? where empno=?",newName,empno);


    }


    //delete employee
    public  int delEmp(int empno){
        return JdbcUtil.executeDML("delete from emp where empno=?",empno);
    }

    /*public static void main(String[] args) {
        EmpDaoImpl ed=new EmpDaoImpl();
        Date hiredate= null;
        try {
            hiredate = new SimpleDateFormat("YYYY-MM-DD").parse("2014-1-1");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int i=ed.insEmpInfo(666,"jake","clerk",7566, hiredate,1500,1000,30);
        System.out.printf("i="+i);
    }*/

}


