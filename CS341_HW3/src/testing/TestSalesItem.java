package testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import ewan.SalesItem;

class TestSalesItem {

	@Test
	void test() {
		fail("Not yet implemented");
	}
	
	/// creating a sales item
	@Test
	void testCreateSalesItem() {
		SalesItem ss = new SalesItem("Pancake", 4, 2.00);
		assert(ss.getName() == "Pancake");
		assert(ss.getQuantity() == 4);
		assert(ss.getPrice() == 2.00);
	}

	// test setters and getters for Name
    @Test
    void testName() {
		SalesItem ss = new SalesItem("Pancake", 4, 2.00);
        ss.getName();
		assert(ss.getName() == "Pancake");
        ss.setName("Cookie");
		assert(ss.getName() == "Cookie");
    }

	// test setters and getters for Quantity
    @Test
    void testQuantity() {
		SalesItem ss = new SalesItem("Pancake", 4, 2.00);
        ss.getQuantity();
		assert(ss.getQuantity() == 4);
        ss.setQuantity(5);
		assert(ss.getQuantity() == 5);
    }

	// test setters and getters for Price 
    @Test
    void testPrice() {
		SalesItem ss = new SalesItem("Pancake", 4, 2.00);
        ss.getPrice();
		assert(ss.getPrice() == 2.00);
        ss.setPrice(3.00);
		assert(ss.getQuantity() == 3.00);
    }

	// test to string
    @Test
    void testToString() {
		SalesItem ss = new SalesItem("Pancake", 4, 2.00);
        assert(ss.toString() == "Pancake              $   2.00     4");
    }

}








