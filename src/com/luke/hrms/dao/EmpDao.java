package com.luke.hrms.dao;

import com.luke.hrms.pojo.Emp;

import java.util.ArrayList;
import java.util.Date;

public interface EmpDao {
    public ArrayList<Emp> selAllEmpInfo();
    public Emp selEmpInfoByEmpno(int empno);
    public int insEmpInfo(int empno, String ename, String job, int mgr, Date hiredate, double sal, double comm, int deptno);
    public int upEmp(String newName,int empno);
    public  int delEmp(int empno);
    }
