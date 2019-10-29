package mun;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Testing the business layer class.
 */
public class BusinessLayerTest {

    @Test
    public void testProcces0() {
        BusinessLayer bl = new BusinessLayer();
        List<Item> result = bl.toposort("test\\testExample\\test0.txt", Collections.EMPTY_SET);
        assertEquals(result.size(), 0);
    }

    @Test
    public void testProcces1() {
        BusinessLayer bl = new BusinessLayer();
        List<Item> result = bl.toposort("test\\testExample\\test1.txt", Collections.EMPTY_SET);
        assertEquals(result.size(), 1);
        assertEquals(result.get(0).getName(), "cs300");
    }

    @Test
    public void testProcces2() {
        BusinessLayer bl = new BusinessLayer();
        List<Item> result = bl.toposort("test\\testExample\\test2.txt", Collections.EMPTY_SET);
        assertEquals(result.size(), 2);
        assertEquals(result.get(0).getName(), "cs202");
        assertEquals(result.get(1).getName(), "cs300");
        assertEquals(result.get(0).getParents().size(), 1);
    }

    @Test
    public void testProcces3() {
        BusinessLayer bl = new BusinessLayer();
        List<Item> result = bl.toposort("test\\testExample\\test3.txt", new HashSet<String>() {
            {
                add("ENG101");
            }
        });
        assertEquals(result.size(), 1);
    }

    @Test
    public void testCheckCoursersOk1() {
        BusinessLayer bl = new BusinessLayer();

        List<Item> items = new ArrayList<Item>() {
            {
                add(new Item("elem0", 0, new ArrayList<>()));
                add(new Item("elem1", 0, new ArrayList<>()));
                add(new Item("elem2", 0, new ArrayList<>()));
            }
        };

        assertEquals(bl.checkCourses(items, new HashSet<String>() {
            {
                add("elem0");
            }
        }).size(), 0);
    }

    @Test
    public void testCheckCoursersOk2() {
        BusinessLayer bl = new BusinessLayer();

        List<Item> items = new ArrayList<Item>() {
            {
                add(new Item("elem0", 0, new ArrayList<>()));
                add(new Item("elem1", 0, new ArrayList<>()));
                add(new Item("elem2", 0, new ArrayList<>()));
            }
        };

        assertEquals(bl.checkCourses(items, new HashSet<String>() {
            {
                add("elem0");
                add("elem2");
            }
        }).size(), 0);
    }

    @Test
    public void testCheckCoursersError1() {
        BusinessLayer bl = new BusinessLayer();

        List<Item> items = new ArrayList<Item>() {
            {
                add(new Item("elem0", 0, new ArrayList<>()));
                add(new Item("elem1", 0, new ArrayList<>()));
                add(new Item("elem2", 0, new ArrayList<>()));
            }
        };

        assertEquals(bl.checkCourses(items, new HashSet<String>() {
            {
                add("elem5");
            }
        }).size(), 1);
    }

    @Test
    public void testCheckCoursersError2() {
        BusinessLayer bl = new BusinessLayer();

        List<Item> items = new ArrayList<Item>() {
            {
                add(new Item("elem0", 0, new ArrayList<>()));
                add(new Item("elem1", 0, new ArrayList<>()));
                add(new Item("elem2", 0, new ArrayList<>()));
            }
        };

        assertEquals(bl.checkCourses(items, new HashSet<String>() {
            {
                add("elem5");
                add("ele231");
            }
        }).size(), 2);
    }
}
