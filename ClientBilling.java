package my.billing;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

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
        int yearIni = iniCal.get(Calendar.YEAR);
        int yearQry = qryCal.get(Calendar.YEAR);

        int yearDiff = Math.abs(yearQry - yearIni);
        if (yearDiff > 0) {
            yearDiff *= 365;
        }

        int dayIni = iniCal.get(Calendar.DAY_OF_YEAR);
        int dayQry = qryCal.get(Calendar.DAY_OF_YEAR);

        return Math.abs(dayIni - dayQry) + yearDiff;
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
