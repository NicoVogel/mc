package mc.core.statemachine;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

/**
 * StateHolder
 */
public class StateHolder<VAL, TAN extends Enum<TAN>> {

    private StateMachine<VAL, TAN> statemachine;
    @Getter
    @Setter(value = AccessLevel.PACKAGE)
    private State<VAL> currentState;

    public StateHolder(StateMachineDistributer distributer, Class<VAL> clazz) {
        this.statemachine = distributer.getStateMachine(clazz);
    }

    public void transition(TAN action) {
        this.statemachine.transition(this, action);
    }
}