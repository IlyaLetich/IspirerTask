package org.ispirer.models;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Random;

public class DebitCard {
    static int countOfInstance = 0;

    private String fullName;
    private YearMonth dateDurationFrom;
    private YearMonth dateDurationTo;
    private int cvv;
    private double balance;

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    public void setDatePeriodFrom(YearMonth dateDurationFrom) {
        this.dateDurationFrom = dateDurationFrom;
    }
    public void setDatePeriodTo(YearMonth dateDurationTo) {
        this.dateDurationTo = dateDurationTo;
    }
    public void setCvv(int cvv) {
        this.cvv = cvv;
    }
    public void setBalance(double balance) {
        this.balance = balance;
    }

    public DebitCard(){
        this.fullName = "Anonymous " + countOfInstance;
        this.cvv = new Random().nextInt(900) + 100;
        this.balance = 0;

        LocalDate currentDate = LocalDate.now();

        int year = currentDate.getYear();
        int month = currentDate.getMonthValue();

        this.dateDurationFrom = YearMonth.of(year, month);
        this.dateDurationTo = YearMonth.of(year + 5, month);

        countOfInstance++;
    }

    public DebitCard(String fullName, int duration){
        this.fullName = fullName;
        this.cvv = new Random().nextInt(900) + 100;
        this.balance = 0;

        LocalDate currentDate = LocalDate.now();

        int year = currentDate.getYear();
        int month = currentDate.getMonthValue();

        this.dateDurationFrom = YearMonth.of(year, month);
        this.dateDurationTo = YearMonth.of(year + duration, month);

        countOfInstance++;
    }

    public static int getCountOfInstance() {
        return countOfInstance;
    }
}
