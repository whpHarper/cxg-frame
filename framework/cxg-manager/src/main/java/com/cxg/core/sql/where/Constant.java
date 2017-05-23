package com.cxg.core.sql.where;

public enum Constant {

	EQ,NE,LIKE,DA,IXAO,IN;
	
	public static String getSqlWhere(Constant c){
		switch (c) {
		case EQ:
			return "=";
		case NE:
			return "<>";
		case LIKE:
			return "like";	
		case DA:
			return ">";	
		case IXAO:
			return "<";	
		case IN:
			return "in";	
		default:
			return "=";	
		}
	}
}
