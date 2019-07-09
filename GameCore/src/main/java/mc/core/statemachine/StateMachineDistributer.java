package mc.core.statemachine;

import java.util.Map;

import mc.core.factory.GameFactory;

/**
 * StateMachineDistributer
 */
public class StateMachineDistributer {

    private Map<Class<?>, StateMachine<?, ?>> stateMachineCollection;

    public <VAL, TAN extends Enum<TAN>> StateMachineBuilder<VAL, TAN> createNewStateMachine(Class<VAL> clazz) {
        return new StateMachineBuilderImpl<>(clazz);
    }

    @SuppressWarnings("unchecked")
    public <VAL, TAN extends Enum<TAN>> StateMachine<VAL, TAN> getStateMachine(Class<VAL> clazz) {
        StateMachine<?, ?> stateMachine = this.stateMachineCollection.get(clazz);
        if (stateMachine == null) {
            // TODO log warning
            return null;
        }
        return (StateMachine<VAL, TAN>) stateMachine;
    }

    private class StateMachineBuilderImpl<VAL, TAN extends Enum<TAN>> implements StateMachineBuilder<VAL, TAN> {

        private Map<Integer, Map<TAN, State<VAL>>> stateLinks;
        private Map<TAN, State<VAL>> forceStateLinks;
        private Class<VAL> key;

        public StateMachineBuilderImpl(Class<VAL> key) {
            this.key = key;
            this.stateLinks = GameFactory.Instance().createMap();
            this.forceStateLinks = GameFactory.Instance().createMap();
        }

        @Override
        public StateMachineBuilder<VAL, TAN> addTransition(State<VAL> from, TAN transition, State<VAL> to) {
            int fromKey = from.hashCode();
            this.stateLinks.computeIfAbsent(fromKey, key -> GameFactory.Instance().createMap()).compute(transition,
                    (key, value) -> {
                        if (value != null) {
                            // TODO log that the key already exists
                        }
                        return to;
                    });
            return this;
        }

        @Override
        public StateMachineBuilder<VAL, TAN> addForceTransition(TAN transition, State<VAL> to) {
            this.forceStateLinks.compute(transition, (key, value) -> {
                if (value != null) {
                    // TODO log that the key already exists
                }
                return to;
            });
            return this;
        }

        @Override
        public StateMachine<VAL, TAN> build() {
            StateMachine<VAL, TAN> statemachine = new StateMachine<VAL, TAN>(this.stateLinks, this.forceStateLinks);
            // TODO add eval for all added transitions
            stateMachineCollection.put(this.key, statemachine);
            return statemachine;
        }

    }
}