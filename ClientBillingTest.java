package my.billing;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
        Date start = sdf.parse("15-05-2018");
        Date end = sdf.parse("14-06-2018");
        ClientBilling instance = new ClientBilling(initial);
        BillingCycle expResult = new BillingCycle(start, end);
        BillingCycle result = instance.getBillingCycle(queryDate);
        assertEquals(expResult.getStart(), result.getStart());
        assertEquals(expResult.getEnd(), result.getEnd());
    }
    
    /**
     * Given a date 
     * When it is after five months
     * Then return a billing cycle
     */
    @Test
    public void testBillingCycleAfterFiveMonths() throws ParseException {
        System.out.println("testBillingCycleAfterFiveMonths");
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Date queryDate = sdf.parse("05-08-2018");
        Date initial = sdf.parse("15-03-2018");
        Date start = sdf.parse("15-07-2018");
        Date end = sdf.parse("14-08-2018");
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
    
    /**
     * Given a date 
     * When it is after ten months within the same year
     * Then return a billing cycle
     */
    @Test
    public void testBillingCycleAfterTenMonths() throws ParseException {
        System.out.println("testBillingCycleAfterTenMonths");
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Date queryDate = sdf.parse("05-11-2018");
        Date initial = sdf.parse("15-02-2018");
        Date start = sdf.parse("15-10-2018");
        Date end = sdf.parse("14-11-2018");
        ClientBilling instance = new ClientBilling(initial);
        BillingCycle expResult = new BillingCycle(start, end);
        BillingCycle result = instance.getBillingCycle(queryDate);
        assertEquals(expResult.getStart(), result.getStart());
        assertEquals(expResult.getEnd(), result.getEnd());
    }
    
    /**
     * Given a date 
     * When it is after one month AND initial date is in February AND non leap year
     * Then return a billing cycle
     */
    @Test
    public void testBillingCycleAfterOneMonthStartingAtFebruaryNonLeap() throws ParseException {
        System.out.println("testBillingCycleAfterTwoMonthsStartingAtFebruaryNonLeap");
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Date queryDate = sdf.parse("05-03-2018");
        Date initial = sdf.parse("15-02-2018");
        Date start = sdf.parse("15-02-2018");
        Date end = sdf.parse("17-03-2018");
        ClientBilling instance = new ClientBilling(initial);
        BillingCycle expResult = new BillingCycle(start, end);
        BillingCycle result = instance.getBillingCycle(queryDate);
        assertEquals(expResult.getStart(), result.getStart());
        assertEquals(expResult.getEnd(), result.getEnd());
    }
    
    /**
     * Given a date 
     * When it is after one month AND initial date is in February AND leap year
     * Then return a billing cycle
     */
    @Test
    public void testBillingCycleAfterOneMonthStartingAtFebruaryLeap() throws ParseException {
        System.out.println("testBillingCycleAfterTwoMonthsStartingAtFebruaryLeap");
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Date queryDate = sdf.parse("05-03-2020");
        Date initial = sdf.parse("15-02-2020");
        Date start = sdf.parse("15-02-2020");
        Date end = sdf.parse("16-03-2020");
        ClientBilling instance = new ClientBilling(initial);
        BillingCycle expResult = new BillingCycle(start, end);
        BillingCycle result = instance.getBillingCycle(queryDate);
        assertEquals(expResult.getStart(), result.getStart());
        assertEquals(expResult.getEnd(), result.getEnd());
    }
    
    /**
     * Given a date 
     * When it is after one month 
     *      AND initial date is in February 
     *      AND non leap year
     *      AND query date is greater than 30 days in difference
     * Then return a billing cycle
     */
    @Test
    public void testBillingCycleAfterOneMonthStartingAtFebruaryNonLeapQryGT30() throws ParseException {
        System.out.println("testBillingCycleAfterTwoMonthsStartingAtFebruaryNonLeapQryGT30");
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Date queryDate = sdf.parse("29-03-2018");
        Date initial = sdf.parse("15-02-2018");
        Date start = sdf.parse("15-03-2018");
        Date end = sdf.parse("14-04-2018");
        ClientBilling instance = new ClientBilling(initial);
        BillingCycle expResult = new BillingCycle(start, end);
        BillingCycle result = instance.getBillingCycle(queryDate);
        assertEquals(expResult.getStart(), result.getStart());
        assertEquals(expResult.getEnd(), result.getEnd());
    }

    /**
     * Given two dates 
     * When there is 1 day difference
     * Then return 1
     */
    @Test
    public void testDiffDays1Day() throws ParseException {
        System.out.println("testDiffDays1Day");
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Date initial = sdf.parse("28-03-2018");
        Date queryDate = sdf.parse("29-03-2018");
        
        Calendar iniCal = Calendar.getInstance();
        iniCal.setTime(initial);

        Calendar qryCal = Calendar.getInstance();
        qryCal.setTime(queryDate);
        
        int expResult = 1;
        int result = ClientBilling.diffDays(iniCal, qryCal);
        assertEquals(expResult, result);
    }
    
    /**
     * Given two dates 
     * When there is 365 day difference
     * Then return 365     */
    @Test
    public void testDiffDays365Day() throws ParseException {
        System.out.println("testDiffDays365Day");
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Date initial = sdf.parse("29-03-2017");
        Date queryDate = sdf.parse("29-03-2018");
        
        Calendar iniCal = Calendar.getInstance();
        iniCal.setTime(initial);

        Calendar qryCal = Calendar.getInstance();
        qryCal.setTime(queryDate);
        
        int expResult = 365;
        int result = ClientBilling.diffDays(iniCal, qryCal);
        assertEquals(expResult, result);
    }

    /**
     * Given two dates 
     * When there is 366 day difference due to leap
     * Then return 366     */
    @Test
    public void testDiffDays366Day() throws ParseException {
        System.out.println("testDiffDays366Day");
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Date initial = sdf.parse("29-03-2019");
        Date queryDate = sdf.parse("29-03-2020");
        
        Calendar iniCal = Calendar.getInstance();
        iniCal.setTime(initial);

        Calendar qryCal = Calendar.getInstance();
        qryCal.setTime(queryDate);
        
        int expResult = 366;
        int result = ClientBilling.diffDays(iniCal, qryCal);
        assertEquals(expResult, result);
    }
    
    /**
     * Given two dates 
     * When there is less than a year of day difference
     * Then return 366     */
    @Test
    public void testDiffDaysLTYear() throws ParseException {
        System.out.println("testDiffDaysLTYear");
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Date queryDate = sdf.parse("14-01-2019");
        Date initial = sdf.parse("15-02-2018");
        
        Calendar iniCal = Calendar.getInstance();
        iniCal.setTime(initial);

        Calendar qryCal = Calendar.getInstance();
        qryCal.setTime(queryDate);
        
        int expResult = 333;
        int result = ClientBilling.diffDays(iniCal, qryCal);
        assertEquals(expResult, result);
    }
    
    /**
     * Given a date 
     * When it changes the year
     * Then return a billing cycle
     */
    @Test
    public void testBillingCycleWhenYearChanges() throws ParseException {
        System.out.println("testBillingCycleWhenYearChanges");
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Date queryDate = sdf.parse("29-01-2019");
        Date initial = sdf.parse("15-02-2018");
        Date start = sdf.parse("15-01-2019");
        Date end = sdf.parse("14-02-2019");
        ClientBilling instance = new ClientBilling(initial);
        BillingCycle expResult = new BillingCycle(start, end);
        BillingCycle result = instance.getBillingCycle(queryDate);
        assertEquals(expResult.getStart(), result.getStart());
        assertEquals(expResult.getEnd(), result.getEnd());
    }

    /**
     * Given a date 
     * When it changes the year
     * Then return a billing cycle
     */
    @Test
    public void testBillingCycleWhenYearChangesFirstCycle() throws ParseException {
        System.out.println("testBillingCycleWhenYearChangesFirstCycle");
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Date queryDate = sdf.parse("10-01-2019");
        Date initial = sdf.parse("15-02-2018");
        Date start = sdf.parse("15-12-2018");
        Date end = sdf.parse("14-01-2019");
        ClientBilling instance = new ClientBilling(initial);
        BillingCycle expResult = new BillingCycle(start, end);
        BillingCycle result = instance.getBillingCycle(queryDate);
        assertEquals(expResult.getStart(), result.getStart());
        assertEquals(expResult.getEnd(), result.getEnd());
    }
    
    /**
     * Given a date 
     * When it is the same as the initial
     * Then return a billing cycle
     */
    @Test
    public void testBillingCycleSameDay() throws ParseException {
        System.out.println("testBillingCycleSameDay");
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Date queryDate = sdf.parse("15-02-2018");
        Date initial = sdf.parse("15-02-2018");
        Date start = sdf.parse("15-02-2018");
        Date end = sdf.parse("17-03-2018");
        ClientBilling instance = new ClientBilling(initial);
        BillingCycle expResult = new BillingCycle(start, end);
        BillingCycle result = instance.getBillingCycle(queryDate);
        assertEquals(expResult.getStart(), result.getStart());
        assertEquals(expResult.getEnd(), result.getEnd());
    }
    
    /**
     * Given a date 
     * When it is an invalid date
     * Then return a billing cycle based on the lenient property of the Calendar
     */
    @Test
    public void testBillingCycleInvalidQryDay() throws ParseException {
        System.out.println("testBillingCycleInvalidQryDay");
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Date queryDate = sdf.parse("30-02-2018");
        Date initial = sdf.parse("15-02-2018");
        Date start = sdf.parse("15-02-2018");
        Date end = sdf.parse("17-03-2018");
        ClientBilling instance = new ClientBilling(initial);
        BillingCycle expResult = new BillingCycle(start, end);
        BillingCycle result = instance.getBillingCycle(queryDate);
        assertEquals(expResult.getStart(), result.getStart());
        assertEquals(expResult.getEnd(), result.getEnd());
    }
}
