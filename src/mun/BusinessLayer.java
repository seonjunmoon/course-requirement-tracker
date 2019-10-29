package mun;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * This is Business class that handles topological sorts logic and requests from
 * UI. It also handles the exception cases too.
 *
 * @author Seonjun Mun
 * @version 6/14/2018
 */
public class BusinessLayer {

    public static final String DEFAULT_FILE_NAME = "courses.txt";

    /**
     * This is the method for applying topological sorting.
     *
     * @return sorted list
     */
    public List<Item> toposort(String fileName, Set<String> startCourses) {
        List<Item> items = getItems(fileName);
        return new Toposort().sort(items, startCourses);
    }

    /**
     * This is the method for checking courses if they are valid courses.
     *
     * @return set of invalid courses
     */
    public Set<String> checkCourses(List<Item> items, Set<String> startCourses) {
        Set<String> errorCourses = new HashSet<>();

        for (String course : startCourses) {
            boolean found = false;
            for (Item item : items) {
                if (item.getName().equals(course)) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                errorCourses.add(course);
            }
        }
        return errorCourses;
    }

    /**
     * This is the method for creating the list and adding files of courses to
     * the list.
     *
     * @return list with courses
     */
    public List<Item> getItems(String fileName) {
        List<Item> list = new ArrayList<>();
        try {
            Path currentPath = Paths.get("");
            String totalPath = currentPath.toAbsolutePath().toString() + "\\" + fileName;
            List<String> files = Files.lines(Paths.get(totalPath), StandardCharsets.UTF_8).collect(Collectors.toList());
            for (int i = 0; i < files.size(); i++) {
                String[] items = files.get(i).split("\t");
                List<Item> parents = new ArrayList<>();
                for (int j = 1; j < items.length; j++) {
                    parents.add(list.get(Integer.parseInt(items[j]) - 1));
                }
                list.add(new Item(items[0], i, parents));
            }
        } catch (IOException ex) {
            System.out.println("Invalid file.");
        } catch (NumberFormatException ex) {
            System.out.println("Undefined file format.");
        }
        return list;
    }
}
