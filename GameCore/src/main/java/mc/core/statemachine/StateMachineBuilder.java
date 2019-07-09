package mc.core.statemachine;

/**
 * StateMachineBuilder
 */
public interface StateMachineBuilder<VAL, TAN extends Enum<TAN>> {

    public StateMachineBuilder<VAL, TAN> addTransition(State<VAL> from, TAN transition, State<VAL> to);

    public StateMachineBuilder<VAL, TAN> addForceTransition(TAN transition, State<VAL> to);

    /**
     * is automaticly added to the {@link StateMachineDistributer} where this
     * Builder was created
     */
    public StateMachine<VAL, TAN> build();
}