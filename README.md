# HTTPProxy

Brandon Otto

My HTTP proxy interacts with a web browser (Mozilla Firefox) or direct connection (PuTTy) to return web pages from servers in the internet. 
It supports HTTP version 1.0 and the HTTP method GET only. All other versions or methods are converted to the supported version. 

The proxy has 3 classes:

proxy - the main program for the proxy. It listens for connections to the proxy and creates a TCPServerThread for each connection.

TCPServerThread - the class that handles the processing of an HTTP request and retrieving and returning the web page objects to the user or browser. 

Parser - the class that parsers the initial HTTP request from the user or browser.

I used these 3 classes to ensure that my proxy could handle multiple browser requests by creating a new thread for each request. I seperated the parser 
into its own class to seperate parsing from the network reading/writing.

The project took an estimated 30 hours to complete.
