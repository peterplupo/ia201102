package br.ufrj.dcc.ia201102.trabalho1.model;

public class Rule {
	
	public enum Context {
		ROOM00("00"), ROOM01("01"), ROOM10("10"), ROOM11("11"), DIRTY("dirty"), CLEAN("clean"), WET("wet");
		
		private String identifier;
		
		private Context(String identifier) {
			this.identifier = identifier;
		}
		
		public String getIdentifier() {
			return identifier;
		}
		
	}
	
	public enum Action {
		MOVE_UP, MOVE_DOWN, MOVE_LEFT, MOVE_RIGHT, SUCK, WASH, DRY, MOVE_UP_SUCK, MOVE_DOWN_SUCK, MOVE_LEFT_SUCK, MOVE_RIGHT_SUCK;
	}
	
	Context context;
	Action action;
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((action == null) ? 0 : action.hashCode());
		result = prime * result + ((context == null) ? 0 : context.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Rule other = (Rule) obj;
		if (action != other.action)
			return false;
		if (context != other.context)
			return false;
		return true;
	}
	public Rule(Context context, Action action) {
		super();
		this.context = context;
		this.action = action;
	}
	
	

}
