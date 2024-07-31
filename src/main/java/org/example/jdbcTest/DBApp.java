package org.example.jdbcTest;

import java.util.Scanner;

public class DBApp {
    public static void main(String[] args) {
        System.out.println("-----------Choice Number-----------");
        System.out.println("1. Insert");
        System.out.println("2. Update");
        System.out.println("3. Delete");
        System.out.println("4. Select");
        System.out.println(" >> ");

        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();

        if (choice == 1) {
            MainInsert.main(new String[0]);
        }
        else if (choice == 2) {
            MainUpdate.main(new String[0]);
        }
        else if (choice == 3) {
            MainDelete.main(new String[0]);
        }
        else if (choice == 4) {
            MainSelect.main(new String[0]);
        }
    }
}
