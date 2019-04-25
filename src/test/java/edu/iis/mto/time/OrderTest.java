package edu.iis.mto.time;

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

    @Test
    public void confirmShouldThrowExceptiomIfOrderExpired() {
        order.submit();

        thrown.expect(OrderExpiredException.class);
    }

}
