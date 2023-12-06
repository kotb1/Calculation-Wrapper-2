package Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import Calculaution.CalculationWrapper;
import Calculaution.ICalculation;

public class CalculationTestMockito {
    private CalculationWrapper systemUnderTest;
    private ICalculation mockedDependency;

    @Before
    public void doBeforeEachTestCase() {
        mockedDependency = mock(ICalculation.class);

        systemUnderTest = new CalculationWrapper(mockedDependency);
    }

    @Test
    public void getPrice() throws Exception {
        int[] arr = { -4, 5, -6, 8 };
        when(mockedDependency.findMin(arr)).thenReturn(-6);

        int result = systemUnderTest.findMinimum(arr);

        verify(mockedDependency, times(2)).findMin(arr);
        assertEquals(-6 - 10, result);
    }

    @Test
    // (expected = IllegalArgumentException.class)
    public void getPriceDataAccessThrowsRuntimeException() throws Exception {

        int result = systemUnderTest.findMinimum(null);

        assertEquals(Integer.MIN_VALUE, result);
    }
}
