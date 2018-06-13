import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PercolationTest {

    private Percolation p;
    @Before
    public void init(){
        p = new Percolation(10);

    }

    @Test
    public void opentest() {
        p.open(1, 1);
        p.open(10, 10);
        p.open(1, 10);
        Assert.assertTrue(p.isOpen(1, 1));
        Assert.assertFalse(p.isFull(1, 2));
        Assert.assertTrue(p.isOpen(10, 10));
        Assert.assertFalse(p.isFull(9, 9));
        Assert.assertTrue(p.isOpen(1, 10));
        Assert.assertFalse(p.isFull(10, 1));
        Assert.assertTrue(p.numberOfOpenSites() == 3);
        p.open(9, 10);
        p.open(10, 9);
        p.open(9, 9);
    }

    @Test
    public void testIsFull(){
        p.open(1, 1);
        Assert.assertTrue(p.isFull(1,1));
        Assert.assertFalse(p.isFull(2,1));
        p.open(3, 1);
        Assert.assertFalse(p.isFull(3,1));
        p.open(2, 1);
        Assert.assertTrue(p.isFull(3,1));
        Assert.assertTrue(p.isFull(2,1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testExceptionLessThanNeeded() {
        p.open(0, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testExceptionMoreThanNeeded() {
        p.open(10, 11);
    }

    @Test
    public void testCellNumber(){
//        Assert.assertTrue(p.getCellNumber(1,1) == 1);
//        Assert.assertTrue(p.getCellNumber(10,10) == 100);
//        Assert.assertTrue(p.getCellNumber(10,1) == 91);
//        Assert.assertTrue(p.getCellNumber(1,10) == 10);
//        Assert.assertTrue(p.getCellNumber(6,5) == 55);
    }

    @Test
    public void testOpenBySerialNumber(){
        p.open(10,10);
//        Assert.assertTrue(p.isOpenByOrderedNumber(100));
//        Assert.assertFalse(p.isOpenByOrderedNumber(99));
//        Assert.assertFalse(p.isOpenByOrderedNumber(90));

    }

    @Test
    public void testPercolation(){
        Percolation p = new Percolation(3);
        p.open(1, 1);
        p.open(2, 1);
        p.open(3, 1);
        Assert.assertTrue(p.percolates());
    }

    @Test
    public void testNoPercolation(){
        Percolation p = new Percolation(3);
        p.open(1, 1);
        p.open(1, 3);
        p.open(3, 1);
        p.open(3, 2);
        p.open(3, 3);

        Assert.assertFalse(p.percolates());
    }

}
