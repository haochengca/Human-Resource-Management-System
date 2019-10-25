/**
 * @program: Human Resource Management System
 * @description:
 * @author: Luke
 * @create: 2019-10-24 09:28
 **/


package com.luke.hrms.test;

import com.luke.hrms.service.EmpService;
import com.luke.hrms.serviceImpl.EmpServiceImpl;

import java.util.Scanner;

public class Test {
    public static void main(String[] args) {

        EmpService es=new EmpServiceImpl();
        do {

            System.out.println("1.Query all employees");
            System.out.println("2.Query employee by id");
            System.out.println("3.Add employee");
            System.out.println("4.update employee's name");
            System.out.println("5.1delete employee");
            System.out.println("6.exit");
            System.out.println("*******Welcome: please select function***********");
            Scanner sc=new Scanner(System.in);
            int choice=sc.nextInt();
            switch (choice){
                case 1:
                    es.selAllEmpInfo();
                    System.out.println("");
                    break;
                case 2:
                    es.selEmpInfoEmpno();
                    System.out.println("");

                    break;
                case 3:
                    es.insEmpInfo();
                    System.out.println("");

                    break;
                case 4:
                    es.upEmpInfo();
                    System.out.println("");

                    break;
                case 5:
                    es.delEmpInfo();
                    System.out.println("");

                    break;
                case 6:
                    System.out.println("Thanks,byebye!");
                    System.out.println("");

                    return;
                default:
                    System.out.println("input error");
                    break;
            }

        } while (true);


    }


}


