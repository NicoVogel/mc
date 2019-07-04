package mc.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

/**
 * ComponentOrganizerTest
 */
public class ComponentOrganizerTest {

    @Test
    public void findComponentByTag() {
        // Arrange
        GameObject gameObj = new GameObject();
        Component component = new ComponentStandard(gameObj);
        Component component1 = new ComponentStandard(gameObj);
        Component component2 = new ComponentStandard(gameObj);
        String tag = "test";
        component.setTag(tag);
        component1.setTag("some other tag");
        component2.setTag("some other tag2");

        // Act
        Component result = gameObj.getComponent(tag);
        List<Component> result2 = gameObj.getComponents(tag).toList();

        // Assert
        assertNotNull(result, "The tagged object was not found");
        assertSame(component, result, "the found object is not the same as the first object");
        assertSame(component, result2.get(0), "the first component is not equals to the first added component");
    }

    @Test
    public void findMultipleComponentsBySameTag() {
        // Arrange
        GameObject gameObj = new GameObject();
        Component component = new ComponentStandard(gameObj);
        Component component1 = new ComponentStandard(gameObj);
        Component component2 = new ComponentStandard(gameObj);
        String tag = "test";
        component.setTag(tag);
        component1.setTag(tag);
        component2.setTag("some other tag");

        // Act
        List<Component> result2 = gameObj.getComponents(tag).toList();

        // Assert
        assertEquals(2l, result2.size(), "didn't found both components");
        assertTrue(result2.contains(component), "the component which was added first is not part of the result");
        assertTrue(result2.contains(component1), "the component which was added second is not part of the result");
    }

    @Test
    public void findComponentByClass() {
        // Arrange
        GameObject gameObj = new GameObject();
        Component component = new ComponentStandard(gameObj);

        // Act
        ComponentStandard result = gameObj.getComponent(ComponentStandard.class);
        List<ComponentStandard> result2 = gameObj.getComponents(ComponentStandard.class).toList();

        // Assert
        assertNotNull(result, "no component with of ComponentStandard class was found");
        assertSame(component, result, "the found object is not the same as the first object");
        assertSame(component, result2.get(0), "the first component is not equals to the first added component");
    }

    @Test
    public void findMultipleComponentsBySameClass() {
        // Arrange
        GameObject gameObj = new GameObject();
        Component component = new ComponentStandard(gameObj);
        Component component1 = new ComponentStandard(gameObj);

        // Act
        List<ComponentStandard> result2 = gameObj.getComponents(ComponentStandard.class).toList();

        // Assert
        assertEquals(2l, result2.size(), "didn't found both components");
        assertTrue(result2.contains(component), "the component which was added first is not part of the result");
        assertTrue(result2.contains(component1), "the component which was added second is not part of the result");
    }

    @Test
    public void findSubComponentByTag() {
        // Arrange
        GameObject gameObj = new GameObject();
        Component component = new ComponentStandard(gameObj);
        Component subComponent = new ComponentStandard(component);
        new ComponentStandard(gameObj);
        new ComponentStandard(component);
        String tag = "tag";
        subComponent.setTag(tag);

        // Act
        Component noResult = gameObj.getComponent(tag);
        Component result = gameObj.getComponentOfChildren(tag);
        List<Component> result2 = gameObj.getComponentsOfChildren(tag).toList();

        // Assert
        assertNull(noResult, "The tagged object was found in the gameobject");
        assertNotNull(result, "The tagged object was not found");
        assertSame(subComponent, result, "the found object is not the same as the first object");
        assertSame(subComponent, result2.get(0), "the first component is not equals to the first added component");
    }

    

}