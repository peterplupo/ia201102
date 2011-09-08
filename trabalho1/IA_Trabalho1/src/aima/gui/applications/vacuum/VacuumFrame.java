package aima.gui.applications.vacuum;

import aima.gui.framework.AgentAppFrame;

/**
 * Adds some selectors to the base class and adjusts its size.
 * 
 * @author Ruediger Lunde
 */
public class VacuumFrame extends AgentAppFrame {
	private static final long serialVersionUID = 1L;
	public static String ENV_SEL = "EnvSelection";
	public static String AGENT_SEL = "AgentSelection";

	public VacuumFrame() {
		setTitle("Vacuum Agent Application");
		setSelectors(new String[] { ENV_SEL, AGENT_SEL }, new String[] {
				"Select Environment", "Select Agent" });
		setSelectorItems(ENV_SEL, new String[] { "A/B Environment", "A/B/C/D Environment" }, 0);
		setSelectorItems(AGENT_SEL, new String[] {
				"TableDrivenVacuumAgent",
				"ReflexVacuumAgent",
				"SimpleReflexVacuumAgent",
				"ModelBasedReflexVacuumAgent",
				"4Rooms",
				"BrokenSensor",
				"Wash/DryAgents"},
				0);
		setEnvView(new VacuumView());
		setSize(800, 400);
	}
}