# HTTPProxy

HTTP Proxy
Brandon Otto

My HTTP proxy interacts with a web browser (Mozilla Firefox) or direct connection (PuTTy) to return web pages from servers in the internet. 
It supports HTTP version 1.0 and the HTTP method GET only. All other versions are converted to HTTP/1.0. All other methods return an error message to the user.
If a request is missing any of the 3 main headers: method, url or version it also returns an error message to the user. 

The proxy has 4 classes:

proxy - the main program for the proxy. It listens for connections to the proxy and creates a TCPServerThread for each connection. When starting the proxy
	the user can enter a command line argument for the port to listen to or if none is provided will be prompted to enter a port number.

TCPServerThread - the class that handles the processing of an HTTP request and retrieving and returning the web page objects to the user or browser. 

Parser - the class that parsers the initial HTTP request from the user or browser.

ImproperRequestForm - an exception class that throws exceptions when an HTTP request is formatted poorly. The parser throws exceptions to the TCPServerThread and
			aborts the session when any of the following occur:
			1. The HTTP method is not GET.
			2. The HTTP request is missing any of the following 3 headers:
				a. method
				b. url
				c. version

I used these 4 classes to ensure that my proxy could handle multiple browser requests by creating a new thread for each request. I seperated the parser 
into its own class to segregate parsing from the network reading/writing. I created my own exception class to handle poorly formatted or incomplete HTTP 
requests.

The project took an estimated 30 hours to complete.

