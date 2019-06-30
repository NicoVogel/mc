package mc.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;

import org.junit.jupiter.api.Test;

import one.util.streamex.StreamEx;

/**
 * ComponentOrganizerTest
 */
public class ComponentOrganizerTest {

    @Test
    public void createFindComponentByTag() {
        // Arrange
        GameObject gameObj = new GameObject();
        Component component = new ComponentStandard(gameObj);
        Component component1 = new ComponentStandard(gameObj);
        Component component2 = new ComponentStandard(gameObj);
        String tag = "test";
        component.setTag(tag);

        // Act
        Component result = gameObj.getComponent(tag);
        StreamEx<Component> result2 = gameObj.getComponents(tag);

        // Assert
        assertNotNull(result, "The tagged object was not found");
        assertEquals(component, result2.findFirst());
        assertSame(component, result, "the found object is not the same as the created object");

    }

}