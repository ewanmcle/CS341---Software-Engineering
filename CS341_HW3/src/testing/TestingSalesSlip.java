package testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import ewan.SalesSlip;

class TestingSalesSlip {

    @Test
    void test() {
        fail("Not yet implemented");
    }

    // test add() method
    @Test
    void testAdd() {
        SalesSlip slip = new SalesSlip();
        slip.add("Pancake", 4, 2.00);
        slip.add("Cookie", 2, 5.00);
        assert(slip.toString() == 
        "Pancake              $   2.00     4\nCookie               $   5.00     2");
    }

    // test toString() method
    @Test
    void testToString() {
        SalesSlip slip = new SalesSlip();
        slip.add("Pancake", 4, 2.00);
        slip.add("Cookie", 2, 5.00);
        assert(slip.toString() == 
        "Pancake              $   2.00     4\nCookie               $   5.00     2");
    }

    // test computeSales() method
    @Test
    void testComputeSales() {
        SalesSlip slip = new SalesSlip();
        slip.add("Pancake", 4, 2.00);
        slip.add("Cookie", 2, 5.00);
        assert(slip.computeSales() == 7.00);
    }

}















