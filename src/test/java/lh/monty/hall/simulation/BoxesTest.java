package lh.monty.hall.simulation;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BoxesTest {

    @Test
    public void testPriceBehind() {
        // Given
        Boxes boxes = new Boxes(1);
        // Then
        assertThat(boxes.priceBehind(0)).isFalse();
        assertThat(boxes.priceBehind(1)).isTrue();
        assertThat(boxes.priceBehind(2)).isFalse();
    }
}