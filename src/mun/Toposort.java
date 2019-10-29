package mun;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * This is Toposort class that provides topological sorts to make the course
 * list ordered. It also provides DFS method with the sort logic too.
 *
 * @author Seonjun Mun
 * @version 6/14/2018
 */
public class Toposort {

    /**
     * This is the method that provides the sort to make the list of courses
     * ordered.
     *
     * @return list in order
     */
    public List<Item> sort(List<Item> list, Set<String> finishedCourses) {
        boolean used[] = new boolean[list.size()];
        boolean skipped[] = new boolean[list.size()];
        List<Item> courseOrder = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            if (!used[list.get(i).getNum()]) {
                dfs(list, used, skipped, courseOrder, list.get(i).getNum(), finishedCourses);
            }
        }
        Collections.reverse(courseOrder);
        return courseOrder;
    }

    /**
     * This is the method that provides the Depth-first search (DFS) with using
     * the topological sort logic.
     *
     * @return true for skipped courses or false if not
     */
    private boolean dfs(List<Item> list, boolean[] used, boolean skipped[], List<Item> order, int index, Set<String> finishedCourses) {
        boolean isSkipped = false;
        if (!used[index]) {
            used[index] = true;
            for (Item item : list.get(index).getParents()) {
                isSkipped |= dfs(list, used, skipped, order, item.getNum(), finishedCourses);
            }
            if (!isSkipped) {
                order.add(list.get(index));
            }
            isSkipped |= finishedCourses.contains(list.get(index).getName());
            skipped[index] = isSkipped;
        } else {
            isSkipped = skipped[index];
        }
        return isSkipped;
    }
}
