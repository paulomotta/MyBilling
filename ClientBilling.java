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
        if (queryDate.before(initialDate)) {
            return null;
        }

        Date localIniDate = (Date) initialDate.clone();

        Calendar iniCal = Calendar.getInstance();
        iniCal.setTime(localIniDate);

        Calendar qryCal = Calendar.getInstance();
        qryCal.setTime(queryDate);

        int diffDays = diffDays(iniCal, qryCal);
        System.out.println(diffDays);

        if (diffDays > CYCLE_SIZE) {
            System.out.println("diffDays/CYCLE_SIZE=" + diffDays / CYCLE_SIZE);
            System.out.println("(diffDays/CYCLE_SIZE)%12=" + (diffDays / CYCLE_SIZE) % 12);
            iniCal.add(Calendar.MONTH, ((diffDays / CYCLE_SIZE) % 12));
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

        int dayIni = iniCal.get(Calendar.DAY_OF_YEAR);
        int dayQry = qryCal.get(Calendar.DAY_OF_YEAR);

        int days = Math.abs(dayIni - dayQry);

        int yearDiff = Math.abs(yearQry - yearIni);
        if (yearDiff > 0) {
            yearDiff *= 365;

            if (dayIni < dayQry) {
                days += yearDiff;
            } else {
                days = yearDiff - days;
            }
        }
        System.out.println("dayIni - dayQry" + dayIni + " " + dayQry);

        return days;
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
