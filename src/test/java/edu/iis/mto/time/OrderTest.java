package edu.iis.mto.time;

import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class OrderTest {

    Order order;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void setup() {
        order = new Order();
    }

    @Test(expected = OrderExpiredException.class)
    public void confirmShouldThrowExceptiomIfOrderIsSubmittedMoreThan24HoursAgo() {
        TestableClock.timeTravel(LocalDateTime.parse("2018-01-01T00:00:00"));
        order.submit();
        TestableClock.timeTravel(LocalDateTime.parse("2018-01-03T00:00:00"));
        order.confirm();
    }

    @Test(expected = Test.None.class)
    public void confirmShouldNotThrowExceptiomIfOrderIsSubmittedLessThan24HoursAgo() {
        TestableClock.timeTravel(LocalDateTime.parse("2018-01-01T00:00:00"));
        order.submit();
        TestableClock.timeTravel(LocalDateTime.parse("2018-01-01T23:59:59"));
        order.confirm();
    }

}
