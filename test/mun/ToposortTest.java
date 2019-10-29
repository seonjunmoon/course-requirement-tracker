package mun;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Testing the toposort class.
 */
public class ToposortTest {

    @Test
    public void testSortZeroElements() {
        List<Item> items = new ArrayList<>();
        List<Item> result = new Toposort().sort(items, Collections.EMPTY_SET);
        assertEquals(items, result);
    }

    @Test
    public void testSortOneElements() {
        List<Item> items = new ArrayList<Item>() {
            {
                add(new Item("elem", 0, new ArrayList<>()));
            }
        };
        List<Item> result = new Toposort().sort(items, Collections.EMPTY_SET);
        assertEquals(items, result);
    }

    @Test
    public void testSortTwoElements() {
        Item first = new Item("elem", 0, new ArrayList<>());
        Item second = new Item("elem", 1, new ArrayList<>());

        List<Item> items = new ArrayList<Item>() {
            {
                add(first);
                add(second);
            }
        };
        List<Item> result = new Toposort().sort(items, Collections.EMPTY_SET);
        assertEquals(new ArrayList<Item>() {
            {
                add(second);
                add(first);
            }
        }, result);
    }

    @Test
    public void testSortThree1Elements() {
        Item first = new Item("elem0", 0, new ArrayList<>());
        Item second = new Item("elem1", 1, new ArrayList<Item>() {
            {
                add(first);
            }
        });
        Item third = new Item("elem2", 2, new ArrayList<Item>() {
            {
                add(second);
            }
        });

        List<Item> items = new ArrayList<Item>() {
            {
                add(first);
                add(second);
                add(third);
            }
        };
        List<Item> result = new Toposort().sort(items, Collections.EMPTY_SET);
        assertEquals(new ArrayList<Item>() {
            {
                add(third);
                add(second);
                add(first);
            }
        }, result);
    }

    @Test
    public void testSortThree1ReverseElements() {
        Item first = new Item("elem0", 0, new ArrayList<>());
        Item second = new Item("elem1", 1, new ArrayList<Item>() {
            {
                add(first);
            }
        });
        Item third = new Item("elem2", 2, new ArrayList<Item>() {
            {
                add(second);
            }
        });

        List<Item> items = new ArrayList<Item>() {
            {
                add(first);
                add(third);
                add(second);
            }
        };
        List<Item> result = new Toposort().sort(items, Collections.EMPTY_SET);
        assertEquals(new ArrayList<Item>() {
            {
                add(third);
                add(second);
                add(first);
            }
        }, result);
    }

    @Test
    public void testSortThree2Elements() {
        Item first = new Item("elem0", 0, new ArrayList<>());
        Item second = new Item("elem1", 1, new ArrayList<Item>() {
            {
                add(first);
            }
        });
        Item third = new Item("elem2", 2, new ArrayList<Item>() {
            {
                add(first);
            }
        });

        List<Item> items = new ArrayList<Item>() {
            {
                add(first);
                add(second);
                add(third);
            }
        };
        List<Item> result = new Toposort().sort(items, Collections.EMPTY_SET);
        assertEquals(new ArrayList<Item>() {
            {
                add(third);
                add(second);
                add(first);
            }
        }, result);
    }

    @Test
    public void testSortThree3Elements() {
        Item first = new Item("elem0", 0, new ArrayList<>());
        Item second = new Item("elem1", 1, new ArrayList<>());
        Item third = new Item("elem2", 2, new ArrayList<Item>() {
            {
                add(first);
                add(second);
            }
        });

        List<Item> items = new ArrayList<Item>() {
            {
                add(first);
                add(third);
                add(second);
            }
        };
        List<Item> result = new Toposort().sort(items, Collections.EMPTY_SET);
        assertEquals(new ArrayList<Item>() {
            {
                add(third);
                add(second);
                add(first);
            }
        }, result);
    }

    @Test
    public void testSortThree4Elements() {
        Item first = new Item("elem0", 0, new ArrayList<>());
        Item second = new Item("elem1", 1, new ArrayList<>());
        Item third = new Item("elem2", 2, new ArrayList<Item>() {
            {
                add(first);
                add(second);
            }
        });

        List<Item> items = new ArrayList<Item>() {
            {
                add(first);
                add(third);
                add(second);
            }
        };
        List<Item> result = new Toposort().sort(items, new HashSet<String>() {
            {
                add(first.getName());
            }
        });
        assertEquals(new ArrayList<Item>() {
            {
                add(second);
                add(first);
            }
        }, result);
    }
}
