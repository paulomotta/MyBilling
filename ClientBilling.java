package my.billing;

import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author paulo
 */
public class ClientBilling {

    private Date initialDate;
    private static final int CYCLE_SIZE = 30;

    public ClientBilling(Date initialDate) {
        this.initialDate = initialDate;
    }

    public ClientBilling() {
        this(new Date());
    }

    public BillingCycle getBillingCycle(Date queryDate) {
        if (queryDate.before(initialDate)) return null;
        
        Date localIniDate = (Date) initialDate.clone();
        
        Calendar iniCal = Calendar.getInstance();
        iniCal.setTime(localIniDate);

        Calendar qryCal = Calendar.getInstance();
        qryCal.setTime(queryDate);

        int diffDays = diffDays(iniCal, qryCal);
        System.out.println(diffDays);

        if (diffDays > CYCLE_SIZE) {
            iniCal.add(Calendar.MONTH, (diffDays/CYCLE_SIZE));
            int tmpMonth = iniCal.get(Calendar.MONTH);
            System.out.println("tmpMonth=" + tmpMonth);
        }
        Date ini = iniCal.getTime();

        iniCal.add(Calendar.DAY_OF_MONTH, CYCLE_SIZE);
        Date end = iniCal.getTime();
        return new BillingCycle(ini, end);
    }

    public static int diffDays(Calendar iniCal, Calendar qryCal) {
        
        int d1Month = iniCal.get(Calendar.DAY_OF_YEAR);
        System.out.println("d1Month =" + d1Month);
        int d2Month = qryCal.get(Calendar.DAY_OF_YEAR);
        System.out.println("d2Month =" + d2Month);

        return Math.abs(d1Month - d2Month);
    }

    /**
     * @return the initialDate
     */
    public Date getInitialDate() {
        return initialDate;
    }

    /**
     * @param initialDate the initialDate to set
     */
    public void setInitialDate(Date initialDate) {
        this.initialDate = initialDate;
    }

}
