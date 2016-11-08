/**
 * ImproperRequestFrom exceptions are thrown by the parser class when
 * an HTTP request is missing a method, url, or version.
 * @author Brandon Otto
 *
 */

public class ImproperRequestForm extends Exception 
{
	
	/**
	 * This constructor uses a default error message.
	 */
	
	public ImproperRequestForm()
	{
		super("Error: Unsupported header.");
	}
	
	/**
	 * This constructor uses a default error message.
	 */
	
	public ImproperRequestForm(String message)
	{
		super("Error: Incorrect header - " + message);
	}
}
