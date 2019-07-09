package mc.core.statemachine;

import java.util.Map;

/**
 * Statemachine
 */
public class StateMachine<VAL, TAN extends Enum<TAN>> {

    private Map<Integer, Map<TAN, State<VAL>>> stateLinks;
    private Map<TAN, State<VAL>> forceStateLinks;

    /* package */ StateMachine(Map<Integer, Map<TAN, State<VAL>>> stateLinks, Map<TAN, State<VAL>> forceStateLinks) {
        this.stateLinks = stateLinks;
        this.forceStateLinks = forceStateLinks;
    }

    public void transition(StateHolder<VAL, TAN> stateHolder, TAN action) {
        State<VAL> newState = this.forceStateLinks.get(action);
        if (newState == null) {
            Map<TAN, State<VAL>> intermediate = this.stateLinks.get(stateHolder.getCurrentState().hashCode());
            if (intermediate == null) {
                // TODO log that this transition is not possible
                return;
            }
            newState = intermediate.get(action);
        }
        if (newState == null) {
            // TODO log that there is no transition
            return;
        }
        stateHolder.setCurrentState(newState);
    }
}