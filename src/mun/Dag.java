package mun;

import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

/**
 * This is Dag class that has a main method and lets users put the placement
 * result so that user can see the list of the required classes. *
 *
 * @author Seonjun Mun
 * @version 6/14/2018
 */
public class Dag {

    public static void main(String[] args) {
        new Dag().display();
    }

    /**
     * This method displays the input screen and lets users enter the course
     * that user is placed.
     *
     */
    public void display() {
        Scanner scanner = new Scanner(System.in);
        BusinessLayer bl = new BusinessLayer();

        do {
            System.out.println("Insert the courses you are placed (use tab between two courses):");
            Set<String> coursesSet = new HashSet<>();
            String input = scanner.nextLine();
            if (!input.trim().isEmpty()) {
                String[] courses = input.split("\t");
                for (String course : courses) {
                    coursesSet.add(course.replace(" ", "").trim().toUpperCase());
                }
                Set<String> mistakes = bl.checkCourses(bl.getItems(BusinessLayer.DEFAULT_FILE_NAME), coursesSet);
                if (mistakes.size() > 0) {
                    System.out.println("Please enter valid courses name (with tab if two courses):");
                    for (String mistake : mistakes) {
                        System.out.println(mistake);
                    };
                    System.out.println();
                    System.out.println("Y - Exit \nOther buttons - Again");
                    String input2 = scanner.nextLine();
                    if (input2.equalsIgnoreCase("y")) {
                        break;
                    }
                    continue;
                }
            }

            List<Item> list = bl.toposort(BusinessLayer.DEFAULT_FILE_NAME, coursesSet);
            System.out.println("Next courses" + ":");
            for (Item one : list) {
                System.out.println(one.getName() + " ");
            }
            System.out.println();
            System.out.println("Y - Exit \nOther buttons - Again");
            String input3 = scanner.nextLine();
            if (input3.equalsIgnoreCase("y")) {
                break;
            }
        } while (true);
    }
}
