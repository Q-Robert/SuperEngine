package net.myserver.engine.server;

public enum ServerSettings {
	
	PLACEBLOCKS((Boolean) false, DataType.BOOLEAN),
	BREAKBLOCKS((Boolean) false, DataType.BOOLEAN),
	PVP((Boolean) false, DataType.BOOLEAN),
	PVE((Boolean) false, DataType.BOOLEAN),
	PLAYER_MOVE((Boolean) true, DataType.BOOLEAN);
	
	private Object value;
	private DataType type;
	
	ServerSettings(Object value, DataType type)
	{
		this.value = value;
		this.type = type;
	}
	
	public void setValue(Object value)
	{
		this.value = value;
	}
	
	public Object getValue()
	{
		return value;
	}
	
	public DataType getType()
	{
		return type;
	}

}
