import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LeaveRequestStateTest {

    @Test
    public void testStateMachine() {
        LeaveRequestState state = LeaveRequestState.Submitted;

        state = state.nextState();
        assertEquals(LeaveRequestState.Escalated, state);

        state = state.nextState();
        assertEquals(LeaveRequestState.Approved, state);

        state = state.nextState();
        assertEquals(LeaveRequestState.Approved, state);
    }
}