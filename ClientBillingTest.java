package my.billing;

import my.billing.ClientBilling;
import my.billing.BillingCycle;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author paulo
 */
public class ClientBillingTest {
    
    public ClientBillingTest() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Given a date 
     * When it is within the first month
     * Then return a billing cycle
     */
    @Test
    public void testBillingCycleWithinFirstMonth() throws ParseException {
        System.out.println("testBillingCycleWithinFirstMonth");
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Date queryDate = sdf.parse("05-04-2018");
        Date start = sdf.parse("15-03-2018");
        Date end = sdf.parse("14-04-2018");
        ClientBilling instance = new ClientBilling(start);
        BillingCycle expResult = new BillingCycle(start, end);
        BillingCycle result = instance.getBillingCycle(queryDate);
        assertEquals(expResult.getStart(), result.getStart());
        assertEquals(expResult.getEnd(), result.getEnd());
    }
    
    /**
     * Given a date 
     * When it is after three months
     * Then return a billing cycle
     */
    @Test
    public void testBillingCycleAfterThreeMonths() throws ParseException {
        System.out.println("testBillingCycleAfterThreeMonths");
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Date queryDate = sdf.parse("05-06-2018");
        Date initial = sdf.parse("15-03-2018");
        Date start = sdf.parse("15-06-2018");
        Date end = sdf.parse("15-07-2018");
        ClientBilling instance = new ClientBilling(initial);
        BillingCycle expResult = new BillingCycle(start, end);
        BillingCycle result = instance.getBillingCycle(queryDate);
        assertEquals(expResult.getStart(), result.getStart());
        assertEquals(expResult.getEnd(), result.getEnd());
    }
    
    /**
     * Given a date 
     * When it is before the initial month
     * Then return null
     */
    @Test
    public void testBillingCycleBeforeInitialMonth() throws ParseException {
        System.out.println("testBillingCycleBeforeInitialMonth");
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Date queryDate = sdf.parse("05-02-2018");
        Date initial = sdf.parse("15-03-2018");

        ClientBilling instance = new ClientBilling(initial);
        BillingCycle expResult = null;
        BillingCycle result = instance.getBillingCycle(queryDate);
        assertEquals(expResult, result);
    }
    
}