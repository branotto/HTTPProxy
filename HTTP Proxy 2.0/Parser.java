/**
 * This class parses a HTTP request and formats it correctly.
 * @author Brandon Otto
 *
 */

public class Parser 
{
	private String[] fullRequest;
	private String[] uri;
	private String method;
	private String location = "/";
	private String version;
	private String host;
	private String port = "80";
	private String connection = "Close";
	private String headers = "";
	
	/**
	 * This constructor takes the request message and splits it by spaces. 
	 * It further splits the uri by "/".
	 * @param request The string message to parse.
	 */
	
    public Parser(String request) throws ImproperRequestForm
    {
    	fullRequest = request.split("\\s");
    	
    	if(fullRequest.length < 3)
    	{
    		throw new ImproperRequestForm("missing header field(s).");
    	}
    	
    	uri = fullRequest[1].split("/");	
    }
    
    /**
     * The formattedRequest method returns a string with a properly formatted HTTP request. 
     * @return the formatted HTTP request.
     */
    
    public String formattedRequest() throws ImproperRequestForm
    {
    	String formatted = getMethod() + " " + getLocation() + " " + getVersion() + "\r\n" +
    						"Host: " + getHost() + "\r\n" +
    						"Connection: " + getConnection() + "\r\n" +
    						getHeaders();
    	return formatted;					
    }
    
    /**
     * The getMethod method sets the HTTP method, checks to see it is a GET request and returns the method.
     * @return method.
     */
    
    public String getMethod() throws ImproperRequestForm
    {
    	method = fullRequest[0];
    	boolean checkMethod = method.equals("GET");
    	
    	if(!checkMethod)
    	{
    		throw new ImproperRequestForm("method.");
    	}
    	return method;
    }
   
    /**
     * The getLocation method splits the uri string array and stores the location field.
     * It also splits the port if provided.
     * @return location.
     */
    
    public String getLocation()
    {
    	//Check the uri for a port.
		
		String[] loc = uri[2].split(":");
    	host = loc[0];
    		
    	//If the uri was split above check the port and set accordingly.
    	if(loc.length < 2)
    		{
    		port = "80";
    		} else 
    			{
    			port = loc[1];
    			}
		
		//Reconstitute the pieces of the uri for the location field.
    	
    	for( int i = 3; i < uri.length-1; i++)
    	{
    	    location += uri[i] + "/";
    	}
    	
    	if(uri.length-1 > 2)
    	{
    		location += uri[uri.length-1];
    	}
    	   	
    	return location;
    }
    
    /**
     * The getVersion method sets the version field and confirms it is HTTP/1.0.
     * @return version
     */
    
    public String getVersion()
    {
    	version = fullRequest[2];
    	boolean checkVersion = version.equals("HTTP/1.0");
    	if(!checkVersion)
    	{
    		version = "HTTP/1.0";
    	}
    	return version;
    }
    
    /**
     * The getHost method returns the host field.
     * @return host
     */
    
    public String getHost()
    {
    	return host;
    }
    
    /**
     * The getPort method returns the port field.
     * @return port
     */
    
    public String getPort()
    {
    	
    	return port;
    }
    
    /**
     * The getConnection method returns the connection field
     * @return connection 
     */
    
    public String getConnection()
    {
    	return connection;
    }
    
    /**
     * The getHeaders method checks for additional headers in the request and returns them. It ignores 
     * any Host or Connection headers.
     * @return The string containing the additional HTTP headers.
     */
    
    public String getHeaders()
    {
    	if (fullRequest.length > 3 )
    	{
    		for(int i = 4 ; i <fullRequest.length-1; i += 3)
    		{
    			if(!fullRequest[i].equals("Host:") && !fullRequest[i].equals("Connection:"))
    			{
    			  headers += fullRequest[i]+ " " + fullRequest[i+1] + "\r\n";
    			}
    		}
    	}
    	
    	return headers + "\r\n";
    }
}
