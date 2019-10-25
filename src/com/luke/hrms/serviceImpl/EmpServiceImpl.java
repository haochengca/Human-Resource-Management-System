/**
 * @program: Human Resource Management System
 * @description:
 * @author: Luke
 * @create: 2019-10-24 11:10
 **/


package com.luke.hrms.serviceImpl;

import com.luke.hrms.dao.EmpDao;
import com.luke.hrms.dao.impl.EmpDaoImpl;
import com.luke.hrms.pojo.Emp;
import com.luke.hrms.service.EmpService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;


public class EmpServiceImpl implements EmpService {
    EmpDao ed=new EmpDaoImpl();//for upgrade
    //update employee's name
    public void upEmpInfo(){
        //get data from terminal
        Scanner sc=new Scanner(System.in);
        System.out.println("Please input empno");
        int empno=sc.nextInt();
        System.out.println("Please input new empno");
        String newName=sc.next();
//        EmpDaoImpl ed=new EmpDaoImpl();
        int i=ed.upEmp(newName,empno);
        if(i>0){
            System.out.println("Update successfully");

        }else {
            System.out.println("Update failed");
        }
    }

    //add employee
    public void insEmpInfo(){
        Scanner sc=new Scanner(System.in);
        System.out.println("please input new empno:");
        int empno=sc.nextInt();
        System.out.println("please intput new empname:");
        String empname=sc.next();
        System.out.println("please input new job:");
        String job=sc.next();
        System.out.println("please input new mgr:");
        int mgr=sc.nextInt();
        System.out.println("please input hiredate(YYYY-MM-DD):" );
        String date=sc.next();
        System.out.println("please input salary:");
        double sal=sc.nextDouble();
        System.out.println("please input commission");
        double comm=sc.nextDouble();
        System.out.println("please input deptno");
        int deptno=sc.nextInt();
        //transform to date type
        Date hiredate=null;
        try {
            hiredate=new SimpleDateFormat("YYYY-MM-DD").parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

//        EmpDaoImpl ed=new EmpDaoImpl();
        int i=ed.insEmpInfo(empno,empname,job,mgr,hiredate,sal,comm,deptno);
        if(i>0){
            System.out.println("add successfully");
        }else {
            System.out.println("add failed");
        }
    }
    //delete employee
    public void delEmpInfo(){

        Scanner sc=new Scanner(System.in);
        System.out.println("please input empno you want to delete:");
        int empno=sc.nextInt();
//        EmpDaoImpl ed=new EmpDaoImpl();
        int i=ed.delEmp(empno);

        if(i>0){
            System.out.println("delete successfully");
        }else{
            System.out.println("delete failed");
        }


    }

    //query by empno(id)
    public void selEmpInfoEmpno(){
        Scanner sc=new Scanner(System.in);
        System.out.println("please input empno you want to query:");
        int empno=sc.nextInt();
//        EmpDaoImpl ed=new EmpDaoImpl();
        Emp p=ed.selEmpInfoByEmpno(empno);
        if (p!=null) {
            System.out.println("empno \t ename \t job \t mgr \t hiredate \t salary \t commission \t deptno");
            System.out.print(p.getEmpno());
            System.out.print("\t");
            System.out.print(p.getEname());
            System.out.print("\t");
            System.out.print(p.getJob());
            System.out.print("\t");
            System.out.print(p.getMgr());
            System.out.print("\t");
            System.out.print(p.getDate());
            System.out.print("\t");
            System.out.print(p.getSal());
            System.out.print("\t\t\t");
            System.out.print(p.getComm());
            System.out.print("\t\t\t\t");
            System.out.print(p.getDeptno());
        } else {
            System.out.println("no such person");
        }

    }

    public void selAllEmpInfo(){
//        EmpDaoImpl ed=new EmpDaoImpl();
        ArrayList<Emp> list=ed.selAllEmpInfo();
        System.out.println("empno \t ename \t job \t\t\t \tmgr \t\t\t hiredate \t\t salary \t commission \t deptno");
        for (int i = 0; i <list.size() ; i++) {
            Emp p=list.get(i);
            System.out.print(p.getEmpno());
            System.out.print("\t");
            System.out.print(p.getEname());
            System.out.print("\t");
            System.out.print(p.getJob());
            System.out.print("\t\t\t");
            System.out.print(p.getMgr());
            System.out.print("\t\t\t");
            System.out.print(p.getDate());
            System.out.print("\t\t");
            System.out.print(p.getSal());
            System.out.print("\t\t\t");
            System.out.print(p.getComm());
            System.out.print("\t\t\t\t");
            System.out.print(p.getDeptno());
            System.out.println();

        }


    }
}


