package my.billing;

import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author paulo
 */
public class ClientBilling {

    private Date initialDate;

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

        int iniMonth = iniCal.get(Calendar.MONTH);
        int qryMonth = qryCal.get(Calendar.MONTH);

        System.out.println("iniMonth=" + iniMonth);
        System.out.println("qryMonth=" + qryMonth);

        int diffDays = diffDays(localIniDate, queryDate);
        System.out.println(diffDays);

        if (diffDays > 30) {
            iniCal.add(Calendar.MONTH, (qryMonth - iniMonth));
        }
        Date ini = iniCal.getTime();

        iniCal.add(Calendar.DAY_OF_MONTH, 30);
        Date end = iniCal.getTime();
        return new BillingCycle(ini, end);
    }

    public static int diffDays(Date d1, Date d2) {
        Calendar iniCal = Calendar.getInstance();
        iniCal.setTime(d1);

        Calendar qryCal = Calendar.getInstance();
        qryCal.setTime(d2);

        int d1Month = iniCal.get(Calendar.DAY_OF_YEAR);
        System.out.println("d1Month =" + d1Month);
        int d2Month = qryCal.get(Calendar.DAY_OF_YEAR);
        System.out.println("d2Month =" + d2Month);

        int result = Math.abs(d1Month - d2Month);

        return result;
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
