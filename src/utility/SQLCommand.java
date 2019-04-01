package utility;

public interface SQLCommand {
	String INSERT_REC = "Insert into records (code) values(?)";
	
	String GET_ALL_REC= "Select * from records";
	
	String DROP_TABLE = "Drop table records";

}
