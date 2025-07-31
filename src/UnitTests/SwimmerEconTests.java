package UnitTests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import Models.Swimmer;

import java.time.LocalDate;

public class SwimmerEconTests {

    @org.junit.Test
    public void testJuniorActiveQuota(){
        assertEquals(1000, new Swimmer("Ung Tester", LocalDate.parse("2020-01-01"),true).getQuota());
    }

    @org.junit.Test
    public void testSeniorActiveQuota(){
        assertEquals(1600, new Swimmer("Ung Tester", LocalDate.parse("2003-01-01"),true).getQuota());
    }
    @org.junit.Test
    public void testSixstyActiveQuota(){
        assertEquals(1200, new Swimmer("60+ Tester", LocalDate.parse("1960-01-01"),true).getQuota());
    }
    @org.junit.Test
    public void testPassivQuota(){
        assertEquals(500, new Swimmer("60+ Tester", LocalDate.parse("1920-01-01"),false).getQuota());
    }

    @org.junit.Test
    public void testMembershipYears(){
        assertEquals(0, new Swimmer("Ung Tester", LocalDate.parse("2020-01-01"),true).membershipYears());
    }

    @org.junit.Test
    public void testSwimmerPayments(){
        Swimmer testSwimmer = new Swimmer("Ung Tester", LocalDate.parse("2020-01-01"),true);

        assertEquals(0, testSwimmer.calTotalPayments());
        assertEquals(-1000, testSwimmer.calMemberBalance());

        testSwimmer.addPayment(500);
        assertEquals(500, testSwimmer.calTotalPayments());
        assertEquals(-500, testSwimmer.calMemberBalance());

        testSwimmer.addPayment(500);
        assertEquals(1000, testSwimmer.calTotalPayments());
        assertEquals(0, testSwimmer.calMemberBalance());
    }
}
